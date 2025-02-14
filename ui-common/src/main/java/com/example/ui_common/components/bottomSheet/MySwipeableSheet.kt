package com.example.ui_common.components.bottomSheet

import android.annotation.SuppressLint
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animate
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.math.abs

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@ExperimentalMaterialApi
internal fun <T> Modifier.mySwipeableSheet(
    state: MySwipeableState<T>,
    orientation: Orientation,
    enabled: Boolean = true,
    reverseDirection: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) = draggable(
    state = state.draggableState,
    orientation = orientation,
    enabled = enabled,
    interactionSource = interactionSource,
    reverseDirection = reverseDirection,
    startDragImmediately = state.isAnimationRunning,
    onDragStopped = { velocity -> launch { state.settle(velocity) } }
)

internal fun interface MySheetAnchorChangeHandler<T> {
    fun onAnchorsChanged(
        previousTargetValue: T,
        previousAnchors: Map<T, Float>,
        newAnchors: Map<T, Float>
    )
}

@ExperimentalMaterialApi
internal fun <T> Modifier.swipeAnchors(
    state: MySwipeableState<T>,
    possibleValues: Set<T>,
    anchorChangeHandler: MySheetAnchorChangeHandler<T>? = null,
    calculateAnchor: (value: T, layoutSize: IntSize) -> Float?,
) = this.then(MySheetSwipeAnchorsModifier(
    onDensityChanged = { state.density = it },
    onSizeChanged = { layoutSize ->
        val previousAnchors = state.anchors
        val newAnchors = mutableMapOf<T, Float>()
        possibleValues.forEach {
            val anchorValue = calculateAnchor(it, layoutSize)
            if (anchorValue != null) {
                newAnchors[it] = anchorValue
            }
        }
        if (previousAnchors != newAnchors) {
            val previousTarget = state.targetValue
            val stateRequiresCleanup = state.updateAnchors(newAnchors)
            if (stateRequiresCleanup) {
                anchorChangeHandler?.onAnchorsChanged(previousTarget, previousAnchors, newAnchors)
            }
        }
    },
    inspectorInfo = debugInspectorInfo {
        name = "swipeAnchors"
        properties["state"] = state
        properties["possibleValues"] = possibleValues
        properties["anchorChangeHandler"] = anchorChangeHandler
        properties["calculateAnchor"] = calculateAnchor
    }
))

@Stable
@ExperimentalMaterialApi
internal class MySwipeableState<T>(
    initialValue: T,
    internal val animationSpec: AnimationSpec<Float> = MySwipeableSheetDefaults.AnimationSpec,
    internal val confirmValueChange: (newValue: T) -> Boolean = { true },
    internal val velocityThreshold: Dp = MySwipeableSheetDefaults.VelocityThreshold,
) {

    var currentValue: T by mutableStateOf(initialValue)
        private set

    //아래에서 위로/위에서 아래로 드래그 시 value
    var dragValue: MyBottomSheetValue by mutableStateOf(MyBottomSheetValue.None)

    //Hidden 에서 half로 이동되는지 value
    val targetValue: T by derivedStateOf {
        animationTarget ?: run {
            val currentOffset = offset
            if (currentOffset != null) {
                return@derivedStateOf computeTarget(currentOffset, currentValue, velocity = 0f)
            } else return@derivedStateOf currentValue
        }
    }

    //드래그 중인지 여부
    var isDragging = mutableStateOf(false)

    @get:Suppress("AutoBoxing")
    var offset: Float? by mutableStateOf(null)
        private set

    fun requireOffset(): Float = checkNotNullOffset(offset)

    @OptIn(ExperimentalContracts::class)
    fun checkNotNullOffset(value: Float?): Float {
        contract {
            returns() implies (value != null)
        }

        return value ?: minOffset
    }

    val isAnimationRunning: Boolean get() = animationTarget != null

    var lastVelocity: Float by mutableFloatStateOf(0f)
        private set

    val minOffset by derivedStateOf { anchors.minOrNull() ?: Float.NEGATIVE_INFINITY }

    val maxOffset by derivedStateOf { anchors.maxOrNull() ?: Float.POSITIVE_INFINITY }

    private var animationTarget: T? by mutableStateOf(null)

    val draggableState = DraggableState {
        offset = ((offset ?: 0f) + it).coerceIn(minOffset, maxOffset)
        isDragging.value = true //드래그 중
    }

    internal var anchors by mutableStateOf(emptyMap<T, Float>())

    internal var density: Density? = null

    internal fun updateAnchors(newAnchors: Map<T, Float>): Boolean {
        val previousAnchorsEmpty = anchors.isEmpty()
        anchors = newAnchors
        val initialValueHasAnchor = if (previousAnchorsEmpty) {
            val initialValueAnchor = anchors[currentValue]
            val initialValueHasAnchor = initialValueAnchor != null
            if (initialValueHasAnchor) offset = initialValueAnchor
            initialValueHasAnchor
        } else true
        return !initialValueHasAnchor || !previousAnchorsEmpty
    }

    fun hasAnchorForValue(value: T): Boolean = anchors.containsKey(value)

    suspend fun snapTo(targetValue: T) {
        val targetOffset = anchors[targetValue]
        if (targetOffset != null) {
            try {
                draggableState.drag {
                    animationTarget = targetValue
                    dragBy(targetOffset - requireOffset())
                }
                this.currentValue = targetValue
            } finally {
                animationTarget = null
            }
        } else {
            currentValue = targetValue
        }
    }

    suspend fun animateTo(
        targetValue: T,
        velocity: Float = lastVelocity,
    ) {
        val targetOffset = anchors[targetValue]
        if (targetOffset != null) {
            try {
                draggableState.drag {
                    animationTarget = targetValue
                    var prev = offset ?: 0f
                    animate(prev, targetOffset, velocity, animationSpec) { value, velocity ->
                        offset = value
                        prev = value
                        lastVelocity = velocity
                    }
                    lastVelocity = 0f
                }
            } finally {
                animationTarget = null
                val endOffset = requireOffset()
                val endState = anchors
                    .entries
                    .firstOrNull { (_, anchorOffset) -> abs(anchorOffset - endOffset) < 0.5f }
                    ?.key
                this.currentValue = endState ?: currentValue
            }
        } else {
            currentValue = targetValue
        }
    }

    suspend fun settle(velocity: Float) {
        val previousValue = this.currentValue
        val targetValue = computeTarget(
            offset = requireOffset(),
            currentValue = previousValue,
            velocity = velocity
        )
        if (confirmValueChange(targetValue)) {
            animateTo(targetValue, velocity)
        } else {
            animateTo(previousValue, velocity)
        }
    }

    fun dispatchRawDelta(delta: Float): Float {
        val currentDragPosition = offset ?: 0f
        val potentiallyConsumed = currentDragPosition + delta
        val clamped = potentiallyConsumed.coerceIn(minOffset, maxOffset)
        val deltaToConsume = clamped - currentDragPosition
        if (abs(deltaToConsume) > 0) {
            draggableState.dispatchRawDelta(deltaToConsume)
        }
        return deltaToConsume
    }

    private fun computeTarget(
        offset: Float,
        currentValue: T,
        velocity: Float
    ): T {
        val currentAnchors = anchors
        val currentAnchor = currentAnchors[currentValue] //1822.0

        val currentDensity = requireDensity()
        val velocityThresholdPx = with(currentDensity) { velocityThreshold.toPx() }
        return if (currentAnchor == offset || currentAnchor == null) {
            dragValue = MyBottomSheetValue.None
            currentValue
        } else if (currentAnchor < offset) {
            //위 -> 아래
            dragValue = MyBottomSheetValue.UpToDown

            if (velocity >= velocityThresholdPx) {
                currentAnchors.closestAnchor(offset, true)
            } else {
                currentAnchors.closestAnchor(offset, true)
            }
        } else {
            //아래 -> 위
            dragValue = MyBottomSheetValue.DownToUp

            if (velocity <= -velocityThresholdPx) {
                currentAnchors.closestAnchor(offset, false)
            } else {
                currentAnchors.closestAnchor(offset, false)
            }
        }
    }

    private fun requireDensity() = requireNotNull(density) {
        "SwipeableState did not have a density attached. Are you using Modifier.swipeable with " +
                "this=$this SwipeableState?"
    }

    companion object {
        /**
         * The default [Saver] implementation for [MySwipeableState].
         */
        @ExperimentalMaterialApi
        fun <T : Any> Saver(
            animationSpec: AnimationSpec<Float>,
            confirmValueChange: (T) -> Boolean,
            positionalThreshold: Density.(distance: Float) -> Float,
            velocityThreshold: Dp
        ) = Saver<MySwipeableState<T>, T>(
            save = { it.currentValue },
            restore = {
                MySwipeableState(
                    initialValue = it,
                    animationSpec = animationSpec,
                    confirmValueChange = confirmValueChange,
                    velocityThreshold = velocityThreshold
                )
            }
        )
    }
}

@ExperimentalMaterialApi
internal fun fixedPositionalThreshold(threshold: Dp): Density.(distance: Float) -> Float = {
    threshold.toPx()
}

@Stable
@ExperimentalMaterialApi
internal object MySwipeableSheetDefaults {

    @ExperimentalMaterialApi
    val AnimationSpec = SpringSpec<Float>()


    @ExperimentalMaterialApi
    val VelocityThreshold: Dp = 125.dp

    @ExperimentalMaterialApi
    val PositionalThreshold: Density.(totalDistance: Float) -> Float =
        fixedPositionalThreshold(56.dp)

    @ExperimentalMaterialApi
    internal fun <T> myReconcileAnimationOnAnchorChangeHandler(
        state: MySwipeableState<T>,
        animate: (target: T, velocity: Float) -> Unit,
        snap: (target: T) -> Unit
    ) = MySheetAnchorChangeHandler { previousTarget, previousAnchors, newAnchors ->
        val previousTargetOffset = previousAnchors[previousTarget]
        val newTargetOffset = newAnchors[previousTarget]
        if (previousTargetOffset != newTargetOffset) {
            if (newTargetOffset != null) {
                animate(previousTarget, state.lastVelocity)
            } else {
                snap(newAnchors.closestAnchor(offset = state.requireOffset()))
            }
        }
    }
}

@Stable
private class MySheetSwipeAnchorsModifier(
    private val onDensityChanged: (density: Density) -> Unit,
    private val onSizeChanged: (layoutSize: IntSize) -> Unit,
    inspectorInfo: InspectorInfo.() -> Unit,
) : LayoutModifier, OnRemeasuredModifier, InspectorValueInfo(inspectorInfo) {

    private var lastDensity: Float = -1f
    private var lastFontScale: Float = -1f

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        if (density != lastDensity || fontScale != lastFontScale) {
            onDensityChanged(Density(density, fontScale))
            lastDensity = density
            lastFontScale = fontScale
        }
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) { placeable.place(0, 0) }
    }

    override fun onRemeasured(size: IntSize) {
        onSizeChanged(size)
    }

    override fun toString() = "SwipeAnchorsModifierImpl(updateDensity=$onDensityChanged, " +
            "onSizeChanged=$onSizeChanged)"
}

private fun <T> Map<T, Float>.closestAnchor(
    offset: Float = 0f,
    searchUpwards: Boolean = false
): T {
    require(isNotEmpty()) { "The anchors were empty when trying to find the closest anchor" }
    val d = minBy { (_, anchor) ->
        val delta = if (searchUpwards) anchor - offset else offset - anchor
        if (delta < 0) Float.POSITIVE_INFINITY else delta
    }.key
    return d
}

private fun <T> Map<T, Float>.minOrNull() = minOfOrNull { (_, offset) -> offset }
private fun <T> Map<T, Float>.maxOrNull() = maxOfOrNull { (_, offset) -> offset }
