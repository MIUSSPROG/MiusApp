package com.example.miusapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.Model.GraphInfo
import com.example.miusapp.Model.Group
import com.example.miusapp.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class GraphAdapter internal constructor(
    items: List<GraphInfo>,
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: List<GraphInfo> = ArrayList()
    var context: Context

    init {
        this.items = items
        this.context = context
    }

    class GraphViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val chartName: TextView = itemView.findViewById(R.id.chartName)
        val chart: BarChart = itemView.findViewById(R.id.chart)

        @SuppressLint("ResourceType")
        fun bind(context: Context, data: GraphInfo, position: Int){
            chartName.text = data.trainName

            var l = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            var yVals = ArrayList<BarEntry>()
            yVals.add(BarEntry(1f, floatArrayOf(data.firstMin.toFloat(), data.firstMean.toFloat(), data.firstMax.toFloat())))
            yVals.add(BarEntry(2f, floatArrayOf(data.secondMin.toFloat(), data.secondMean.toFloat(), data.secondMax.toFloat())))

            var set1: BarDataSet
            if (chart.data != null && chart.data.dataSetCount > 0){
                set1 = chart.data.getDataSetByIndex(0) as BarDataSet
                set1.values = yVals
                chart.data.notifyDataChanged()
                chart.notifyDataSetChanged()
            }
            else{
                set1 = BarDataSet(yVals, "")
                set1.colors = getColors()
                set1.stackLabels = arrayOf("Минимальное", "Среднее", "Максимальное")
                val dataSets = ArrayList<IBarDataSet>()
                dataSets.add(set1)
                val data = BarData(dataSets)
                data.setValueTextColor(Color.WHITE)
                chart.data = data
            }

            val labels = arrayOf("", "1 полугодие", "2 полугодие", "")
            var xAxis = chart.xAxis
            xAxis.setCenterAxisLabels(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(true)
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            chart.setFitBars(true)
            chart.description.isEnabled = false
            chart.invalidate()
        }

        private fun getColors(): List<Int> {
            val colors = IntArray(3)
            System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3)
            return colors.reversed().toList()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GraphViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.graph_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GraphViewHolder -> {
                holder.bind(context, items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}