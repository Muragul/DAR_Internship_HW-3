package com.example.studentlistadvanced

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val listener: ItemClickListener) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var list = listOf<Student>()

    fun submitList(newList: List<Student>?) {
        list = newList ?: listOf()
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun itemClicked(item: Student)
    }

    interface RemoveItemClickListener {
        fun itemRemoved(item: Student)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.student_id)
        private var name = view.findViewById<TextView>(R.id.student_name)

        fun bind(item: Student) {
            id.text = item.id.toString()
            name.text = item.name

            itemView.setOnClickListener {
                listener.itemClicked(item)
            }

            itemView.findViewById<Button>(R.id.remove_student).setOnClickListener {
                val secondListener = listener as RemoveItemClickListener
                secondListener.itemRemoved(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}