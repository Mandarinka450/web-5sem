package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ViewParentsChildBinding
import com.example.myapplication.data.*
import com.example.myapplication.models.ConnectionsViewModel
import com.example.myapplication.models.ConnectionsViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NodeFragment : Fragment() {
    private var selectedElem: Node = Node(0, 0)
    private var freeChildren: List<Connect> = emptyList()
    private val args: NodeFragmentArgs by navArgs()
    private var _binding: ViewParentsChildBinding? = null
    private val binding get() = _binding!!
    private var freeParents: List<Connect> = emptyList()
    private var isShowingChildren = true
    private val viewModel: ConnectionsViewModel by activityViewModels {
        ConnectionsViewModelFactory(
            (activity?.application as Application).database.nodeDao()
        )
    }

    private val listAdapter = ConnectionAdapter { onItemClick(it) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = ViewParentsChildBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedElem = Node(args.nodeId, 0)
        val recycler = binding.connectionsRecycler
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = listAdapter
        binding.ButtonParents.isEnabled = true
        binding.ButtonChild.isEnabled = false
        binding.ButtonChild.setOnClickListener { selectChildren() }
        binding.ButtonParents.setOnClickListener { selectParents() }
        lifecycle.coroutineScope.launch {
            val selectedNodeConnection = viewModel.getNodeConnection(args.nodeId)
            selectedNodeConnection.collect { node ->
                selectedElem = node
            }
        }

        lifecycle.coroutineScope.launch {
            val nodesListConnection = viewModel.getListConnection()
            nodesListConnection.collect { nodesList: List<Node> ->
                updateChildrenList(nodesList)
                updateParentList(nodesList)
            }
        }
        selectChildren()
    }

    private fun onItemClick(connection: Connect) {
        lifecycle.coroutineScope.launch {
            viewModel.changeConnection(connection)
        }
    }
    private fun selectChildren() {
        isShowingChildren = true
        updateButtons()
        listAdapter.submitList(freeChildren)
    }

    private fun updateButtons() {
        viewModel.setChildFilter(isShowingChildren)
        binding.ButtonParents.isEnabled = isShowingChildren
        binding.ButtonChild.isEnabled = !isShowingChildren
    }

    private fun selectParents() {
        isShowingChildren = false
        updateButtons()
        listAdapter.submitList(freeParents)
    }

    private fun extractParents(node: Node, nodesList: List<Node>): List<Node>{
        return nodesList.filter { item -> item.nodes.map { it.id }.contains(node.id) }
    }

    private fun updateChildrenList(nodes: List<Node>) {
        val actualCurrentNode = viewModel.getNode(args.nodeId)
        val restrictedValues = extractParents(actualCurrentNode, nodes).map { it.id }.toMutableList()
        restrictedValues.add(actualCurrentNode.id)
        val filteredData = nodes.filter { item -> !restrictedValues.contains(item.id) }

        val connectionsList: List<Connect> = filteredData.map { item ->
            Connect(
                actualCurrentNode,
                item,
                actualCurrentNode.nodes.map { it.id }.contains(item.id)
            )
        }
        freeChildren = connectionsList
        if (isShowingChildren) {
            listAdapter.submitList(freeChildren)
        }
    }

    private fun updateParentList(nodes: List<Node>) {
        val actualCurrentNode = viewModel.getNode(args.nodeId)
        val restrictedValues = actualCurrentNode.nodes.map { it.id }.toMutableList()
        restrictedValues.add(actualCurrentNode.id)
        val filteredData = nodes.filter { item -> !restrictedValues.contains(item.id) }
        val connectionsList: List<Connect> = filteredData.map { item ->
            Connect(
                item,
                actualCurrentNode,
                extractParents(actualCurrentNode, nodes).map { it.id }.contains(item.id)
            )
        }
        freeParents = connectionsList
        if (!isShowingChildren) {
            listAdapter.submitList(freeParents)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}