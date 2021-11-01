package com.example.recyclerviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // arrayListOf -> list 형태의 객체를 넣어줌
        // Profiles -> (val gender : Int, val name: String, val age: Int, val job: String)
        val profileList = arrayListOf(
            Profiles(R.drawable.man, name="김인제",25,"안드로이드 앱 개발자"),
            Profiles(R.drawable.man2, name="김길동",20,"지도 앱 개발자"),
            Profiles(R.drawable.man3, name="외계인",30,"인공지능 개발자"),
            Profiles(R.drawable.woman, name="사과",28,"아이폰 앱 개발자"),
            Profiles(R.drawable.woman2, name="겜순이",22,"모바일 게임 개발자"),
            Profiles(R.drawable.woman, name="김프로",24,"유니티 개발자"),
            Profiles(R.drawable.man3, name="김백수",26,"백수1"),
            Profiles(R.drawable.man, name="백수좋아",29,"백수2"),
            Profiles(R.drawable.man2, name="백수만세",35,"백수3"),
            Profiles(R.drawable.woman2, name="신취준",45,"취준생1"),
            Profiles(R.drawable.woman, name="취업하자",20,"취준생2")
        )


        // VERTICAL -> 세로
        rv_profile.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_profile.setHasFixedSize(true)
        rv_profile.adapter = ProfileAdapter(profileList)
    }
}