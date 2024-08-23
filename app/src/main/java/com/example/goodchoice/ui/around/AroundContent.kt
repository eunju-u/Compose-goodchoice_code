package com.example.goodchoice.ui.around

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.Const
import com.example.ui.R
import com.example.common.ServerConst
import com.example.domain.model.AroundFilterData
import com.example.goodchoice.ui.around.widget.AroundTopWidget
import com.example.goodchoice.ui.main.MainActivity
import com.example.goodchoice.ui.main.MainViewModel
import com.example.domain.info.ConnectInfo
import com.example.ui.filter.FilterActivity
import com.example.goodchoice.ui.main.AroundFilterSelectedData
import com.example.goodchoice.ui.search.detailSearch.DetailSearchActivity
import com.example.ui.components.*
import com.example.ui.components.bottomSheet.*
import com.example.ui.theme.Theme
import com.example.ui.theme.*
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class, ExperimentalNaverMapApi::class)
@Composable
fun AroundContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val context = LocalContext.current
    val selectRoomType = viewModel.selectRoomType
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val fullHeight = configuration.screenHeightDp
    val sheetState: MyBottomSheetState =
        rememberMyBottomSheetState(initialValue = MyBottomSheetValue.HalfExpanded)

    //각 필터 클릭시 하위 필터 노출 여부
    var selectFilter by remember { mutableStateOf("") }
    var selectFilterData by remember { mutableStateOf(AroundFilterData()) }

    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val aroundFilterSelect = viewModel.aroundFilterSelect
    val filterList = viewModel.filterList.collectAsStateWithLifecycle()
    val selectSearchItem = viewModel.selectSearchItem
    val cameraPositionState = rememberCameraPositionState()
    var currentLatLng by remember { mutableStateOf(LatLng(0.0, 0.0)) } //현재 위치 위도 경도 값
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var isClickedShowList by remember { mutableStateOf(false) } //목록보기 노출 여부
    val isDragging by remember { sheetState.isDragging } //바텀시트 드래그 중인지 여부

    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    //위치 퍼미션 허용 여부
    val checkPermission = ActivityCompat.checkSelfPermission(
        context,
        permissions[0]
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        permissions[1]
    ) == PackageManager.PERMISSION_GRANTED

    if (checkPermission) {
        //초기 지도 로딩시 현재 위치로 카메라 전환이 느림.(현재 위치 바로 노출 하지 않고 내부 기본 위치값 노출 후 현재 위치로 이동됨.)
        //내 위치 미리 저장해두고 현재 위치로 바로 이동할 수 있도록 함
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                cameraPositionState.position = CameraPosition(LatLng(it), 14.0)
                currentLatLng = cameraPositionState.position.target
            }
        }
    }

    //currentValue 바뀌었을 때 목록보기 노출 여부 초기화
    LaunchedEffect(sheetState.currentValue) {
        snapshotFlow { sheetState.currentValue }
            .distinctUntilChanged()
            .collect {
                isClickedShowList = false
            }
    }

    Box(modifier = modifier) {

        //네이버 지도
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState, //현재 위치 표시
            properties = MapProperties(
                locationTrackingMode = LocationTrackingMode.Follow,
            ),
            onLocationChange = { location -> }
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AroundTopWidget(
                selectedRoomType = selectRoomType.value,
                onRoomTypeClick = { roomType ->
                    viewModel.selectRoomType.value = roomType
                    // 룸타입이 변경되면 필터값 초기화
                    selectFilter = ""
                    selectFilterData = AroundFilterData()
                    viewModel.aroundFilterSelect = AroundFilterSelectedData()
                    viewModel.requestAroundData()
                },
                selectedSearchText = selectSearchItem.value.name ?: "",
                onSearchClick = {
                    (context as MainActivity).activityForSearchResult.launch(
                        Intent(
                            context,
                            DetailSearchActivity::class.java
                        )
                    )
                })
        }

        // 목록보기 버튼 (Hidden 상태에서만 보여지도록 함.)
        if (sheetState.currentValue == MyBottomSheetValue.Hidden) {
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = dp90)
            ) {
                LeftImageButtonWidget(
                    title = stringResource(id = R.string.str_show_list),
                    style = MaterialTheme.typography.labelMedium,
                    shape = dp30,
                    containerColor = Theme.colorScheme.darkGray,
                    contentColor = Theme.colorScheme.white,
                    onItemClick = {
                        scope.launch {
                            sheetState.halfExpand()
                        }
                    },
                    content = {
                        Image(
                            modifier = Modifier.size(dp15),
                            painter = painterResource(id = R.drawable.ic_list_menu),
                            colorFilter = ColorFilter.tint(Theme.colorScheme.white),
                            contentDescription = null
                        )
                    }
                )
            }
        }

        // 바텀시트
        MyBottomSheetLayout(
            sheetState = sheetState,
            hiddenHeight = 430f,
            isScrim = false,
            sheetContent = {
                if (sheetState.currentValue != MyBottomSheetValue.Expanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dp70)
                    ) {
                        if (!(sheetState.targetValue == MyBottomSheetValue.Expanded && sheetState.dragValue == MyBottomSheetValue.DownToUp)) {
                            RoundImageWidget(
                                modifier = Modifier
                                    .padding(
                                        bottom = dp15,
                                        start = dp15,
                                        top = dp15  //top은 이미지에 shadow 넣기 위해
                                    ),
                                imageModifier = Modifier
                                    .size(dp40)
                                    .clickable {
                                        if (checkPermission) {
                                            cameraPositionState.move(
                                                CameraUpdate.toCameraPosition(
                                                    CameraPosition(
                                                        currentLatLng, 14.0, 0.0, 0.0
                                                    )
                                                )
                                            )
                                        } else {
                                            //위치 권한 없을 경우 권한 요청
                                            val activity = context as MainActivity
                                            activity.aroundRequestPermission.launch(
                                                arrayOf(
                                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                                )
                                            )
                                        }
                                    },
                                painter = painterResource(R.drawable.bg_white),
                                roundShape = dp30,
                                boxAlignment = Alignment.Center,
                                isVisibleShadow = true,
                                content = {
                                    Image(
                                        modifier = Modifier.size(dp25),
                                        painter = painterResource(id = R.drawable.ic_location),
                                        colorFilter =
                                        if ((currentLatLng != cameraPositionState.position.target) || !checkPermission) //퍼미션 체크 안되어있으면 그레이색상 노출
                                            ColorFilter.tint(Theme.colorScheme.gray)
                                        else ColorFilter.tint(Theme.colorScheme.blue),
                                        contentDescription = null
                                    )
                                }
                            )
                        }

                        // 목록보기 버튼 (바텀시트 위에 있는 현재 위치 아이콘으로 인해 하단 목록보기가 클릭이 되지 않아, 바텀시트에도 목록보기가 조건에 따라 노출 하도록 함.)
                        //isClickedShowList : 바텀시트에 붙어있는 목록보기 클릭시 목록보기 미노출 하도록 함
                        //isDragging : 드래그 중일때는 바텀시트에 붙어있는 목록보기 미노출함
                        if (sheetState.currentValue == MyBottomSheetValue.Hidden
                            && sheetState.dragValue != MyBottomSheetValue.DownToUp
                            && !isClickedShowList && !isDragging
                        ) {
                            LeftImageButtonWidget(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                outerPadding = PaddingValues(bottom = dp17),
                                title = stringResource(id = R.string.str_show_list),
                                style = MaterialTheme.typography.labelMedium,
                                shape = dp30,
                                containerColor = Theme.colorScheme.darkGray,
                                contentColor = Theme.colorScheme.white,
                                onItemClick = {
                                    //목록보기 클릭시 바로 사라지지 않아 플래그 추가
                                    isClickedShowList = true
                                    scope.launch {
                                        sheetState.halfExpand()
                                    }
                                },
                                content = {
                                    Image(
                                        modifier = Modifier.size(dp15),
                                        painter = painterResource(id = R.drawable.ic_list_menu),
                                        colorFilter = ColorFilter.tint(Theme.colorScheme.white),
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                    }
                }

                SheetWidget(
                    modifier = Modifier.wrapContentHeight(),
                    hasIndicator = sheetState.currentValue != MyBottomSheetValue.Expanded
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(fullHeight.dp)
                            .padding(top = dp15),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dp35),
                            horizontalArrangement = Arrangement.spacedBy(dp10),
                        ) {
                            itemsIndexed(items = filterList.value) { index, item ->
                                //선택된 필터 상세
                                val selectDepthItem = when (item.type) {
                                    ServerConst.FILTER -> aroundFilterSelect.selectedFilter
                                    ServerConst.RECOMMEND -> aroundFilterSelect.selectedRecommend
                                    ServerConst.ROOM -> aroundFilterSelect.selectedRoom
                                    ServerConst.RESERVATION -> aroundFilterSelect.selectedReservation
                                    else -> aroundFilterSelect.selectedPrice
                                }

                                //추천순의 경우 하위 필터 "추천순" 이 선택되어 있지만 상위는 체크 되어있지 않아 예외처리
                                val sameTypeFilterList =
                                    item.filterList?.find { item.type == selectDepthItem.value.subType }

                                //예약 가능의 경우 depth 가 없어 색상 다르게 해야 함.
                                val hasDepthItem = !item.filterList.isNullOrEmpty()
                                //상위 필터 선택한 경우
                                val isSelectUpper = selectFilter == item.type
                                //하위 필터 선택한 경우
                                val isSelectDepth = !selectDepthItem.value.subType.isNullOrEmpty()
                                //상위 필터와 하위 필터가 같은 type 일 경우
                                val isSameType = !sameTypeFilterList?.type.isNullOrEmpty()

                                val contentColor =
                                    if (isSelectUpper && !hasDepthItem && isSelectDepth) Theme.colorScheme.white
                                    else if (isSelectUpper && hasDepthItem) Theme.colorScheme.blue
                                    else if (isSameType || !isSelectDepth) Theme.colorScheme.darkGray
                                    else Theme.colorScheme.white

                                if (index == 0) Spacer(Modifier.width(15.dp))
                                ImageButtonWidget(
                                    title = if (isSelectDepth) selectDepthItem.value.text else item.text,
                                    shape = dp30,
                                    containerColor = if (isSelectUpper && !hasDepthItem && isSelectDepth) Theme.colorScheme.blue
                                    else if (isSelectUpper && hasDepthItem) Theme.colorScheme.pureBlue
                                    else if (isSameType || !isSelectDepth) Theme.colorScheme.pureGray
                                    else Theme.colorScheme.blue,
                                    contentColor = contentColor,
                                    borderColor = if (isSelectUpper && !hasDepthItem) {
                                        Color.Transparent
                                    } else if (isSelectUpper) Theme.colorScheme.blue
                                    else Color.Transparent,
                                    isLeftImage = item.type == ServerConst.FILTER,
                                    onItemClick = {
                                        selectFilterData = item

                                        selectFilter = when (selectFilter) {
                                            "" -> item.type ?: ""
                                            item.type -> ""
                                            else -> item.type ?: ""
                                        }

                                        //예약 가능 필터의 경우 select 할 필터가 없기 때문에 강제로 주입시킨다.
                                        if (selectFilterData.type == ServerConst.RESERVATION) {

                                            if (selectDepthItem.value.subType.isNullOrEmpty()) {
                                                selectDepthItem.value =
                                                    com.example.ui.model.AroundFilterItem(
                                                        mainType = selectFilterData.type,
                                                        subType = selectFilterData.type,
                                                        text = selectFilterData.text
                                                    )
                                            } else {
                                                selectDepthItem.value =
                                                    com.example.ui.model.AroundFilterItem()
                                            }
                                        } else if (item.type == ServerConst.FILTER) {
                                            (context as MainActivity).activityForFilterResult.launch(
                                                Intent(
                                                    context,
                                                    FilterActivity::class.java
                                                ).apply {
                                                    // viewModel.aroundFilterSelect 의 데이터는 mutable 타입이라 serialize 불가
                                                    val selectedData = viewModel.aroundFilterSelect
                                                    val data =
                                                        com.example.ui.model.AroundFilterSelectedModel(
                                                            selectedData.selectedFilter.value,
                                                            selectedData.selectedRecommend.value,
                                                            selectedData.selectedRoom.value,
                                                            selectedData.selectedReservation.value,
                                                            selectedData.selectedPrice.value
                                                        )
                                                    putExtra(Const.DATA, data)
                                                }
                                            )
                                        }
                                    },
                                    content = when (item.type) {
                                        ServerConst.FILTER -> {
                                            {
                                                Image(
                                                    modifier = Modifier.size(dp15),
                                                    painter = painterResource(id = R.drawable.ic_filter),
                                                    colorFilter = ColorFilter.tint(Theme.colorScheme.darkGray),
                                                    contentDescription = null
                                                )
                                            }
                                        }

                                        ServerConst.RESERVATION -> {
                                            null
                                        }

                                        else -> {
                                            {
                                                Image(
                                                    modifier = Modifier.size(dp15),
                                                    painter = if (isSelectUpper) painterResource(
                                                        id = R.drawable.ic_arrow_up
                                                    )
                                                    else painterResource(id = R.drawable.ic_arrow_down),
                                                    colorFilter = ColorFilter.tint(contentColor),
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                    },
                                    pointContent = when (item.type) {
                                        ServerConst.FILTER -> {
                                            {
                                                if (!viewModel.aroundFilterSelect.selectedRoom.value.subType.isNullOrEmpty() ||
                                                    !viewModel.aroundFilterSelect.selectedReservation.value.subType.isNullOrEmpty() ||
                                                    !viewModel.aroundFilterSelect.selectedPrice.value.subType.isNullOrEmpty()
                                                ) {
                                                    Box(
                                                        modifier = Modifier
                                                            .size(
                                                                width = dp5,
                                                                height = dp5
                                                            )
                                                            .background(
                                                                color = Theme.colorScheme.red,
                                                                shape = CircleShape
                                                            )
                                                    )
                                                }
                                            }
                                        }

                                        else -> {
                                            null
                                        }
                                    }
                                )
                                if (index == filterList.value.lastIndex) Spacer(Modifier.width(15.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(dp10))
                        HorizontalDivider(color = Theme.colorScheme.pureGray)

                        val list = selectFilterData.filterList ?: listOf()
                        if (selectFilter != "" && selectFilter == selectFilterData.type && list.isNotEmpty()) {

                            val select = when (selectFilterData.type) {
                                ServerConst.FILTER -> aroundFilterSelect.selectedFilter
                                ServerConst.RECOMMEND -> aroundFilterSelect.selectedRecommend
                                ServerConst.ROOM -> aroundFilterSelect.selectedRoom
                                ServerConst.RESERVATION -> aroundFilterSelect.selectedReservation
                                else -> aroundFilterSelect.selectedPrice
                            }

                            //값이 지워진 경우 초기값 넣어줌
                            if (aroundFilterSelect.selectedRecommend.value.subType.isNullOrEmpty()) {
                                aroundFilterSelect.selectedRecommend.value =
                                    com.example.ui.model.AroundFilterItem(
                                        subType = ServerConst.RECOMMEND, text = "추천순"
                                    )
                            }

                            val filterDetailList = selectFilterData.filterList ?: listOf()
                            // 필터 depth 뷰
                            FlowRow(
                                modifier = Modifier.padding(
                                    start = dp30,
                                    end = dp30,
                                    top = dp15,
                                    bottom = dp5
                                ),
                                horizontalArrangement = Arrangement.spacedBy(dp20)
                            ) {
                                filterDetailList.forEach {
                                    val isSelect = select.value.subType == it.type
                                    ImageButtonWidget(title = it.text,
                                        shape = dp30,
                                        outerPadding = PaddingValues(bottom = dp10),
                                        containerColor = if (isSelect) Theme.colorScheme.blue
                                        else Theme.colorScheme.pureGray,
                                        contentColor = if (isSelect) Theme.colorScheme.white
                                        else Theme.colorScheme.darkGray,
                                        onItemClick = {
                                            //select 값이 초기값이나 클릭한 type 과 같지 않을 때
                                            if (select.value.subType.isNullOrEmpty() || select.value.subType != it.type) {
                                                select.value =
                                                    com.example.ui.model.AroundFilterItem(
                                                        mainType = selectFilterData.type,
                                                        subType = it.type,
                                                        text = it.text
                                                    )
                                            } else {
                                                select.value =
                                                    com.example.ui.model.AroundFilterItem()
                                            }
                                            //뷰 노출 여부 초기화
                                            selectFilter = ""
                                        })
                                }
                            }
                            HorizontalDivider(color = Theme.colorScheme.pureGray)
                        }
                    }

                    when (homeUiState.value) {
                        is ConnectInfo.Available -> {}
                        is ConnectInfo.Error -> {}
                        else -> {}
                    }
                }
            })

        //지도보기 버튼 (Expanded 상태에서만 보여지도록 함.)
        if (sheetState.currentValue == MyBottomSheetValue.Expanded) {
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = dp20)
            ) {
                LeftImageButtonWidget(
                    title = stringResource(id = R.string.str_show_map),
                    style = MaterialTheme.typography.labelMedium,
                    shape = dp30,
                    containerColor = Theme.colorScheme.darkGray,
                    contentColor = Theme.colorScheme.white,
                    onItemClick = {
                        scope.launch {
                            sheetState.halfExpand()
                        }
                    },
                    content = {
                        Image(
                            modifier = Modifier.size(dp15),
                            painter = painterResource(id = R.drawable.ic_map),
                            colorFilter = ColorFilter.tint(Theme.colorScheme.white),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}
