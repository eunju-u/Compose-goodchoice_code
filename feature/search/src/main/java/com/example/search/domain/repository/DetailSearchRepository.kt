package com.example.search.domain.repository

import com.example.domain.model.KoreaSearchData
import com.example.search.domain.model.SearchItem

interface DetailSearchRepository {
    suspend fun getSearchData(): List<KoreaSearchData>

    suspend fun getRankData(): List<SearchItem>
}