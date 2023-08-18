package com.example.TakeMeWithYou

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_VERSION = 1
        const val DB_NAME = "DBName"

        val TABLE_NAME = "USER"
        val UID = "UID"         // SQLite를 사용하기 위해 필수적으로 필요한 Column..?
        val KEY_ID = "id"
        val KEY_USERNAME = "userName"
        val KEY_PW = "pw"
    }

    // 데이터베이스 처음 생성 시 호출, 사용자 테이블 생성
    override fun onCreate(db: SQLiteDatabase?) {
        var sql : String = "CREATE TABLE IF NOT EXISTS " +
                "$TABLE_NAME ($UID " +
                "$KEY_ID, $KEY_PW text, $KEY_USERNAME text);"
        db?.execSQL(sql)
    }

    // 데이터베이스 업그레이드 시 호출 -> 기존 데이터 삭제 후 재생성
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "$TABLE_NAME"!!

        db?.execSQL(sql)
        onCreate(db)
    }
    //  사용자 정보 조회
    /*val allUsers : List<User>
        get() {
            val users = ArrayList<User>()
            val selectQueryHandler = "$TABLE_NAME"!!
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler, null)
            if(cursor.moveToFirst()) {
                do {
                    val user = User()
                }
            }
        }
*/

    // 사용자 정보 추가


}