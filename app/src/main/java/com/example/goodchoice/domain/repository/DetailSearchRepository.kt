package com.example.goodchoice.domain.repository

import com.example.goodchoice.data.dto.FilterItem
import com.example.goodchoice.ui.search.data.KoreaSearchData

interface DetailSearchRepository {
    suspend fun getSearchData(): List<KoreaSearchData>

    suspend fun getRankData(): List<FilterItem>
}