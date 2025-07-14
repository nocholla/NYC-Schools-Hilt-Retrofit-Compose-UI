package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.R
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel.SchoolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDetailScreen(navController: NavController, dbn: String?) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val school = viewModel.getSchoolByDbn(dbn)

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.placeholder_new_york_schools), // Replace with your actual image resource
                    contentDescription = "NYC Department of Education Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 16.dp, bottom = 16.dp),
                    alignment = Alignment.Center
                )
                SchoolActionButtonsRow(
                    onFeedbackClick = { /* Handle feedback click */ },
                    onSocialClick = { /* Handle social click */ },
                    onWebsiteClick = { school?.website?.let { /* Handle website intent */ } }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("scores/$dbn") },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(Icons.Filled.Send, contentDescription = "View Scores", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // School Name
            Text(
                text = "School Name",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = school?.schoolName ?: "School Not Found",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Overview
            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = school?.overviewParagraph ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Location
            Text(
                text = "Location",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = school?.location ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Phone
            Text(
                text = "Phone",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = school?.phoneNumber ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Email
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = school?.schoolEmail ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(64.dp)) // To ensure FAB doesn't cover content
        }
    }
}

@Composable
fun SchoolActionButtonsRow(
    onFeedbackClick: () -> Unit,
    onSocialClick: () -> Unit,
    onWebsiteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SchoolActionButton(
            icon = Icons.Filled.Share,
            label = "Feedback",
            onClick = onFeedbackClick
        )
        SchoolActionButton(
            icon = Icons.Filled.Person,
            label = "Social",
            onClick = onSocialClick
        )
        SchoolActionButton(
            icon = Icons.Filled.Send,
            label = "Website",
            onClick = onWebsiteClick
        )
    }
}

@Composable
fun SchoolActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .size(56.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primary // Use primary color for the button background
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(32.dp).padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true, name = "SchoolDetailScreen Preview")
@Composable
fun PreviewSchoolDetailScreen() {
    // Create a mock NavController for the preview
    val mockNavController = rememberNavController()
    // Create a mock School object for the preview
    val mockSchool = School(
        dbn = "02M260",
        schoolName = "Clinton School Writers and Artists, M.S. 260",
        boro = "M",
        overviewParagraph = "Brooklyn School for Music Theatre (BSMT) uses our academic program to accommodate the intellectual, social, and emotional needs of creative high school students. Our vision is to provide a model professional environment where respect is mutual, ideas are shared, and learning is not limited to the classroom. We prepare students for higher education through our rigorous academic program while simultaneously allowing them to develop professional careers in the music and theatre industries.",
        location = "2865 West 19th Street, Brooklyn, NY 11224 (40.576976, -73.985413)",
        phoneNumber = "718-542-0740",
        schoolEmail = "sburns@schools.nyc.gov",
        website = "http://www.brooklynschoolformusictheatre.org",
        school10thSeats = null, academicOpportunities1 = null, academicOpportunities2 = null,
        ellPrograms = null, neighborhood = null, buildingCode = null, faxNumber = null,
        subway = null, bus = null, grades2018 = null, finalGrades = null,
        totalStudents = null, extracurricularActivities = null, schoolSports = null,
        attendanceRate = null, pctStuEnoughVariety = null, pctStuSafe = null,
        schoolAccessibilityDescription = null, directions1 = null, requirement1 = null,
        requirement2 = null, requirement3 = null, requirement4 = null, requirement5 = null,
        offerRate1 = null, program1 = null, code1 = null, interest1 = null, method1 = null,
        seats9ge1 = null, grade9gefilledflag1 = null, grade9geapplicants1 = null,
        seats9swd1 = null, grade9swdfilledflag1 = null, grade9swdapplicants1 = null,
        seats101 = null, admissionspriority11 = null, admissionspriority21 = null,
        admissionspriority31 = null, grade9geapplicantsperseat1 = null,
        grade9swdapplicantsperseat1 = null, primaryAddressLine1 = null, city = null,
        zip = null, stateCode = null, latitude = null, longitude = null,
        communityBoard = null, councilDistrict = null, censusTract = null, bin = null,
        bbl = null, nta = null, borough = null
    )

    PreviewMaterial3Theme {
        // Since hiltViewModel() cannot be directly used in @Preview,
        // we'll mock the data by passing it directly or by creating a mock ViewModel if needed.
        // For simplicity in preview, let's just display the static content as it would appear.
        Scaffold(
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { /* no-op for preview */ }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.placeholder_new_york_schools),
                        contentDescription = "NYC Department of Education Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 16.dp, bottom = 16.dp),
                        alignment = Alignment.Center
                    )
                    SchoolActionButtonsRow(
                        onFeedbackClick = { /* no-op for preview */ },
                        onSocialClick = { /* no-op for preview */ },
                        onWebsiteClick = { /* no-op for preview */ }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* no-op for preview */ },
                    containerColor = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(Icons.Filled.Send, contentDescription = "View Scores", tint = Color.White)
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "School Name",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = mockSchool.schoolName ?: "School Not Found",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = mockSchool.overviewParagraph ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Location",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = mockSchool.location ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Phone",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = mockSchool.phoneNumber ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = mockSchool.schoolEmail ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun PreviewMaterial3Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(), // Use a default light color scheme for Material 3
        typography = MaterialTheme.typography,
        content = content
    )
}