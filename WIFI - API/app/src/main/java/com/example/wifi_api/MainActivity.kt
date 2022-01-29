package com.example.wifi_api

import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.example.wifi_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding 활성화
    lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // object create
        binding = ActivityMainBinding.inflate(layoutInflater)

        // binding object change
        setContentView(binding.root)



        // WIFI ON Function
        // binding.btnwifion.setOnClickListener(ON)
        binding.btnwifion.setOnClickListener(View.OnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val intent = Intent(Settings.Panel.ACTION_WIFI)
                startActivityForResult(intent,1) // startActivityForResult(intent,1)
            }
            else {
                val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                wifiManager.setWifiEnabled(true)
            }
        })



        // WIFI OFF Function
        // binding.btnwifion.setOnClickListener(ON)
        binding.btnwifioff.setOnClickListener(View.OnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val intent = Intent(Settings.Panel.ACTION_WIFI)
                startActivityForResult(intent,0) // startActivityForResult(intent,0)
            }
            else {
                val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                wifiManager.setWifiEnabled(false)
            }
        })


    }
}