package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model

data class Score(
    val dbn: String?,
    val schoolName: String?,
    val numOfSatTestTakers: String?,
    val satCriticalReadingAvgScore: String?,
    val satMathAvgScore: String?,
    val satWritingAvgScore: String?
)

/*
https://data.cityofnewyork.us/resource/f9bf-2cp4.json
[
   {
      "dbn":"01M292",
      "school_name":"HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES",
      "num_of_sat_test_takers":"29",
      "sat_critical_reading_avg_score":"355",
      "sat_math_avg_score":"404",
      "sat_writing_avg_score":"363"
   }
]
*/