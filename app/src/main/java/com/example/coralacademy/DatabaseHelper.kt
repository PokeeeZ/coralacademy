package com.example.coralacademy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

const val DATABASE_NAME = "CoralDB"
const val TABLE_NAME = "Users"
const val COL_NAME = "username"
const val COL_PASS = "password"
const val COL_COR_STATUS = "coralMemberStatus"
const val COL_ID = "id"
const val DATABASE_VERSION = 1

class DataBaseHandler(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NAME VARCHAR(256)," +
                "$COL_PASS VARCHAR(256)," +
                "$COL_COR_STATUS INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(user: User): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, user.getUser())
        cv.put(COL_PASS, user.getPass())
        cv.put(COL_COR_STATUS, if (user.getMemStat()) 1 else 0)

        val result = db.insert(TABLE_NAME, null, cv)

        if (result == -1L) {
            Log.e("DatabaseError", "Failed to insert data for user: ${user.getUser()}")
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("DatabaseSuccess", "Successfully inserted data for user: ${user.getUser()}")
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

        db.close()
        return result
    }

    fun readData(): MutableList<User> {
        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val idIndex = result.getColumnIndex(COL_ID)
                val nameIndex = result.getColumnIndex(COL_NAME)
                val passIndex = result.getColumnIndex(COL_PASS)
                val statusIndex = result.getColumnIndex(COL_COR_STATUS)

                if (idIndex >= 0 && nameIndex >= 0 && passIndex >= 0 && statusIndex >= 0) {
                    val user = User(
                        id = result.getInt(idIndex),
                        username = result.getString(nameIndex),
                        password = result.getString(passIndex),
                        coralMemberStatus = result.getInt(statusIndex) == 1
                    )
                    list.add(user)
                }
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }


    fun deleteData() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

    fun updateData() {
        val db = this.writableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val idIndex = result.getColumnIndex(COL_ID)
                val nameIndex = result.getColumnIndex(COL_NAME)
                val passIndex = result.getColumnIndex(COL_PASS)
                val statusIndex = result.getColumnIndex(COL_COR_STATUS)

                if (idIndex >= 0 && nameIndex >= 0 && passIndex >= 0 && statusIndex >= 0) {
                    val cv = ContentValues()
                    cv.put(COL_PASS, result.getString(passIndex))

                    db.update(
                        TABLE_NAME, cv, "$COL_ID=? AND $COL_NAME=?",
                        arrayOf(
                            result.getString(idIndex),
                            result.getString(nameIndex)
                        )
                    )
                }
            } while (result.moveToNext())
        }
        result.close()
        db.close()
    }


    fun resetIDs() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
        db.close()
        Toast.makeText(context, "IDs have been reset.", Toast.LENGTH_SHORT).show()
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
