package com.grove.personalfinanceapp.domain.usecase.common

import java.util.UUID

fun interface IdGenerator {
    fun generate(): String
}

object UuidGenerator : IdGenerator {
    override fun generate(): String = UUID.randomUUID().toString()
}
