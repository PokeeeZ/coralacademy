package com.example.coralacademy

class User {
    private var id : Int = 0
    private var username : String = ""
    private var password : String = ""
    private var coralMemberStatus : Boolean

    constructor(id:Int,username:String,password:String,coralMemberStatus:Boolean) {
        this.id = id
        this.username = username
        this.password = password
        this.coralMemberStatus = coralMemberStatus

    }

    public fun getId():Int {
        return id
    }

    public fun getUser():String {
        return username
    }

    public fun getPass():String {
        return password
    }

    public fun getMemStat():Boolean {
        return coralMemberStatus
    }
}