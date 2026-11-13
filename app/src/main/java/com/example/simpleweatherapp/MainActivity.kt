package com.example.simpleweatherapp

import android.os.Bundle
import android.widget.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val apiKey = BuildConfig.OPENWEATHER_API_KEY
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCity = findViewById<EditText>(R.id.etCity)
        val btnGetWeather = findViewById<Button>(R.id.btnGetWeather)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnGetWeather.setOnClickListener {

            val city = etCity.text.toString().trim()
            if (city.isNotEmpty()) {

                getWeather(city, tvResult)
            }else {
                Toast.makeText(this, "Please enter a city", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getWeather(city: String, tvResult: TextView){
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val url = "https://api.openweathermap.org/data.2.5/weather?q=$city&appid=$apiKey&units=metric" //https://api.openweathermap.org/
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                val jsonData = response.body?.string()
                val json = JSONObject(jsonData)

                val weatherArray = json.getJSONArray("weather")
                val weather = weatherArray.getJSONObject(0).getString("description")
                val temp = json.getJSONObject("main").getDouble("temp")

                withContext(Dispatchers.Main){
                    tvResult.text = "Temperature: $temp°C\nCondition: $weather"

                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    tvResult.text = "Error: ${e.localizedMessage}"
                }
            }

        }
    }
}