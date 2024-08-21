package com.example.domain.model

data class MyInfoData (
    //내정보 > 상단 메뉴 (최근 본 상품)
    val topMenuList : List<CategoryItem>? = emptyList(),
    //내 정보 > 하단 메뉴(예약내역, 고객센터, 기타등등, 설정)
    val menuList : List<MyMenuData>? = emptyList()
)