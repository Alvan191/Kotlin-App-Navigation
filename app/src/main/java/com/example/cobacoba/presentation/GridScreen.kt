package com.example.cobacoba.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cobacoba.data.DataSaya
import com.example.cobacoba.data.Serangga
import com.example.cobacoba.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridScreen(
    modifier: Modifier,
    navController: NavController,
    seranggaa:List<Serangga> = DataSaya.datGridd,
    ) {
    Column { TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = "Lazy Grid", fontWeight = FontWeight.Bold)
        }
    )
        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp), columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize()
        ) {
            items(seranggaa, key = { it.id }) { serangga ->
                GridScItem(
                    serangga = serangga,
                    onClick = { navController.navigate(Screen.DetailGrid.route + "/${serangga.id}") } // Navigate to DetailScreen with item id
                )
            }
        }
    }
}

@Composable
fun GridScItem(
    serangga: Serangga,
    onClick: () -> Unit
) { Card (
    modifier = Modifier
        .padding(8.dp)
        .clickable(onClick = onClick)
        .fillMaxWidth()
        .wrapContentHeight()
        .aspectRatio(1f),
    colors = CardDefaults.cardColors(
        containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(10.dp)
) { Box (
    modifier = Modifier
        .padding(8.dp)
        .clickable(onClick = onClick)
        .height(120.dp),
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(id = serangga.photo),
            contentDescription = serangga.name,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .size(width = 150.dp, height = 100.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
//        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = serangga.name,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}
}
}

@Preview
@Composable
private fun GridScreenPreview() {
    GridScreen(modifier = Modifier, navController = rememberNavController())
}

