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
import androidx.compose.ui.tooling.preview.Preview
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.theme.NYCSchoolsTheme

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
                    SchoolItem(school = school, navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "SchoolListScreen M3 Preview")
@Composable
fun SchoolListScreenPreview() {
    val mockNavController = androidx.navigation.compose.rememberNavController()
    NYCSchoolsTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.padding(8.dp)) {
                items(listOf(
                    School(
                        dbn = "01M450",
                        schoolName = "Brooklyn School for Music Theatre",
                        boro = "M",
                        overviewParagraph = "A school for arts and theater, specializing in music and theatre.",
                        school10thSeats = null, academicOpportunities1 = null, academicOpportunities2 = null,
                        ellPrograms = null, neighborhood = null, buildingCode = null, location = "123 Main St, Brooklyn, NY",
                        phoneNumber = "212-555-1234", faxNumber = null, schoolEmail = "info@bsmt.edu",
                        website = "https://www.bsmt.edu", subway = null, bus = null, grades2018 = null, finalGrades = null,
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
                )) { school ->
                    SchoolItem(school = school, navController = mockNavController)
                }
            }
        }
    }
}