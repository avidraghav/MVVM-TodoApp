package com.raghav.mvvmtodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.raghav.mvvmtodo.databinding.ItemTaskBinding
import com.raghav.mvvmtodo.models.TaskEntity
import com.raghav.mvvmtodo.ui.AllTasksFragmentDirections

class TasksAdapter(
    private val tasks: List<TaskEntity>,
    private val onItemClickListener: (ItemTaskBinding, TaskEntity) -> Unit = { _: ItemTaskBinding, _: TaskEntity -> }
) :
    RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {

        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskEntity) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description

            binding.root.setOnClickListener {
                val action =
                    AllTasksFragmentDirections.actionAllTasksFragmentToAddTaskFragment(item)
                it.findNavController().navigate(action)
            }
            onItemClickListener(binding, item)
        }
    }

}