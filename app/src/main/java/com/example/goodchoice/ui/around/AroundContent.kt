package com.example.goodchoice.ui.around

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.R
import com.example.goodchoice.ConnectInfo
import com.example.goodchoice.Const
import com.example.goodchoice.ServerConst
import com.example.goodchoice.data.dto.AroundFilterData
import com.example.goodchoice.ui.around.widget.AroundTopWidget
import com.example.goodchoice.ui.components.ImageButtonWidget
import com.example.goodchoice.ui.components.LeftImageButtonWidget
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetLayout
import com.example.goodchoice.ui.components.bottomSheet.MyBottomSheetState
import com.example.goodchoice.ui.components.bottomSheet.SheetWidget
import com.example.goodchoice.ui.components.bottomSheet.rememberMyBottomSheetState
import com.example.goodchoice.ui.filter.FilterActivity
import com.example.goodchoice.ui.main.MainActivity
import com.example.goodchoice.domain.model.AroundFilterItem
import com.example.goodchoice.domain.model.AroundFilterSelectedModel
import com.example.goodchoice.ui.main.AroundFilterSelectedData
import com.example.goodchoice.ui.main.MainViewModel
import com.example.goodchoice.ui.search.detailSearch.DetailSearchActivity
import com.example.goodchoice.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun AroundContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val context = LocalContext.current
    val selectRoomType = viewModel.selectRoomType
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val fullHeight = configuration.screenHeightDp
    val sheetState: MyBottomSheetState =
        rememberMyBottomSheetState(initialValue = ModalBottomSheetValue.HalfExpanded)

    //각 필터 클릭시 하위 필터 노출 여부
    var selectFilter by remember { mutableStateOf("") }
    var selectFilterData by remember { mutableStateOf(AroundFilterData()) }

    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val aroundFilterSelect = viewModel.aroundFilterSelect
    val filterList = viewModel.filterList.collectAsStateWithLifecycle()
    val selectSearchItem = viewModel.selectSearchItem

    Box(modifier = modifier) {
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
        //TODO Map 넣기

        // 목록보기 버튼 (Hidden 상태에서만 보여지도록 함.)
        if (sheetState.currentValue == ModalBottomSheetValue.Hidden) {
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
            hiddenHeight = 225f,
            isScrim = false,
            sheetContent = {
                SheetWidget(
                    modifier = Modifier.wrapContentHeight(),
                    hasIndicator = sheetState.currentValue != ModalBottomSheetValue.Expanded
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
                                                selectDepthItem.value = AroundFilterItem(
                                                    mainType = selectFilterData.type,
                                                    subType = selectFilterData.type,
                                                    text = selectFilterData.text
                                                )
                                            } else {
                                                selectDepthItem.value = AroundFilterItem()
                                            }
                                        } else if (item.type == ServerConst.FILTER) {
                                            (context as MainActivity).activityForFilterResult.launch(
                                                Intent(
                                                    context,
                                                    FilterActivity::class.java
                                                ).apply {
                                                    // viewModel.aroundFilterSelect 의 데이터는 mutable 타입이라 serialize 불가
                                                    val selectedData = viewModel.aroundFilterSelect
                                                    val data = AroundFilterSelectedModel(
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
                                                            .size(width = dp5, height = dp5)
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
                                aroundFilterSelect.selectedRecommend.value = AroundFilterItem(
                                    subType = ServerConst.RECOMMEND, text = "추천순"
                                )
                            }

                            val filterDetailList = selectFilterData.filterList ?: listOf()
                            // 필터 depth 뷰
                            FlowRow(
                                modifier = Modifier.padding(
                                    start = dp30, end = dp30, top = dp15, bottom = dp5
                                ), horizontalArrangement = Arrangement.spacedBy(dp20)
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
                                                select.value = AroundFilterItem(
                                                    mainType = selectFilterData.type,
                                                    subType = it.type,
                                                    text = it.text
                                                )
                                            } else {
                                                select.value = AroundFilterItem()
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
        if (sheetState.currentValue == ModalBottomSheetValue.Expanded) {
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
