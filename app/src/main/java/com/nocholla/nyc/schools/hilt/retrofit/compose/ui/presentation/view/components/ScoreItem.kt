package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card // Material 3 Card
import androidx.compose.material3.Divider // Material 3 Divider
import androidx.compose.material3.Icon // Material 3 Icon
import androidx.compose.material3.MaterialTheme // Material 3 MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score

@Composable
fun ScoreItem(score: Score) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 0.dp),
        shape = RoundedCornerShape(0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.Top
        ) {
            // PDF Icon Section
            PdfIconM3()

            Spacer(modifier = Modifier.width(16.dp))

            // Text Content Section
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = score.schoolName ?: "N/A",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                ScoreRowM3(label = "Math", score = score.satMathAvgScore)
                ScoreRowM3(label = "Reading", score = score.satCriticalReadingAvgScore)
                ScoreRowM3(label = "Writing", score = score.satWritingAvgScore)
            }
        }
    }
    // Divider at the bottom of each item, similar to the XML's View
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color(0xFFE0E0E0)
    )
}

@Composable
fun PdfIconM3(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFF0F0F0))
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = "PDF Document",
            tint = Color(0xFFE53935),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ScoreRowM3(label: String, score: String?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = score ?: "N/A",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            color = Color(0xFF757575),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp))
    }
}

// --- Previews ---
@Composable
fun PreviewM3Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}

@Preview(showBackground = true, name = "ScoreItem M3 Preview")
@Composable
fun ScoreItemM3Preview() {
    PreviewM3Theme {
        Column {
            ScoreItem(
                score = Score(
                    dbn = "01M292",
                    schoolName = "HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES",
                    numOfSatTestTakers = "29",
                    satMathAvgScore = "404",
                    satCriticalReadingAvgScore = "355",
                    satWritingAvgScore = "363"
                )
            )
            ScoreItem(
                score = Score(
                    dbn = "01M539",
                    schoolName = "UNIVERSITY NEIGHBORHOOD HIGH SCHOOL",
                    numOfSatTestTakers = "100",
                    satMathAvgScore = "423",
                    satCriticalReadingAvgScore = "383",
                    satWritingAvgScore = "366"
                )
            )
            ScoreItem(
                score = Score(
                    dbn = "01M448",
                    schoolName = "EAST SIDE COMMUNITY SCHOOL",
                    numOfSatTestTakers = "50",
                    satMathAvgScore = "402",
                    satCriticalReadingAvgScore = "377",
                    satWritingAvgScore = "370"
                )
            )
            ScoreItem(
                score = Score(
                    dbn = "01M450",
                    schoolName = "FORSYTH SATELLITE ACADEMY",
                    numOfSatTestTakers = "35",
                    satMathAvgScore = "401",
                    satCriticalReadingAvgScore = "414",
                    satWritingAvgScore = "359"
                )
            )
            ScoreItem(
                score = Score(
                    dbn = "01M696",
                    schoolName = "MARTA VALLE HIGH SCHOOL",
                    numOfSatTestTakers = "20",
                    satMathAvgScore = null, // Example with null score
                    satCriticalReadingAvgScore = "320",
                    satWritingAvgScore = "310"
                )
            )
        }
    }
}