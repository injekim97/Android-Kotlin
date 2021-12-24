package com.example.qrscanner_

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter (val menuList: ArrayList<Menus>) : RecyclerView.Adapter<MenuAdapter.CustomViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_list,parent,false) //MenuList를 가져와서 Adapter에 붙힘.
        return CustomViewHolder(view).apply{
            //.apply를 통해 아이템을 선택하면 setOnClickListener 실행
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition //선택한 아이템 Number
                val menu : Menus = menuList.get(curPos) //선택한 아이템을 Menus 클래스 객체로 생성. (밑의 Toast Message에서처럼 값을 참조 가능

                //Toast.makeText(parent.context,"이름 : ${menu.menu}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //BindViewHolder
    override fun onBindViewHolder(holder: MenuAdapter.CustomViewHolder, position: Int) {
        holder.menu_img.setImageResource(menuList.get(position).menu_img)
        holder.name.text = menuList.get(position).menu
        holder.price.text = menuList.get(position).price.toString()

        //리사이클러뷰에서 선택한 항목의 placeId, menuId, menu, price 의 값을 변수로 선언.
        val placeId = menuList.get(position).placeId
        val menuId = menuList.get(position).menuId
        val menu = menuList.get(position).menu
        val price = menuList.get(position).price

        //화면 전환 코드
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context,Menu_detail_Activity::class.java)

            intent.putExtra("placeId",placeId)
            intent.putExtra("menuId",menuId)
            intent.putExtra("menu",menu)
            intent.putExtra("price",price)



            ContextCompat.startActivity(holder.itemView.context,intent,null)

            //배열을 보낸다음에 받는 방식을 고민해보자.
        }
    }

    //RecyclerView의 Item 개수를 리턴
    override fun getItemCount(): Int {
        return menuList.size
    }

    //라사이클러뷰에 사용될 아이템 선언 onBindViewHolder에서 사용
    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val menu_img = itemView.findViewById<ImageView>(R.id.menu_img_id) // 메뉴 이미지
        val name = itemView.findViewById<TextView>(R.id.menuname_id) // 메뉴 이름
        val price = itemView.findViewById<TextView>(R.id.price_id)// 메뉴 가
        //val placeId = itemView.findViewById<TextView>(R.id.place)

    }
}