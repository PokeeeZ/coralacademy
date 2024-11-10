package com.example.coralacademy

class User {
    var id : Int = 0
    var username : String = ""
    var password : String = ""
    var coralMemberStatus : Boolean

    constructor(id:Int,username:String,password:String,coralMemberStatus:Boolean) {
        this.id = id
        this.username = username
        this.password = password
        this.coralMemberStatus = coralMemberStatus

    }
}