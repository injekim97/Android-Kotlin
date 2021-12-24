package com.example.qrscanner_

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startBarCodeReader(view: View){ //xml파일에서 onClick으로 실행되게 만들었다.
        IntentIntegrator(this).initiateScan() //버튼 클릭하면 qr코드 리더기 실행한다.
    }

    //커스텀할 수 있다.
    fun startBarCodeReaderCustom(view: View){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) //여러가지 바코드 규격중에 특정 규격만 설정가능 우리는 QR코드로 지정. -> 인식률 높아짐
        integrator.setPrompt("QR 코드를 스캔하여 주세요")  //하단의 문구
        integrator.setCameraId(1) //0 은 후면 카메라 1은 전면 카메라
        integrator.setBeepEnabled(true) //qr코드 스캐너가 바코드를 인식했을때 삑하는 소리
        integrator.setBarcodeImageEnabled(true) // 바코드 이미지도 비트맵으로 저장한다.
        integrator.initiateScan()
    }

    fun startBarCodeReaderCustomActivity(view: View){
        val integrator = IntentIntegrator(this)
        integrator.setBarcodeImageEnabled(true) // 바코드 이미지도 비트맵으로 저장한다.
        integrator.captureActivity = MyBarcodeReaderActivity::class.java
        integrator.initiateScan()
    }


    //바코드 스캔 후 결과를 전달 받는다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            //스캔 결과 참일 경우 실
            if(result.contents != null){ //contents에 결과가 있다.
                Toast.makeText(this,"scanned: ${result.contents} format: ${result.formatName}",Toast.LENGTH_LONG).show()
                //화면전환 test
                val intent = Intent(this,SubActivity::class.java)
                intent.putExtra("testurl",result.contents.toString()) //testurl이라는 key에 qr코드에서 읽은 url을 삽입.
                startActivity(intent)
            }else{
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show()
            }



        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

}