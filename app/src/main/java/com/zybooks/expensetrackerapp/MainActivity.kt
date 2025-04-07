package com.zybooks.expensetrackerapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var editAmount: EditText
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseList: MutableList<Expense>
    private lateinit var adapter: ExpenseAdapter
    private lateinit var tipButton: Button

    private lateinit var headerFragment: HeaderFragment
    private lateinit var footerFragment: FooterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ActivityLifecycle", "onCreate is called")

        editName = findViewById(R.id.name)
        editAmount = findViewById(R.id.amount)
        addButton = findViewById(R.id.add)
        recyclerView = findViewById(R.id.expenses)
        tipButton = findViewById(R.id.tip)

        footerFragment = FooterFragment()
        supportFragmentManager.beginTransaction().replace(R.id.footer,footerFragment).addToBackStack(null).commit()

        headerFragment = HeaderFragment()
        supportFragmentManager.beginTransaction().replace(R.id.header,headerFragment).addToBackStack(null).commit()

        expenseList = mutableListOf()
        adapter = ExpenseAdapter(expenseList,footerFragment)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val name = editName.text.toString().trim()
            val amount = editAmount.text.toString().trim()

            val amountDouble = amount.toDoubleOrNull() ?: 0.0


            val expense = Expense(name, amountDouble.toString())

            expenseList.add(expense)
            adapter.notifyDataSetChanged()
            updateTotalExpenses()
            editName.text.clear()
            editAmount.text.clear()
        }

        tipButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.manulife.ca/personal/plan-and-learn/healthy-finances/financial-planning/ten-simple-money-management-tips.html"))

            startActivity(intent)
        }

        updateTotalExpenses()

    }

    fun updateTotalExpenses() {
        val total = expenseList.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
        footerFragment.updateTotal(total)
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