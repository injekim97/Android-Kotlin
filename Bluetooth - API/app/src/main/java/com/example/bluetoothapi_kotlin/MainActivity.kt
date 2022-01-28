package com.example.bluetoothapi_kotlin

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bluetoothapi_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 뷰 바인딩 활성화
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 아래 코드 수정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bluetoothManager = applicationContext.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter


        // bluetooth on btn click -> enable
        // setOnClickListener(On 입력시 OnClickListener 뜨는거 엔터하면 됨)
        binding.btnBluetoothOn.setOnClickListener(View.OnClickListener {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            //  startActivityForResult(intent,1) 여기에 에러뜨는 건 permission 부분 추가해주면 쉽게 해결
            startActivityForResult(intent,1)

            // Toast.를 사용하면 해당 btn click -> 말풍선 처럼 떴다가 사라지는 text 보여주는 기능
            Toast.makeText(applicationContext,"Bluetooth Enabled",Toast.LENGTH_SHORT).show()
        })

        // bluetooth off btn click -> disable
        binding.btnBluetoothOff.setOnClickListener(View.OnClickListener {
            bluetoothAdapter.disable()

            // Toast.를 사용하면 해당 btn click -> 말풍선 처럼 떴다가 사라지는 text 보여주는 기능
            Toast.makeText(applicationContext,"Bluetooth Disable",Toast.LENGTH_SHORT).show()
        })


        // bluetooth Paired btn click -> device name + address output
        binding.btnBluetoothPaired.setOnClickListener(View.OnClickListener {
            var pairedDevices = bluetoothAdapter.bondedDevices
            var data:StringBuffer = StringBuffer()
            for(device:BluetoothDevice in pairedDevices) {
                data.append("Device Name ="+device.name+" Device Address="+device.address)
            }

            if(data.isEmpty()) {
                Toast.makeText(applicationContext,"Bluetooth Devices is not Paired",Toast.LENGTH_SHORT).show()
            }

            else {
                Toast.makeText(applicationContext,"Bluetooth Disable",Toast.LENGTH_SHORT).show()
        }
        })
    }
}