package com.example.cobacoba.navigation

sealed class Screen (val route: String){
    data object Home: Screen("home")
    data object GridSc: Screen("grid_screen")
    data object Course: Screen("course")
    data object DetailGrid: Screen("detail_grid")
    data object DetailRows: Screen("detail_row")
    data object DetailColumns: Screen("detail_column")
}