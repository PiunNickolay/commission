package ru.netology

fun main() {
    val cardType = "Mastercard"
    val previousTransfersThisMonth = 0
    val transferAmount = 80000

    val result = calculateCommission(cardType, previousTransfersThisMonth, transferAmount)
    if (result != null) {
        println("Сумма перевода: $transferAmount руб.")
        println("Комиссия: ${result.first} руб.")
        println("Итоговая сумма с комиссией: ${result.second} руб.")
    } else {
        println("Операция блокирована из-за превышения лимита.")
    }
}

fun calculateCommission(cardType: String, previousTransfersThisMonth: Int, transferAmount: Int): Pair<Int, Int>? {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000

    if (transferAmount > dailyLimit || previousTransfersThisMonth + transferAmount > monthlyLimit) {
        return null
    }

    val commission = when (cardType) {
        "Mastercard" -> {
            val limit = 75_000
            if (previousTransfersThisMonth + transferAmount <= limit) {
                0
            } else {
                ((transferAmount - limit) * 0.006 + 20).toInt()
            }
        }
        "Visa" -> {
            val commission = (transferAmount * 0.0075).toInt()
            if (commission < 35){
                35
            } else commission
        }
        "Мир" -> 0
        else -> 0 
    }

    val totalAmount = transferAmount + commission
    return Pair(commission, totalAmount)
}

