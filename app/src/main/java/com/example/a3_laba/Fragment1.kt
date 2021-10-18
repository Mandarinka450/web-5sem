package com.example.a3_laba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3_laba.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    lateinit var binding: Fragment1Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater)
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            tryToSetOperand(binding.first.text.toString(), true)
            activityCallback?.showNextFragment2()
        }
        binding.exit.setOnClickListener() {
            val activityCallback = requireActivity() as? ActivityCallback
            activityCallback?.ExitFragment()
        }
    }

    companion object{
        fun newInstance() = Fragment1()
    }
}