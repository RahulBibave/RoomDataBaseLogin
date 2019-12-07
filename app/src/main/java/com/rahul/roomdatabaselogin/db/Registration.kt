package com.rahul.roomdatabaselogin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registration")
class Registration (  @ColumnInfo (name="name") var name:String,
                      @ColumnInfo (name="email") var email:String
                      ,@ColumnInfo(name="password") var password:String,
                      @ColumnInfo(name="mobile") var mobile:String) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="_id")
    var id:Int=0 ;
}