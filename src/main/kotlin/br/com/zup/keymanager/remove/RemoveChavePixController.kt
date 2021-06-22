package br.com.zup.keymanager.remove

import br.com.zup.KeymanagerRemoveGrpcServiceGrpc
import br.com.zup.RemoveChavePixRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject

@Controller("/api/v1/clientes/{clientId}")
class RemoveChavePixController(private val grpcClient: KeymanagerRemoveGrpcServiceGrpc.KeymanagerRemoveGrpcServiceBlockingStub) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Delete("/pix/{pixId}")
    fun remove(clientId: UUID, pixId: UUID) : HttpResponse<Any> {

        LOGGER.info("[$clientId] removendo uma chave pix com $pixId")
        grpcClient.remove(RemoveChavePixRequest.newBuilder()
            .setClientId(clientId.toString())
            .setPixId(pixId.toString())
            .build())

        return HttpResponse.ok()
    }

}