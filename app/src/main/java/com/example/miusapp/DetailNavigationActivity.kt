package com.example.miusapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.miusapp.Adapter.SliderDetailAdapter
import com.example.miusapp.Fragments.AddItemFragment
import com.example.miusapp.Model.FirebaseModel
import com.example.miusapp.Model.SliderDetailItem
import com.example.miusapp.Model.SliderDetailRvItem
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.ActivityDetailNavigationBinding
import com.google.firebase.firestore.FirebaseFirestore

class DetailNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNavigationBinding
    private lateinit var db: FirebaseFirestore
    private var position = -1
    private lateinit var program1Items: MutableList<SliderDetailRvItem>
    private lateinit var program2Items: MutableList<SliderDetailRvItem>
    private lateinit var program3Items: MutableList<SliderDetailRvItem>
    private lateinit var program4Items: MutableList<SliderDetailRvItem>
    private lateinit var program5Items: MutableList<SliderDetailRvItem>
    private lateinit var program6Items: MutableList<SliderDetailRvItem>

    private lateinit var project1Items: MutableList<SliderDetailRvItem>
    private lateinit var project2Items: MutableList<SliderDetailRvItem>
    private lateinit var project3Items: MutableList<SliderDetailRvItem>
    private lateinit var project4Items: MutableList<SliderDetailRvItem>
    private lateinit var project5Items: MutableList<SliderDetailRvItem>
    private lateinit var project6Items: MutableList<SliderDetailRvItem>
    private lateinit var project7Items: MutableList<SliderDetailRvItem>
    private lateinit var project8Items: MutableList<SliderDetailRvItem>

    private lateinit var qualification1Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification2Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification3Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification4Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification5Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification6Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification7Items: MutableList<SliderDetailRvItem>
    private lateinit var qualification8Items: MutableList<SliderDetailRvItem>

    private lateinit var achievement1Items: MutableList<SliderDetailRvItem>
    private lateinit var achievement2Items: MutableList<SliderDetailRvItem>
    private lateinit var achievement3Items: MutableList<SliderDetailRvItem>
    private lateinit var achievement4Items: MutableList<SliderDetailRvItem>

    private lateinit var result1Items: MutableList<SliderDetailRvItem>
    private lateinit var result2Items: MutableList<SliderDetailRvItem>
    private lateinit var result3Items: MutableList<SliderDetailRvItem>
    private lateinit var result4Items: MutableList<SliderDetailRvItem>
    private lateinit var result5Items: MutableList<SliderDetailRvItem>

    private lateinit var plan1Items: MutableList<SliderDetailRvItem>
    private lateinit var plan2Items: MutableList<SliderDetailRvItem>
    private lateinit var plan3Items: MutableList<SliderDetailRvItem>
    private lateinit var plan4Items: MutableList<SliderDetailRvItem>
    private lateinit var plan5Items: MutableList<SliderDetailRvItem>
    private lateinit var plan6Items: MutableList<SliderDetailRvItem>
    private lateinit var plan7Items: MutableList<SliderDetailRvItem>

    private lateinit var programItems: Array<String>
    private lateinit var projectItems: Array<String>
    private lateinit var qualificationItems: Array<String>
    private lateinit var achievementItems: Array<String>
    private lateinit var resultItems: Array<String>
    private lateinit var planItems: Array<String>

    private var bottomSheet = AddItemFragment()
    private var categoryItems: MutableList<SliderDetailItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        programItems = resources.getStringArray(R.array.programs)
        projectItems = resources.getStringArray(R.array.projects)
        qualificationItems = resources.getStringArray(R.array.qualifications)
        achievementItems = resources.getStringArray(R.array.achievements)
        resultItems = resources.getStringArray(R.array.results)
        planItems = resources.getStringArray(R.array.plans)

        var desc = prefs.myDesc
        val title = intent.extras?.get("title").toString()
        val background = intent.extras?.get("background").toString().toInt()
        val positionToGo = intent.extras?.get("position").toString().toInt()
        binding.tvTitleNavDetail.text = title

        binding.viewPagerNavDetail.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                this@DetailNavigationActivity.position = position
            }
        })

        binding.fabAddItem.setOnClickListener {

//                val intent = Intent(this, AddItemToCategory::class.java)
//                intent.putExtra("itemTitle", getItemByPosition(title, position))
//                startActivity(intent)

            if(!bottomSheet.isAdded){
                val bundle = Bundle()
                bundle.putString("itemTitle", getItemByPosition(title, position))
                bundle.putString("mainTitle", title)
                bundle.putInt("background", background)
                bottomSheet.arguments = bundle
                bottomSheet.show(supportFragmentManager, "")
            }

//            val info: MutableMap<String, Any> = HashMap()
//            info["question"] = desc
//            val rand = (0..100).random()
//            info["answer"] = "ответ: $rand"
//
//            db.collection("Users").document(prefs.myUUId)
//                .collection("Секции").document(title).collection("questions")
//                .add(info)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Секция создана!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
//                }

//---------------------------------------------------------

//            val info: MutableMap<String, Any> = HashMap()
//            info["section"] = title
//            db.collection("Users").document(prefs.myUUId)
//                .collection("Секции").document(title).set(info)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Секция создана!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
//                }

//            val item: MutableMap<String, Any> = HashMap()
//            item["question"] = desc
//            item["answer"] = "тестовый ответ"
//
//            db.collection("Users").document(prefs.myUUId)
//                .collection("Секции").document(title).collection("questions")
//                .add(item)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Данные успешно сохранены!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
//                }
//-----------------------------------------------------------
//            val output: MutableMap<String, Any> = HashMap()
//            item["title"] = desc
//
//            db.collection("Users").document(prefs.myUUId)
//                .collection("Секции").document(title).collection("questions")
//                .document(desc).set(item)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Элемент секции создан!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
//                }
//
//            val userInput: MutableMap<String, Any> = HashMap()
//            userInput["desc"] = "и еще данные"
//
//            db.collection("Users").document(prefs.myUUId)
//                .collection("Секции").document(title).collection("questions")
//                .document(desc).collection("items").add(userInput)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Данные успешно сохранены!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
//                }

        }

        when (title){
            resources.getString(R.string.sliderItem1) -> {
                program1Items = ArrayList()
                program2Items = ArrayList()
                program3Items = ArrayList()
                program4Items = ArrayList()
                program5Items = ArrayList()
                program6Items = ArrayList()
            }
            resources.getString(R.string.sliderItem2) -> {
                project1Items = ArrayList()
                project2Items = ArrayList()
                project3Items = ArrayList()
                project4Items = ArrayList()
                project5Items = ArrayList()
                project6Items = ArrayList()
                project7Items = ArrayList()
                project8Items = ArrayList()
            }
            resources.getString(R.string.sliderItem3) -> {
                qualification1Items = ArrayList()
                qualification2Items = ArrayList()
                qualification3Items = ArrayList()
                qualification4Items = ArrayList()
                qualification5Items = ArrayList()
                qualification6Items = ArrayList()
                qualification7Items = ArrayList()
                qualification8Items = ArrayList()
            }
            resources.getString(R.string.sliderItem4) -> {
                achievement1Items = ArrayList()
                achievement2Items = ArrayList()
                achievement3Items = ArrayList()
                achievement4Items = ArrayList()
            }
            resources.getString(R.string.sliderItem5) -> {
                result1Items = ArrayList()
                result2Items = ArrayList()
                result3Items = ArrayList()
                result4Items = ArrayList()
                result5Items = ArrayList()
            }
            resources.getString(R.string.sliderItem6) -> {
                plan1Items = ArrayList()
                plan2Items = ArrayList()
                plan3Items = ArrayList()
                plan4Items = ArrayList()
                plan5Items = ArrayList()
                plan6Items = ArrayList()
                plan7Items = ArrayList()
            }
        }


        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(title).collection("questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                value?.let {
                    clearItemLists(title)
                    for(document in it.documents){
                        val newData = document.toObject(FirebaseModel::class.java)
                        if (newData != null) {
                            when(newData.question){
                                programItems[0] -> program1Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                programItems[1] -> program2Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                programItems[2] -> program3Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                programItems[3] -> program4Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                programItems[4] -> program5Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                programItems[5] -> program6Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))

                                projectItems[0] -> project1Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[1] -> project2Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[2] -> project3Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[3] -> project4Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[4] -> project5Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[5] -> project6Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[6] -> project7Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                projectItems[7] -> project8Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))

                                qualificationItems[0] -> qualification1Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[1] -> qualification2Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[2] -> qualification3Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[3] -> qualification4Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[4] -> qualification5Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[5] -> qualification6Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[6] -> qualification7Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                qualificationItems[7] -> qualification8Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))

                                achievementItems[0] -> achievement1Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                achievementItems[1] -> achievement2Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                achievementItems[2] -> achievement3Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                achievementItems[3] -> achievement4Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))

                                resultItems[0] -> result1Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                resultItems[1] -> result2Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                resultItems[2] -> result3Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                resultItems[3] -> result4Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                resultItems[4] -> result5Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))

                                planItems[0] -> plan1Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                planItems[1] -> plan2Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                planItems[2] -> plan3Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                planItems[3] -> plan4Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                planItems[4] -> plan5Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                planItems[5] -> plan6Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                                planItems[6] -> plan7Items.add(SliderDetailRvItem(newData.answer, getTitleDrawable(background)))
                            }
                        }
                    }

                    categoryItems.clear()
                    when(title){
                        resources.getString(R.string.sliderItem1) -> {
                            categoryItems.add(SliderDetailItem(programItems[0], program1Items))
                            categoryItems.add(SliderDetailItem(programItems[1], program2Items))
                            categoryItems.add(SliderDetailItem(programItems[2], program3Items))
                            categoryItems.add(SliderDetailItem(programItems[3], program4Items))
                            categoryItems.add(SliderDetailItem(programItems[4], program5Items))
                            categoryItems.add(SliderDetailItem(programItems[5], program6Items))
                        }
                        resources.getString(R.string.sliderItem2) -> {
                            categoryItems.add(SliderDetailItem(projectItems[0], project1Items))
                            categoryItems.add(SliderDetailItem(projectItems[1], project2Items))
                            categoryItems.add(SliderDetailItem(projectItems[2], project3Items))
                            categoryItems.add(SliderDetailItem(projectItems[3], project4Items))
                            categoryItems.add(SliderDetailItem(projectItems[4], project5Items))
                            categoryItems.add(SliderDetailItem(projectItems[5], project6Items))
                            categoryItems.add(SliderDetailItem(projectItems[6], project7Items))
                            categoryItems.add(SliderDetailItem(projectItems[7], project8Items))
                        }
                        resources.getString(R.string.sliderItem3) -> {
                            categoryItems.add(SliderDetailItem(qualificationItems[0], qualification1Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[1], qualification2Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[2], qualification3Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[3], qualification4Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[4], qualification5Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[5], qualification6Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[6], qualification7Items))
                            categoryItems.add(SliderDetailItem(qualificationItems[7], qualification8Items))
                        }
                        resources.getString(R.string.sliderItem4) -> {
                            categoryItems.add(SliderDetailItem(achievementItems[0], achievement1Items))
                            categoryItems.add(SliderDetailItem(achievementItems[1], achievement2Items))
                            categoryItems.add(SliderDetailItem(achievementItems[2], achievement3Items))
                            categoryItems.add(SliderDetailItem(achievementItems[3], achievement4Items))
                        }
                        resources.getString(R.string.sliderItem5) -> {
                            categoryItems.add(SliderDetailItem(resultItems[0], result1Items))
                            categoryItems.add(SliderDetailItem(resultItems[1], result2Items))
                            categoryItems.add(SliderDetailItem(resultItems[2], result3Items))
                            categoryItems.add(SliderDetailItem(resultItems[3], result4Items))
                            categoryItems.add(SliderDetailItem(resultItems[4], result5Items))
                        }
                        resources.getString(R.string.sliderItem6) -> {
                            categoryItems.add(SliderDetailItem(planItems[0], plan1Items))
                            categoryItems.add(SliderDetailItem(planItems[1], plan2Items))
                            categoryItems.add(SliderDetailItem(planItems[2], plan3Items))
                            categoryItems.add(SliderDetailItem(planItems[3], plan4Items))
                            categoryItems.add(SliderDetailItem(planItems[4], plan5Items))
                            categoryItems.add(SliderDetailItem(planItems[5], plan6Items))
                            categoryItems.add(SliderDetailItem(planItems[6], plan7Items))
                        }
                    }


                    binding.viewPagerNavDetail.adapter = SliderDetailAdapter(categoryItems, this, supportFragmentManager)
                    if(position == -1) {
                        binding.viewPagerNavDetail.currentItem = positionToGo
                    }
                    else{
                        binding.viewPagerNavDetail.currentItem = position
                    }
                    binding.circleIndicator3.setViewPager(binding.viewPagerNavDetail)
                }
            }
    }

    private fun clearItemLists(title: String) {
        when(title){
            resources.getString(R.string.sliderItem1) ->{
                program1Items.clear()
                program2Items.clear()
                program3Items.clear()
                program4Items.clear()
                program5Items.clear()
                program6Items.clear()
            }
            resources.getString(R.string.sliderItem2) ->{
                project1Items.clear()
                project2Items.clear()
                project3Items.clear()
                project4Items.clear()
                project5Items.clear()
                project6Items.clear()
                project7Items.clear()
                project8Items.clear()
            }
            resources.getString(R.string.sliderItem3) ->{
                qualification1Items.clear()
                qualification2Items.clear()
                qualification3Items.clear()
                qualification4Items.clear()
                qualification5Items.clear()
                qualification6Items.clear()
                qualification7Items.clear()
                qualification8Items.clear()
            }
            resources.getString(R.string.sliderItem4) ->{
                achievement1Items.clear()
                achievement2Items.clear()
                achievement3Items.clear()
                achievement4Items.clear()
            }
            resources.getString(R.string.sliderItem5) ->{
                result1Items.clear()
                result2Items.clear()
                result3Items.clear()
                result4Items.clear()
                result5Items.clear()
            }
            resources.getString(R.string.sliderItem6) ->{
                plan1Items.clear()
                plan2Items.clear()
                plan3Items.clear()
                plan4Items.clear()
                plan5Items.clear()
                plan6Items.clear()
                plan7Items.clear()
            }
        }
    }

    private fun getItemByPosition(title: String, position: Int): String{
        return when(title){
            resources.getString(R.string.sliderItem1) -> programItems[position]
            resources.getString(R.string.sliderItem2) -> projectItems[position]
            resources.getString(R.string.sliderItem3) -> qualificationItems[position]
            resources.getString(R.string.sliderItem4) -> achievementItems[position]
            resources.getString(R.string.sliderItem5) -> resultItems[position]
            resources.getString(R.string.sliderItem6) -> planItems[position]
            else -> ""
        }
    }

    private fun getTitleDrawable(background: Int) : Drawable? {
        return when(background){
            R.drawable.rounded1 -> getDrawable(R.drawable.left_rounded1)
            R.drawable.rounded2 -> getDrawable(R.drawable.left_rounded2)
            R.drawable.rounded3 -> getDrawable(R.drawable.left_rounded3)
            R.drawable.rounded4 -> getDrawable(R.drawable.left_rounded4)
            R.drawable.rounded5 -> getDrawable(R.drawable.left_rounded5)
            R.drawable.rounded6 -> getDrawable(R.drawable.left_rounded6)
            else -> null
        }
    }
}