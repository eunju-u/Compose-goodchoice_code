package com.example.filter.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.ServerConst
import com.example.common.ui_data.AroundFilterItem
import com.example.ui_common.R
import com.example.ui_theme.*
import com.example.domain.info.ConnectInfo
import com.example.filter.domain.model.FilterData
import com.example.filter.domain.model.FilterItem
import com.example.filter.ui.widget.FilterItemWidget
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.ButtonWidget
import com.example.ui_common.components.CardWidget
import com.example.ui_common.components.LeftImageButtonWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_common.components.ShapeButton
import com.example.ui_common.components.SpaceBetweenRowWidget
import com.example.ui_common.components.TopAppBarWidget
import java.util.LinkedList

@SuppressLint("MutableCollectionMutableState")
@Composable
fun FilterContent(viewModel: FilterViewModel, onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    var checkReservation by remember { viewModel.checkReservation }
    val clickStayType = viewModel.clickStayType
    // 다이얼로그 에서 확인 클릭시 clickStayType set 해야 하므로 추가
    val clickFilterStayItem = remember { mutableStateOf(FilterItem()) }

    val stayTypeList = viewModel.stayTypeList
    var list = listOf(FilterData())
    val filterUiState = viewModel.filterUiState.collectAsStateWithLifecycle()

    // key 에는 #취향, 할인혜택, 가격 등 상위 제목이 들어가고
    // value 에는 상위 title 에서 선택한 필터값이 list 로 들어간다.
    val selectFilterMap = viewModel.selectFilterMap

    // selectFilterMap 의 value 를 MutableList 로 했는데, list add, remove 시 상태가 변경 (필터 클릭시 색상 변하지 않음.) 되지 않음
    // selectFilterMap 의 value 값이 변경되면 상태 변경(recomposition)할 수 있도록 Mutable list 를 사용한다.
    // selectFilterMap 의 value 는 기존 MutableList 였는데,
    // selectFilterList 추가 되었기 때문에 MutableList 로 사용할 필요성이 없어 LinkedList 로 대체함.
    val selectFilterList = remember { viewModel.selectFilterList }

    // 필터한 숙소 갯수
    val stayCount = viewModel.stayCount
    // 다이얼로그 노출 여부
    var isShowDialog by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.colorScheme.white)
        ) {

            when (filterUiState.value) {
                is ConnectInfo.Error -> {}
                else -> {
                    if (filterUiState.value is ConnectInfo.Available) {
                        val item = filterUiState.value as ConnectInfo.Available
                        list = item.data as List<FilterData>
                    }

                    TopAppBarWidget(title = stringResource(id = R.string.str_filter),
                        isCloseButton = true,
                        onFinish = { onFinish() })
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = dp20, end = dp20, bottom = dp70),
                        state = scrollState
                    ) {
                        item {
                            SpaceBetweenRowWidget(modifier = Modifier
                                .fillMaxWidth()
                                .height(dp40),
                                text = stringResource(id = R.string.str_reservation_available),
                                textStyle = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                content = {
                                    Switch(
                                        checked = checkReservation,
                                        onCheckedChange = {
                                            checkReservation = it
                                        },
                                        colors = SwitchDefaults.colors(
                                            checkedTrackColor = Theme.colorScheme.blue,
                                            uncheckedTrackColor = Theme.colorScheme.pureGray,
                                        )
                                    )
                                })
                            Spacer(modifier = Modifier.height(dp20))
                        }

                        if (stayTypeList.isNotEmpty()) {
                            item {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "숙소유형",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Spacer(modifier = Modifier.height(dp20))

                                val height =
                                    ((stayTypeList.size / 2) + (if (stayTypeList.size % 2 > 0) 1 else 0)) * 50
                                LazyVerticalGrid(
                                    modifier = Modifier.height(height.dp),
                                    columns = GridCells.Fixed(count = 2),
                                    userScrollEnabled = false
                                ) {
                                    items(items = stayTypeList) { item ->
                                        LeftImageButtonWidget(modifier = Modifier.height(dp50),
                                            title = item.filterTitle,
                                            style = MaterialTheme.typography.labelMedium,
                                            isCenterHorizontalArrangement = false,
                                            content = {
                                                ShapeButton(
                                                    isChecked = clickStayType.value.filterType == item.filterType,
                                                    checkedColor = Theme.colorScheme.blue,
                                                )
                                            },
                                            onItemClick = {
                                                clickFilterStayItem.value = item
                                                if (clickStayType.value != item && selectFilterMap.values.isNotEmpty()) {
                                                    isShowDialog = true
                                                } else {
                                                    if (clickStayType.value != item) {
                                                        clickStayType.value = item
                                                    }
                                                    selectFilterMap.clear()
                                                    viewModel.requestFilterData()
                                                }
                                            })
                                    }
                                }
                            }
                            item { Spacer(modifier = Modifier.height(dp20)) }
                        }

                        if (list.isNotEmpty()) {
                            items(items = list, key = { it.title ?: "" }) { filterData ->
                                filterData.title?.let {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = dp10, bottom = dp10),
                                        text = it,
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                }

                                val filterDataList = filterData.list ?: listOf()
                                filterDataList.forEach { filterListData ->
                                    FilterItemWidget(
                                        subTitle =
                                        if (filterDataList.size == 1 && filterData.code == filterDataList[0].code)
                                            "" else filterListData.title ?: "",
                                        list = filterListData.list ?: listOf(),
                                        selectList = if (selectFilterList.isNotEmpty()) selectFilterMap[filterListData.code]
                                            ?: LinkedList() else LinkedList(),
                                        onItemClick = { clickItem ->
                                            val selectItem = AroundFilterItem(
                                                filterData.code,
                                                clickItem.filterType,
                                                clickItem.filterTitle
                                            )

                                            var value = selectFilterMap[filterListData.code]
                                                ?: LinkedList()
                                            //가격의 경우는 한개만 선택 가능함.
                                            if (filterListData.code == ServerConst.PRICE) {
                                                //map 의 value 값 초기화 하고 삽입
                                                value = LinkedList()
                                                selectFilterList.clear()

                                                value.add(clickItem)
                                                selectFilterList.add(selectItem)
                                            } else {
                                                if (!value.contains(clickItem)) {
                                                    value.add(clickItem)
                                                    selectFilterList.add(selectItem)
                                                } else {
                                                    value.remove(clickItem)
                                                    selectFilterList.remove(selectItem)
                                                }
                                            }

                                            filterListData.code?.let {
                                                selectFilterMap[it] = value
                                            }
                                        })
                                }

                                Spacer(modifier = Modifier.height(dp20))
                            }
                        }
                    }
                }
            }
        }

        CardWidget(modifier = Modifier.align(Alignment.BottomEnd),
            containerColor = Theme.colorScheme.white,
            isVisibleShadow = true,
            shadowOffsetY = -dp5,
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dp50)
                ) {
                    val isChecked =
                        selectFilterMap.values.isNotEmpty() || clickStayType.value.filterType != ServerConst.ALL
                    LeftImageButtonWidget(modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight(),
                        title = stringResource(id = R.string.str_filter_reset),
                        innerPadding = PaddingValues(horizontal = dp5),
                        shape = dp0,
                        contentColor = if (isChecked) Theme.colorScheme.darkGray else Theme.colorScheme.gray,
                        style = MaterialTheme.typography.labelMedium,
                        endPadding = dp10,
                        onItemClick = {
                            viewModel.checkReservation.value = false
                            selectFilterMap.clear()
                            val allData =
                                stayTypeList.filter { item -> item.filterType == ServerConst.ALL }
                            if (allData.isNotEmpty()) {
                                clickStayType.value =
                                    stayTypeList.filter { item ->
                                        item.filterType == ServerConst.ALL
                                    }[0]
                            }
                        },
                        content = {
                            Icon(
                                modifier = Modifier.size(dp15),
                                painter = painterResource(id = R.drawable.ic_refresh),
                                tint = if (isChecked) Theme.colorScheme.darkGray else Theme.colorScheme.gray,
                                contentDescription = null
                            )
                        })
                    ButtonWidget(
                        modifier = Modifier.weight(0.7f),
                        containerColor = Theme.colorScheme.pureGray,
                        content = {
                            Text(
                                text = stringResource(
                                    id = R.string.str_filter_stay_count, stayCount
                                ),
                                style = MaterialTheme.typography.labelLarge,
                                color = if (stayCount == 0) Theme.colorScheme.gray else Theme.colorScheme.white
                            )
                        }, onItemClick = {
                            (context as FilterActivity).apply {
                                sendForActivity()
                                finish()
                            }
                        })
                }
            })

        if (filterUiState.value is ConnectInfo.Loading) {
            LoadingWidget()
        }
    }

    if (isShowDialog) {
        AlertDialogWidget(
            onDismiss = { isShowDialog = false },
            title = stringResource(id = R.string.str_filter_reset_dialog),
            onConfirm = {
                viewModel.checkReservation.value = false
                clickStayType.value = clickFilterStayItem.value
                viewModel.requestFilterData()
                isShowDialog = false
            },
            oneButtonText = stringResource(id = R.string.str_cancel),
            twoButtonText = stringResource(id = R.string.str_ok)
        )
    }
}