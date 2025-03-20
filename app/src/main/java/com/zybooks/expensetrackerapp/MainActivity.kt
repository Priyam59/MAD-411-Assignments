package com.zybooks.expensetrackerapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
        private lateinit var editName: EditText
        private lateinit var editAmount: EditText
        private lateinit var addButton: Button
        private lateinit var recyclerView: RecyclerView
        private lateinit var expenseList: MutableList<Expense>
        private lateinit var adapter: ExpenseAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            editName = findViewById(R.id.name)
            editAmount = findViewById(R.id.amount)
            addButton = findViewById(R.id.add)
            recyclerView = findViewById(R.id.expenses)

            expenseList = mutableListOf()
            adapter = ExpenseAdapter(expenseList)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            addButton.setOnClickListener {
                val name = editName.text.toString().trim()
                val amount = editAmount.text.toString().trim()

                val expense = Expense(name, amount)
                expenseList.add(expense)
                adapter.notifyDataSetChanged()

                editName.text.clear()
                editAmount.text.clear()
            }
        }
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy is called")
    }
    }