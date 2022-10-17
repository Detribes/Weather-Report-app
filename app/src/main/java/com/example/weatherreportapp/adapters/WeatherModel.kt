package com.example.weatherreportapp.adapters

data class WeatherModel(
    val city: String,
    val time: String,
    val condition: String,
    val currrentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String,
    val hours: String
    )
