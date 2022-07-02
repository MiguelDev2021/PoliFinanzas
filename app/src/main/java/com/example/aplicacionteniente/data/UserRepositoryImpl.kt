package com.example.aplicacionteniente.data

  class UserRepositoryImpl(
    private  val userDao : UserDao
): UserRepository{

    override suspend fun getUsersFromRoom() = userDao.readAllUser()

    override suspend fun getUserFromRoom(id : Int) = userDao.getOneUser(id)

    override suspend fun addUserFromRoom(user : User) = userDao.addUser(user)

    override suspend fun updateUserFromRoom(user : User) = userDao.updateUser(user)

    override suspend fun deleteUserFromRoom(user : User) = userDao.deleteUser(user)



}