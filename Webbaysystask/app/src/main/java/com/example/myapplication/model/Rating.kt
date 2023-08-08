package com.example.myapplication.model

data class Rating(
    var rate:Double?,
    var count:Int?
) {
    constructor() : this (null,null)
    }

