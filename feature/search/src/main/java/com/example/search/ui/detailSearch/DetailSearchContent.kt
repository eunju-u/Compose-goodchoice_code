package com.example.search.ui.detailSearch

import android.annotation.SuppressLint
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
import com.example.common.DialogType
import com.example.common.intent.CommonIntent
import com.example.ui_common.R
import com.example.ui_theme.*
import com.example.domain.model.KoreaSearchData
import com.example.search.ui.detailSearch.widget.SearchResultWidget
import com.example.search.ui.state.DetailSearchUiState
import com.example.ui_common.components.AlertDialogWidget
import com.example.ui_common.components.LeftImageEditTextWidget
import com.example.ui_common.components.LoadingWidget
import com.example.ui_common.components.RowTwoWidget
import com.example.ui_common.components.TopAppBarWidget

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailSearchContent(
    viewModel: DetailSearchViewModel,
    onFinish: () -> Unit = {},
    onSearchClick: (item: KoreaSearchData) -> Unit = {}
) {
    val detailSearchUiState by viewModel.detailSearchUiState.collectAsState()
    val searchData by viewModel.searchData.collectAsStateWithLifecycle()
    var isShowResult by remember { mutableStateOf(false) }
    val keyWord by viewModel.keyWord.collectAsStateWithLifecycle()

    val isShowDialog = viewModel.isShowDialog.collectAsStateWithLifecycle()
    val dialogType = viewModel.dialogType.collectAsStateWithLifecycle()

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
                viewModel.dialogType.value = DialogType.MORE_TWO_WORD
                viewModel.isShowDialog.value = it
            },
            onInputChanged = {
                viewModel.onWordChanged(it)
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
            if (detailSearchUiState is DetailSearchUiState.Success) {
                if (!isShowResult) {
                    val list = (detailSearchUiState as DetailSearchUiState.Success).list
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
                            rightText = item.searchTitle,
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

    if (isShowDialog.value) {
        when (dialogType.value) {
            DialogType.MORE_TWO_WORD -> {
                AlertDialogWidget(
                    title = stringResource(id = R.string.str_dialog_more_two_word),
                    oneButtonText = stringResource(id = R.string.str_ok),
                    onDismiss = { viewModel.isShowDialog.value = false },
                    onConfirm = { viewModel.isShowDialog.value = false })
            }

            DialogType.NETWORK_ERROR -> {
                AlertDialogWidget(
                    onDismiss = { },
                    title = stringResource(id = R.string.str_dialog_network_not_connect),
                    onConfirm = {
                        viewModel.isShowDialog.value = false
                        viewModel.sendIntent(CommonIntent.Retry("reload"))
                    },
                    oneButtonText = stringResource(id = R.string.str_ok),
                )
            }

            else -> {}
        }
    }



    when (detailSearchUiState) {
        is DetailSearchUiState.Loading -> LoadingWidget()
        is DetailSearchUiState.Error -> {
            viewModel.dialogType.value = DialogType.NETWORK_ERROR
            viewModel.isShowDialog.value = true
        }
        else -> {}
    }
}