package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School

@Composable
fun SchoolItem(school: School, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = school.schoolName ?: "")
            Text(text = school.overviewParagraph ?: "")
        }
    }
}