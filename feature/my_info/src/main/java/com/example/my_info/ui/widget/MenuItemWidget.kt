package com.example.my_info.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_common.R
import com.example.ui_theme.*
import com.example.my_info.domain.model.MyMenuItem
import com.example.ui_common.components.CardWidget
import com.example.ui_common.components.SpaceBetweenRowWidget
import com.example.ui_common.components.TextWidget

@Composable
fun MenuItemWidget(
    menuItem: MyMenuItem, isOnlyText: Boolean = false, onItemClick: () -> Unit = {},
) {
    val textMediumStyle = MaterialTheme.typography.labelMedium
    SpaceBetweenRowWidget(
        modifier = Modifier
            .fillMaxWidth()
            .height(dp45)
            .clickable {
                onItemClick()
            }
            .padding(start = dp25, end = dp15, top = dp5, bottom = dp5),
        text = if (menuItem.icon == 0) menuItem.name else null,
        textStyle = textMediumStyle,
        content = {
            if (menuItem.icon != 0) {
                var secondModifier: Modifier = Modifier

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(dp20),
                        tint = Theme.colorScheme.gray,
                        contentDescription = null,
                        imageVector = ImageVector.vectorResource(id = menuItem.icon)
                    )
                    TextWidget(
                        modifier = Modifier
                            .padding(start = dp15),
                        text = menuItem.name,
                        color = Theme.colorScheme.darkGray,
                        style = textMediumStyle
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (menuItem.subText != "") {
                        secondModifier = Modifier.padding(start = dp5)
                        CardWidget(
                            innerPadding = PaddingValues(horizontal = dp10, vertical = dp7),
                            containerColor = Theme.colorScheme.pureGray,
                            cornerShape = RoundedCornerShape(dp15),
                            content = {
                                TextWidget(
                                    text = menuItem.subText,
                                    color = Theme.colorScheme.gray,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            })

                    }

                    Icon(
                        modifier = Modifier.then(secondModifier),
                        tint = Theme.colorScheme.gray,
                        contentDescription = null,
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_right)
                    )
                }

            } else {
                if (!isOnlyText) {
                    Icon(
                        tint = Theme.colorScheme.gray,
                        contentDescription = null,
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_right)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewMenuItemWidget() {
    MenuItemWidget(
        menuItem = MyMenuItem(id = 0, name = "테스트")
    )
}