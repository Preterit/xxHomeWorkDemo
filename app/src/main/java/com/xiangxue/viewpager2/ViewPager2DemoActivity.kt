package com.xiangxue.viewpager2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.xiangxue.xxhomeworkdemo.R
import kotlinx.android.synthetic.main.activity_view_pager2_demo.*

class ViewPager2DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_demo)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)
        val myAdapter = MyAdapter()

        // 设置viewpager滚动方向为垂直
//        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        myAdapter.setList(arrayListOf(0, 1, 2, 3, 4, 5, 6, 7))
        viewPager2.adapter = myAdapter

        // 设置滑动监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(this@ViewPager2DemoActivity, "page selected $position", Toast.LENGTH_SHORT).show()
            }
        })



        // 设置一屏多页面
        cl_main.clipChildren = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 2
        val params = viewPager2.layoutParams as ViewGroup.MarginLayoutParams
        params.leftMargin = resources.getDimension(R.dimen.dp_10).toInt() * 2
        params.rightMargin = params.leftMargin
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))
        viewPager2.setPageTransformer(compositePageTransformer)

    }


    class MyAdapter : RecyclerView.Adapter<MyAdapter.PagerViewHolder>() {
        private var mList: List<Int> = ArrayList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
            return PagerViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
            holder.bindData(mList[position])
        }

        fun setList(list: List<Int>) {
            mList = list
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        //	ViewHolder需要继承RecycleView.ViewHolder
        class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val mTextView: TextView = itemView.findViewById(R.id.tv_text)
            private var colors = arrayOf("#CCFF99", "#41F1E5", "#8D41F1", "#FF99CC", "#00FFFF", "#FFD700", "#FF8C00", "#A52A2A")

            fun bindData(i: Int) {
                mTextView.text = i.toString()
                mTextView.setBackgroundColor(Color.parseColor(colors[i]))
            }
        }
    }
}
