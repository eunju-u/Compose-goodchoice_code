package com.example.ui.calendar.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.ui_common.R
import com.example.ui_common.utils.ConvertUtil.convertMonth
import com.example.ui_theme.Theme

@Composable
internal fun MonthHeader(modifier: Modifier = Modifier, month: String, year: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.str_year_month, year, convertMonth(month)),
        color = Theme.colorScheme.darkGray,
        style = MaterialTheme.typography.labelLarge
    )
}
