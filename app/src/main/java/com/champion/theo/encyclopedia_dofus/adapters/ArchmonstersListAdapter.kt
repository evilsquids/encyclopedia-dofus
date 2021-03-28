package com.champion.theo.encyclopedia_dofus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.champion.theo.encyclopedia_dofus.databinding.MonsterItemBinding
import com.champion.theo.encyclopedia_dofus.models.Monster

class ArchmonstersListAdapter(
    items: List<Monster>,
    val callback: ArchmonstersListHandler
) : RecyclerView.Adapter<ArchmonstersListAdapter.ViewHolder>() {
    private val monsters: List<Monster> = items

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: MonsterItemBinding = MonsterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monster: Monster = monsters[position]
        /*
        holder.binding.itemListFavoriteButton.setOnClickListener {
            callback.onAddMonster(monster)
        }
        */
        holder.binding.itemListName.text = monster.name
        val context = holder.binding.root.context
        Glide.with(context)
            .load(monster.imgUrl)
            .into(holder.binding.itemListIcon)
    }

    override fun getItemCount(): Int {
        var itemCount = 0
        itemCount = monsters.size
        return itemCount
    }

    class ViewHolder(val binding: MonsterItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
