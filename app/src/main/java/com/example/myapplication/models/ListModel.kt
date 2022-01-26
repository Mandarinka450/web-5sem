package com.example.myapplication.models

import  androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.Node
import com.example.myapplication.data.Dao
import kotlinx.coroutines.flow.Flow

class NodeListViewModel(private val nodeDao: Dao) : ViewModel() {
    fun getNodesList(): Flow<List<Node>> = nodeDao.AllConnection()
    private fun getLastId(): Int = nodeDao.getSize()
    fun addNode(value: Int) = nodeDao.pushNodes(Node(getLastId(), value))
}

class NodeListViewModelFactory(
    private val nodeDao: Dao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NodeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NodeListViewModel(nodeDao) as T
        }
        throw IllegalArgumentException("Unknown Model class")
    }
}