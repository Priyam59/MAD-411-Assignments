package com.zybooks.expensetrackerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter (private val expenseList: MutableList<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

        inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val expenseName: TextView = itemView.findViewById(R.id.name)
            val expenseAmount: TextView = itemView.findViewById(R.id.amount)
            val deleteButton: Button = itemView.findViewById(R.id.delete)
            val showButton : Button = itemView.findViewById(R.id.details)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle, parent, false)
            return ExpenseViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
            val currentExpense = expenseList[position]
            holder.expenseName.text = currentExpense.name
            holder.expenseAmount.text = currentExpense.amount

            holder.deleteButton.setOnClickListener {
                expenseList.removeAt(position)
                notifyItemRemoved(position)
            }

            holder.showButton.setOnClickListener{
                val intent = android.content.Intent(holder.itemView.context, ExpenseDetailsActivity::class.java)

                intent.putExtra("expenseName", currentExpense.name)
                intent.putExtra("expenseAmount", currentExpense.amount)


                holder.itemView.context.startActivity(intent)
            }
        }

    override fun getItemCount(): Int {
            return expenseList.size
        }
}