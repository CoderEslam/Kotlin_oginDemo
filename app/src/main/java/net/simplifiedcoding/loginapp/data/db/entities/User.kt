package net.simplifiedcoding.loginapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var userId: String? = null,
    var userName: String? = null

){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}