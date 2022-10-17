package com.example.weatherreportapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherreportapp.databinding.ActivityMainBinding
import com.example.weatherreportapp.fragment.MainFragment
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        supportFragmentManager.
        beginTransaction().
        replace(R.id.placeHolder, MainFragment.newInstance()).
        commit()

    }
}