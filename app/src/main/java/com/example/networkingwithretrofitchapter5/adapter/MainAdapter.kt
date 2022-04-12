package com.example.networkingwithretrofitchapter5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.networkingwithretrofitchapter5.databinding.ItemContentBinding
import com.example.networkingwithretrofitchapter5.fragment.HomeFragmentDirections
import com.example.networkingwithretrofitchapter5.model.GetAllCarResponseItem

class MainAdapter(
    private val onItemClick: OnClickListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<GetAllCarResponseItem>() {
        override fun areItemsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitData(value: List<GetAllCarResponseItem>?) = differ.submitList(value)

    interface OnClickListener {
        fun onClickItem(data: GetAllCarResponseItem)
    }

    /*    class ViewHolder(val binding: ItemContentBinding) : RecyclerView.ViewHolder(binding.root)*/
    inner class ViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetAllCarResponseItem) {
            binding.apply {
                nameTextView.text = data.name
                priceTextView.text = data.price.toString()
                detailButton.setOnClickListener {
                    val user = GetAllCarResponseItem(
                        category = data.category,
                        createdAt = data.createdAt,
                        finishRentAt = data.finishRentAt,
                        name = data.name,
                        price = data.price,
                        image = data.image,
                        startRentAt = data.startRentAt,
                        status = data.status,
                        updatedAt = data.updatedAt
                    )
                    it.findNavController()
                        .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(user))
                }

                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }
    override fun getItemCount(): Int = differ.currentList.size



}