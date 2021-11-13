package com.hypto.hws.services.starter.interceptors

import io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.grpc.Status
import io.grpc.Metadata


class ExceptionHandler : ServerInterceptor {
    override fun <ReqT, RespT> interceptCall(
        serverCall: ServerCall<ReqT, RespT>, metadata: Metadata,
        serverCallHandler: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        val listener = serverCallHandler.startCall(serverCall, metadata)
        return ExceptionHandlingServerCallListener<ReqT, RespT>(listener, serverCall, metadata)
    }

    private inner class ExceptionHandlingServerCallListener<ReqT, RespT>(
        listener: ServerCall.Listener<ReqT>?, private val serverCall: ServerCall<ReqT, RespT>,
        private val metadata: Metadata
    ) : SimpleForwardingServerCallListener<ReqT>(listener) {
        override fun onHalfClose() {
            try {
                super.onHalfClose()
            } catch (ex: RuntimeException) {
                handleException(ex, serverCall, metadata)
                throw ex
            }
        }

        override fun onReady() {
            try {
                super.onReady()
            } catch (ex: RuntimeException) {
                handleException(ex, serverCall, metadata)
                throw ex
            }
        }

        private fun handleException(
            exception: RuntimeException,
            serverCall: ServerCall<ReqT, RespT>,
            metadata: Metadata
        ) {
            /*
                TODO: Handle known exceptions here and call serverCall.close with appropriate status
                close with  Status.UNKNOWN for the rest.
             */
            println("Unhandled server exception!")
            println(exception.message)
            println(exception)
            exception.printStackTrace()
            serverCall.close(Status.UNKNOWN, metadata)

//            if (exception is IllegalArgumentException) {
//                // Known exception
//                serverCall.close(Status.INVALID_ARGUMENT.withDescription(exception.message), metadata)
//            } else {
//                println("Unhandled server exception!")
//                println(exception)
//                exception.printStackTrace()
//                serverCall.close(Status.UNKNOWN, metadata)
//            }
        }
    }
}