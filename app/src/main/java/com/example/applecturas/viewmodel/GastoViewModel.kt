package com.example.applecturas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.applecturas.data.AppDatabase
import com.example.applecturas.model.Gasto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GastoViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    private val _gastos = MutableStateFlow<List<Gasto>>(emptyList())
    val gastos: StateFlow<List<Gasto>> = _gastos

    var tipo: String = ""
    var valor: String = ""
    var fecha: String = ""

    init {
        viewModelScope.launch {
            db.gastoDao().getAll().collect { lista ->
                _gastos.value = lista
            }
        }
    }

    fun guardarGasto() {
        val valorInt = valor.toIntOrNull() ?: return
        if (tipo.isBlank() || valorInt <= 0 || fecha.isBlank()) return

        val gasto = Gasto(
            tipo = tipo.trim(),
            valor = valorInt,
            fecha = fecha.trim()
        )

        viewModelScope.launch {
            db.gastoDao().insert(gasto)
        }
    }

    fun eliminarGasto(gasto: Gasto) {
        viewModelScope.launch {
            db.gastoDao().delete(gasto)
        }
    }
}
