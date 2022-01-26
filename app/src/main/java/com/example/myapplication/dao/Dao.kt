package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    // различные запросы на чтение и обновление данных
    @Query("SELECT * FROM Node ORDER BY id ASC")
    fun AllConnection(): Flow<List<Node>>

    @Query("SELECT * FROM Node WHERE id = :nodeId")
    fun getNodeByIdConnection(nodeId: Int): Flow<Node>

    @Query("SELECT * FROM Node WHERE id = :nodeId")
    fun getNodeById(nodeId: Int): Node

    @Insert
    fun pushNodes(vararg nodes: Node)

    @Update
    fun updateNode(node: Node)

    @Query("SELECT COUNT(id) FROM Node")
    fun getSize(): Int
}