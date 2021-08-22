package com.example.miusapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miusapp.Adapter.DiagnosticMapAdapter
import com.example.miusapp.Adapter.GraphAdapter
import com.example.miusapp.Model.GraphInfo
import com.example.miusapp.Model.Student
import com.example.miusapp.Utils.prefs
import com.example.miusapp.databinding.ActivityAdminGraphBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.firestore.FirebaseFirestore


class AdminGraphActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminGraphBinding
    private lateinit var db: FirebaseFirestore
    private var graphData: MutableList<GraphInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        val groupId = intent.extras?.getString("groupId")

        if (groupId != null) {
            db.collection("Users").document(prefs.teacherID)
                .collection("Секции").document(resources.getString(R.string.diagnosticMap)).collection("группы")
                .document(groupId).collection("студенты")
                .addSnapshotListener { value, error ->

                    error?.let {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        return@addSnapshotListener
                    }

                    value?.let {

                        var theor1 = GraphInfo()
                        var theor2 = GraphInfo()
                        var practic1 = GraphInfo()
                        var practic2 = GraphInfo()
                        var practic3 = GraphInfo()
                        var general1 = GraphInfo()
                        var general2 = GraphInfo()
                        var general3 = GraphInfo()
                        var general4 = GraphInfo()
                        var general5 = GraphInfo()
                        var general6 = GraphInfo()
                        var general7 = GraphInfo()
                        var general8 = GraphInfo()
                        var general9 = GraphInfo()
                        var general10 = GraphInfo()
                        var general11 = GraphInfo()

                        for (document in it.documents) {
                            val student = document.toObject(Student::class.java)
                            if (student != null) {

                                when (student.theorTrain1First){
                                    1 -> theor1.firstMin++
                                    2 -> theor1.firstMean++
                                    3 -> theor1.firstMax++
                                }
                                when (student.theorTrain1Second){
                                    1 -> theor1.secondMin++
                                    2 -> theor1.secondMean++
                                    3 -> theor1.secondMax++
                                }
                                when (student.theorTrain2First){
                                    1 -> theor2.firstMin++
                                    2 -> theor2.firstMean++
                                    3 -> theor2.firstMax++
                                }
                                when (student.theorTrain2Second){
                                    1 -> theor2.secondMin++
                                    2 -> theor2.secondMean++
                                    3 -> theor2.secondMax++
                                }
                                when (student.practicTrain1First){
                                    1 -> practic1.firstMin++
                                    2 -> practic1.firstMean++
                                    3 -> practic1.firstMax++
                                }
                                when (student.practicTrain1Second){
                                    1 -> practic1.secondMin++
                                    2 -> practic1.secondMean++
                                    3 -> practic1.secondMax++
                                }
                                when (student.practicTrain2First){
                                    1 -> practic2.firstMin++
                                    2 -> practic2.firstMean++
                                    3 -> practic2.firstMax++
                                }
                                when (student.practicTrain2Second){
                                    1 -> practic2.secondMin++
                                    2 -> practic2.secondMean++
                                    3 -> practic2.secondMax++
                                }
                                when (student.practicTrain3First){
                                    1 -> practic3.firstMin++
                                    2 -> practic3.firstMean++
                                    3 -> practic3.firstMax++
                                }
                                when (student.practicTrain3Second){
                                    1 -> practic3.secondMin++
                                    2 -> practic3.secondMean++
                                    3 -> practic3.secondMax++
                                }
                                when (student.generalTrain1First){
                                    1 -> general1.firstMin++
                                    2 -> general1.firstMean++
                                    3 -> general1.firstMax++
                                }
                                when (student.generalTrain1Second){
                                    1 -> general1.secondMin++
                                    2 -> general1.secondMean++
                                    3 -> general1.secondMax++
                                }
                                when (student.generalTrain2First){
                                    1 -> general2.firstMin++
                                    2 -> general2.firstMean++
                                    3 -> general2.firstMax++
                                }
                                when (student.generalTrain2Second){
                                    1 -> general2.secondMin++
                                    2 -> general2.secondMean++
                                    3 -> general2.secondMax++
                                }
                                when (student.generalTrain3First){
                                    1 -> general3.firstMin++
                                    2 -> general3.firstMean++
                                    3 -> general3.firstMax++
                                }
                                when (student.generalTrain3Second){
                                    1 -> general3.secondMin++
                                    2 -> general3.secondMean++
                                    3 -> general3.secondMax++
                                }
                                when (student.generalTrain4First){
                                    1 -> general4.firstMin++
                                    2 -> general4.firstMean++
                                    3 -> general4.firstMax++
                                }
                                when (student.generalTrain4Second){
                                    1 -> general4.secondMin++
                                    2 -> general4.secondMean++
                                    3 -> general4.secondMax++
                                }
                                when (student.generalTrain5First){
                                    1 -> general5.firstMin++
                                    2 -> general5.firstMean++
                                    3 -> general5.firstMax++
                                }
                                when (student.generalTrain5Second){
                                    1 -> general5.secondMin++
                                    2 -> general5.secondMean++
                                    3 -> general5.secondMax++
                                }
                                when (student.generalTrain6First){
                                    1 -> general6.firstMin++
                                    2 -> general6.firstMean++
                                    3 -> general6.firstMax++
                                }
                                when (student.generalTrain6Second){
                                    1 -> general6.secondMin++
                                    2 -> general6.secondMean++
                                    3 -> general6.secondMax++
                                }
                                when (student.generalTrain7First){
                                    1 -> general7.firstMin++
                                    2 -> general7.firstMean++
                                    3 -> general7.firstMax++
                                }
                                when (student.generalTrain7Second){
                                    1 -> general7.secondMin++
                                    2 -> general7.secondMean++
                                    3 -> general7.secondMax++
                                }
                                when (student.generalTrain8First){
                                    1 -> general8.firstMin++
                                    2 -> general8.firstMean++
                                    3 -> general8.firstMax++
                                }
                                when (student.generalTrain8Second){
                                    1 -> general8.secondMin++
                                    2 -> general8.secondMean++
                                    3 -> general8.secondMax++
                                }
                                when (student.generalTrain9First){
                                    1 -> general9.firstMin++
                                    2 -> general9.firstMean++
                                    3 -> general9.firstMax++
                                }
                                when (student.generalTrain9Second){
                                    1 -> general9.secondMin++
                                    2 -> general9.secondMean++
                                    3 -> general9.secondMax++
                                }
                                when (student.generalTrain10First){
                                    1 -> general10.firstMin++
                                    2 -> general10.firstMean++
                                    3 -> general10.firstMax++
                                }
                                when (student.generalTrain10Second){
                                    1 -> general10.secondMin++
                                    2 -> general10.secondMean++
                                    3 -> general10.secondMax++
                                }
                                when (student.generalTrain11First){
                                    1 -> general11.firstMin++
                                    2 -> general11.firstMean++
                                    3 -> general11.firstMax++
                                }
                                when (student.generalTrain11Second){
                                    1 -> general11.secondMin++
                                    2 -> general11.secondMean++
                                    3 -> general11.secondMax++
                                }
                            }
                        }


                        theor1 = normalizeData(theor1, resources.getString(R.string.theor1))
                        theor2 = normalizeData(theor2, resources.getString(R.string.theor2))
                        practic1 = normalizeData(practic1, resources.getString(R.string.practic1))
                        practic2 = normalizeData(practic2, resources.getString(R.string.practic2))
                        practic3 = normalizeData(practic3, resources.getString(R.string.practic3))
                        general1 = normalizeData(general1, resources.getString(R.string.general1))
                        general2 = normalizeData(general2, resources.getString(R.string.general2))
                        general3 = normalizeData(general3, resources.getString(R.string.general3))
                        general4 = normalizeData(general4, resources.getString(R.string.general4))
                        general5 = normalizeData(general5, resources.getString(R.string.general5))
                        general6 = normalizeData(general6, resources.getString(R.string.general6))
                        general7 = normalizeData(general7, resources.getString(R.string.general7))
                        general8 = normalizeData(general8, resources.getString(R.string.general8))
                        general9 = normalizeData(general9, resources.getString(R.string.general9))
                        general10 = normalizeData(general10, resources.getString(R.string.general10))
                        general11 = normalizeData(general11, resources.getString(R.string.general11))

//                        theor2.trainName = resources.getString(R.string.theor2)
//                        theor2.firstMin *= percent
//                        theor2.firstMean *= percent
//                        theor2.firstMax *= percent
//                        theor2.secondMin *= percent
//                        theor2.secondMean *= percent
//                        theor2.secondMax *= percent
//
//                        practic1.trainName = resources.getString(R.string.practic1)
//                        practic1.firstMin *= percent
//                        practic1.firstMean *= percent
//                        practic1.firstMax *= percent
//                        practic1.secondMin *= percent
//                        practic1.secondMean *= percent
//                        practic1.secondMax *= percent
//
//                        practic2.trainName = resources.getString(R.string.practic2)
//                        practic2.firstMin *= percent
//                        practic2.firstMean *= percent
//                        practic2.firstMax *= percent
//                        practic2.secondMin *= percent
//                        practic2.secondMean *= percent
//                        practic2.secondMax *= percent
//
//                        practic3.trainName = resources.getString(R.string.practic3)
//                        practic3.firstMin *= percent
//                        practic3.firstMean *= percent
//                        practic3.firstMax *= percent
//                        practic3.secondMin *= percent
//                        practic3.secondMean *= percent
//                        practic3.secondMax *= percent
//
//                        general1.trainName = resources.getString(R.string.general1)
//                        general1.firstMin *= percent
//                        general1.firstMean *= percent
//                        general1.firstMax *= percent
//                        general1.secondMin *= percent
//                        general1.secondMean *= percent
//                        general1.secondMax *= percent
//
//                        general2.trainName = resources.getString(R.string.general2)
//                        general2.firstMin *= percent
//                        general2.firstMean *= percent
//                        general2.firstMax *= percent
//                        general2.secondMin *= percent
//                        general2.secondMean *= percent
//                        general2.secondMax *= percent
//
//                        general3.trainName = resources.getString(R.string.general3)
//                        general3.firstMin *= percent
//                        general3.firstMean *= percent
//                        general3.firstMax *= percent
//                        general3.secondMin *= percent
//                        general3.secondMean *= percent
//                        general3.secondMax *= percent
//
//                        general4.trainName = resources.getString(R.string.general4)
//                        general4.firstMin *= percent
//                        general4.firstMean *= percent
//                        general4.firstMax *= percent
//                        general4.secondMin *= percent
//                        general4.secondMean *= percent
//                        general4.secondMax *= percent
//
//                        general5.trainName = resources.getString(R.string.general5)
//                        general5.firstMin *= percent
//                        general5.firstMean *= percent
//                        general5.firstMax *= percent
//                        general5.secondMin *= percent
//                        general5.secondMean *= percent
//                        general5.secondMax *= percent
//
//                        general6.trainName = resources.getString(R.string.general6)
//                        general6.firstMin *= percent
//                        general6.firstMean *= percent
//                        general6.firstMax *= percent
//                        general6.secondMin *= percent
//                        general6.secondMean *= percent
//                        general6.secondMax *= percent
//
//                        general7.trainName = resources.getString(R.string.general7)
//                        general7.firstMin *= percent
//                        general7.firstMean *= percent
//                        general7.firstMax *= percent
//                        general7.secondMin *= percent
//                        general7.secondMean *= percent
//                        general7.secondMax *= percent
//
//                        general8.trainName = resources.getString(R.string.general8)
//                        general8.firstMin *= percent
//                        general8.firstMean *= percent
//                        general8.firstMax *= percent
//                        general8.secondMin *= percent
//                        general8.secondMean *= percent
//                        general8.secondMax *= percent
//
//                        general9.trainName = resources.getString(R.string.general9)
//                        general9.firstMin *= percent
//                        general9.firstMean *= percent
//                        general9.firstMax *= percent
//                        general9.secondMin *= percent
//                        general9.secondMean *= percent
//                        general9.secondMax *= percent
//
//                        general10.trainName = resources.getString(R.string.general10)
//                        general10.firstMin *= percent
//                        general10.firstMean *= percent
//                        general10.firstMax *= percent
//                        general10.secondMin *= percent
//                        general10.secondMean *= percent
//                        general10.secondMax *= percent
//
//                        general11.trainName = resources.getString(R.string.general11)
//                        general11.firstMin *= percent
//                        general11.firstMean *= percent
//                        general11.firstMax *= percent
//                        general11.secondMin *= percent
//                        general11.secondMean *= percent
//                        general11.secondMax *= percent

                        graphData.add(theor1)
                        graphData.add(theor2)
                        graphData.add(practic1)
                        graphData.add(practic2)
                        graphData.add(practic3)
                        graphData.add(general1)
                        graphData.add(general2)
                        graphData.add(general3)
                        graphData.add(general4)
                        graphData.add(general5)
                        graphData.add(general6)
                        graphData.add(general7)
                        graphData.add(general8)
                        graphData.add(general9)
                        graphData.add(general10)
                        graphData.add(general11)

                        binding.rvGraphs.adapter = GraphAdapter(graphData, this)
//                        drawGraph(graphData)
                    }
                }
        }
    }

    private fun normalizeData(graphInfo: GraphInfo, resourceName: String) : GraphInfo {
        val firstMin = graphInfo.firstMin
        val secondMin = graphInfo.secondMin
        val firstMean = graphInfo.firstMean
        val secondMean = graphInfo.secondMean
        val firstMax = graphInfo.firstMax
        val secondMax = graphInfo.secondMax

        var koefFirst = 0
        var koefSecond = 0


        if (firstMin + firstMean + firstMax != 0){
            koefFirst = 100 / (firstMin + firstMean + firstMax)
        }
        if (secondMin + secondMean + secondMax != 0){
            koefSecond = 100 / (secondMin + secondMean + secondMax)
        }

        graphInfo.trainName = resourceName
        graphInfo.firstMin *= koefFirst
        graphInfo.firstMean *= koefFirst
        graphInfo.firstMax *= koefFirst
        graphInfo.secondMin *= koefSecond
        graphInfo.secondMean *= koefSecond
        graphInfo.secondMax *= koefSecond
        return graphInfo
    }

//    private fun drawGraph(graphData: MutableList<GraphInfo>) {
//
//        var l = binding.chartTheor1.legend
//        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        l.orientation = Legend.LegendOrientation.HORIZONTAL
//        val x = 0
//        var valList: ArrayList<ArrayList<BarEntry>> = ArrayList()
//        for (item in graphData){
//            var yVals: ArrayList<BarEntry> = ArrayList<BarEntry>()
//            yVals.add(BarEntry(1f, floatArrayOf(item.firstMin.toFloat(), item.firstMean.toFloat(), item.firstMax.toFloat())))
//            yVals.add(BarEntry(2f, floatArrayOf(item.secondMin.toFloat(), item.secondMean.toFloat(), item.secondMax.toFloat())))
//            valList.add(yVals)
//        }
//
//        var setList: ArrayList<BarDataSet> = ArrayList()
//        var set1: BarDataSet
//
//        if (binding.chartTheor1.data != null && binding.chartTheor1.data.dataSetCount > 0){
//            set1 = binding.chartTheor1.data.getDataSetByIndex(0) as BarDataSet
//            set1.values = valList[0]
//            binding.chartTheor1.data.notifyDataChanged()
//            binding.chartTheor1.notifyDataSetChanged()
//        }
//        else{
//            set1 = BarDataSet(valList[0], "")
//            set1.colors = getColors()
//            set1.stackLabels = arrayOf("Минимальное", "Среднее", "Максимальное")
//            val dataSets = ArrayList<IBarDataSet>()
//            dataSets.add(set1)
//            val data = BarData(dataSets)
//            data.setValueTextColor(Color.WHITE)
//            binding.chartTheor1.data = data
//        }
//
//        val labels = arrayOf("", "1 полугодие", "2 полугодие", "")
//        var xAxis = binding.chartTheor1.xAxis
//        xAxis.setCenterAxisLabels(true)
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.setDrawGridLines(true)
//        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
//
//        binding.chartTheor1.setFitBars(true)
//        binding.chartTheor1.description.isEnabled = false
//        binding.chartTheor1.invalidate()
//
//    }


//    private fun getColors(): List<Int> {
//        val colors = IntArray(3)
//        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3)
//        return colors.reversed().toList()
//    }


}