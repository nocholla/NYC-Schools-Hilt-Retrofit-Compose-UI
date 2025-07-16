package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.repository

import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api.SchoolsApiService
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao.SchoolDao
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao.ScoreDao
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model.SchoolEntity
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model.ScoreEntity
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolsApiService: SchoolsApiService,
    private val schoolDao: SchoolDao,
    private val scoreDao: ScoreDao
) : SchoolRepository {

    override suspend fun getSchools(): Flow<List<School>> = flow {
        // Check local storage first
        val localSchools = schoolDao.getAllSchools().map { it.toDomain() }
        if (localSchools.isNotEmpty()) {
            emit(localSchools)
        }

        // Fetch from network and sync
        try {
            val schoolDtos = schoolsApiService.getSchools()
            val schoolEntities = schoolDtos.map { it.toDomain() }.map { it.toEntity() }
            schoolDao.deleteAllSchools()
            schoolDao.insertAllSchools(schoolEntities)
            emit(schoolEntities.map { it.toDomain() })
        } catch (e: Exception) {
            // Fall back to local data if network fails
            if (localSchools.isEmpty()) throw e
            emit(localSchools)
        }
    }

    override suspend fun getScores(): Flow<List<Score>> = flow {
        // Check local storage first
        val localScores = scoreDao.getAllScores().map { it.toDomain() }
        if (localScores.isNotEmpty()) {
            emit(localScores)
        }

        // Fetch from network and sync
        try {
            val scoreDtos = schoolsApiService.getScores()
            val scoreEntities = scoreDtos.map { it.toDomain() }.map { it.toEntity() }
            scoreDao.deleteAllScores()
            scoreDao.insertAllScores(scoreEntities)
            emit(scoreEntities.map { it.toDomain() })
        } catch (e: Exception) {
            // Fall back to local data if network fails
            if (localScores.isEmpty()) throw e
            emit(localScores)
        }
    }

    private fun School.toEntity() = SchoolEntity(
        dbn = dbn ?: "",
        schoolName = schoolName,
        boro = boro,
        overviewParagraph = overviewParagraph,
        school10thSeats = school10thSeats,
        academicOpportunities1 = academicOpportunities1,
        academicOpportunities2 = academicOpportunities2,
        ellPrograms = ellPrograms,
        neighborhood = neighborhood,
        buildingCode = buildingCode,
        location = location,
        phoneNumber = phoneNumber,
        faxNumber = faxNumber,
        schoolEmail = schoolEmail,
        website = website,
        subway = subway,
        bus = bus,
        grades2018 = grades2018,
        finalGrades = finalGrades,
        totalStudents = totalStudents,
        extracurricularActivities = extracurricularActivities,
        schoolSports = schoolSports,
        attendanceRate = attendanceRate,
        pctStuEnoughVariety = pctStuEnoughVariety,
        pctStuSafe = pctStuSafe,
        schoolAccessibilityDescription = schoolAccessibilityDescription,
        directions1 = directions1,
        requirement1 = requirement1,
        requirement2 = requirement2,
        requirement3 = requirement3,
        requirement4 = requirement4,
        requirement5 = requirement5,
        offerRate1 = offerRate1,
        program1 = program1,
        code1 = code1,
        interest1 = interest1,
        method1 = method1,
        seats9ge1 = seats9ge1,
        grade9gefilledflag1 = grade9gefilledflag1,
        grade9geapplicants1 = grade9geapplicants1,
        seats9swd1 = seats9swd1,
        grade9swdfilledflag1 = grade9swdfilledflag1,
        grade9swdapplicants1 = grade9swdapplicants1,
        seats101 = seats101,
        admissionspriority11 = admissionspriority11,
        admissionspriority21 = admissionspriority21,
        admissionspriority31 = admissionspriority31,
        grade9geapplicantsperseat1 = grade9geapplicantsperseat1,
        grade9swdapplicantsperseat1 = grade9swdapplicantsperseat1,
        primaryAddressLine1 = primaryAddressLine1,
        city = city,
        zip = zip,
        stateCode = stateCode,
        latitude = latitude,
        longitude = longitude,
        communityBoard = communityBoard,
        councilDistrict = councilDistrict,
        censusTract = censusTract,
        bin = bin,
        bbl = bbl,
        nta = nta,
        borough = borough
    )

    private fun Score.toEntity() = ScoreEntity(
        dbn = dbn ?: "",
        schoolName = schoolName,
        numOfSatTestTakers = numOfSatTestTakers,
        satCriticalReadingAvgScore = satCriticalReadingAvgScore,
        satMathAvgScore = satMathAvgScore,
        satWritingAvgScore = satWritingAvgScore
    )
}