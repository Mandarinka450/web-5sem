package com.example.a3_laba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3_laba.databinding.Fragment2Binding
import com.example.a3_laba.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    lateinit var binding: Fragment3Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(inflater,container,false)
        val view: View = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            Calculator.operation = binding.operation.text.toString()
            activityCallback?.showNextFragment4()
        }
        binding.prev.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            activityCallback?.showNextFragment2()
        }
    }

    companion object{
        fun newInstance() = Fragment3()
    }
}