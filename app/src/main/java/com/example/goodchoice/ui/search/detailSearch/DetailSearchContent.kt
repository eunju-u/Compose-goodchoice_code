package com.example.goodchoice.ui.search.detailSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodchoice.R
import com.example.goodchoice.api.ConnectInfo
import com.example.goodchoice.api.data.FilterItem
import com.example.goodchoice.ui.components.AlertDialogWidget
import com.example.goodchoice.ui.components.LeftImageEditTextWidget
import com.example.goodchoice.ui.components.RowTwoWidget
import com.example.goodchoice.ui.components.TopAppBarWidget
import com.example.goodchoice.ui.search.detailSearch.widget.SearchResultWidget
import com.example.goodchoice.ui.theme.*
import java.util.regex.Pattern

@Composable
fun DetailSearchContent(
    viewModel: DetailSearchViewModel,
    onFinish: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchData by viewModel.searchData.collectAsStateWithLifecycle()
    var isShowDialog by remember { mutableStateOf(false) }
    var isShowResult by remember { mutableStateOf(false) }
    var keyWord by remember { mutableStateOf("") }

    Column {
        TopAppBarWidget(
            title = stringResource(id = R.string.navi_search),
            isCloseButton = true,
            onFinish = onFinish
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.colorScheme.white)
        ) {
            item {
                LeftImageEditTextWidget(
                    modifier = Modifier.fillMaxWidth(),
                    outerPadding = PaddingValues(top = dp15, start = dp20, end = dp20),
                    containerColor = Theme.colorScheme.pureGray,
                    shape = dp10,
                    style = MaterialTheme.typography.labelMedium,
                    hint = stringResource(id = R.string.str_search_area_stay),
                    vectorImageId = R.drawable.ic_nav_search,
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
            }
            if (uiState is ConnectInfo.Available) {
                if (!isShowResult) {
                    val list = (uiState as ConnectInfo.Available).data as List<FilterItem>
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
                                .clickable {}
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