package com.mobile.azrinurvani.fragmentviewbinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.azrinurvani.fragmentviewbinding.databinding.FragmentMainBinding

//TODO 2 - Membuat BlankFragment dengan nama MainFragment
class MainFragment : Fragment() {
//
// Jika difragment binding harus di inisialisasi terlebih dahulu dengan nilai NULL
//    Karena binding perlu di destroy atau diset null secara default nya agar mudah untuk destroy lifecycle fragment
//    Jadi sebaiknya lateinit tidak digunakan untuk binding view fragment

//    TODO 9
    private var _binding : FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listener : OnFragmentButtonSelected? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //inisialisasi object FragmentMainBinding
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.btnMain.setOnClickListener {
            listener?.onButtonSelected()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentButtonSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement listener")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnFragmentButtonSelected {
        fun onButtonSelected()
    }
}