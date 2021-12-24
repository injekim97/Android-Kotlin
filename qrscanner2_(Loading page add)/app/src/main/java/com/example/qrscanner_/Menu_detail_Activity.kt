package com.example.qrscanner_

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_menu_detail.*

//상세 메뉴 VIEW
class Menu_detail_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)

        val placeId = intent.getStringExtra("placeId")
        val menuId = intent.getStringExtra("menuId")
        val menu = intent.getStringExtra("menu")
        val price = intent.getStringExtra("price")


        menu_tv.text = menu
        price_tv.text = price

        //Counter
        val counter_num = findViewById<TextView>(R.id.counter)
        val cnt_up = findViewById<Button>(R.id.cnt_up)
        val cnt_down = findViewById<Button>(R.id.cnt_down)

        var count = 1
        counter_num.text = count.toString()
        //Count_up
        cnt_up.setOnClickListener {
            if (count == 5) {
                var toast_max = Toast.makeText(this, "최대 5개 입니다.", Toast.LENGTH_SHORT)
                toast_max.show()
            } else {
                count++
                counter_num.text = count.toString()
            }
        }

        //Count_down
        cnt_down.setOnClickListener {
            if (count == 1) {
                var toast_min = Toast.makeText(this, "최소 1개 입니다.", Toast.LENGTH_SHORT)
                toast_min.show()
            } else {
                count--
                counter_num.text = count.toString()
            }
        }

        //Add to Cart
        val addToCart = findViewById<Button>(R.id.addTocart_btn)

        addToCart.setOnClickListener {

            //1. Singleton 에 선택한 목록 추가
            var test1 = price?.toInt()
            var total = test1?.times(count)
            Singleton.cartMenus.add(Menus_Cart(placeId?.toInt(),menuId?.toInt(),menu,price?.toInt(),count,total))
            //화면 전환 여부 물어볼 message box 추가할것.
            showMessageBox()
            //1. 화면 전환
            //val intent = Intent(this, CartActivity::class.java)
            //startActivity(intent)

        }
    }

    private fun showMessageBox() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.cart_messagebox, null)
        val textView: TextView = view.findViewById(R.id.message_box_tv)
        textView.text = "장바구니로 이동하시겠습니까?"

        val alertDialog =
            AlertDialog.Builder(this).setTitle("장바구니에 담겼습니다.")
                .setPositiveButton("장바구니로 이동") { dialog, which ->
                Toast.makeText(applicationContext, "장바구니로 이동합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
                .setNegativeButton("메뉴 더 보기"){ dialog, which ->
                    Toast.makeText(applicationContext, "더 둘러보기.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SubActivity::class.java)
                    startActivity(intent)
                }
                .create()

        alertDialog.setView(view)
        alertDialog.show()
    }
}