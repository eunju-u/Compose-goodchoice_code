package com.example.data.mapper

import com.example.data.remote.dto.PayDto
import com.example.data.remote.dto.PayItemDto
import com.example.data.remote.dto.RoomItemDto
import com.example.data.remote.dto.ServiceDto
import com.example.data.remote.dto.StayDetailDto
import com.example.domain.model.PayData
import com.example.domain.model.PayItem
import com.example.domain.model.RoomItem
import com.example.domain.model.ServiceData
import com.example.domain.model.StayDetailData
import java.util.ArrayList

fun StayDetailDto.generateData(): StayDetailData {
    val item = this@generateData

    return StayDetailData(
        id = item.id,
        label = item.label,
        name = item.name,
        star = item.star,
        commentCount = item.commentCount,
        location = item.location,
        discountPer = item.discountPer,
        defaultPrice = item.defaultPrice,
        discountPrice = item.discountPrice,
        imageList = item.imageList,
        level = item.level,
        coupon = item.coupon,
        payList = item.payList?.map { it.generateData() },
        roomList = item.roomList?.map { it.generateData() },
        message = item.message,
        defaultMessage = item.defaultMessage,
        aroundInfo = item.aroundInfo,
        service = item.service?.map { it.generateData() }?.let { ArrayList(it) }
    )
}

fun ServiceDto.generateData(): ServiceData {
    val item = this@generateData

    return ServiceData(
        type = item.type,
        name = item.name
    )
}

fun RoomItemDto.generateData(): RoomItem {
    val item = this@generateData

    return RoomItem(
        id = item.id,
        name = item.name,
        info = item.info,
        addInfo = item.addInfo,
        discountPer = item.discountPer,
        defaultPrice = item.defaultPrice,
        discountPrice = item.discountPrice,
        inTime = item.inTime,
        outTime = item.outTime,
        coupon = item.coupon,
        count = item.count,
        image = item.image,
    )
}

fun PayDto.generateData(): PayData {
    val item = this@generateData

    return PayData(
        payType = item.payType,
        payName = item.payName,
        payInfoList = item.payInfoList?.map { it.generateData() }
    )
}

fun PayItemDto.generateData(): PayItem {
    val item = this@generateData

    return PayItem(
        payInfo = item.payInfo,
        payLineTest = item.payLineTest,
    )

}