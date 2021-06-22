package br.com.zup.keymanager.shared.grpc

import br.com.zup.KeymanagerConsultaGrpcServiceGrpc
import br.com.zup.KeymanagerRegistraGrpcServiceGrpc
import br.com.zup.KeymanagerRemoveGrpcServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class KeyManagerGrpcFactory(@GrpcChannel("KeyManager") val channel: ManagedChannel) {
    @Singleton
    fun registraChave() = KeymanagerRegistraGrpcServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun removeChave() = KeymanagerRemoveGrpcServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun consultaChave() = KeymanagerConsultaGrpcServiceGrpc.newBlockingStub(channel)
}