package com.example.qrscanner_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sub.*

import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//!!!!!!!!!!!!!!!API적용할것!!!!!!!!!!!!!!!!!!!!!
//밑의 코드 Test Case를 직접 넣어서 사용함.

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        //val menuList = arrayListOf<Menus>()



       //rv_menuList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
       // rv_menuList.setHasFixedSize(true)
       // rv_menuList.adapter = MenuAdapter(menuList)

        //37~ 58 은 서버 없을 경우 테스트이다.
        val jsonString = "{\"menus\":[{\"placeId\":\"1\",\"menuId\":\"1\",\"menu\":\"아메리카노\",\"price\":\"4300\"},{\"placeId\":\"1\",\"menuId\":\"2\",\"menu\":\"라떼\",\"price\":\"4500\"},{\"placeId\":\"1\",\"menuId\":\"3\",\"menu\":\"카페모카\",\"price\":\"4500\"},{\"placeId\":\"1\",\"menuId\":\"4\",\"menu\":\"바닐라라뗴\",\"price\":\"4500\"},{\"placeId\":\"1\",\"menuId\":\"5\",\"menu\":\"토피넛라떼\",\"price\":\"4500\"}]}"

        //string으로 받아온 데이터 Json으로 파싱
        val userInfo = JSONObject(jsonString) //1. 스트링을 json 으로
        val jsonArray = userInfo.optJSONArray("menus") //2. json 배열

        val menuList = arrayListOf<Menus>()
        var i = 0

        while(i <  5){
            val jsonObject = jsonArray.getJSONObject(i)

            val placeId = jsonObject.getString("placeId")
            val menuId = jsonObject.getString("menuId")
            val menu = jsonObject.getString("menu")
            val price = jsonObject.getString("price")

            menuList.add(Menus(R.drawable.rainy,placeId, menuId, menu, price))
            i++
        }




        /*
        if(intent.hasExtra("testurl")){
            //api test start
            val url = "http://af55-211-246-68-124.ngrok.io"
            //val url2 = intent.getStringExtra("testurl") //실제로는 이걸 사용할
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create()) // String으로 받아오기 위해 선언함 // 수정한
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(GetMenu::class.java)
            val call = service.getMenuData()

            call.enqueue(object: Callback<String>{ // String 으로 변경할것.
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("MainActivity2","result :"+t.message)
                }

                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if(response.code() == 200){
                        Log.d("MainActivity","result: " + response.code())
                        val menuResponse = response.body() //여기서 받아왔음.!!!!
                        api_test.text = menuResponse!!
                        val jsonString = "{\"menus\":[{\"placeId\":\"1\",\"menuId\":\"1\",\"menu\":\"아메리카노\",\"price\":\"4300\"},{\"placeId\":\"1\",\"menuId\":\"2\",\"menu\":\"라떼\",\"price\":\"4500\"},{\"placeId\":\"1\",\"menuId\":\"3\",\"menu\":\"카페모카\",\"price\":\"4500\"},{\"placeId\":\"1\",\"menuId\":\"4\",\"menu\":\"바닐라라뗴\",\"price\":\"4500\"},{\"placeId\":\"1\",\"menuId\":\"5\",\"menu\":\"토피넛라떼\",\"price\":\"4500\"}]}"

                        //string으로 받아온 데이터 Json으로 파싱
                        val userInfo = JSONObject(jsonString) //1. 스트링을 json 으로
                        val jsonArray = userInfo.optJSONArray("menus") //2. json 배열

                        //val menuList = arrayListOf<Menus>()
                        var i = 0

                        while(i <  5){
                            val jsonObject = jsonArray.getJSONObject(i)

                            val placeId = jsonObject.getString("placeId")
                            val menuId = jsonObject.getString("menuId")
                            val menu = jsonObject.getString("menu")
                            val price = jsonObject.getString("price")

                            menuList.add(Menus(1,placeId, menuId, menu, price))
                            i++

                        }

                    }
                }
            })
        }

         */
        rv_menuList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_menuList.setHasFixedSize(true)
        rv_menuList.adapter = MenuAdapter(menuList)
    }

}

//https://e24c-211-58-223-157.ngrok.io
interface GetMenu{
    @GET("/place/1")
    fun getMenuData(
    ):retrofit2.Call<String>
}

//class MenuResponse(){
    //@SerializedName("menuString") var menus_String = String
   // @SerializedName("menus") var menus = ArrayList<Menu>()
//}

data class MenuResponse(val menus: String)

class Menu(){
    @SerializedName("placeId") var placeId = String
    @SerializedName("menuId") var menuId = String
    @SerializedName("menu") var menu = String
    @SerializedName("price") var price = String
}

class Menu_String(){
    @SerializedName("Menu_String") var Menu_Strings = String
}
//---------------------------------------------
interface WeatherService{
    @GET("/data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String) :
            Call<WeatherResponse>
}

class WeatherResponse(){
    @SerializedName("weather") var weather = ArrayList<Weather>()
    @SerializedName("main") var main: Main? = null
}

class Weather {
    @SerializedName("id") var id: Int = 0
    @SerializedName("main") var main : String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("icon") var icon : String? = null
}

class Main {
    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat()
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat()
    @SerializedName("temp_min")
    var temp_min: Float = 0.toFloat()
    @SerializedName("temp_max")
    var temp_max: Float = 0.toFloat()

}

//qrder menu class
class QrderResponse(){
    @SerializedName("menu") var menu = ArrayList<qrder_menu>()
}

class qrder_menu {
    @SerializedName("placeId") var placeId: Int = 0
    @SerializedName("menuId") var menuId : Int = 0
    @SerializedName("menu") var menu: String? = null
    @SerializedName("price") var price : Int = 0
}


