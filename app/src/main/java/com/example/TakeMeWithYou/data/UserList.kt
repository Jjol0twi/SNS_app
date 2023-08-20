package com.example.TakeMeWithYou.data

import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.model.User

class UserList {
    var userList = mutableListOf<User>()
    var appNowUser: String = ""

    companion object {  // 싱글턴
        @Volatile
        private var instance: UserList? = null
        fun getInstance(): UserList { // 다 같은 interface를 사용하게 유도
            return instance ?: synchronized(this) {
                instance ?: UserList().also { instance = it }
            }
        }
    }

    // 클래스 생성자 초기화
    init {
        userList.add(User(null, "admin", "admin", "admin"))
        userList.add(User(R.drawable.user_img_4, "hyeon123", "hyeon", "남궁현"))
        userList.add(User(R.drawable.user_img_1, "minda", "2dl78dldy^^", "이다민"))
        userList.add(User(R.drawable.user_img_3, "tlstmdcjfekt", "101801qw", "신승철"))
        userList.add(User(R.drawable.user_img_5, "ddw098", "oi0987", "이용준"))
        userList.add(User(R.drawable.user_img_2, "hyunmin", "sksqkqhdi", "홍현민"))
    }

    fun addUser(user: User) {
        userList.add(user)
    }

    fun getUserImage(id: String): Int? {
        return userList.find { it -> it.userID == id }?.userImg
    }

    fun getUserId(position: Int): String {
        return userList[position].userID
    }

    fun getUserIds(): List<String> {
        return userList.map { user -> user.userID }
    }

    fun getUserPWs(): List<String> {
        return userList.map { user -> user.userPW }
    }

    fun getUserPW(position: Int): String {
        return userList[position].userPW
    }

    fun setNowUser(id: String) {
        appNowUser = id
    }

    fun getNowUser(): String {
        return appNowUser
    }

}