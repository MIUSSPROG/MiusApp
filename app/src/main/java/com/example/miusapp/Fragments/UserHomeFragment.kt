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

        val programResourceItems = resources.getStringArray(R.array.programs)
        val programms: MutableList<SliderRvItem> = ArrayList()
        programms.add(SliderRvItem(programResourceItems[0], 2))
        programms.add(SliderRvItem(programResourceItems[1], 3))
        programms.add(SliderRvItem(programResourceItems[2], 1))
        programms.add(SliderRvItem(programResourceItems[3], 0))
        programms.add(SliderRvItem(programResourceItems[4], 2))
        programms.add(SliderRvItem(programResourceItems[5], 3))

        val projectResourceItems = resources.getStringArray(R.array.projects)
        val projects: MutableList<SliderRvItem> = ArrayList()
        projects.add(SliderRvItem(projectResourceItems[0], 0))
        projects.add(SliderRvItem(projectResourceItems[1], 1))
        projects.add(SliderRvItem(projectResourceItems[2], 3))
        projects.add(SliderRvItem(projectResourceItems[3], 4))
        projects.add(SliderRvItem(projectResourceItems[4], 5))
        projects.add(SliderRvItem(projectResourceItems[5], 6))
        projects.add(SliderRvItem(projectResourceItems[6], 7))
        projects.add(SliderRvItem(projectResourceItems[7], 8))

        val qualificationResourceItems = resources.getStringArray(R.array.qualifications)
        val qualifications: MutableList<SliderRvItem> = ArrayList()
        qualifications.add(SliderRvItem(qualificationResourceItems[0], 0))
        qualifications.add(SliderRvItem(qualificationResourceItems[1], 1))
        qualifications.add(SliderRvItem(qualificationResourceItems[2], 2))
        qualifications.add(SliderRvItem(qualificationResourceItems[3], 3))
        qualifications.add(SliderRvItem(qualificationResourceItems[4], 4))
        qualifications.add(SliderRvItem(qualificationResourceItems[5], 5))
        qualifications.add(SliderRvItem(qualificationResourceItems[6], 6))
        qualifications.add(SliderRvItem(qualificationResourceItems[7], 7))

        val achievementResourceItems = resources.getStringArray(R.array.achievements)
        val achievements: MutableList<SliderRvItem> = ArrayList()
        achievements.add(SliderRvItem(achievementResourceItems[0], 0))
        achievements.add(SliderRvItem(achievementResourceItems[1], 1))
        achievements.add(SliderRvItem(achievementResourceItems[2], 2))
        achievements.add(SliderRvItem(achievementResourceItems[3], 3))

        val resultResourceItems = resources.getStringArray(R.array.results)
        val results: MutableList<SliderRvItem> = ArrayList()
        results.add(SliderRvItem(resultResourceItems[0], 0))
        results.add(SliderRvItem(resultResourceItems[1], 0))
        results.add(SliderRvItem(resultResourceItems[2], 1))
        results.add(SliderRvItem(resultResourceItems[3], 2))
        results.add(SliderRvItem(resultResourceItems[4], 3))

        val planResourceItems = resources.getStringArray(R.array.plans)
        val plans: MutableList<SliderRvItem> = ArrayList()
        plans.add(SliderRvItem(planResourceItems[0], 0))
        plans.add(SliderRvItem(planResourceItems[1], 1))
        plans.add(SliderRvItem(planResourceItems[2], 2))
        plans.add(SliderRvItem(planResourceItems[3], 3))
        plans.add(SliderRvItem(planResourceItems[4], 4))
        plans.add(SliderRvItem(planResourceItems[5], 5))
        plans.add(SliderRvItem(planResourceItems[6], 6))

        val sliderResourceItems = resources.getStringArray(R.array.sliderItems)
        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(sliderResourceItems[0], R.drawable.rounded1, programms))
        sliderItems.add(SliderItem(sliderResourceItems[1], R.drawable.rounded2, projects))
        sliderItems.add(SliderItem(sliderResourceItems[2], R.drawable.rounded3, qualifications))
        sliderItems.add(SliderItem(sliderResourceItems[3], R.drawable.rounded4, achievements))
        sliderItems.add(SliderItem(sliderResourceItems[4], R.drawable.rounded5, results))
        sliderItems.add(SliderItem(sliderResourceItems[5], R.drawable.rounded6, plans))

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