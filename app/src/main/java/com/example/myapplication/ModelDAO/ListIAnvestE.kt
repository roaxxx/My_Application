package com.example.myapplication.ModelDAO

class ListIAnvestE {
    private var nameI:String?
    private var vInvested:String?

    constructor(nameI: String?, vInvested: String?) {
        this.nameI = nameI
        this.vInvested = vInvested
    }

    fun getNameI():String?{
        return nameI
    }
    fun  getVInvested():String?{
        return vInvested
    }
}