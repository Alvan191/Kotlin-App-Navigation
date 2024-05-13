package com.example.cobacoba.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cobacoba.data.Bersayap
import com.example.cobacoba.data.DataSaya
import com.example.cobacoba.data.Hewan
import com.example.cobacoba.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
    hewann:List<Hewan> = DataSaya.datHorizonntal,
    bersayap:List<Bersayap> = DataSaya.datVertikal
) {
    Column (modifier.fillMaxSize()){
        TopAppBar(
            modifier = Modifier,
            title = {
                Text(text = "Home", fontWeight = FontWeight.Bold)
            }
        )
       LazyRow (contentPadding = PaddingValues(10.dp)){
           items(hewann, key = { it.id }){hewan ->
               RowItem(
                   hewan = hewan,
                   modifier = Modifier,
                   onClick = { navController.navigate(Screen.DetailRows.route + "/${hewan.id}") }
               )
           }
       }
        LazyColumn (contentPadding = PaddingValues(10.dp)){
            items(bersayap, key = { it.id }){sayap ->
                ColumnItem(
                    sayap = sayap,
                    modifier = Modifier,
                    onClick = { navController.navigate(Screen.DetailColumns.route + "/${sayap.id}") }
                )
            }
        }
    }
}

@Composable
fun RowItem(hewan: Hewan, modifier: Modifier, onClick: () -> Unit) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(100.dp)
            .aspectRatio(1.5f),
    colors = CardDefaults.cardColors(
        containerColor = Color.White
    ),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
            Box(
                modifier
                    .padding(10.dp)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ){
                Column (){
                    Image(
                        painter = painterResource(id = hewan.photo),
                        contentDescription = hewan.name,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .size(width = 130.dp, height = 85.dp)
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
    }
}

@Composable
fun ColumnItem(sayap: Bersayap, modifier: Modifier, onClick: () -> Unit) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Box(
            modifier
                .padding(10.dp)
        ){
            Row (){
                Image(
                    painter = painterResource(id = sayap.photo),
                    contentDescription = sayap.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .size(width = 150.dp, height = 100.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = sayap.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier, navController = rememberNavController())
}

