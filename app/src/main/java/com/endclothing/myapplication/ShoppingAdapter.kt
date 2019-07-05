package com.endclothing.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ShoppingAdapter(context: Context, val shoppingitems: List<ProductsItem>) :
    RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
    var c = context;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.shoppinglist_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return shoppingitems.size
    }

    override fun onBindViewHolder(holder: ShoppingAdapter.ViewHolder, position: Int) {
        holder.bindItems(shoppingitems[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(
                c,
                "Clicked on : " + shoppingitems[position].id + " " + shoppingitems[position].name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: ProductsItem) {
            val textitemname = itemView.findViewById(R.id.itemname_txt) as TextView
            val textprice = itemView.findViewById(R.id.itemprice_txt) as TextView
            val item_img = itemView.findViewById(R.id.item_img) as ImageView
            textitemname.text = user.name
            textprice.text = user.price
            Glide.with(c)
                .load(user.image)
                .into(item_img);

        }
    }
}