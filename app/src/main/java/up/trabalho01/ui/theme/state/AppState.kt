package up.trabalho01.ui.theme.state

import androidx.compose.runtime.mutableStateListOf
import up.trabalho01.ui.theme.components.Caixinha
import up.trabalho01.ui.theme.components.Cartao
import up.trabalho01.ui.theme.components.TipoTransacao
import up.trabalho01.ui.theme.components.Transacao

class AppState {

    val caixinhas = mutableStateListOf(
        Caixinha(
            id = 1,
            nome = "Viagem",
            saldo = 500.0
        ),
        Caixinha(
            id = 2,
            nome = "Casa",
            saldo = 250.0
        )
    )

    val cartoes = mutableStateListOf(
        Cartao(
            id = 1,
            nome = "Cartão principal",
            numeroFinal = "1234",
            limiteTotal = 2000.0,
            limiteUsado = 750.0
        )
    )

    private var proximaCaixinhaId = 3L
    private var proximoCartaoId = 2L
    private var proximaTransacaoId = 1L

    fun adicionarCaixinha(nome: String, saldoInicial: Double) {
        val transacoesIniciais = if (saldoInicial > 0) {
            listOf(
                Transacao(
                    id = proximaTransacaoId++,
                    descricao = "Saldo inicial",
                    valor = saldoInicial,
                    tipo = TipoTransacao.DEPOSITO
                )
            )
        } else {
            emptyList()
        }

        caixinhas.add(
            Caixinha(
                id = proximaCaixinhaId++,
                nome = nome,
                saldo = saldoInicial,
                transacoes = transacoesIniciais
            )
        )
    }

    fun removerCaixinha(id: Long) {
        caixinhas.removeAll { it.id == id }
    }

    fun buscarCaixinha(id: Long): Caixinha? {
        return caixinhas.firstOrNull { it.id == id }
    }

    fun depositar(caixinhaId: Long, valor: Double) {
        val indice = caixinhas.indexOfFirst {
            it.id == caixinhaId
        }

        if (indice < 0 || valor <= 0) return

        val caixinha = caixinhas[indice]

        val transacao = Transacao(
            id = proximaTransacaoId++,
            descricao = "Saldo adicionado",
            valor = valor,
            tipo = TipoTransacao.DEPOSITO
        )

        caixinhas[indice] = caixinha.copy(
            saldo = caixinha.saldo + valor,
            transacoes = listOf(transacao) +
                    caixinha.transacoes
        )
    }

    fun retirar(caixinhaId: Long, valor: Double): Boolean {
        val indice = caixinhas.indexOfFirst {
            it.id == caixinhaId
        }

        if (indice < 0 || valor <= 0) return false

        val caixinha = caixinhas[indice]

        if (valor > caixinha.saldo) return false

        val transacao = Transacao(
            id = proximaTransacaoId++,
            descricao = "Retirada",
            valor = valor,
            tipo = TipoTransacao.RETIRADA
        )

        caixinhas[indice] = caixinha.copy(
            saldo = caixinha.saldo - valor,
            transacoes = listOf(transacao) +
                    caixinha.transacoes
        )

        return true
    }

    fun adicionarCartao(
        nome: String,
        numeroFinal: String,
        limite: Double
    ) {
        cartoes.add(
            Cartao(
                id = proximoCartaoId++,
                nome = nome,
                numeroFinal = numeroFinal,
                limiteTotal = limite
            )
        )
    }

    fun removerCartao(id: Long) {
        cartoes.removeAll { it.id == id }
    }

    fun buscarCartao(id: Long): Cartao? {
        return cartoes.firstOrNull { it.id == id }
    }
}
