package up.trabalho01.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val BankBlue = Color(0xFF155EEF)
private val DarkBlue = Color(0xFF0B1F44)
private val Background = Color(0xFFF6F7F9)
private val TextSecondary = Color(0xFF6B7280)
private val PositiveGreen = Color(0xFF15803D)
private val NegativeRed = Color(0xFFDC2626)

enum class TipoTransacao {
    DEPOSITO,
    RETIRADA
}

data class Transacao(
    val id: Long,
    val tipo: TipoTransacao,
    val valor: Double,
    val data: Date = Date()
)

data class Caixinha(
    val id: Long,
    val nome: String,
    val saldo: Double = 0.0,
    val icone: ImageVector = Icons.Default.Savings,
    val transacoes: List<Transacao> = emptyList()
)

@Composable
fun caixinha() {
    val caixinhas = remember {
        mutableStateListOf(
            Caixinha(1, "Viagem", icone = Icons.Default.Flight),
            Caixinha(2, "Casa", icone = Icons.Default.Home),
            Caixinha(3, "Carro", icone = Icons.Default.DirectionsCar),
            Caixinha(4, "Aliança", icone = Icons.Default.Favorite)
        )
    }

    var proximoId by remember { mutableLongStateOf(5L) }
    var proximaTransacaoId by remember { mutableLongStateOf(1L) }
    var caixinhaSelecionadaId by remember { mutableStateOf<Long?>(null) }
    var mostrarCriacao by remember { mutableStateOf(false) }

    val caixinhaSelecionada =
        caixinhas.firstOrNull { it.id == caixinhaSelecionadaId }

    if (caixinhaSelecionada == null) {
        ListaCaixinhas(
            caixinhas = caixinhas,
            onCriar = { mostrarCriacao = true },
            onSelecionar = { caixinhaSelecionadaId = it.id }
        )
    } else {
        DetalhesCaixinha(
            caixinha = caixinhaSelecionada,
            onVoltar = { caixinhaSelecionadaId = null },
            onExcluir = {
                caixinhas.removeAll { it.id == caixinhaSelecionada.id }
                caixinhaSelecionadaId = null
            },
            onAdicionar = { valor ->
                val indice = caixinhas.indexOfFirst {
                    it.id == caixinhaSelecionada.id
                }

                if (indice >= 0) {
                    val atual = caixinhas[indice]
                    val transacao = Transacao(
                        id = proximaTransacaoId++,
                        tipo = TipoTransacao.DEPOSITO,
                        valor = valor
                    )

                    caixinhas[indice] = atual.copy(
                        saldo = atual.saldo + valor,
                        transacoes = listOf(transacao) + atual.transacoes
                    )
                }
            },
            onRetirar = { valor ->
                val indice = caixinhas.indexOfFirst {
                    it.id == caixinhaSelecionada.id
                }

                if (indice >= 0) {
                    val atual = caixinhas[indice]

                    if (valor <= atual.saldo) {
                        val transacao = Transacao(
                            id = proximaTransacaoId++,
                            tipo = TipoTransacao.RETIRADA,
                            valor = valor
                        )

                        caixinhas[indice] = atual.copy(
                            saldo = atual.saldo - valor,
                            transacoes = listOf(transacao) + atual.transacoes
                        )
                    }
                }
            }
        )
    }

    if (mostrarCriacao) {
        CriarCaixinhaDialog(
            onCancelar = { mostrarCriacao = false },
            onCriar = { nome, saldoInicial ->
                val transacoes = if (saldoInicial > 0) {
                    listOf(
                        Transacao(
                            id = proximaTransacaoId++,
                            tipo = TipoTransacao.DEPOSITO,
                            valor = saldoInicial
                        )
                    )
                } else {
                    emptyList()
                }

                caixinhas.add(
                    Caixinha(
                        id = proximoId++,
                        nome = nome,
                        saldo = saldoInicial,
                        transacoes = transacoes
                    )
                )

                mostrarCriacao = false
            }
        )
    }
}

@Composable
private fun ListaCaixinhas(
    caixinhas: List<Caixinha>,
    onCriar: () -> Unit,
    onSelecionar: (Caixinha) -> Unit
) {
    val total = caixinhas.sumOf { it.saldo }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Caixinhas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        Text(
            text = "Organize seu dinheiro por objetivos.",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkBlue)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total guardado",
                        color = Color.White.copy(alpha = 0.75f)
                    )

                    Text(
                        text = formatarDinheiro(total),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Button(
                    onClick = onCriar,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = DarkBlue
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text("Criar")
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Suas caixinhas",
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "${caixinhas.size} caixinhas",
                color = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (caixinhas.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Savings,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = TextSecondary
                )

                Text(
                    text = "Nenhuma caixinha criada",
                    color = TextSecondary
                )

                TextButton(onClick = onCriar) {
                    Text("Criar a primeira")
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(
                    items = caixinhas,
                    key = { it.id }
                ) { item ->
                    CaixinhaCard(
                        caixinha = item,
                        onClick = { onSelecionar(item) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CaixinhaCard(
    caixinha: Caixinha,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(145.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(BankBlue.copy(alpha = 0.10f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = caixinha.icone,
                    contentDescription = caixinha.nome,
                    tint = BankBlue
                )
            }

            Column {
                Text(
                    text = caixinha.nome,
                    color = TextSecondary,
                    maxLines = 1
                )

                Text(
                    text = formatarDinheiro(caixinha.saldo),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun DetalhesCaixinha(
    caixinha: Caixinha,
    onVoltar: () -> Unit,
    onExcluir: () -> Unit,
    onAdicionar: (Double) -> Unit,
    onRetirar: (Double) -> Unit
) {
    var operacao by remember {
        mutableStateOf<TipoTransacao?>(null)
    }

    var confirmarExclusao by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onVoltar) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }

            Text(
                text = caixinha.nome,
                modifier = Modifier.weight(1f),
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue
            )

            IconButton(
                onClick = { confirmarExclusao = true }
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Excluir",
                    tint = NegativeRed
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 24.dp
            )
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = DarkBlue
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp)
                    ) {
                        Text(
                            text = "Saldo da caixinha",
                            color = Color.White.copy(alpha = 0.75f)
                        )

                        Text(
                            text = formatarDinheiro(caixinha.saldo),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            operacao = TipoTransacao.DEPOSITO
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BankBlue
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDownward,
                            contentDescription = null
                        )

                        Text("Adicionar")
                    }

                    OutlinedButton(
                        onClick = {
                            operacao = TipoTransacao.RETIRADA
                        },
                        enabled = caixinha.saldo > 0,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            contentDescription = null
                        )

                        Text("Retirar")
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Transações",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            if (caixinha.transacoes.isEmpty()) {
                item {
                    Text(
                        text = "Nenhuma transação realizada.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 35.dp),
                        color = TextSecondary
                    )
                }
            } else {
                items(
                    items = caixinha.transacoes,
                    key = { it.id }
                ) { transacao ->
                    TransacaoCard(transacao)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

    operacao?.let { tipo ->
        ValorDialog(
            tipo = tipo,
            saldoDisponivel = caixinha.saldo,
            onCancelar = { operacao = null },
            onConfirmar = { valor ->
                if (tipo == TipoTransacao.DEPOSITO) {
                    onAdicionar(valor)
                } else {
                    onRetirar(valor)
                }

                operacao = null
            }
        )
    }

    if (confirmarExclusao) {
        AlertDialog(
            onDismissRequest = {
                confirmarExclusao = false
            },
            title = {
                Text("Excluir caixinha?")
            },
            text = {
                Text(
                    "A caixinha “${caixinha.nome}” e suas " +
                            "transações serão excluídas."
                )
            },
            confirmButton = {
                TextButton(onClick = onExcluir) {
                    Text(
                        text = "Excluir",
                        color = NegativeRed
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        confirmarExclusao = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun TransacaoCard(transacao: Transacao) {
    val deposito =
        transacao.tipo == TipoTransacao.DEPOSITO

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (deposito) {
                    Icons.Default.ArrowDownward
                } else {
                    Icons.Default.ArrowUpward
                },
                contentDescription = null,
                tint = if (deposito) {
                    PositiveGreen
                } else {
                    NegativeRed
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (deposito) {
                        "Saldo adicionado"
                    } else {
                        "Retirada"
                    },
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = formatarData(transacao.data),
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Text(
                text = buildString {
                    append(if (deposito) "+ " else "- ")
                    append(formatarDinheiro(transacao.valor))
                },
                fontWeight = FontWeight.Bold,
                color = if (deposito) {
                    PositiveGreen
                } else {
                    NegativeRed
                }
            )
        }
    }
}

@Composable
private fun CriarCaixinhaDialog(
    onCancelar: () -> Unit,
    onCriar: (String, Double) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var saldo by remember { mutableStateOf("") }

    val saldoConvertido =
        converterValor(saldo) ?: 0.0

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text("Nova caixinha")
        },
        text = {
            Column(
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome da caixinha") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = saldo,
                    onValueChange = { saldo = it },
                    label = { Text("Saldo inicial") },
                    prefix = { Text("R$ ") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onCriar(
                        nome.trim(),
                        saldoConvertido
                    )
                },
                enabled = nome.isNotBlank() &&
                        saldoConvertido >= 0
            ) {
                Text("Criar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
private fun ValorDialog(
    tipo: TipoTransacao,
    saldoDisponivel: Double,
    onCancelar: () -> Unit,
    onConfirmar: (Double) -> Unit
) {
    var valorTexto by remember {
        mutableStateOf("")
    }

    val valor = converterValor(valorTexto)

    val saldoInsuficiente =
        tipo == TipoTransacao.RETIRADA &&
                valor != null &&
                valor > saldoDisponivel

    val valorValido =
        valor != null &&
                valor > 0 &&
                !saldoInsuficiente

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text(
                if (tipo == TipoTransacao.DEPOSITO) {
                    "Adicionar saldo"
                } else {
                    "Retirar saldo"
                }
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = valorTexto,
                    onValueChange = { valorTexto = it },
                    label = { Text("Valor") },
                    prefix = { Text("R$ ") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    isError = saldoInsuficiente,
                    singleLine = true
                )

                if (saldoInsuficiente) {
                    Text(
                        text = "Saldo insuficiente. Disponível: ${
                            formatarDinheiro(saldoDisponivel)
                        }",
                        color = NegativeRed,
                        fontSize = 12.sp
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    valor?.let(onConfirmar)
                },
                enabled = valorValido
            ) {
                Text(
                    if (tipo == TipoTransacao.DEPOSITO) {
                        "Adicionar"
                    } else {
                        "Retirar"
                    }
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

private fun converterValor(texto: String): Double? {
    if (texto.isBlank()) return 0.0

    return texto
        .trim()
        .replace(",", ".")
        .toDoubleOrNull()
}

private fun formatarDinheiro(valor: Double): String {
    return NumberFormat
        .getCurrencyInstance(Locale("pt", "BR"))
        .format(valor)
}

private fun formatarData(data: Date): String {
    return SimpleDateFormat(
        "dd/MM/yyyy 'às' HH:mm",
        Locale("pt", "BR")
    ).format(data)
}