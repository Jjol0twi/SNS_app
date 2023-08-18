package com.example.TakeMeWithYou

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.database.getStringOrNull

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
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
    val allUsers : List<User>
        get() {
            val users = ArrayList<User>()
            val selectQueryHandler = "$TABLE_NAME"!!
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler, null)
            if(cursor.moveToFirst()) {
                do {
                    val user = User()
                    user.id = cursor.getString(cursor.getColumnIndex(KEY_ID) - 1)
                    user.pw = cursor.getString(cursor.getColumnIndex(KEY_PW) - 1)
                    user.name = cursor.getString(cursor.getColumnIndex(KEY_USERNAME) - 1)

                    users.add(user)
                } while(cursor.moveToNext())
            }
            db.close()
            return users
        }

    // 사용자 정보 추가
    fun addUser(user:User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, user.id)
        values.put(KEY_PW, user.pw)
        values.put(KEY_USERNAME, user.name)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun login(user: User) : Boolean{
        val db = this.readableDatabase

        val projection = arrayOf(UID)

        val selection = "$KEY_ID = ? AND $KEY_PW = ?"
        val selectionArgs = arrayOf(user.id, user.pw)

        val cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        return cursor.count > 0
    }
    // 유저 정보 업데이트 메소드
    fun updateUser(user: User): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, user.id)
        values.put(KEY_PW, user.pw)
        values.put(KEY_USERNAME, user.name)

        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(user.id))
    }

    // 유저 삭제 메소드
    fun deleteUser(user: User){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(user.id))
        db.close()
    }
}