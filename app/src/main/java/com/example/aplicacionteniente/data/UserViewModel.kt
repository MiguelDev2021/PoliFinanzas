package com.example.aplicacionteniente.data

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.List
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {
    var users by mutableStateOf(emptyList<User>())
    var user by mutableStateOf(User(0, "", ""))
    var openDialog by mutableStateOf(false)

    fun readAllUsers() {
        viewModelScope.launch {
            repo.getUsersFromRoom().collect { response ->
                 users = response
            }
        }
    }

    fun getOneUser(id: Int) {
        viewModelScope.launch {
            repo.getUserFromRoom(id).collect { response ->
                user = response
            }
        }
    }

    fun updateRango(Rango: String) {
        user = user.copy(Rango = Rango)
    }



    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUserFromRoom(user)
        }
    }

    fun updateuser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUserFromRoom(user)
        }
    }

    fun deleteBook(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUserFromRoom(user)
        }
    }
}