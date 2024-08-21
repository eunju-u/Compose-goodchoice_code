package com.example.domain.repository

import com.example.domain.model.FilterItem
import com.example.domain.model.KoreaSearchData

interface DetailSearchRepository {
    suspend fun getSearchData(): List<KoreaSearchData>

    suspend fun getRankData(): List<FilterItem>
}