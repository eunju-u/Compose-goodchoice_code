package com.example.data.mapper

import com.example.data.remote.dto.RecommendAreaDto
import com.example.domain.model.RecommendAreaData

fun RecommendAreaDto.generateData(): RecommendAreaData {
    val item = this@generateData

    return RecommendAreaData(
        code = item.code,
        title = item.title,
        image = item.image
    )
}