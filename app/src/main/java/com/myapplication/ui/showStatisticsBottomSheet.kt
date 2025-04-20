package com.myapplication.ui

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.myapplication.databinding.BottomSheetStatisticsBinding

fun showStatisticsBottomSheet(context: Context, data: List<String>, page: Int = 1) {
    val dialog = BottomSheetDialog(context)
    val binding = BottomSheetStatisticsBinding.inflate(LayoutInflater.from(context))
    
    // Page info
    val itemCount = data.size
    binding.tvPageInfo.text = "Page $page ($itemCount items)"

    // Character frequency
    val charFrequency = data.joinToString("")
        .lowercase()
        .filter { it.isLetter() }
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }
        .take(3)
        .joinToString("\n") { "${it.first} = ${it.second}" }

    binding.tvCharStats.text = charFrequency

    dialog.setContentView(binding.root)
    dialog.show()
}
