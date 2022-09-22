package com.example.myapplication.ModelDAO

class ListClientE {
    private var iNamee: String?
    private var iVal: String?

    constructor(iNamee: String?, iVal: String?) {
        this.iNamee = iNamee
        this.iVal = iVal
    }
    fun getIName():String?{
        return iNamee
    }
    fun getIVal():String?{
        return iVal
    }
}