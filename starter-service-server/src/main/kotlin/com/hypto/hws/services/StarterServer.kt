/*
 * Copyright 2020 gRPC authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package {TEMPLATE_PKG_NAME}

import io.grpc.Server
import io.grpc.ServerBuilder

class {TEMPLATE_SERVICE_NAME}Server(private val port: Int) {
    private val server: Server = ServerBuilder
            .forPort(port)
            .addService({TEMPLATE_SERVICE_NAME}Service())
            .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
                Thread {
                    println("*** shutting down gRPC server since JVM is shutting down")
                    this@{TEMPLATE_SERVICE_NAME}Server.stop()
                    println("*** server shut down")
                }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    private class {TEMPLATE_SERVICE_NAME}Service : {TEMPLATE_SERVICE_NAME}GrpcKt.{TEMPLATE_SERVICE_NAME}CoroutineImplBase() {
        override suspend fun printText(request: InputRequest): OutputResponse {
            println("Input Requested: $request")
            return OutputResponse.newBuilder()
                .setText(request.text)
                .build()
        }
    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 50051
    val server = {TEMPLATE_SERVICE_NAME}Server(port)
    server.start()
    server.blockUntilShutdown()
}
