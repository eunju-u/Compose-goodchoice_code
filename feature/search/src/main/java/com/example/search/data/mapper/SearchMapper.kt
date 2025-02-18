package com.example.search.data.mapper

import com.example.search.domain.model.RecommendAreaData
import com.example.search.domain.model.SearchItem
import com.example.search.data.remote.dto.RecommendAreaDto
import com.example.search.data.remote.dto.SearchItemDto

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