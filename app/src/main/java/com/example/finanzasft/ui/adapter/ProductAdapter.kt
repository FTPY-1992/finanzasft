package com.example.finanzasft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzasft.R
import com.example.finanzasft.data.ProductEntity

class ProductAdapter(
    private val products: MutableList<ProductEntity> = mutableListOf()
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    //ViewHolder: clase que mantiene las referencias a las vistas de cada fila
    class ProductViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvBrandDescription: TextView = itemView.findViewById(R.id.tvBrandDescription)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvBought : TextView = itemView.findViewById(R.id.tvBought)
        val tvConsumed: TextView = itemView.findViewById(R.id.tvConsumed)
        val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
    }

    //Crea una nueva fila (infla el layout item_product.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product,parent,false)
        return ProductViewHolder(view)
    }

    //Conecta los datos del producto con las vistas de la fila
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.tvProductName.text = product.name
        //Marca y descripcion combinadas (si no hay, muestra "-")
        val brandDesc = buildString {
            if (!product.brand.isNullOrBlank()) append(product.brand)
            if (!product.description.isNullOrBlank()){
                if(isNotEmpty()) append("-")
                append(product.description)
            }
            if (isEmpty()) append("-")
        }
        holder.tvBrandDescription.text = brandDesc
        holder.tvPrice.text = "Precio: $${"%.2f".format(product.price)}"
        holder.tvBought.text = "Compradas: ${product.quantityBought}"
        holder.tvConsumed.text = "Consumidas: ${product.quantityConsumed}"
        //Foto: por ahora invisible (despues lo usamos)
        holder.ivPhoto.visibility = View.GONE
    }

    //Cuantas filas hay
    override fun getItemCount(): Int = products.size

    //Metodo para actualizar la lista completa (lo llamamos desde la Activity)
    fun updateProducts(newProducts: List<ProductEntity>){
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged() //Actualiza la vista (simple por ahora)
    }

}