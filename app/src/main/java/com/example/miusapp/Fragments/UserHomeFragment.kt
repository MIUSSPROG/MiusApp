package com.example.miusapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.miusapp.Adapter.SliderAdapter
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R
import com.example.miusapp.databinding.FragmentUserHomeBinding


class UserHomeFragment : Fragment() {

    private lateinit var binding: FragmentUserHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)

        val programms: MutableList<SliderRvItem> = ArrayList()
        programms.add(SliderRvItem(resources.getString(R.string.program1), 2))
        programms.add(SliderRvItem(resources.getString(R.string.program2), 3))
        programms.add(SliderRvItem(resources.getString(R.string.program3), 1))
        programms.add(SliderRvItem(resources.getString(R.string.program4), 0))
        programms.add(SliderRvItem(resources.getString(R.string.program5), 2))
        programms.add(SliderRvItem(resources.getString(R.string.program6), 3))

        val projects: MutableList<SliderRvItem> = ArrayList()
        projects.add(SliderRvItem(resources.getString(R.string.project1), 0))
        projects.add(SliderRvItem(resources.getString(R.string.project2), 1))
        projects.add(SliderRvItem(resources.getString(R.string.project3), 3))
        projects.add(SliderRvItem(resources.getString(R.string.project4), 4))
        projects.add(SliderRvItem(resources.getString(R.string.project5), 5))
        projects.add(SliderRvItem(resources.getString(R.string.project6), 6))
        projects.add(SliderRvItem(resources.getString(R.string.project7), 7))
        projects.add(SliderRvItem(resources.getString(R.string.project8), 8))

        val qualifications: MutableList<SliderRvItem> = ArrayList()
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification1), 0))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification2), 1))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification3), 2))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification4), 3))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification5), 4))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification6), 5))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification7), 6))
        qualifications.add(SliderRvItem(resources.getString(R.string.qualification8), 7))

        val achievements: MutableList<SliderRvItem> = ArrayList()
        achievements.add(SliderRvItem(resources.getString(R.string.achievement1), 0))
        achievements.add(SliderRvItem(resources.getString(R.string.achievement2), 1))
        achievements.add(SliderRvItem(resources.getString(R.string.achievement3), 2))
        achievements.add(SliderRvItem(resources.getString(R.string.achievement4), 3))

        val results: MutableList<SliderRvItem> = ArrayList()
        results.add(SliderRvItem(resources.getString(R.string.result1), 0))
        results.add(SliderRvItem(resources.getString(R.string.result2), 1))
        results.add(SliderRvItem(resources.getString(R.string.result3), 2))
        results.add(SliderRvItem(resources.getString(R.string.result4), 3))

        val plans: MutableList<SliderRvItem> = ArrayList()
        plans.add(SliderRvItem(resources.getString(R.string.plan1), 0))
        plans.add(SliderRvItem(resources.getString(R.string.plan2), 1))
        plans.add(SliderRvItem(resources.getString(R.string.plan3), 2))
        plans.add(SliderRvItem(resources.getString(R.string.plan4), 3))
        plans.add(SliderRvItem(resources.getString(R.string.plan5), 4))
        plans.add(SliderRvItem(resources.getString(R.string.plan6), 5))
        plans.add(SliderRvItem(resources.getString(R.string.plan7), 6))

        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem("Программы", R.drawable.rounded1, programms))
        sliderItems.add(SliderItem("Проекты", R.drawable.rounded2, projects))
        sliderItems.add(SliderItem("Квалификация", R.drawable.rounded3, qualifications))
        sliderItems.add(SliderItem("Достижения", R.drawable.rounded4, achievements))
        sliderItems.add(SliderItem("Образовательные результаты", R.drawable.rounded5, results))
        sliderItems.add(SliderItem("Планы", R.drawable.rounded6, plans))

        binding.viewPager.adapter = context?.let { SliderAdapter(sliderItems, it) }
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

        binding.viewPager.setPageTransformer(compositePageTransformer)

        return binding.root
    }

}