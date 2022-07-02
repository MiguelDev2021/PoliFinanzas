package com.example.aplicacionteniente.data;


import androidx.room.*
import com.example.aplicacionteniente.data.User
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List ;


@Dao
interface UserDao {


    //Agregar un usuario
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)


    //leer todos los usuarios
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllUser(): Flow<List<User>>

    //encontrar uno por id

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getOneUser(id: Int): Flow<User>


    //Actualizar
    @Update
    fun updateUser(user: User)



    @Delete
    fun deleteUser(user: User)



}