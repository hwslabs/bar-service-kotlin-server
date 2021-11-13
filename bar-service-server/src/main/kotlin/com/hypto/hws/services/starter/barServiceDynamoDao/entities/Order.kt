package com.hypto.hws.services.starter.barServiceDynamoDao.entities

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.hypto.hws.services.starter.Bill
import com.hypto.hws.services.starter.PaymentStatus
import java.time.Instant

@DynamoDBTable(tableName = "Orders")
data class Order(
    @DynamoDBHashKey(attributeName="order_id")
    var orderId: String = "",

    @DynamoDBAttribute(attributeName="amount")
    var amount: Long? = null,

    @DynamoDBAttribute(attributeName="created_at")
    var timestamp: Long? = null,

//    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    var orderStatus: PaymentStatus = PaymentStatus.PENDING
) {

    constructor(bill: Bill) : this() {
        orderId = bill.orderId
        amount = bill.orderAmount
        timestamp = Instant.ofEpochSecond(bill.orderTimestamp.seconds, bill.orderTimestamp.nanos.toLong()).toEpochMilli()
    }
}