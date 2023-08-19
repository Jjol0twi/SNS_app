package com.example.TakeMeWithYou

class UserList {
    var userList = mutableListOf<User>()

    // 클래스 생성자 초기화
    init {
        userList.add(User("", "", ""))
    }
}