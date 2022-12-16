package uz.muhamadaziz.roomcodialapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.muhamadaziz.roomcodialapp.databinding.CourseItemBinding
import uz.muhamadaziz.roomcodialapp.model.Course

class CourseRvAdapter(var onItemClick: OnItemClick, var itemList: ArrayList<Course>) :
    RecyclerView.Adapter<CourseRvAdapter.CourseVh>() {

    inner class CourseVh(private val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(course: Course, position: Int) {
            binding.root.setOnClickListener {
                onItemClick.onItemClick(course, position)
            }
            binding.courseTitle.text = course.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVh {
        return CourseVh(
            CourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseVh, position: Int) {
        holder.onBind(itemList[position], position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClick {
        fun onItemClick(course: Course, position: Int)
    }
}