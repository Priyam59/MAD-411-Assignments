package com.zybooks.expensetrackerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FooterFragment : Fragment(){

    private lateinit var expensesTotal: TextView
    private var totalexpenses: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expensesTotal = view.findViewById(R.id.total)
        expensesTotal.text = "Total Expense: $$totalexpenses"
    }
    fun updateTotal(total: Double) {
        view?.findViewById<TextView>(R.id.total)?.text = "Total: $%.2f".format(total)
    }

}