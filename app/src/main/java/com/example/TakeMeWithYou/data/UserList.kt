package com.example.TakeMeWithYou.data

import com.example.TakeMeWithYou.model.User

class UserList {
    var userList = mutableListOf<User>()

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
        userList.add(User("admin", "admin", "admin"))
    }

    fun addUser(user: User) {
        userList.add(user)
    }

    fun getUserId(position: Int): String {
        return userList[position].userID
    }

    fun getUserIds(): List<String> {
        return userList.map { user -> user.userID }
    }

    fun getUserPWs(): List<String> {
        return userList.map { user -> user.userID }
    }

    fun getUserPW(position: Int): String {
        return userList[position].userPW
    }


}