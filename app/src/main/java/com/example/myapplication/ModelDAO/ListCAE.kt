package com.example.myapplication.ModelDAO

class ListCAE {
    private var cName: String?
    private var tInvest: String?

    constructor(cName: String?, tInvest: String?) {
        this.cName = cName
        this.tInvest = tInvest
    }
    fun getCName():String?{
        return cName
    }
    fun gettInvest():String?{
        return tInvest
    }
}