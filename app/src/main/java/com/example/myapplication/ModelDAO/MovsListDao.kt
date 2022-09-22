package com.example.myapplication.ModelDAO

class MovsListDao {
    private var movClient: String?

    constructor(movClient: String?) {
        this.movClient = movClient
    }
    fun getMovClient():String?{
        return movClient
    }
}