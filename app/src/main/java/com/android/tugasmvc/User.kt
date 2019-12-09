package com.android.tugasmvc

class User{
    val id: String
    val username: String
    val email: String
    val password:String
    val images: String

    constructor(id: String, username: String, email: String, password: String, images: String) {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
        this.images = images
    }
}