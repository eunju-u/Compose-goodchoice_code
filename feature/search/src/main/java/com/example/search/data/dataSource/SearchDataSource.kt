package com.example.search.data.dataSource

import com.example.search.data.remote.dto.RecommendAreaDto
import com.example.search.data.remote.dto.SearchItemDto
import javax.inject.Inject

class SearchDataSource @Inject constructor() {
    fun getKoreaRankData() = listOf(
        SearchItemDto(searchType = "l1_1_1", searchTitle = "경주"),
        SearchItemDto(searchType = "l1_1_2", searchTitle = "여수"),
        SearchItemDto(searchType = "l1_1_3", searchTitle = "속초"),
        SearchItemDto(searchType = "l1_1_4", searchTitle = "전주"),
        SearchItemDto(searchType = "l1_1_5", searchTitle = "부산"),
        SearchItemDto(searchType = "l1_1_6", searchTitle = "제주도"),
        SearchItemDto(searchType = "l1_1_7", searchTitle = "강릉"),
        SearchItemDto(searchType = "l1_1_8", searchTitle = "순천"),
        SearchItemDto(searchType = "l1_1_9", searchTitle = "서울"),
        SearchItemDto(searchType = "l1_1_10", searchTitle = "춘천")
    )

    fun getRecommendWordData() = listOf(
        SearchItemDto(searchType = "l3_1_1", searchTitle = "웰니스 페스타"),
        SearchItemDto(searchType = "l3_1_2", searchTitle = "특가"),
        SearchItemDto(searchType = "l3_1_3", searchTitle = "테마파크"),
        SearchItemDto(searchType = "l3_1_4", searchTitle = "에버랜드"),
        SearchItemDto(searchType = "l3_1_5", searchTitle = "롯데월드"),
        SearchItemDto(searchType = "l3_1_6", searchTitle = "경주월드"),
        SearchItemDto(searchType = "l3_1_7", searchTitle = "아쿠아필드"),
        SearchItemDto(searchType = "l3_1_8", searchTitle = "패러글라이딩"),
        SearchItemDto(searchType = "l3_1_9", searchTitle = "유니버셜 스튜디오 재팬")
    )

    fun getAreaData() = listOf(
        RecommendAreaDto(
            code = "l3_2_1",
            title = "서울",
            image = "https://a.cdn-hotels.com/gdcs/production146/d7/1272dc18-817c-436d-b9ac-47e0a139e998.jpg?impolicy=fcrop&w=1600&h=1066&q=medium"
        ),
        RecommendAreaDto(
            code = "l3_2_2",
            title = "제주",
            image = "https://i.namu.wiki/i/LikrOx-3D5xH5XjNNi4gw6cRWY46zt2h_hvND9ia3yeHnOqV3mwn_VE3sw7QYbupu_MR2rWRAiFUsqKLmEHRwQ.jpg"
        ),
        RecommendAreaDto(
            code = "l3_2_3",
            title = "부산",
            image = "https://www.visitbusan.net/uploadImgs/files/cntnts/20191229160530047_oen"
        ),
        RecommendAreaDto(
            code = "l3_2_4",
            title = "강원",
            image = "https://images.freeimages.com/images/large-previews/15b/lamb-1578074.jpg"
        ),
        RecommendAreaDto(
            code = "l3_2_6",
            title = "인천",
            image = "https://www.icnews.co.kr/news/photo/202306/2293_2783_5017.jpg"
        ),
        RecommendAreaDto(
            code = "l3_2_7",
            title = "경주",
            image = "https://i1.wp.com/www.agoda.com/wp-content/uploads/2021/02/Gyeongju-World-Expo-Gyeongju-si-attractions-South-Korea.jpg?ssl=1"
        ),
        RecommendAreaDto(
            code = "l3_2_8",
            title = "여수",
            image = "https://a.cdn-hotels.com/gdcs/production24/d273/e5de0657-4fef-4317-b924-cb05bb99c6d8.jpg?impolicy=fcrop&w=800&h=533&q=medium"
        )
    )
}

