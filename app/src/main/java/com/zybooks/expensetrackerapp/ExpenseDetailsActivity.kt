package com.zybooks.expensetrackerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExpenseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_details)

        val expenseName = intent.getStringExtra("expenseName")
        val expenseAmount = intent.getDoubleExtra("expenseAmount", 0.0)


        val name = findViewById<TextView>(R.id.name)
        val amount = findViewById<TextView>(R.id.amount)


        name.text = "Name: $expenseName"
        amount.text = "Amount: $expenseAmount"
    }
}