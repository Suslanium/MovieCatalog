package com.suslanium.filmus.presentation.ui.screen.mainroot.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.suslanium.filmus.presentation.ui.common.NoRippleInteractionSource
import com.suslanium.filmus.presentation.ui.navigation.bottomnavigation.BottomNavItem
import com.suslanium.filmus.presentation.ui.navigation.bottomnavigation.BottomNavItems
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.NavBarBackground
import com.suslanium.filmus.presentation.ui.theme.S11_W400

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    buttons: List<BottomNavItem> = BottomNavItems.bottomNavItems,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val interactionSource = remember { NoRippleInteractionSource() }
    NavigationBar(
        modifier = modifier,
        containerColor = NavBarBackground,
        contentColor = Gray400
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(colors = NavigationBarItemDefaults.colors(
                indicatorColor = NavBarBackground,
                selectedIconColor = Accent,
                selectedTextColor = Accent,
                unselectedIconColor = Gray400,
                unselectedTextColor = Gray400,
                disabledTextColor = Gray400,
                disabledIconColor = Gray400
            ), selected = selected, onClick = { onItemClick(item) }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(item.iconId),
                    contentDescription = null
                )
            },
                label = {
                    Text(
                        text = stringResource(id = item.name),
                        style = S11_W400,
                        textAlign = TextAlign.Center
                    )
                }, interactionSource = interactionSource)
        }
    }
}