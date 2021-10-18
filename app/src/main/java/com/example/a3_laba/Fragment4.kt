package com.example.a3_laba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3_laba.databinding.Fragment3Binding
import com.example.a3_laba.databinding.Fragment4Binding

class Fragment4 : Fragment() {
    lateinit var binding: Fragment4Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = Fragment4Binding.inflate(inflater,container,false)
        binding.result.text = Calculator.first.toString() + " " + Calculator.operation + " " +
                Calculator.second.toString() + " = " + Calculator.calculate().toString()
        val view: View = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.prev.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            activityCallback?.showNextFragment3()
        }
    }

    companion object{
        fun newInstance() = Fragment4()
    }
}