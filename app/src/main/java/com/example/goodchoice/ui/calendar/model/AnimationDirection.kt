package com.example.goodchoice.ui.calendar.model

enum class AnimationDirection {
    FORWARDS,
    BACKWARDS;

    fun isBackwards() = this == BACKWARDS
    fun isForwards() = this == FORWARDS
}
