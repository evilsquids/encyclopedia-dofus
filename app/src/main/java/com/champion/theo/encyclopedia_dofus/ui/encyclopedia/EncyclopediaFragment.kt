package com.champion.theo.encyclopedia_dofus.ui.encyclopedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.champion.theo.encyclopedia_dofus.R

class EncyclopediaFragment : Fragment() {

    private lateinit var encyclopediaViewModel: EncyclopediaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        encyclopediaViewModel =
            ViewModelProvider(this).get(EncyclopediaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_encyclopedia, container, false)
        val textView: TextView = root.findViewById(R.id.text_encyclopedia)

        encyclopediaViewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                textView.text = it
            }
        )

        return root
    }
}
