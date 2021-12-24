package com.example.qrscanner_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_personal_number.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class Personal_Number_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_number)

        var manCount = 1
        var i = 0
        numberPicker.minValue = 1
        numberPicker.maxValue = 12
        numberPicker.wrapSelectorWheel = false
        numberPicker.setOnValueChangedListener{picker, oldVal, newVal ->
            Log.d("Log",picker.toString())
            Log.d("Log",oldVal.toString())
            Log.d("Log",newVal.toString())
            manCount = newVal
        }
        personal_number_add_btn.setOnClickListener {
            if(i<manCount) {
                var test : Personal_Number = Personal_Number("default")
                test.personalNumber = personal_number_ptv.text.toString()
                //Singleton.personalNumber.add(personal_number_ptv.text.toString())
                //Singleton.personalNumber.add(Personal)
                i++
                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                Log.d("Log", "add 했습니다.")
            }
            else {
                Toast.makeText(this, "초과했습니다.", Toast.LENGTH_SHORT).show()
                Log.d("Log", "초과했습니다.")
            }
        }

        val api = RestfulAPI.create();
        val api2 = RestfulAPI_PN.create();
        final_order_btn.setOnClickListener{
            var order_finish = Toast.makeText(this, "주문 완료.", Toast.LENGTH_SHORT)
            order_finish.show()

            api.post_menus(Singleton.cartMenus[0]).enqueue(object: Callback<Menus_Cart> {
                override fun onResponse(call: Call<Menus_Cart>, response: Response<Menus_Cart>) {
                    Log.d("log",Singleton.cartMenus[0].toString())
                    Log.d("log",response.toString())
                    Log.d("log",response.body().toString())
                    if(!response.body().toString().isEmpty())
                        Log.d("log", "empty")
                    //post 후에는 Singleton 배열 초기화 후 주문 완료 화면으로 이동.
                }

                override fun onFailure(call: Call<Menus_Cart>, t: Throwable) {
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }
            })

            //api2.post_personalnumbers(Singleton.personalNumber[
            val intent = Intent(this,Order_Finish_Activity::class.java)
            startActivity(intent)

        }
    }
}

interface RestfulAPI {
    @POST("/post") //수정 필요
    //@Headers("accept: application/json",
    //"content-type: application/json")
    fun post_menus(
        @Body jsonparams: Menus_Cart
    ): retrofit2.Call<Menus_Cart>

    companion object {
        private const val BASE_URL = "https://httpbin.org"

        fun create():RestfulAPI{
            val gson : Gson = GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RestfulAPI::class.java)

        }
    }
}

interface RestfulAPI_PN {
    @POST("/post")
    fun post_personalnumbers(
        @Body jsonparams: Personal_Number
    ): retrofit2.Call<Personal_Number>

    companion object {
        private const val BASE_URL = "https://httpbin.org"

        fun create(): RestfulAPI_PN {
            val gson: Gson = GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RestfulAPI_PN::class.java)

        }
    }
}