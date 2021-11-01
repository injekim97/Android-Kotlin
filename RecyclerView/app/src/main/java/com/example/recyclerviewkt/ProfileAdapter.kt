package com.example.recyclerviewkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ProfileAdapter(val profileList: ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>(){

    // plug로 연결된 화면을 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false) // context는 activity에서 담겨진 모든 정보
        return CustomViewHolder(view)  // itemview가 되는 거라고 생각하기
    }

    //profile.list에 대한 크기를 return 해줌
    override fun getItemCount(): Int {
        return profileList.size
    }

    // 실제 연결 해주는 곳(스크롤, 리스트 뷰 사용시), 계속 실행 되는 곳
    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.gender.setImageResource(profileList.get(position).gender)
        holder.name.text = profileList.get(position).name
        holder.age.text = profileList.get(position).age.toString() // int형이기 때문에.tostring() 사용
        holder.job.text = profileList.get(position).job
    }


    // 내부 클래스
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // profiles 에 만든 모델들을 활용하는 곳
        val gender = itemView.findViewById<ImageView>(R.id.iv_man) // 성별: 특정 XML에서 ID 값으로 찾아올 수 있음 , <TYPE>(R.id.ID이름)
        val name = itemView.findViewById<TextView>(R.id.tv_name) // 이름
        val age = itemView.findViewById<TextView>(R.id.tv_age) // 나이
        val job = itemView.findViewById<TextView>(R.id.tv_job) // 직업
    }

}