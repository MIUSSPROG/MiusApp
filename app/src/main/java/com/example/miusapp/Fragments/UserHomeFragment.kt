package com.example.miusapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.miusapp.Adapter.SliderAdapter
import com.example.miusapp.Model.FirebaseModel
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.FragmentUserHomeBinding
import com.google.firebase.firestore.FirebaseFirestore


class UserHomeFragment : Fragment() {

    private lateinit var binding: FragmentUserHomeBinding
    private lateinit var db: FirebaseFirestore

    private lateinit var programItems: Array<String>
    private lateinit var projectItems: Array<String>
    private lateinit var qualificationItems: Array<String>
    private lateinit var achievementItems: Array<String>
    private lateinit var resultItems: Array<String>
    private lateinit var planItems: Array<String>

    private lateinit var programItemsCount: MutableMap<String, Int>
    private lateinit var projectItemsCount: MutableMap<String, Int>
    private lateinit var qualificationItemsCount: MutableMap<String, Int>
    private lateinit var achievementItemsCount: MutableMap<String, Int>
    private lateinit var resultItemsCount: MutableMap<String, Int>
    private lateinit var planItemsCount: MutableMap<String, Int>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)

        programItems = resources.getStringArray(R.array.programs)
        projectItems = resources.getStringArray(R.array.projects)
        qualificationItems = resources.getStringArray(R.array.qualifications)
        achievementItems = resources.getStringArray(R.array.achievements)
        resultItems = resources.getStringArray(R.array.results)
        planItems = resources.getStringArray(R.array.plans)

        programItemsCount = mutableMapOf()
        projectItemsCount = mutableMapOf()
        qualificationItemsCount = mutableMapOf()
        achievementItemsCount = mutableMapOf()
        resultItemsCount = mutableMapOf()
        planItemsCount = mutableMapOf()

        val sliderResourceItems = resources.getStringArray(R.array.sliderItems)
        val sliderItems: MutableList<SliderItem> = ArrayList()
        db = FirebaseFirestore.getInstance()
        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.sliderItem1)).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }
                value?.let {
                    for (document in it.documents) {
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when(newData.question){
                                programItems[0] -> programItemsCount[programItems[0]] = (programItemsCount[programItems[0]] ?: 0) + 1
                                programItems[1] -> programItemsCount[programItems[1]] = (programItemsCount[programItems[1]] ?: 0) + 1
                                programItems[2] -> programItemsCount[programItems[2]] = (programItemsCount[programItems[2]] ?: 0) + 1
                                programItems[3] -> programItemsCount[programItems[3]] = (programItemsCount[programItems[3]] ?: 0) + 1
                                programItems[4] -> programItemsCount[programItems[4]] = (programItemsCount[programItems[4]] ?: 0) + 1
                                programItems[5] -> programItemsCount[programItems[5]] = (programItemsCount[programItems[5]] ?: 0) + 1
                            }
                        }
                    }


                    val programms: MutableList<SliderRvItem> = ArrayList()
                    programms.add(SliderRvItem(programItems[0], programItemsCount[programItems[0]] ?: 0))
                    programms.add(SliderRvItem(programItems[1], programItemsCount[programItems[1]] ?: 0))
                    programms.add(SliderRvItem(programItems[2], programItemsCount[programItems[2]] ?: 0))
                    programms.add(SliderRvItem(programItems[3], programItemsCount[programItems[3]] ?: 0))
                    programms.add(SliderRvItem(programItems[4], programItemsCount[programItems[4]] ?: 0))
                    programms.add(SliderRvItem(programItems[5], programItemsCount[programItems[5]] ?: 0))
                    sliderItems.add(SliderItem(sliderResourceItems[0], R.drawable.rounded1, programms))
                    binding.viewPager.adapter?.notifyDataSetChanged()
                }
            }


        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.sliderItem2)).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    return@addSnapshotListener
                }
                value?.let {
                    for (document in it.documents) {
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when (newData.question) {

                                projectItems[0] -> projectItemsCount[projectItems[0]] =
                                    (projectItemsCount[projectItems[0]] ?: 0) + 1
                                projectItems[1] -> projectItemsCount[projectItems[1]] =
                                    (projectItemsCount[projectItems[1]] ?: 0) + 1
                                projectItems[2] -> projectItemsCount[projectItems[2]] =
                                    (projectItemsCount[projectItems[2]] ?: 0) + 1
                                projectItems[3] -> projectItemsCount[projectItems[3]] =
                                    (projectItemsCount[projectItems[3]] ?: 0) + 1
                                projectItems[4] -> projectItemsCount[projectItems[4]] =
                                    (projectItemsCount[projectItems[4]] ?: 0) + 1
                                projectItems[5] -> projectItemsCount[projectItems[5]] =
                                    (projectItemsCount[projectItems[5]] ?: 0) + 1
                                projectItems[6] -> projectItemsCount[projectItems[6]] =
                                    (projectItemsCount[projectItems[6]] ?: 0) + 1
                                projectItems[7] -> projectItemsCount[projectItems[7]] =
                                    (projectItemsCount[projectItems[7]] ?: 0) + 1
                            }
                        }
                    }

                    val projects: MutableList<SliderRvItem> = ArrayList()
                    projects.add(SliderRvItem(projectItems[0], projectItemsCount[projectItems[0]] ?: 0))
                    projects.add(SliderRvItem(projectItems[1], projectItemsCount[projectItems[1]] ?: 0))
                    projects.add(SliderRvItem(projectItems[2], projectItemsCount[projectItems[2]] ?: 0))
                    projects.add(SliderRvItem(projectItems[3], projectItemsCount[projectItems[3]] ?: 0))
                    projects.add(SliderRvItem(projectItems[4], projectItemsCount[projectItems[4]] ?: 0))
                    projects.add(SliderRvItem(projectItems[5], projectItemsCount[projectItems[5]] ?: 0))
                    projects.add(SliderRvItem(projectItems[6], projectItemsCount[projectItems[6]] ?: 0))
                    projects.add(SliderRvItem(projectItems[7], projectItemsCount[projectItems[7]] ?: 0))

                    sliderItems.add(SliderItem(sliderResourceItems[1], R.drawable.rounded2, projects))
                    binding.viewPager.adapter?.notifyDataSetChanged()
                }
            }

        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.sliderItem3)).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    return@addSnapshotListener
                }
                value?.let {
                    for (document in it.documents) {
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when (newData.question) {

                                qualificationItems[0] -> qualificationItemsCount[qualificationItems[0]] = (qualificationItemsCount[qualificationItems[0]] ?: 0) + 1
                                qualificationItems[1] -> qualificationItemsCount[qualificationItems[1]] = (qualificationItemsCount[qualificationItems[1]] ?: 0) + 1
                                qualificationItems[2] -> qualificationItemsCount[qualificationItems[2]] = (qualificationItemsCount[qualificationItems[2]] ?: 0) + 1
                                qualificationItems[3] -> qualificationItemsCount[qualificationItems[3]] = (qualificationItemsCount[qualificationItems[3]] ?: 0) + 1
                                qualificationItems[4] -> qualificationItemsCount[qualificationItems[4]] = (qualificationItemsCount[qualificationItems[4]] ?: 0) + 1
                                qualificationItems[5] -> qualificationItemsCount[qualificationItems[5]] = (qualificationItemsCount[qualificationItems[5]] ?: 0) + 1
                                qualificationItems[6] -> qualificationItemsCount[qualificationItems[6]] = (qualificationItemsCount[qualificationItems[6]] ?: 0) + 1
                                qualificationItems[7] -> qualificationItemsCount[qualificationItems[7]] = (qualificationItemsCount[qualificationItems[7]] ?: 0) + 1
                            }
                        }
                    }

                    val qualifications: MutableList<SliderRvItem> = ArrayList()
                    qualifications.add(SliderRvItem(qualificationItems[0], qualificationItemsCount[qualificationItems[0]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[1], qualificationItemsCount[qualificationItems[1]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[2], qualificationItemsCount[qualificationItems[2]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[3], qualificationItemsCount[qualificationItems[3]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[4], qualificationItemsCount[qualificationItems[4]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[5], qualificationItemsCount[qualificationItems[5]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[6], qualificationItemsCount[qualificationItems[6]] ?: 0))
                    qualifications.add(SliderRvItem(qualificationItems[7], qualificationItemsCount[qualificationItems[7]] ?: 0))

                    sliderItems.add(SliderItem(sliderResourceItems[2], R.drawable.rounded3, qualifications))
                    binding.viewPager.adapter?.notifyDataSetChanged()
                }
            }

        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.sliderItem4)).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    return@addSnapshotListener
                }
                value?.let {
                    for (document in it.documents) {
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when (newData.question) {
                                achievementItems[0] -> achievementItemsCount[achievementItems[0]] = (achievementItemsCount[achievementItems[0]] ?: 0) + 1
                                achievementItems[1] -> achievementItemsCount[achievementItems[1]] = (achievementItemsCount[achievementItems[1]] ?: 0) + 1
                                achievementItems[2] -> achievementItemsCount[achievementItems[2]] = (achievementItemsCount[achievementItems[2]] ?: 0) + 1
                                achievementItems[3] -> achievementItemsCount[achievementItems[3]] = (achievementItemsCount[achievementItems[3]] ?: 0) + 1
                            }
                        }
                    }

                    val achievements: MutableList<SliderRvItem> = ArrayList()
                    achievements.add(SliderRvItem(achievementItems[0], achievementItemsCount[achievementItems[0]] ?: 0))
                    achievements.add(SliderRvItem(achievementItems[1], achievementItemsCount[achievementItems[1]] ?: 0))
                    achievements.add(SliderRvItem(achievementItems[2], achievementItemsCount[achievementItems[2]] ?: 0))
                    achievements.add(SliderRvItem(achievementItems[3], achievementItemsCount[achievementItems[3]] ?: 0))

                    sliderItems.add(SliderItem(sliderResourceItems[3], R.drawable.rounded4, achievements))
                    binding.viewPager.adapter?.notifyDataSetChanged()
                }
            }

        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.sliderItem5)).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    return@addSnapshotListener
                }
                value?.let {
                    for (document in it.documents) {
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when (newData.question) {
                                resultItems[0] -> resultItemsCount[resultItems[0]] = (resultItemsCount[resultItems[0]] ?: 0) + 1
                                resultItems[1] -> resultItemsCount[resultItems[1]] = (resultItemsCount[resultItems[1]] ?: 0) + 1
                                resultItems[2] -> resultItemsCount[resultItems[2]] = (resultItemsCount[resultItems[2]] ?: 0) + 1
                                resultItems[3] -> resultItemsCount[resultItems[3]] = (resultItemsCount[resultItems[3]] ?: 0) + 1
//                                resultItems[4] -> resultItemsCount[resultItems[4]] = (resultItemsCount[resultItems[4]] ?: 0) + 1
                            }
                        }
                    }

                    val results: MutableList<SliderRvItem> = ArrayList()
                    results.add(SliderRvItem(resultItems[0], resultItemsCount[resultItems[0]] ?: 0))
                    results.add(SliderRvItem(resultItems[1], resultItemsCount[resultItems[1]] ?: 0))
                    results.add(SliderRvItem(resultItems[2], resultItemsCount[resultItems[2]] ?: 0))
                    results.add(SliderRvItem(resultItems[3], resultItemsCount[resultItems[3]] ?: 0))
//                    results.add(SliderRvItem(resultItems[4], resultItemsCount[resultItems[4]] ?: 0))

                    sliderItems.add(SliderItem(sliderResourceItems[4], R.drawable.rounded5, results))
                    binding.viewPager.adapter?.notifyDataSetChanged()
                }
            }

        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.sliderItem6)).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    return@addSnapshotListener
                }
                value?.let {
                    for (document in it.documents) {
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when (newData.question) {
                                planItems[0] -> planItemsCount[planItems[0]] = (planItemsCount[planItems[0]] ?: 0) + 1
                                planItems[1] -> planItemsCount[planItems[1]] = (planItemsCount[planItems[1]] ?: 0) + 1
                                planItems[2] -> planItemsCount[planItems[2]] = (planItemsCount[planItems[2]] ?: 0) + 1
                                planItems[3] -> planItemsCount[planItems[3]] = (planItemsCount[planItems[3]] ?: 0) + 1
                                planItems[4] -> planItemsCount[planItems[4]] = (planItemsCount[planItems[4]] ?: 0) + 1
                                planItems[5] -> planItemsCount[planItems[5]] = (planItemsCount[planItems[5]] ?: 0) + 1
                                planItems[6] -> planItemsCount[planItems[6]] = (planItemsCount[planItems[6]] ?: 0) + 1
                            }
                        }
                    }

                    val plans: MutableList<SliderRvItem> = ArrayList()
                    plans.add(SliderRvItem(planItems[0], planItemsCount[planItems[0]] ?: 0))
                    plans.add(SliderRvItem(planItems[1], planItemsCount[planItems[1]] ?: 0))
                    plans.add(SliderRvItem(planItems[2], planItemsCount[planItems[2]] ?: 0))
                    plans.add(SliderRvItem(planItems[3], planItemsCount[planItems[3]] ?: 0))
                    plans.add(SliderRvItem(planItems[4], planItemsCount[planItems[4]] ?: 0))
                    plans.add(SliderRvItem(planItems[5], planItemsCount[planItems[5]] ?: 0))
                    plans.add(SliderRvItem(planItems[6], planItemsCount[planItems[6]] ?: 0))

                    sliderItems.add(SliderItem(sliderResourceItems[5], R.drawable.rounded6, plans))
                    binding.viewPager.adapter?.notifyDataSetChanged()
                }
            }

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