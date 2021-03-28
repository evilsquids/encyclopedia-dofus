package com.champion.theo.encyclopedia_dofus.ui.archmonsters

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.champion.theo.encyclopedia_dofus.R
import com.champion.theo.encyclopedia_dofus.adapters.ArchmonstersListAdapter
import com.champion.theo.encyclopedia_dofus.adapters.ArchmonstersListHandler
import com.champion.theo.encyclopedia_dofus.databinding.ArchmonstersFragmentBinding
import com.champion.theo.encyclopedia_dofus.models.Monster

class ArchmonstersFragment : Fragment(), ArchmonstersListHandler {

    private lateinit var binding: ArchmonstersFragmentBinding
    private lateinit var adapter: ArchmonstersListAdapter
    private lateinit var archmonstersViewModel: ArchmonstersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArchmonstersFragmentBinding.inflate(inflater, container, false)
        binding.archmonstersList.layoutManager = LinearLayoutManager(context)
        binding.archmonstersList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        archmonstersViewModel = ViewModelProvider(this).get(ArchmonstersViewModel::class.java)
        archmonstersViewModel.monsters.observe(
            viewLifecycleOwner,
            Observer {
                binding.textArchmonsters.text = "Liste Archimonstres"
                if (it != null) {
                    Log.d("monsters", it.toString())
                }
            }
        )

        /*
        archmonstersViewModel.ownedMonsters.observe(
            viewLifecycleOwner,
            Observer<List<OwnedMonster>> { t ->
                Log.d("ownedMonster", t.toString())
            }
        )
        */

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    override fun onAddMonster(monster: Monster) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_icon_dofus)
            .setTitle("Ajouter ce monstre ?")
            .setMessage("Êtes-vous sûr de vouloir ajouter ce monstre à vos favoris ?")
            .setPositiveButton(
                "Ajouter",
                DialogInterface.OnClickListener { dialog, which -> addMonster(monster, adapter) }
            )
            .setNegativeButton("Annuler", null)
            .show()
    }

    fun addMonster(monster: Monster, adapter: ArchmonstersListAdapter) {
        TODO("Not yet implemented")
    }

    private fun setData() {
        archmonstersViewModel.monsters.observe(
            viewLifecycleOwner,
            Observer {
                binding.textArchmonsters.text = "Liste Archimonstres"
                if (it != null) {
                    Log.d("monsters", it.toString())
                    val adapter = ArchmonstersListAdapter(it, this)
                    binding.archmonstersList.adapter = adapter
                }
            }
        )
    }
}
