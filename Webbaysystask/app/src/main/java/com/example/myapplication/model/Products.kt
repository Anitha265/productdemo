package com.example.myapplication.model

import java.io.Serializable

class Products(
    var id: Int?,
    var title : String?,
    var price : Double?,
    var description : String?,
    var category : String?,
    var image : String?,
    var rating : Rating?
    ) : Serializable {
    constructor():this(null,null,null,null,null,null,null)
    }