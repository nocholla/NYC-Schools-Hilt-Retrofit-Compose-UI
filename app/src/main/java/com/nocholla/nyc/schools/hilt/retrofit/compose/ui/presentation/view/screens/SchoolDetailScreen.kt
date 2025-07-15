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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel.SchoolViewModel
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.utils.IntentUtil
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDetailScreen(navController: NavController, dbn: String?) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val school = dbn?.let { viewModel.getSchoolByDbn(it) } ?: uiState.schools.firstOrNull()

    // Trigger data fetch for the specific DBN if not already loaded
    if (dbn != null && school == null) {
        viewModel.fetchSchoolDetails(dbn)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("School Details") },
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
            school?.let { s ->
                if (s.schoolEmail != null || s.phoneNumber != null) {
                    SchoolActionButtonsRow(
                        onFeedbackClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = "mailto:${s.schoolEmail}".toUri()
                                putExtra(Intent.EXTRA_SUBJECT, "Parent Feedback!")
                                putExtra(Intent.EXTRA_TEXT, "Hello, NYC City Schools")
                            }
                            IntentUtil.openIntentWithUriAndAction(
                                navController.context, intent.data!!, Intent.ACTION_SENDTO
                            )
                        },
                        onSocialClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Check out the NYC Schools")
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "NYC Schools\n\nGet more info " +
                                            "about ${s.schoolName}\n\nThank you!"
                                )
                            }
                            IntentUtil.openIntentChooser(
                                navController.context, shareIntent, "Share Via"
                            )
                        },
                        onWebsiteClick = { s.website?.let {
                            IntentUtil.openUrlIntent(navController.context, it) }
                        }
                    )
                }
                if (s.schoolName != null) {
                    Text(
                        text = "School Name",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = s.schoolName,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (s.overviewParagraph != null) {
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = s.overviewParagraph,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (s.location != null) {
                    Text(
                        text = "Location",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = s.location,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (s.phoneNumber != null) {
                    Text(
                        text = "Phone",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = s.phoneNumber,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (s.schoolEmail != null) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = s.schoolEmail,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (s.website != null) {
                    Text(
                        text = "Website",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = s.website,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(64.dp))
            } ?: run {
                Text(
                    text = "School Not Found",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
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
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SchoolActionButton(
            icon = Icons.Filled.Share,
            label = "Feedback",
            onClick = onFeedbackClick
        )
        Spacer(modifier = Modifier.width(30.dp))
        SchoolActionButton(
            icon = Icons.Filled.Person,
            label = "Social",
            onClick = onSocialClick
        )
        Spacer(modifier = Modifier.width(30.dp))
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
            color = MaterialTheme.colorScheme.primary
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "SchoolDetailScreen Preview")
@Composable
fun PreviewSchoolDetailScreen() {
    val mockNavController = rememberNavController()
    val mockSchool = School(
        dbn = "02M260",
        schoolName = "Clinton School Writers and Artists, M.S. 260",
        boro = "M",
        overviewParagraph = "Brooklyn School for Music Theatre (BSMT) uses our academic " +
                "program to accommodate the intellectual, social, and emotional needs of " +
                "creative high school students. Our vision is to provide a model professional" +
                " environment where respect is mutual, ideas are shared, and learning is not " +
                "limited to the classroom. We prepare students for higher education through " +
                "our rigorous academic program while simultaneously allowing them to develop " +
                "professional careers in the music and theatre industries.",
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("School Details") },
                    actions = {
                        IconButton(onClick = { /* no-op for preview */ }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
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
                if (mockSchool.schoolEmail != null || mockSchool.phoneNumber != null) {
                    SchoolActionButtonsRow(
                        onFeedbackClick = { /* no-op for preview */ },
                        onSocialClick = { /* no-op for preview */ },
                        onWebsiteClick = { mockSchool.website?.let {
                            IntentUtil.openUrlIntent(mockNavController.context, it) }
                        }
                    )
                }
                if (mockSchool.schoolName != null) {
                    Text(
                        text = "School Name",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = mockSchool.schoolName,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (mockSchool.overviewParagraph != null) {
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = mockSchool.overviewParagraph,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (mockSchool.location != null) {
                    Text(
                        text = "Location",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = mockSchool.location,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (mockSchool.phoneNumber != null) {
                    Text(
                        text = "Phone",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = mockSchool.phoneNumber,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (mockSchool.schoolEmail != null) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = mockSchool.schoolEmail,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (mockSchool.website != null) {
                    Text(
                        text = "Website",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = mockSchool.website,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun PreviewMaterial3Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = MaterialTheme.typography,
        content = content
    )
}