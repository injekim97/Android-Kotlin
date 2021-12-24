package com.example.qrscanner_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qrscanner_.CartMenuAdapter
import com.example.qrscanner_.Personal_Number_Activity
import com.example.qrscanner_.R
import com.example.qrscanner_.SubActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_cart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.*

class CartActivity : AppCompatActivity() {
    //var test_arr2 = Array(10){Menus_Cart(1,2,"ad",1000)}
    //var test_arr3 : Array<Menus_Cart> = Array(10){test1}

    //var test_arr4 = ArrayList<Menus_Cart>();
    //val api = RestfulAPI.create();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //리사이클러뷰
        rv_cartMenuList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_cartMenuList.setHasFixedSize(true)
        rv_cartMenuList.adapter = CartMenuAdapter(Singleton.cartMenus)

        //주문 추가 선택 버튼
        add_menu_btn.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

        order_btn.setOnClickListener {
            var order_finish = Toast.makeText(this, "주문 완료.", Toast.LENGTH_SHORT)
            order_finish.show()

            //안심 번호 입력 창으로 화면 전환.
            val intent = Intent(this, Personal_Number_Activity::class.java)
            startActivity(intent)
        }
        //주문 (push) 버튼
        /*
        val api = RestfulAPI.create();
        order_btn.setOnClickListener {
            var order_finish = Toast.makeText(this, "주문 완료.", Toast.LENGTH_SHORT)
            order_finish.show()

            //안심 번호 입력 창으로 화면 전환.
            val intent = Intent(this, Personal_Number_Activity::class.java)
            startActivity(intent)

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

        }

         */



    /* 그냥 테스
        var i = 0
        while(i<Singleton.cartMenus.size){
            println("hihi")
            i++
        }

     */
    }
}


data class CartList(val arr : ArrayList<Menus_Cart>)
data class Menus_Cart(val placeId: Int?, val menuId: Int?, val menu: String?, val price: Int?, val count: Int?, val total_price: Int?) //totalprice 추가함.
data class Personal_Number(var personalNumber: String?)  //안심번호 data class 선언

object Singleton {
    val cartMenus = arrayListOf<Menus_Cart>()
    val personalNumber = arrayListOf<Personal_Number>()
}
/*
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
*/
