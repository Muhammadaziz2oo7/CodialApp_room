package uz.muhamadaziz.roomcodialapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.muhamadaziz.roomcodialapp.databinding.TeacherItemBinding
import uz.muhamadaziz.roomcodialapp.model.Teacher

class TeacherRvAdapter(var onItemClick: OnItemClick, var itemList: ArrayList<Teacher>) :
    RecyclerView.Adapter<TeacherRvAdapter.TeacherVh>() {

    inner class TeacherVh(private val binding: TeacherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(teacher: Teacher, position: Int) {
            binding.root.setOnClickListener {
                onItemClick.onItemClick(teacher, position)
            }
            binding.teacherFullName.text = "${teacher.name} ${teacher.surname}"
            binding.edit.setOnClickListener { onItemClick.onItemEditClick(teacher, position) }
            binding.delete.setOnClickListener { onItemClick.onItemDeleteClick(teacher, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherVh {
        return TeacherVh(
            TeacherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeacherVh, position: Int) {
        holder.onBind(itemList[position], position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClick {
        fun onItemClick(teacher: Teacher, position: Int)
        fun onItemEditClick(teacher: Teacher, position: Int)
        fun onItemDeleteClick(teacher: Teacher, position: Int)
    }
}