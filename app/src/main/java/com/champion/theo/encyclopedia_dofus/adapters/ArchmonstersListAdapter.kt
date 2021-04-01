package com.champion.theo.encyclopedia_dofus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.champion.theo.encyclopedia_dofus.databinding.MonsterItemBinding
import com.champion.theo.encyclopedia_dofus.models.Monster

class ArchmonstersListAdapter(
    val callback: ArchmonstersListHandler
) : RecyclerView.Adapter<ArchmonstersListAdapter.ViewHolder>() {
    /**
     * Monster collection
     */
    private var monsters: List<Monster> = ArrayList<Monster>()

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
        fillViewHolderData(holder, position)
    }

    override fun getItemCount(): Int {
        var itemCount = 0
        itemCount = monsters.size
        return itemCount
    }

    private fun fillViewHolderData(holder: ViewHolder, position: Int) {
        val archMonster: Monster = monsters[position]
        holder.binding.itemListName.text = archMonster.name
        val context = holder.binding.root.context
        Glide.with(context)
                .load(archMonster.imgUrl)
                .into(holder.binding.itemListIcon)
    }

    fun setMonsters(monsters: List<Monster>) {
        this.monsters = monsters
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MonsterItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
