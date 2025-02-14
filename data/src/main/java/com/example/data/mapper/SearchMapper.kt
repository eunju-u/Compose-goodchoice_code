package com.example.data.mapper

import com.example.data.remote.dto.RecommendAreaDto
import com.example.domain.model.RecommendAreaData

fun RecommendAreaDto.generateRecommendAreaData(): RecommendAreaData {
    val item = this@generateRecommendAreaData

    return RecommendAreaData(
        code = item.code,
        title = item.title,
        image = item.image
    )
}