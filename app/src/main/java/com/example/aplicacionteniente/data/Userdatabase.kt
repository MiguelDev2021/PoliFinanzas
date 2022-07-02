package com.example.aplicacionteniente.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User:: class], version = 1, exportSchema = false)
abstract class Userdatabase : RoomDatabase(){
    abstract fun userDao() : UserDao


        }

