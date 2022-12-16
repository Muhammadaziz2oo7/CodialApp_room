package uz.muhamadaziz.roomcodialapp.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.muhamadaziz.roomcodialapp.databinding.GroupItemBinding
import uz.muhamadaziz.roomcodialapp.model.Group

class GroupRvAdapter(var onItemClick: OnItemClick, var itemList: ArrayList<Group>) :
    RecyclerView.Adapter<GroupRvAdapter.GroupVh>() {

    inner class GroupVh(private val binding: GroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(group: Group, position: Int) {
            binding.root.setOnClickListener {
                onItemClick.onItemClick(group, position)
            }
            binding.groupTitle.text = group.groupTitle

            binding.see.setOnClickListener {
                onItemClick.onItemClick(group, position)
            }
            binding.edit.setOnClickListener {
                onItemClick.onItemEditClick(group, position)
            }
            binding.delete.setOnClickListener {
                onItemClick.onItemDeleteClick(group, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupVh {
        return GroupVh(
            GroupItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GroupVh, position: Int) {
        holder.onBind(itemList[position], position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClick {
        fun onItemClick(group: Group, position: Int)
        fun onItemEditClick(group: Group, position: Int)
        fun onItemDeleteClick(group: Group, position: Int)
    }
}