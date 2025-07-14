package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score

@Composable
fun ScoreItem(score: Score) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = score.schoolName ?: "")
        Text(text = "Math: ${score.satMathAvgScore}")
        Text(text = "Reading: ${score.satCriticalReadingAvgScore}")
        Text(text = "Writing: ${score.satWritingAvgScore}")
    }
}