package com.example.goodchoice.data.dataSource

import com.example.domain.model.FilterItem
import com.example.domain.model.RecommendAreaData
import javax.inject.Inject

class SearchDataSource @Inject constructor() {
    fun getKoreaRankData() = listOf(
        FilterItem(filterType = "l1_1_1", filterTitle = "경주"),
        FilterItem(filterType = "l1_1_2", filterTitle = "여수"),
        FilterItem(filterType = "l1_1_3", filterTitle = "속초"),
        FilterItem(filterType = "l1_1_4", filterTitle = "전주"),
        FilterItem(filterType = "l1_1_5", filterTitle = "부산"),
        com.example.domain.model.FilterItem(filterType = "l1_1_6", filterTitle = "제주도"),
        com.example.domain.model.FilterItem(filterType = "l1_1_7", filterTitle = "강릉"),
        com.example.domain.model.FilterItem(filterType = "l1_1_8", filterTitle = "순천"),
        com.example.domain.model.FilterItem(filterType = "l1_1_9", filterTitle = "서울"),
        com.example.domain.model.FilterItem(filterType = "l1_1_10", filterTitle = "춘천")
    )

    fun getRecommendWordData() = listOf(
        com.example.domain.model.FilterItem(filterType = "l3_1_1", filterTitle = "웰니스 페스타"),
        com.example.domain.model.FilterItem(filterType = "l3_1_2", filterTitle = "특가"),
        com.example.domain.model.FilterItem(filterType = "l3_1_3", filterTitle = "테마파크"),
        com.example.domain.model.FilterItem(filterType = "l3_1_4", filterTitle = "에버랜드"),
        com.example.domain.model.FilterItem(filterType = "l3_1_5", filterTitle = "롯데월드"),
        com.example.domain.model.FilterItem(filterType = "l3_1_6", filterTitle = "경주월드"),
        com.example.domain.model.FilterItem(filterType = "l3_1_7", filterTitle = "아쿠아필드"),
        com.example.domain.model.FilterItem(filterType = "l3_1_8", filterTitle = "패러글라이딩"),
        com.example.domain.model.FilterItem(filterType = "l3_1_9", filterTitle = "유니버셜 스튜디오 재팬")
    )

    fun getAreaData() = listOf(
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_1",
            title = "서울",
            image = "https://a.cdn-hotels.com/gdcs/production146/d7/1272dc18-817c-436d-b9ac-47e0a139e998.jpg?impolicy=fcrop&w=1600&h=1066&q=medium"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_2",
            title = "제주",
            image = "https://i.namu.wiki/i/LikrOx-3D5xH5XjNNi4gw6cRWY46zt2h_hvND9ia3yeHnOqV3mwn_VE3sw7QYbupu_MR2rWRAiFUsqKLmEHRwQ.jpg"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_3",
            title = "부산",
            image = "https://www.visitbusan.net/uploadImgs/files/cntnts/20191229160530047_oen"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_4",
            title = "강원",
            image = "https://images.freeimages.com/images/large-previews/15b/lamb-1578074.jpg"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_5",
            title = "경기",
            image = "https://i.namu.wiki/i/q1zaFVkL-53KLWf9oPdhJLIEuxeKS99k6SWK9ZAqy8l4tHjrwQiUmqFZVw9R-m0rfjZ6i8fYgWxCe_CQDupqeGdjMgEJwdzj-OsgOWVcd6b0tR9O5FDdJKdwXlKd3uD4GTltJadcE8sfBdVL6rAjSw.webp"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_6",
            title = "인천",
            image = "https://www.icnews.co.kr/news/photo/202306/2293_2783_5017.jpg"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_7",
            title = "경주",
            image = "https://i1.wp.com/www.agoda.com/wp-content/uploads/2021/02/Gyeongju-World-Expo-Gyeongju-si-attractions-South-Korea.jpg?ssl=1"
        ),
        com.example.domain.model.RecommendAreaData(
            code = "l3_2_8",
            title = "여수",
            image = "https://a.cdn-hotels.com/gdcs/production24/d273/e5de0657-4fef-4317-b924-cb05bb99c6d8.jpg?impolicy=fcrop&w=800&h=533&q=medium"
        )
    )
}

