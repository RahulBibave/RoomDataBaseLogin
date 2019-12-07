package com.rahul.roomdatabaselogin.db

import androidx.room.*

@Dao
interface RegistrationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(registration: Registration)

    @Query("select * from registration where email=:email and password=:pass")
    fun checkLogin(email:String,pass:String):List<Registration>

   @Query("select * from registration where _id=:id")
    fun fetchUser(id:Int): Registration;


  /*  @Delete
    fun deleteUser(registration: Registration):Int*/
}