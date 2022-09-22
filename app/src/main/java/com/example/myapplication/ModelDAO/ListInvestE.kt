package com.example.myapplication.ModelDAO

class ListInvestE {
    val nameIvest:String?
    val valInves:String?

    constructor(nameIvest: String?, valInvest: String?) {
        this.nameIvest = nameIvest
        this.valInves = valInvest
    }
    fun getNameInvest():String?{
        return nameIvest
    }
    fun getValInvest():String?{
        return valInves
    }
}