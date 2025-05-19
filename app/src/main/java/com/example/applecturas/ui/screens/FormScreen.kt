package com.example.applecturas.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.applecturas.R
import com.example.applecturas.viewmodel.GastoViewModel
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun FormScreen(viewModel: GastoViewModel, onGuardar: () -> Unit) {
    val sdf = remember { SimpleDateFormat("dd-MM-yyyy") }
    val fechaActual = sdf.format(Date())
    viewModel.fecha = fechaActual

    var valorTexto by remember { mutableStateOf(viewModel.valor) }
    var tipoSeleccionado by remember { mutableStateOf(viewModel.tipo.ifBlank { "agua" }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(stringResource(R.string.form_title), style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = valorTexto,
            onValueChange = {
                valorTexto = it
                viewModel.valor = it
            },
            label = { Text(stringResource(R.string.valor_hint)) },
            placeholder = { Text("Ingresar monto") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(R.string.medidor_de), style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))

        val opciones = listOf("agua", "luz", "gas")
        opciones.forEach { tipo ->
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = tipoSeleccionado == tipo,
                    onClick = {
                        tipoSeleccionado = tipo
                        viewModel.tipo = tipo
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(tipo.replaceFirstChar { it.uppercase() })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = fechaActual,
            onValueChange = {},
            label = { Text("Fecha") },
            placeholder = { Text("Fecha") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.tipo = tipoSeleccionado
                viewModel.valor = valorTexto
                viewModel.fecha = fechaActual
                viewModel.guardarGasto()
                onGuardar()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.guardar_btn))
        }
    }
}
