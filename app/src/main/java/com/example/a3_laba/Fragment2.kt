package com.example.a3_laba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3_laba.databinding.Fragment1Binding
import com.example.a3_laba.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    lateinit var binding: Fragment2Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater)
        val view: View = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            tryToSetOperand(binding.second.text.toString(), false)
            activityCallback?.showNextFragment3()
        }
        binding.prev.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            activityCallback?.showNextFragment1()
        }
    }
    companion object{
        fun newInstance() = Fragment2()
    }
}