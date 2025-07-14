package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components.ScoreItem
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel.SchoolViewModel
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(navController: NavController, dbn: String?) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Scores") },
            actions = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            uiState.error != null -> Text(
                text = "Error: ${uiState.error}",
                modifier = Modifier.align(Alignment.Center)
            )
            else -> {
                LazyColumn(modifier = Modifier.padding(top = 64.dp)) {
                    items(uiState.scores.filter { it.dbn == dbn }) { score ->
                        ScoreItem(score)
                    }
                }
            }
        }
    }
}