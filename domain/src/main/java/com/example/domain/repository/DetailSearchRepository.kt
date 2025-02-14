package com.example.domain.repository

import com.example.domain.model.KoreaSearchData
import com.example.domain.model.SearchItem

interface DetailSearchRepository {
    suspend fun getSearchData(): List<KoreaSearchData>

    suspend fun getRankData(): List<SearchItem>
}