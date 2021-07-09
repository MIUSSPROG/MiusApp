package com.example.miusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.Adapter.SliderDetailAdapter
import com.example.miusapp.Model.SliderDetailItem
import com.example.miusapp.Model.SliderDetailRvItem
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.databinding.ActivityDetailNavigationBinding
import com.example.miusapp.databinding.ActivityMainBinding

class DetailNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val desc = intent.extras?.get("desc").toString()
        binding.tvNavDetailTitle.text = desc

        val program1: MutableList<SliderDetailRvItem> = ArrayList()
        program1.add(SliderDetailRvItem("Т001 \"Разработка компьютерной сети\""))
        program1.add(SliderDetailRvItem("Т002 \" Системное администрирование\""))

        val program2: MutableList<SliderDetailRvItem> = ArrayList()
        program2.add(SliderDetailRvItem("Т003 \"Разработка компьютерной сети\""))
        program2.add(SliderDetailRvItem("Т004 \" Системное администрирование\""))

        val programs: MutableList<SliderDetailItem> = ArrayList()
        programs.add(SliderDetailItem("Программы, реализованные в этом  учебном \n" + "году, включая экспресс-курсы", program1))
        programs.add(SliderDetailItem("Количество программ, по которым реальная посещаемость за год выше 50%/65%/80%", program2))

        binding.viewPagerNavDetail.adapter = SliderDetailAdapter(programs, this)
        binding.circleIndicator3.setViewPager(binding.viewPagerNavDetail)
    }
}