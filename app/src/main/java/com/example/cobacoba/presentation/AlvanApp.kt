package com.example.cobacoba.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Topic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cobacoba.navigation.NavigationItem
import com.example.cobacoba.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlvanApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(contentPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier, navController)
            }

            composable(Screen.GridSc.route) {
                GridScreen(modifier, navController)
            }

            composable(Screen.Course.route) {
                AboutScreen(modifier)
            }

            composable(
                Screen.DetailGrid.route + "/{seranggaId}",
                arguments = listOf(navArgument("seranggaId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailGrid(
                    navController = navController,
                    seranggaId = navBackStackEntry.arguments?.getInt("seranggaId")
                )
            }

            composable(
                Screen.DetailRows.route + "/{hewanId}",
                arguments = listOf(navArgument("hewanId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailRowScreen(
                    navController = navController,
                    hewanId = navBackStackEntry.arguments?.getInt("hewanId")
                )
            }

            composable(
                Screen.DetailColumns.route + "/{sayapId}",
                arguments = listOf(navArgument("sayapId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailColumnScreen(
                    navController = navController,
                    sayapId = navBackStackEntry.arguments?.getInt("sayapId")
                )
            }


        }
    }
}



@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Screen1",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Screen2",
                icon = Icons.Default.BrowseGallery,
                screen = Screen.GridSc
            ),
            NavigationItem(
                title = "Screen3",
                icon = Icons.Default.Topic,
                screen = Screen.Course
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )
        }
    }
}

@Preview
@Composable
private fun AlvanAppPreview() {
    AlvanApp()
}