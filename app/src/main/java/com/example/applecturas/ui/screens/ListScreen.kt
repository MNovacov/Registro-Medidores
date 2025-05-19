package com.example.applecturas.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.applecturas.R
import com.example.applecturas.model.Gasto
import com.example.applecturas.viewmodel.GastoViewModel

@Composable
fun ListScreen(viewModel: GastoViewModel, onAgregar: () -> Unit) {
    val gastos = viewModel.gastos.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregar) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = stringResource(id = R.string.list_title),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {
                items(gastos.value) { gasto ->
                    val icon = when (gasto.tipo.lowercase()) {
                        "agua" -> Icons.Filled.WaterDrop
                        "luz" -> Icons.Filled.Bolt
                        "gas" -> Icons.Filled.LocalFireDepartment
                        else -> Icons.Filled.Info
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = gasto.tipo,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .padding(end = 8.dp)
                                )
                                Column {
                                    Text(text = "Tipo: ${gasto.tipo}")
                                    val valorFormateado = "%,.0f".format(gasto.valor.toFloat()).replace(",", ".")
                                    Text(text = "Valor: $$valorFormateado")
                                    Text(text = "Fecha: ${gasto.fecha}")
                                }
                            }

                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = stringResource(id = R.string.eliminar),
                                modifier = Modifier
                                    .clickable { viewModel.eliminarGasto(gasto) }
                                    .padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
