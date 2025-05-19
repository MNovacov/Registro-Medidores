package com.example.applecturas.data

import androidx.room.*
import com.example.applecturas.model.Gasto
import kotlinx.coroutines.flow.Flow

@Dao
interface GastoDao {
    @Query("SELECT * FROM gastos ORDER BY id DESC")
    fun getAll(): Flow<List<Gasto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gasto: Gasto)

    @Delete
    suspend fun delete(gasto: Gasto)
}
