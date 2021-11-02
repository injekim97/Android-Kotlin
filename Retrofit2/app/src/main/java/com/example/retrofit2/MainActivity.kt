package com.example.retrofit2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit2.databinding.ActivityMainBinding
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    val mbinding by lazy { ActivityMainBinding.inflate(layoutInflater) } //뷰 바인딩
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mbinding.root)

        mbinding.btn.setOnClickListener {
            Toast.makeText(this, "Get the user's github info ", Toast.LENGTH_SHORT).show()
            RetrofitBuilder.api.getUserInfo().enqueue(object : retrofit2.Callback<UserInfo> {

                //통신 성공
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val userinfo = response.body()!!


                    // ★★★★★ mbinding.id값.text = userinfo?.API 키값 ★★★★★
                    mbinding.login.text = userinfo?.userId
                    mbinding.blog.text = userinfo?.blog
                    mbinding.bio.text = userinfo?.bio
                    mbinding.publicRepos.text = userinfo?.public_repos.toString()

                    Log.d(TAG, "onResponse: 유저아이디: ${userinfo?.userId}, 블로그: ${userinfo?.blog}, 자기소개: ${userinfo?.bio}, 리포지토리 수: ${userinfo?.public_repos}")
                }

                //통신 실패
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.d("error", t.message.toString())
                }

            })

        }
    }

}