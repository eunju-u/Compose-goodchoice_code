package com.example.data.mapper

import com.example.data.remote.dto.BannerDto
import com.example.data.remote.dto.CategoryDto
import com.example.data.remote.dto.CategoryItemDto
import com.example.data.remote.dto.HomeDto
import com.example.data.remote.dto.OverSeaCityItemDto
import com.example.data.remote.dto.OverseaSpecialItemDto
import com.example.data.remote.dto.StayDto
import com.example.data.remote.dto.StayItemDto
import com.example.domain.model.BannerData
import com.example.domain.model.CategoryData
import com.example.domain.model.CategoryItem
import com.example.domain.model.HomeData
import com.example.domain.model.OverSeaCityItem
import com.example.domain.model.OverseaSpecialItem
import com.example.domain.model.StayData
import com.example.domain.model.StayItem

fun HomeDto.generateData(): HomeData {
    val item = this@generateData

    return HomeData(
        categoryList = item.categoryList?.map { it.generateData() },
        bannerList = item.bannerList?.map { it.generateData() },
        stayList = item.stayList?.map { it.generateData() },
        overSeaCityList = item.overSeaCityList?.map { it.generateData() },
        overseaSpecialList = item.overseaSpecialList?.map { it.generateData() },
    )
}

fun CategoryDto.generateData(): CategoryData {
    val item = this@generateData

    return CategoryData(
        countryType = item.countryType,
        categoryList = item.categoryList?.map { it.generateData() })

}

fun CategoryItemDto.generateData(): CategoryItem {
    val item = this@generateData

    return CategoryItem(
        id = item.id,
        name = item.name,
        icon = item.icon,
        code = item.code,
    )
}

fun BannerDto.generateData(): BannerData {
    val item = this@generateData

    return BannerData(
        title = item.title,
        image = item.image,
        url = item.url,
    )
}

fun StayDto.generateData(): StayData {
    val item = this@generateData

    return StayData(
        type = item.type,
        title = item.title,
        stayList = item.stayList?.map { it.generateData() },
    )
}

fun StayItemDto.generateData(): StayItem {
    val item = this@generateData

    return StayItem(
        id = item.id,
        label = item.label,
        name = item.name,
        star = item.star,
        commentCount = item.commentCount,
        location = item.location,
        discountPer = item.discountPer,
        defaultPrice = item.defaultPrice,
        discountPrice = item.discountPrice,
        mainImage = item.mainImage,
        level = item.level,
    )
}

fun OverSeaCityItemDto.generateData(): OverSeaCityItem {
    val item = this@generateData

    return OverSeaCityItem(
        id = item.id,
        code = item.code,
        cityName = item.cityName,
        cityImage = item.cityImage
    )
}

fun OverseaSpecialItemDto.generateData(): OverseaSpecialItem {
    val item = this@generateData

    return OverseaSpecialItem(
        id = item.id,
        name = item.name,
        discountPer = item.discountPer,
        defaultPrice = item.defaultPrice,
        discountPrice = item.discountPrice,
        mainImage = item.mainImage,
    )
}

