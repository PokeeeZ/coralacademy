package com.example.coralacademy

class User {
    private var id: Int = 0
    private var username: String = ""
    private var password: String = ""
    private var coralMemberStatus: Boolean

    constructor(id: Int, username: String, password: String, coralMemberStatus: Boolean) {
        this.id = id
        this.username = username
        this.password = password
        this.coralMemberStatus = coralMemberStatus

    }

    fun getId(): Int {
        return id
    }

    fun getUser(): String {
        return username
    }

    fun getPass(): String {
        return password
    }

    fun getMemStat(): Boolean {
        return coralMemberStatus
    }
}