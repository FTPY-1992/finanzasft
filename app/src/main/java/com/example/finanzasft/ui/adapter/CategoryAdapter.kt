package com.example.finanzasft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzasft.R
import com.example.finanzasft.data.Category

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val category = categories[position]

        holder.ivIcon.setImageResource(category.iconResId)
        holder.tvName.text = category.displayName

        holder.itemView.setOnClickListener {
            onCategoryClick(category)
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivIcon: ImageView = itemView.findViewById(R.id.ivCategoryIcon)
        val tvName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }


}