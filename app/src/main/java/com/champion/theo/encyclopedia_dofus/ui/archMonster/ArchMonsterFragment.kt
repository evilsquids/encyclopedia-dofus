package com.champion.theo.encyclopedia_dofus.ui.archMonster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.champion.theo.encyclopedia_dofus.R

class ArchMonsterFragment : Fragment() {

    private lateinit var archMonsterViewModel: ArchMonsterViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        archMonsterViewModel =
                ViewModelProvider(this).get(ArchMonsterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_archmonster, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)

        archMonsterViewModel.monsters.observe(
                viewLifecycleOwner,
                Observer {
                    if (it != null) {
                        Log.d("monsters", it.toString())
                    }
                }
        )

        return root
    }
}