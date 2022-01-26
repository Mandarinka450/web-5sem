package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ListFragmentBinding
import com.example.myapplication.data.Node
import com.example.myapplication.data.Application
import com.example.myapplication.data.NodeData
import com.example.myapplication.models.NodeListViewModel
import com.example.myapplication.models.NodeListViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class NodeListFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: ListFragmentBinding? = null

    private val Model: NodeListViewModel by activityViewModels {
        NodeListViewModelFactory(
            (activity?.application as Application).database.nodeDao()
        )
    }

    private val nodeListAdapter = Adapter { navigateToNodeId(it) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNode.setOnClickListener { _ ->
            val action = { value: Int -> addNode(value) }
            val addNodeDialog = AddNode(action)
            fragmentManager?.let { addNodeDialog.show(it, AddNode.TAG) }
        }
        val recyclerNode = binding.nodeListRecycler
        recyclerNode.layoutManager = LinearLayoutManager(requireContext())
        recyclerNode.adapter = nodeListAdapter
        lifecycle.coroutineScope.launch {
            val nodesFlow = Model.getNodesList()
            nodesFlow.collect {
                updateNodeList(it)
            }
        }
    }

    private fun updateNodeList(values: List<Node>) {
        val ChildConnect = values.map { it.nodes }.toMutableList()
        val nodesParents = ChildConnect.flatten().map { it.id }
        val nodes = values.map {
            NodeData(
                it.id,
                it.value,
                it.nodes.isNotEmpty(),
                nodesParents.contains(it.id)
            )
        }
        nodeListAdapter.submitList(nodes)
    }
    private fun addNode(value: Int) {
        lifecycle.coroutineScope.launch {
            Model.addNode(value)
        }
    }

    private fun navigateToNodeId(NodeId: Int) {
        val action = NodeListFragmentDirections.actionFirstFragmentToSecondFragment(NodeId)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


