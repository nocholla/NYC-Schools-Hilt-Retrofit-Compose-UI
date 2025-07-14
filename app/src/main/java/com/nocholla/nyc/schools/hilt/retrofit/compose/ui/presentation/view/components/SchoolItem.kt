package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.view.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.R
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.utils.IntentUtil
import androidx.navigation.NavController
import androidx.core.net.toUri

@Composable
fun SchoolItem(school: School, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                school.dbn?.let { dbn ->
                    navController.navigate("schoolDetail/$dbn")
                }
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 24.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_new_york_schools),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = school.schoolName ?: "N/A",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color(0xFF757575),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = school.overviewParagraph ?: "No overview available.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp
                        ),
                        color = Color(0xFF757575),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SchoolActionButtons(
                schoolEmail = school.schoolEmail,
                phoneNumber = school.phoneNumber,
                navController = navController
            )
        }
    }
}

@Composable
fun SchoolActionButtons(schoolEmail: String?, phoneNumber: String?, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        schoolEmail?.takeIf { it.isNotBlank() }?.let {
            IconButton(onClick = {
                IntentUtil.openIntentWithUriAndAction(
                    navController.context,
                    "mailto:$it".toUri(),
                    Intent.ACTION_SENDTO
                )
            }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email School",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        phoneNumber?.takeIf { it.isNotBlank() }?.let {
            IconButton(onClick = {
                IntentUtil.openIntentWithUriAndAction(
                    navController.context,
                    "tel:$it".toUri(),
                    Intent.ACTION_DIAL
                )
            }) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "Call School",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        phoneNumber?.takeIf { it.isNotBlank() }?.let {
            IconButton(onClick = {
                IntentUtil.openIntentWithUriAndAction(
                    navController.context,
                    "sms:$it".toUri(),
                    Intent.ACTION_SENDTO
                )
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Text School",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun PreviewMaterial3Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}

@Preview(showBackground = true, name = "SchoolItem M3 Preview")
@Composable
fun SchoolItemM3Preview() {
    val mockNavController = rememberNavController()
    PreviewMaterial3Theme {
        Column {
            SchoolItem(
                school = School(
                    dbn = "01M450",
                    schoolName = "Brooklyn School for Music Theatre",
                    boro = "M",
                    overviewParagraph = "Brooklyn School for Music Theatre (BSMT) uses our academic program to accommodate the intellectual, social, and emotional needs of creative high school students. Our vision is to provide a model professional environment where respect is mutual, ideas are shared, and learning is not limited to the classroom. We prepare students for higher education through our rigorous academic program while simultaneously allowing them to develop professional careers in the music and theatre industries.",
                    school10thSeats = null, academicOpportunities1 = null, academicOpportunities2 = null,
                    ellPrograms = null, neighborhood = null, buildingCode = null, location = null,
                    phoneNumber = "212-555-1234", faxNumber = null, schoolEmail = "info@bsmt.edu",
                    website = null, subway = null, bus = null, grades2018 = null, finalGrades = null,
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
                ),
                navController = mockNavController
            )
        }
    }
}