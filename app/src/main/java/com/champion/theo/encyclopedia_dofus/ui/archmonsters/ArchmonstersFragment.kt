package com.champion.theo.encyclopedia_dofus.ui.archmonsters

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

class ArchmonstersFragment : Fragment() {

    private lateinit var archmonstersViewModel: ArchmonstersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        archmonstersViewModel =
            ViewModelProvider(this).get(ArchmonstersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_archmonsters, container, false)
        val textView: TextView = root.findViewById(R.id.text_archmonsters)

        archmonstersViewModel.monsters.observe(
            viewLifecycleOwner,
            Observer {
                textView.text = "Hey"
                if (it != null) {
                    Log.d("monsters", it.toString())
                }
            }
        )

        return root
    }
}
