package com.example.myapplication.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.AddNodeBinding

class AddNode(private val AddNode: (Int) -> Unit) : DialogFragment() {
    private var _binding: AddNodeBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "Dialog for add node"
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            _binding = AddNodeBinding.inflate(LayoutInflater.from(context))
            builder.setView(binding.root)
                .setMessage("Добавить новый эелемент: ")
                .setPositiveButton("Добавить") { _, _ ->
                    val valueInput = binding.addValue
                    try {
                        val value = valueInput.text.toString().toInt()
                        AddNode(value)
                    } catch (exception: Throwable) {
                    }
                }
                .setNegativeButton("Отменить") { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Invalid add activity (not null)")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}