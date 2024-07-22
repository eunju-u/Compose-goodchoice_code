package com.example.goodchoice.ui.search.detailSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.R
import com.example.goodchoice.ui.components.*
import com.example.goodchoice.ui.search.data.KoreaSearchData
import com.example.goodchoice.ui.search.detailSearch.widget.SearchResultWidget
import com.example.goodchoice.ui.theme.*

@Composable
fun DetailSearchContent(
    viewModel: DetailSearchViewModel,
    onFinish: () -> Unit = {},
    onSearchClick: (item: KoreaSearchData) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchData by viewModel.searchData.collectAsStateWithLifecycle()
    var isShowDialog by remember { mutableStateOf(false) }
    var isShowResult by remember { mutableStateOf(false) }
    var keyWord by remember { mutableStateOf("") }
    // 키보드 컨트롤
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()

    // 키보드 떠있는 상태에서 스크롤 할 경우 자판기가 내려가야 하므로 아래 내용 추가.
    // lazyColumn 에 detectDragGestures , focus 등등 으로 확인 했지만 되지 않습니다.
    val isScrolling by remember {
        derivedStateOf {
            scrollState.isScrollInProgress
        }
    }
    LaunchedEffect(key1 = isScrolling) {
        if (isScrolling) {
            keyboardController?.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colorScheme.white)
    ) {
        TopAppBarWidget(
            title = stringResource(id = R.string.navi_search),
            isCloseButton = true,
            onFinish = onFinish
        )

        //검색
        LeftImageEditTextWidget(
            modifier = Modifier.fillMaxWidth(),
            outerPadding = PaddingValues(top = dp15, start = dp20, end = dp20, bottom = dp15),
            containerColor = Theme.colorScheme.pureGray,
            shape = dp10,
            style = MaterialTheme.typography.labelMedium,
            hint = stringResource(id = R.string.str_search_area_stay),
            vectorImageId = R.drawable.ic_nav_search,
            keyboardController = keyboardController,
//            isVisibleShadow = true, //TODO eunju : index == 0 인 상태에서 스크롤 내릴 경우만 ture 여야 한다.
            isShowDialog = {
                isShowDialog = it
            },
            onInputChanged = {
                keyWord = it
                viewModel.requestResultSearchData(it)
            },
            focusChanged = {
                isShowResult = it
            }
        )

        //검색 결과
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(), state = scrollState
        ) {
            if (uiState is DetailSearchConnectInfo.Available) {
                if (!isShowResult) {
                    val list = (uiState as DetailSearchConnectInfo.Available).data
                    item {
                        Text(
                            modifier = Modifier.padding(
                                top = dp15,
                                start = dp20,
                                end = dp20,
                                bottom = dp15
                            ),
                            text = stringResource(id = R.string.str_search_a_lot),
                            style = MaterialTheme.typography.labelLarge,
                            color = Theme.colorScheme.darkGray
                        )
                    }
                    itemsIndexed(items = list) { idx, item ->
                        RowTwoWidget(
                            modifier = Modifier.fillMaxWidth(),
                            innerPadding = PaddingValues(
                                top = dp10,
                                bottom = dp10,
                                start = dp20,
                                end = dp20
                            ),
                            leftModifier = Modifier.width(dp25),
                            leftText = (idx + 1).toString(),
                            rightText = item.filterTitle,
                            leftStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                            rightStyle = MaterialTheme.typography.labelLarge,
                            endPadding = dp20,
                            onItemClick = {}
                        )
                    }
                } else {
                    items(items = searchData) { item ->
                        SearchResultWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSearchClick(item) }
                                .padding(start = dp20, end = dp20, top = dp15),
                            item = item,
                            targetText = keyWord
                        )
                    }
                }
            }
        }
    }

    if (isShowDialog) {
        AlertDialogWidget(
            title = stringResource(id = R.string.str_dialog_more_two_word),
            oneButtonText = stringResource(id = R.string.str_ok),
            onDismiss = { isShowDialog = false },
            onConfirm = { isShowDialog = false })
    }
}