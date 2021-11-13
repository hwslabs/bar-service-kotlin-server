package com.hypto.hws.services.starter.barServiceDynamoDao

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.*
import com.hypto.hws.services.starter.Bill
import com.hypto.hws.services.starter.barServiceDynamoDao.entities.Order

class BarServiceDao {

    companion object {

        private val syncClient: AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(
                "http://localhost:8000",
                Regions.US_WEST_2.toString()))
            .build()

        private val dynamoDB = DynamoDB(syncClient)
        private val mapper = DynamoDBMapper(syncClient)

        fun initTables() {
            try {
                println("Creating orders table.")
                dynamoDB.createTable(
                    mapper.generateCreateTableRequest(Order::class.java)
                        .withBillingMode(BillingMode.PAY_PER_REQUEST)
                )
            } catch (e: ResourceInUseException) {
                println("table already created.")
            }
        }

        fun createOrder(bill: Bill): Order {
            var order = Order(bill)
            try {
                println("saving order: ${order}")
                mapper.save(Order(bill))
                order = mapper.load(Order::class.java, bill.orderId)

            } catch (e: Exception) {
                println("Exception occurred")
                e.printStackTrace(System.out)
            }

            return order
        }

        fun getOrder(orderId: String): Order { return mapper.load(Order::class.java, orderId) }

        fun save(order: Order) { mapper.save(order) }
    }
}