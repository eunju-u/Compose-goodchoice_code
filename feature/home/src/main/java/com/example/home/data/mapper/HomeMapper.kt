package com.example.home.data.mapper

import com.example.data.mapper.generateStayItem
import com.example.data.remote.dto.CategoryItemDto
import com.example.data.remote.dto.StayItemDto
import com.example.home.domain.model.BannerData
import com.example.home.domain.model.CategoryData
import com.example.home.domain.model.CategoryItem
import com.example.home.domain.model.HomeData
import com.example.home.domain.model.OverSeaCityItem
import com.example.home.domain.model.OverseaSpecialItem
import com.example.home.domain.model.StayData
import com.example.domain.model.StayItem
import com.example.home.data.remote.dto.BannerDto
import com.example.home.data.remote.dto.CategoryDto
import com.example.home.data.remote.dto.HomeDto
import com.example.home.data.remote.dto.OverSeaCityItemDto
import com.example.home.data.remote.dto.OverseaSpecialItemDto
import com.example.home.data.remote.dto.StayDto

fun HomeDto.generateHomeData(): HomeData {
    val item = this@generateHomeData

    return HomeData(
        categoryList = item.categoryList?.map { it.generateCategoryData() },
        bannerList = item.bannerList?.map { it.generateBannerData() },
        stayList = item.stayList?.map { it.generateStayData() },
        overSeaCityList = item.overSeaCityList?.map { it.generateOverSeaCityItem() },
        overseaSpecialList = item.overseaSpecialList?.map { it.generateOverseaSpecialItem() },
    )
}

fun CategoryDto.generateCategoryData(): CategoryData {
    val item = this@generateCategoryData

    return CategoryData(
        countryType = item.countryType,
        categoryList = item.categoryList?.map { it.generateCategoryItem() })

}

fun CategoryItemDto.generateCategoryItem(): CategoryItem {
    val item = this@generateCategoryItem

    return CategoryItem(
        id = item.id,
        name = item.name,
        icon = item.icon,
        code = item.code,
    )
}

fun BannerDto.generateBannerData(): BannerData {
    val item = this@generateBannerData

    return BannerData(
        title = item.title,
        image = item.image,
        url = item.url,
    )
}

fun StayDto.generateStayData(): StayData {
    val item = this@generateStayData

    return StayData(
        type = item.type,
        title = item.title,
        stayList = item.stayList?.map { it.generateStayItem() },
    )
}

fun OverSeaCityItemDto.generateOverSeaCityItem(): OverSeaCityItem {
    val item = this@generateOverSeaCityItem

    return OverSeaCityItem(
        id = item.id,
        code = item.code,
        cityName = item.cityName,
        cityImage = item.cityImage
    )
}

fun OverseaSpecialItemDto.generateOverseaSpecialItem(): OverseaSpecialItem {
    val item = this@generateOverseaSpecialItem

    return OverseaSpecialItem(
        id = item.id,
        name = item.name,
        discountPer = item.discountPer,
        defaultPrice = item.defaultPrice,
        discountPrice = item.discountPrice,
        mainImage = item.mainImage,
    )
}

