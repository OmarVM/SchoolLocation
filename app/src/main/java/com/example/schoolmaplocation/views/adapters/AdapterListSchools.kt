package com.example.schoolmaplocation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolmaplocation.R
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.views.OnClickItemAdapter
import kotlinx.android.synthetic.main.item_school_list.view.*

class AdapterListSchools (val listSchools: ArrayList<School>, val view: OnClickItemAdapter) : RecyclerView.Adapter<AdapterListSchools.ItemSchoolHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSchoolHolder {
        var layoutInflate = LayoutInflater.from(parent.context).inflate(R.layout.item_school_list, parent, false)
        return ItemSchoolHolder(layoutInflate)
    }

    override fun getItemCount(): Int {
        return listSchools.size
    }

    override fun onBindViewHolder(holder: ItemSchoolHolder, position: Int) {
        var item = listSchools[position]
        holder.BindItem(item, view)
    }

    class ItemSchoolHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        fun BindItem(item: School, viewItem: OnClickItemAdapter){
            itemView.school_name.text = item.name
            itemView.school_address.text = item.formatted_address
            itemView.school_rating.text = item.rating.toString()

            itemView.setOnClickListener {
                viewItem.onClickItem(item)
            }
        }
    }

}