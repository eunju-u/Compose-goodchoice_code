package com.example.data.mapper

import com.example.data.remote.dto.RecommendAreaDto
import com.example.data.remote.dto.SearchItemDto
import com.example.domain.model.RecommendAreaData
import com.example.domain.model.SearchItem

fun RecommendAreaDto.generateRecommendAreaData(): RecommendAreaData {
    val item = this@generateRecommendAreaData

    return RecommendAreaData(
        code = item.code,
        title = item.title,
        image = item.image
    )
}

fun SearchItemDto.generateSearchItem(): SearchItem {
    val item = this@generateSearchItem

    return SearchItem(
        searchType = item.searchType,
        searchTitle = item.searchTitle
    )
}