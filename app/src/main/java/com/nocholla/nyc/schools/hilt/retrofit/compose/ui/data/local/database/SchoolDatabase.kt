package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao.SchoolDao
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao.ScoreDao
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model.SchoolEntity
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model.ScoreEntity

@Database(entities = [SchoolEntity::class, ScoreEntity::class], version = 1, exportSchema = false)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun scoreDao(): ScoreDao
}