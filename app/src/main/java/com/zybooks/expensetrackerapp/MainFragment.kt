package com.zybooks.expensetrackerapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

private const val FILE_NAME = "expenses.txt"

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseList: MutableList<Expense>
    private lateinit var adapter: ExpenseAdapter
    private lateinit var footerFragment: FooterFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mainfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.expenses)

        expenseList = loadTasksFromFile(requireContext()).toMutableList()
        adapter = ExpenseAdapter(expenseList, footerFragment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    fun onTaskAdded(adding : Expense) {
        expenseList.add(adding)
        adapter.notifyItemInserted(expenseList.size - 1)
        saveTasksToFile(requireContext(), expenseList)
    }

    fun onTaskDelete(delete: Expense) {
        val index = expenseList.indexOfFirst { it.name == delete.name }
        if (index != -1) {
            expenseList[index] = delete
            adapter.notifyItemChanged(index)
            saveTasksToFile(requireContext(), expenseList)
        }
    }

    private fun saveTasksToFile(context: Context, expenseList: List<Expense>) {
        try {
            val json = Gson().toJson(expenseList)
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { output ->
                output.write(json.toByteArray())
            }
            Log.d("FileStorage", "Expenses saved successfully")
        } catch (e: IOException) {
            Log.e("FileStorage", "Error saving expenses: ${e.message}")
            Toast.makeText(context, "Error saving expenses.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadTasksFromFile(context: Context): MutableList<Expense> {
        val expenseList: MutableList<Expense> = mutableListOf()
        try {
            val file = File(context.filesDir, FILE_NAME)
            if (!file.exists()) return expenseList

            val json = file.readText()
            val type = object : TypeToken<List<Expense>>() {}.type
            return Gson().fromJson(json, type) ?: mutableListOf()
        } catch (e: Exception) {
            Log.e("FileStorage", "Error loading expenses: ${e.message}")
            Toast.makeText(context, "Error loading expenses.", Toast.LENGTH_SHORT).show()
            return mutableListOf()
        }
    }
}