package br.com.zup.keymanager.consulta

import br.com.zup.ConsultaChavePixResponse
import br.com.zup.TipoDeConta
import io.micronaut.core.annotation.Introspected
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Introspected
class DetalheChavePixResponse(chaveResponse: ConsultaChavePixResponse) {

    val pixId = chaveResponse.pixId
    val tipo = chaveResponse.chave.tipoDeChave
    val chave = chaveResponse.chave.chave

    val criadaEm = chaveResponse.chave.criadaEm.let {
        LocalDateTime.ofInstant(Instant.ofEpochSecond(it.seconds, it.nanos.toLong()), ZoneOffset.UTC)
    }

    val tipoConta = when (chaveResponse.chave.conta.tipoDeConta) {
        TipoDeConta.CONTA_CORRENTE -> "CONTA_CORRENTE"
        TipoDeConta.CONTA_POUPANCA -> "CONTA_POUPANCA"
        else -> "NAO_RECONHECIDA"
    }

    val conta = mapOf(Pair("tipo", tipoConta),
        Pair("instituicao", chaveResponse.chave.conta.instituicao),
        Pair("nomeDoTitular", chaveResponse.chave.conta.nomeDoTitular),
        Pair("cpfDoTitular", chaveResponse.chave.conta.cpfDoTitular),
        Pair("agencia", chaveResponse.chave.conta.agencia),
        Pair("numero", chaveResponse.chave.conta.numeroDaConta))


}
