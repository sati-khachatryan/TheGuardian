package com.example.theguardianapp.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.theguardianapp.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}