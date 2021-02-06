package com.threecats.fastsmoothscrolltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.threecats.fastsmoothscroll.FastSmoothScroll
import com.threecats.fastsmoothscroll.fastSmoothScrollToPosition
import kotlin.random.Random

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val size = 1000

        val rv: RecyclerView = findViewById(R.id.rv)

        val adapter = ExampleNumberAdapter(size)
        rv.setHasFixedSize(true)
        rv.adapter = adapter

        findViewById<Button>(R.id.buttonRandom).setOnClickListener {
            val newPosition = Random.nextInt(size)
            adapter.selectedPosition = newPosition

            rv.scrollToPosition(newPosition)
        }
        findViewById<Button>(R.id.buttonRandomSmooth).setOnClickListener {
            val newPosition = Random.nextInt(size)
            adapter.selectedPosition = newPosition

            rv.smoothScrollToPosition(newPosition)
        }
        findViewById<Button>(R.id.buttonRandomFS).setOnClickListener {
            val newPosition = Random.nextInt(size)
            adapter.selectedPosition = newPosition

            rv.fastSmoothScrollToPosition(newPosition)
        }
    }
}