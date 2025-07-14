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
            .padding(vertical = 0.dp, horizontal = 0.dp), // No padding on the card itself, padding is inside
        shape = RoundedCornerShape(0.dp), // No visible rounded corners in the image
        // Material 3 Card doesn't have elevation directly like M2. Use Modifier.shadow if needed for specific elevation.
        // For matching the flat look, we'll rely on the default or no explicit shadow.
        // backgroundColor is replaced by colors.surface in M3, which is the default for Card.
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp, // global_space_large
                    top = 8.dp,    // global_space_medium
                    end = 16.dp,   // global_space_large
                    bottom = 8.dp  // global_space_large (for the whole item, before divider)
                ),
            verticalAlignment = Alignment.Top
        ) {
            // PDF Icon Section
            PdfIconM3()

            Spacer(modifier = Modifier.width(16.dp)) // global_space_large

            // Text Content Section
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = score.schoolName ?: "N/A",
                    style = MaterialTheme.typography.titleMedium.copy( // M3 titleMedium is a good fit
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp, // Adjust font size to match image
                        lineHeight = 20.sp // To allow for 2 lines without truncation
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis // ellipsize="end"
                )

                Spacer(modifier = Modifier.height(8.dp)) // Space between school name and scores

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
            .height(1.dp), // global_space_xxsmall
        color = Color(0xFFE0E0E0) // buttersGray - a light gray color
    )
}

@Composable
fun PdfIconM3(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp) // icon_size_small (assuming 40dp based on image scale)
            .clip(RoundedCornerShape(4.dp)) // Small rounded corners
            .background(Color(0xFFF0F0F0)) // Light gray background
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp)) // Light gray border
            .padding(4.dp), // Padding inside the icon container
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.List, // Material Icons: a document icon
            contentDescription = "PDF Document",
            tint = Color(0xFFE53935), // A common red color for PDF
            modifier = Modifier.size(24.dp) // Icon size inside the box
        )
    }
}

@Composable
fun ScoreRowM3(label: String, score: String?) {
    Column(modifier = Modifier.fillMaxWidth()) { // Changed to Column for vertical stacking of label and score
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy( // M3 bodySmall (similar to Body3)
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp // Adjust font size
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f), // Slightly faded label
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(2.dp)) // global_space_xsmall
        Text(
            text = score ?: "N/A", // Display "N/A" if score is null
            style = MaterialTheme.typography.labelLarge.copy( // M3 labelLarge (similar to Caption2)
                fontWeight = FontWeight.Medium, // Score slightly bolder
                fontSize = 14.sp // Adjust font size
            ),
            color = Color(0xFF757575), // medGray - specific gray color
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp)) // global_space_small_medium for spacing between score rows
    }
}

// --- Previews ---
// You'll need a Material 3 theme for previews.
// If your main app theme is already M3, you can reuse it.
// Otherwise, define a simple M3 theme for preview purposes.
@Composable
fun PreviewM3Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme, // Use default M3 color scheme
        typography = MaterialTheme.typography,
        content = content
    )
}

@Preview(showBackground = true, name = "ScoreItem M3 Preview")
@Composable
fun ScoreItemM3Preview() {
    PreviewM3Theme { // Use the custom preview theme
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

/*
@Composable
fun ScoreItem(score: Score) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = score.schoolName ?: "")
        Text(text = "Math: ${score.satMathAvgScore}")
        Text(text = "Reading: ${score.satCriticalReadingAvgScore}")
        Text(text = "Writing: ${score.satWritingAvgScore}")
    }
}
*/