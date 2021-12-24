package com.example.qrscanner_

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar.getInstance

class CartMenuAdapter(val cartmenuList: ArrayList<Menus_Cart>) : RecyclerView.Adapter<CartMenuAdapter.CustomViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartMenuAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_list,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartMenuAdapter.CustomViewHolder, position: Int) {
        holder.selected_menu.text = cartmenuList.get(position).menu
        holder.selected_price.text = cartmenuList.get(position).price.toString()
        holder.selected_count.text = cartmenuList.get(position).count.toString()
        holder.selected_tprice.text = cartmenuList.get(position).total_price.toString()

        //버튼 클릭시 메뉴 삭제
        holder.delete_btn.setOnClickListener{
            Singleton.cartMenus.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    override fun getItemCount(): Int {
        return cartmenuList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val selected_menu = itemView.findViewById<TextView>(R.id.selected_menu_tv)
        val selected_price = itemView.findViewById<TextView>(R.id.selected_price_tv)
        val selected_count = itemView.findViewById<TextView>(R.id.selected_count_tv)
        val selected_tprice = itemView.findViewById<TextView>(R.id.selected_tprice_tv)
        val delete_btn = itemView.findViewById<Button>(R.id.delete_btn)




    }


}
