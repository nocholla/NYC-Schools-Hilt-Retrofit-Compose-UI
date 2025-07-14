package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel.SchoolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDetailScreen(navController: NavController, dbn: String?) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val school = viewModel.getSchoolByDbn(dbn)

    Scaffold(
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        },
        floatingActionButton = {
            IconButton(onClick = { navController.navigate("scores/$dbn") }) {
                Icon(Icons.Filled.Send, contentDescription = "View Scores")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = school?.schoolName ?: "School Not Found")
            Text(text = school?.overviewParagraph ?: "")
            Text(text = school?.location ?: "")
            Text(text = "Phone: ${school?.phoneNumber ?: ""}")
            Text(text = "Email: ${school?.schoolEmail ?: ""}")
        }
    }
}