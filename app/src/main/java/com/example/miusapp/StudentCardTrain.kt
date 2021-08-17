package com.example.miusapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.miusapp.Utils.prefs
import com.google.firebase.firestore.FirebaseFirestore

class StudentCardTrain @JvmOverloads constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0) : ConstraintLayout(ctx, attributeSet, defStyleAttr) {
    private var view: View

    private var tvName: TextView
    private var tvValues: TextView
    private var btnExpand: ImageView
    private var expandLayout: ConstraintLayout
    private var btnMinusFirst: ImageView
    private var btnPlusFirst: ImageView
    private var btnMinusSecond: ImageView
    private var btnPlusSecond: ImageView
    private var firstValue: TextView
    private var secondValue: TextView
    private var db: FirebaseFirestore

    private lateinit var groupId: String
    private lateinit var studentId: String
    private lateinit var colName1: String
    private lateinit var colName2: String

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        db = FirebaseFirestore.getInstance()

        view = inflater.inflate(R.layout.compound_view, this)
        tvName = view.findViewById(R.id.tvName)
        tvValues = view.findViewById(R.id.tvValues)
        btnExpand = view.findViewById(R.id.btnExpand)
        expandLayout = view.findViewById(R.id.expandLayout)
        btnMinusFirst = view.findViewById(R.id.btnMinusFirst)
        btnPlusFirst = view.findViewById(R.id.btnPlusFirst)
        btnMinusSecond = view.findViewById(R.id.btnMinusSecond)
        btnPlusSecond = view.findViewById(R.id.btnPlusSecond)
        firstValue = view.findViewById(R.id.firstValue)
        secondValue = view.findViewById(R.id.secondValue)

        btnExpand.setOnClickListener {
            if (expandLayout.visibility == View.VISIBLE){
                expandLayout.visibility = View.GONE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                expandLayout.animate().alpha(0.0f)
            }
            else{
                expandLayout.visibility = View.VISIBLE
                (it as ImageView).setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                expandLayout.animate().alpha(1.0f)
            }
        }

        btnMinusFirst.setOnClickListener {
            var value = firstValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                firstValue.text = value.toString()
                saveValueToDB(colName1, value)
            }
        }

        btnPlusFirst.setOnClickListener {
            var value = firstValue.text.toString().toInt()
            if(value < 3){
                value += 1
                firstValue.text = value.toString()
                saveValueToDB(colName1, value)
            }
        }

        btnMinusSecond.setOnClickListener {
            var value = secondValue.text.toString().toInt()
            if (value > 0){
                value -= 1
                secondValue.text = value.toString()
                saveValueToDB(colName2, value)
            }
        }

        btnPlusSecond.setOnClickListener {
            var value = secondValue.text.toString().toInt()
            if (value < 3){
                value += 1
                secondValue.text = value.toString()
                saveValueToDB(colName2, value)
            }
        }
    }

    fun setData(groupId: String, studentId: String, name: String, colName1: String, colName2: String){
        tvName.text = name
        this.groupId = groupId
        this.studentId = studentId
        this.colName1 = colName1
        this.colName2 = colName2

        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
            .document(groupId).collection("студенты")
            .document(studentId).addSnapshotListener { value, error ->
                value?.let {
                    val value1 = it[colName1].toString().toInt()
                    val value2 = it[colName2].toString().toInt()
                    tvValues.text = "${value1}/${value2}"
                    firstValue.text = value1.toString()
                    secondValue.text = value2.toString()
                }
            }
    }

    fun updateValues(value1: Int, value2: Int){
        tvValues.text = "${value1}/${value2}"
    }

    private fun saveValueToDB(name: String, value: Int){
        db.collection("Users").document(prefs.myUUId)
            .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
            .document(groupId).collection("студенты")
            .document(studentId)
            .update(name, value)
    }
}