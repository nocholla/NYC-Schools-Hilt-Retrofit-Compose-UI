package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components.ScoreItem
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel.SchoolViewModel

@Composable
fun ScoresScreen(navController: NavController, dbn: String?) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            uiState.error != null -> Text(
                text = "Error: ${uiState.error}",
                modifier = Modifier.align(Alignment.Center)
            )
            else -> {
                LazyColumn {
                    items(uiState.scores.filter { it.dbn == dbn }) { score ->
                        ScoreItem(score)
                    }
                }
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            }
        }
    }
}