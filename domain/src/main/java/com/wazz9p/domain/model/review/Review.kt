package com.wazz9p.domain.model.review

data class Review(
    val name: String,
    val phoneNumber: String?,
    val message: String,
    val adminMessage: String?
)