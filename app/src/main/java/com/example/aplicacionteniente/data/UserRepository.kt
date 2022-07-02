package com.example.aplicacionteniente.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List

interface UserRepository
{

    suspend fun getUsersFromRoom(): Flow<List<User>>
    suspend fun getUserFromRoom(id: Int): Flow<User>
    suspend fun addUserFromRoom(user: User)
    suspend fun updateUserFromRoom(user: User)
    suspend fun deleteUserFromRoom(user: User)
}