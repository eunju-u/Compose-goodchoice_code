package com.example.stay_detail.data.mapper

import com.example.stay_detail.data.remote.dto.PayDto
import com.example.stay_detail.data.remote.dto.PayItemDto
import com.example.stay_detail.data.remote.dto.RoomItemDto
import com.example.stay_detail.data.remote.dto.ServiceDto
import com.example.stay_detail.data.remote.dto.StayDetailDto
import com.example.stay_detail.domain.model.PayData
import com.example.stay_detail.domain.model.PayItem
import com.example.stay_detail.domain.model.RoomItem
import com.example.stay_detail.domain.model.ServiceData
import com.example.stay_detail.domain.model.StayDetailData
import java.util.ArrayList

fun StayDetailDto.generateStayDetailData(): StayDetailData {
    val item = this@generateStayDetailData

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
        payList = item.payList?.map { it.generatePayData() },
        roomList = item.roomList?.map { it.generateRoomItem() },
        message = item.message,
        defaultMessage = item.defaultMessage,
        aroundInfo = item.aroundInfo,
        service = item.service?.map { it.generateServiceData() }?.let { ArrayList(it) }
    )
}

fun ServiceDto.generateServiceData(): ServiceData {
    val item = this@generateServiceData

    return ServiceData(
        type = item.type,
        name = item.name
    )
}

fun RoomItemDto.generateRoomItem(): RoomItem {
    val item = this@generateRoomItem

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

fun PayDto.generatePayData(): PayData {
    val item = this@generatePayData

    return PayData(
        payType = item.payType,
        payName = item.payName,
        payInfoList = item.payInfoList?.map { it.generatePayItem() }
    )
}

fun PayItemDto.generatePayItem(): PayItem {
    val item = this@generatePayItem

    return PayItem(
        payInfo = item.payInfo,
        payLineTest = item.payLineTest,
    )

}