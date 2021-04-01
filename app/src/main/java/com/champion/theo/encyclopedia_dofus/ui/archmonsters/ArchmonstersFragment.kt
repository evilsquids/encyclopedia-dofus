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

    /**
     * Binding
     */
    private var _binding: ArchmonstersFragmentBinding? = null;
    private val binding get() = _binding!!;

    /**
     * View Model
     */
    private lateinit var viewModel: ArchmonstersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArchmonstersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArchmonstersFragmentBinding.inflate(inflater, container, false)
        binding.archmonstersList.layoutManager = LinearLayoutManager(context)
        binding.archmonstersList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.textArchmonsters.text = "Liste Archimonstres"

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
                DialogInterface.OnClickListener { dialog, which -> addMonster(monster) }
            )
            .setNegativeButton("Annuler", null)
            .show()
    }

    fun addMonster(monster: Monster) {

    }

    private fun setData() {
        val adapter = ArchmonstersListAdapter(this)
        binding.archmonstersList.adapter = adapter

        viewModel.monsters.observe(
                viewLifecycleOwner,
                Observer<List<Monster>> {
                    if (it != null) {
                        adapter.setMonsters(it)
                    }
                }
        )
    }
}
