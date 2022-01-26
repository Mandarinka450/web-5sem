package com.example.myapplication.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.Node
import com.example.myapplication.data.Connect
import com.example.myapplication.data.Dao
import kotlinx.coroutines.flow.Flow


class ConnectionsViewModel(private val nodeDao: Dao) : ViewModel() {
    fun getNode(id: Int): Node = nodeDao.getNodeById(id)
    fun getNodeConnection(id: Int): Flow<Node> = nodeDao.getNodeByIdConnection(id)
    fun getListConnection(): Flow<List<Node>> = nodeDao.AllConnection()

    fun changeConnection(connection: Connect): Boolean {
        val From = nodeDao.getNodeById(connection.from.id)
        val To = nodeDao.getNodeById(connection.to.id)
        var result: Boolean
        if (From.nodes.map { it.id }.contains(To.id)) {
            deleteRelation(From, To,)
            result = true
        } else {
            addRelation(From, To)
            result = false
        }
        return result
    }

    private fun addRelation(parent: Node, child: Node) {
        val updatedParent = parent.nodes.toMutableList()
        updatedParent.add(child)
        val newParent = Node(parent.id, parent.value, updatedParent)
        nodeDao.updateNode(newParent)
    }

    private fun deleteRelation(parent: Node, child: Node) {
        val updatedParent = parent.nodes.toMutableList().filter { it.id != child.id }
        val newParent = Node(parent.id, parent.value, updatedParent)
        nodeDao.updateNode(newParent)
    }

    private var isFilterOnChildren = true
    fun setChildFilter(isChildren: Boolean) {
        isFilterOnChildren = isChildren
    }
}

class ConnectionsViewModelFactory(private val nodeDao: Dao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConnectionsViewModel(nodeDao) as T
        }
        throw IllegalArgumentException("Unknown Model class")
    }
}