package com.example.goodchoice.ui.components.bottomSheet

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.*
import androidx.compose.ui.unit.*
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetState.Companion.MySaver
import com.example.goodchoice.ui.theme.dp5
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.roundToInt
import com.example.goodchoice.ui.theme.Theme


@ExperimentalMaterialApi
@Suppress("Deprecation")
fun MyBottomSheetState(
    initialValue: ModalBottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmValueChange: (ModalBottomSheetValue) -> Boolean = { true },
    isSkipHalfExpanded: Boolean = false
) = MyBottomSheetState(
    initialValue = initialValue,
    animationSpec = animationSpec,
    isSkipHalfExpanded = isSkipHalfExpanded,
    confirmStateChange = confirmValueChange
)

@ExperimentalMaterialApi
class MyBottomSheetState constructor(
    initialValue: ModalBottomSheetValue,
    internal val animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    internal val isSkipHalfExpanded: Boolean = false,
    confirmStateChange: (ModalBottomSheetValue) -> Boolean
) {
    internal val mySwipeableState = MySwipeableState(
        initialValue = initialValue,
        animationSpec = animationSpec,
        confirmValueChange = confirmStateChange,
        positionalThreshold = PositionalThreshold,
        velocityThreshold = VelocityThreshold
    )

    val currentValue: ModalBottomSheetValue
        get() = mySwipeableState.currentValue

    val targetValue: ModalBottomSheetValue
        get() = mySwipeableState.targetValue

    val isVisible: Boolean
        get() = mySwipeableState.currentValue != ModalBottomSheetValue.Hidden

    internal val hasHalfExpandedState: Boolean
        get() = mySwipeableState.hasAnchorForValue(ModalBottomSheetValue.HalfExpanded)

    @Suppress("Deprecation")
    constructor(
        initialValue: ModalBottomSheetValue,
        animationSpec: AnimationSpec<Float>,
        confirmStateChange: (ModalBottomSheetValue) -> Boolean
    ) : this(initialValue, animationSpec, isSkipHalfExpanded = false, confirmStateChange)

    init {
        if (isSkipHalfExpanded) {
            require(initialValue != ModalBottomSheetValue.HalfExpanded)
        }
    }

    suspend fun show() {
        val targetValue = when {
            hasHalfExpandedState -> ModalBottomSheetValue.HalfExpanded
            else -> ModalBottomSheetValue.Expanded
        }
        animateTo(targetValue)
    }

    internal suspend fun halfExpand() {
        if (!hasHalfExpandedState) {
            return
        }
        animateTo(ModalBottomSheetValue.HalfExpanded)
    }

    internal suspend fun expand() {
        if (!mySwipeableState.hasAnchorForValue(ModalBottomSheetValue.Expanded)) {
            return
        }
        animateTo(ModalBottomSheetValue.Expanded)
    }

    suspend fun hide() = animateTo(ModalBottomSheetValue.Hidden)

    internal suspend fun animateTo(
        target: ModalBottomSheetValue,
        velocity: Float = mySwipeableState.lastVelocity
    ) = mySwipeableState.animateTo(target, velocity)

    internal suspend fun snapTo(target: ModalBottomSheetValue) = mySwipeableState.snapTo(target)

    internal fun requireOffset() = mySwipeableState.requireOffset()

    internal val lastVelocity: Float get() = mySwipeableState.lastVelocity

    internal val isAnimationRunning: Boolean get() = mySwipeableState.isAnimationRunning

    companion object {
        fun MySaver(
            animationSpec: AnimationSpec<Float>,
            confirmValueChange: (ModalBottomSheetValue) -> Boolean,
            skipHalfExpanded: Boolean,
        ): Saver<MyBottomSheetState, *> = Saver(
            save = { it.currentValue },
            restore = {
                MyBottomSheetState(
                    initialValue = it,
                    animationSpec = animationSpec,
                    isSkipHalfExpanded = skipHalfExpanded,
                    confirmValueChange = confirmValueChange
                )
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun rememberMyBottomSheetState(
    initialValue: ModalBottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmValueChange: (ModalBottomSheetValue) -> Boolean = { true },
    skipHalfExpanded: Boolean = false,
): MyBottomSheetState {
    return key(initialValue) {
        rememberSaveable(
            initialValue, animationSpec, skipHalfExpanded, confirmValueChange,
            saver = MySaver(
                animationSpec = animationSpec,
                skipHalfExpanded = skipHalfExpanded,
                confirmValueChange = confirmValueChange
            )
        ) {
            MyBottomSheetState(
                initialValue = initialValue,
                animationSpec = animationSpec,
                isSkipHalfExpanded = skipHalfExpanded,
                confirmValueChange = confirmValueChange
            )
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun rememberMyBottomSheetState(
    initialValue: ModalBottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    skipHalfExpanded: Boolean,
    confirmStateChange: (ModalBottomSheetValue) -> Boolean,
): MyBottomSheetState = rememberMyBottomSheetState(
    initialValue = initialValue,
    animationSpec = animationSpec,
    confirmValueChange = confirmStateChange,
    skipHalfExpanded = skipHalfExpanded
)

@Composable
@ExperimentalMaterialApi
fun rememberMyBottomSheetState(
    initialValue: ModalBottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmStateChange: (ModalBottomSheetValue) -> Boolean,
): MyBottomSheetState = rememberMyBottomSheetState(
    initialValue = initialValue,
    animationSpec = animationSpec,
    skipHalfExpanded = false,
    confirmValueChange = confirmStateChange
)

@Composable
@ExperimentalMaterialApi
fun MyBottomSheetLayout(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    sheetState: MyBottomSheetState = rememberMyBottomSheetState(ModalBottomSheetValue.Hidden),
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    hiddenHeight: Float = 0.0f,
    isFullExpand: Boolean = true,
    isScrim: Boolean = true, //바텀 시트 뒤 어두운 배경 넣을건지
    onDismiss: () -> Unit = {},
    ) {
    val scope = rememberCoroutineScope()
    val orientation = Orientation.Vertical
    val anchorChangeHandler = remember(sheetState, scope) {
        MySheetAnchorChangeHandler(
            state = sheetState,
            animateTo = { target, velocity ->
                scope.launch { sheetState.animateTo(target, velocity = velocity) }
            },
            snapTo = { target -> scope.launch { sheetState.snapTo(target) } }
        )
    }

    BoxWithConstraints(modifier) {
        val fullHeight = constraints.maxHeight.toFloat() - hiddenHeight
        val fullWidth = constraints.maxWidth.toFloat()
        Box(
            Modifier.fillMaxSize()
        ) {
            if (isScrim) {
                Scrim(
                    color = Theme.colorScheme.darkGray.copy(alpha = 0.3f),
                    onDismiss = {
                        if (sheetState.mySwipeableState.confirmValueChange(ModalBottomSheetValue.Hidden)) {
                            onDismiss()
                            scope.launch { sheetState.hide() }
                        }
                    },
                    visible = sheetState.mySwipeableState.targetValue != ModalBottomSheetValue.Hidden
                )
            }
        }
        Surface(
            Modifier
                .align(Alignment.TopCenter) // We offset from the top so we'll center from there
                .widthIn(max = fullWidth.dp)
                .fillMaxWidth()
                .nestedScroll(
                    remember(sheetState.mySwipeableState, orientation) {
                        ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(
                            state = sheetState.mySwipeableState,
                            orientation = orientation
                        )
                    }
                )
                .offset {
                    IntOffset(
                        0,
                        sheetState.mySwipeableState
                            .requireOffset()
                            .roundToInt()
                    )
                }
                .mySwipeableSheet(
                    state = sheetState.mySwipeableState,
                    orientation = orientation,
                )
                .swipeAnchors(
                    state = sheetState.mySwipeableState,
                    possibleValues = setOf(
                        ModalBottomSheetValue.Hidden,
                        ModalBottomSheetValue.HalfExpanded,
                        ModalBottomSheetValue.Expanded
                    ),
                    anchorChangeHandler = anchorChangeHandler
                ) { state, sheetSize ->
                    when (state) {
                        ModalBottomSheetValue.Hidden -> fullHeight
                        ModalBottomSheetValue.HalfExpanded -> when {
                            sheetSize.height < fullHeight / 1.6f -> null
                            sheetState.isSkipHalfExpanded -> null
                            else -> fullHeight / 1.6f
                        }

                        ModalBottomSheetValue.Expanded -> if (sheetSize.height != 0) {
                            max(
                                0f,
                                fullHeight - (if (isFullExpand) sheetSize.height else sheetSize.height - 200)
                            )
                        } else null
                    }
                }
                .semantics {
                    dismiss {
                        if (sheetState.mySwipeableState.confirmValueChange(
                                ModalBottomSheetValue.Hidden
                            )
                        ) {
                            scope.launch { sheetState.show() }
                        }
                        true
                    }
                    if (sheetState.mySwipeableState.currentValue == ModalBottomSheetValue.HalfExpanded) {
                        expand {
                            if (sheetState.mySwipeableState.confirmValueChange(
                                    ModalBottomSheetValue.Expanded
                                )
                            ) {
                                scope.launch { sheetState.expand() }
                            }
                            true
                        }
                    } else if (sheetState.hasHalfExpandedState) {
                        collapse {
                            if (sheetState.mySwipeableState.confirmValueChange(
                                    ModalBottomSheetValue.HalfExpanded
                                )
                            ) {
                                scope.launch { sheetState.halfExpand() }
                            }
                            true
                        }
                    }
                },
            shape = sheetShape,
            elevation = dp5,
            color = sheetBackgroundColor,
            contentColor = sheetContentColor
        ) {
            Column(content = sheetContent)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(
    state: MySwipeableState<*>,
    orientation: Orientation
): NestedScrollConnection = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.toFloat()
        return if (delta < 0 && source == NestedScrollSource.Drag) {
            state.dispatchRawDelta(delta).toOffset()
        } else {
            Offset.Zero
        }
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        return if (source == NestedScrollSource.Drag) {
            state.dispatchRawDelta(available.toFloat()).toOffset()
        } else {
            Offset.Zero
        }
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val toFling = available.toFloat()
        val currentOffset = state.requireOffset()
        return if (toFling < 0 && currentOffset > state.minOffset) {
            state.settle(velocity = toFling)
            // since we go to the anchor with tween settling, consume all for the best UX
            available
        } else {
            Velocity.Zero
        }
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        state.settle(velocity = available.toFloat())
        return available
    }

    private fun Float.toOffset(): Offset = Offset(
        x = if (orientation == Orientation.Horizontal) this else 0f,
        y = if (orientation == Orientation.Vertical) this else 0f
    )

    @JvmName("velocityToFloat")
    private fun Velocity.toFloat() = if (orientation == Orientation.Horizontal) x else y

    @JvmName("offsetToFloat")
    private fun Offset.toFloat(): Float = if (orientation == Orientation.Horizontal) x else y
}


@OptIn(ExperimentalMaterialApi::class)
private fun MySheetAnchorChangeHandler(
    state: MyBottomSheetState,
    animateTo: (target: ModalBottomSheetValue, velocity: Float) -> Unit,
    snapTo: (target: ModalBottomSheetValue) -> Unit,
) =
    MySheetAnchorChangeHandler<ModalBottomSheetValue> { previousTarget, previousAnchors, newAnchors ->
        val previousTargetOffset = previousAnchors[previousTarget]
        val newTarget = when (previousTarget) {
            ModalBottomSheetValue.Hidden -> ModalBottomSheetValue.Hidden
            ModalBottomSheetValue.HalfExpanded, ModalBottomSheetValue.Expanded -> {
                val hasHalfExpandedState =
                    newAnchors.containsKey(ModalBottomSheetValue.HalfExpanded)
                val newTarget = if (hasHalfExpandedState) ModalBottomSheetValue.HalfExpanded
                else if (newAnchors.containsKey(ModalBottomSheetValue.Expanded)) ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden
                newTarget
            }
        }
        val newTargetOffset = newAnchors.getValue(newTarget)
        if (newTargetOffset != previousTargetOffset) {
            if (state.isAnimationRunning) {
                animateTo(newTarget, state.lastVelocity)
            } else {
                snapTo(newTarget)
            }
        }
    }

@Composable
fun Scrim(
    color: Color,
    onDismiss: () -> Unit,
    visible: Boolean
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec(),
        )
        //background 클릭시 sheet 사라짐.
        val dismissModifier = if (visible) {
            Modifier
                .pointerInput(onDismiss) { detectTapGestures { onDismiss() } }
                .semantics(mergeDescendants = true) {
                    contentDescription = ""
                    onClick { onDismiss(); true }
                }
        } else {
            Modifier
        }

        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissModifier)
        ) {
            drawRect(
                color = color, alpha = alpha
            )
        }
    }
}

private val PositionalThreshold: Density.(Float) -> Float = { 56.dp.toPx() }
private val VelocityThreshold = 125.dp
val MaxModalSheetWidth = 640.dp
