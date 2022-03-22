package com.example.nestedlayouts

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currentNumber = 0

        findViewById<LinearLayout>(R.id.main_layout).also {
            val layoutsArray = ArrayList<ArrayList<TextView>>().apply {
                for (j : Int in 0 until it.childCount - 1) {
                    val textArray = ArrayList<TextView>()

                    for (i : Int in 0 until (it.getChildAt(j) as ViewGroup).childCount) {
                        textArray.add(((it.getChildAt(j) as ViewGroup).getChildAt(i) as TextView))
                    }
                    this.add(textArray)
                }
            }

            findViewById<Button>(R.id.roll_button).setOnClickListener {
                currentNumber++

                layoutsArray.forEach {
                    it.clearField()
                    it[currentNumber % 3].text = (currentNumber + 1).toString()
                }
            }
        }

    }
}

fun ArrayList<TextView>.clearField() = this.forEach { it.text = "" }