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
import com.champion.theo.encyclopedia_dofus.di.DI
import com.champion.theo.encyclopedia_dofus.models.Monster
import com.champion.theo.encyclopedia_dofus.models.OwnedMonster
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ArchmonstersFragment : Fragment(), ArchmonstersListHandler {

    /**
     * Binding
     */
    private var _binding: ArchmonstersFragmentBinding? = null
    private val binding get() = _binding!!

    /**
     * View Model
     */
    private lateinit var viewModel: ArchmonstersViewModel

    /**
     * ExecutorService
     */
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

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
        loadData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*
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
    */

    override fun addMonster(monster: Monster) {
        this.executorService.execute {
            DI.repository.createOwnedMonster(OwnedMonster(1, monster.ankamaId!!))
        }
    }

    override fun removeMonster(monster: Monster) {
        this.executorService.execute {
            DI.repository.delete(OwnedMonster(1, monster.ankamaId!!))
        }
    }

    private fun loadMonsterDataFromAPI() {

    }

    private fun loadData() {
        val adapter = ArchmonstersListAdapter(this)
        binding.archmonstersList.adapter = adapter

        viewModel.monsters.observe(
                viewLifecycleOwner,
                Observer<List<Monster>> {
                    if (it != null) {
                        adapter.setMonsters(it)
                        Log.d("DEBUG", "Monster not null");
                    } else {
                        Log.d("DEBUG", "Monster null");
                    }
                }
        )

        viewModel.ownedMonsters.observe(
                viewLifecycleOwner,
                Observer<List<OwnedMonster>> {
                    if (it != null) {
                        adapter.setOwnedMonsters(it)
                        Log.d("DEBUG", "OwnedMonster not null");
                    } else {
                        Log.d("DEBUG", "OwnedMonster null");
                    }
                }
        )
    }
}
