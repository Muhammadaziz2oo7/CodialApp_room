package uz.muhamadaziz.roomcodialapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.muhamadaziz.roomcodialapp.databinding.StudentItemBinding
import uz.muhamadaziz.roomcodialapp.model.Student

class StudentRvAdapter(var onItemClick: OnItemClick, var itemList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentRvAdapter.StudentVh>() {

    inner class StudentVh(private val binding: StudentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(student: Student, position: Int) {
            binding.root.setOnClickListener {
                onItemClick.onItemClick(student, position)
            }
            binding.studentFullName.text = "${student.name} ${student.surname}"
            binding.edit.setOnClickListener { onItemClick.onItemEditClick(student, position) }
            binding.delete.setOnClickListener { onItemClick.onItemDeleteClick(student, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVh {
        return StudentVh(
            StudentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentVh, position: Int) {
        holder.onBind(itemList[position], position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClick {
        fun onItemClick(student: Student, position: Int)
        fun onItemEditClick(student: Student, position: Int)
        fun onItemDeleteClick(student: Student, position: Int)
    }
}