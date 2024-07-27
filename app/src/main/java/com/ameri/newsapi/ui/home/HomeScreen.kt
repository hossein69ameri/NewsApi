package com.ameri.newsapi.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ameri.newsapi.util.theme.AntiFlashWhite
import com.ameri.newsapi.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController,viewModel: HomeViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .background(AntiFlashWhite)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {
            viewModel.getTopHeadlinesData()
        }){
            Text(text = "apicall")
        }
    }
}