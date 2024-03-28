package com.paulus.studentapp.model

data class Food (
    val id:Int?,
    val name:String?,
    val type:String?,
    val ingredients:List<String>?,
    val nutrition_facts:NutritionFacts,
    val images:String?
)

data class NutritionFacts (
    val calories:Int?,
    val fat:Int?,
    val carbs:Int?,
    val protein:Int?
)