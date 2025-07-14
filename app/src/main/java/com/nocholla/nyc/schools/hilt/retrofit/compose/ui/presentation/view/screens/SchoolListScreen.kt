package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components.SchoolItem
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel.SchoolViewModel

@Composable
fun SchoolListScreen(navController: NavController) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            uiState.error != null -> Text(
                text = "Error: ${uiState.error}",
                modifier = Modifier.align(Alignment.Center)
            )
            else -> LazyColumn(modifier = Modifier.padding(8.dp)) {
                items(uiState.schools) { school ->
                    SchoolItem(school) {
                        navController.navigate("schoolDetail/${school.dbn}")
                    }
                }
            }
        }
    }
}