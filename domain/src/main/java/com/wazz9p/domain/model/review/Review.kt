package com.wazz9p.domain.model.review

data class Review(
    val id: Int,
    val name: String,
    val phoneNumber: String?,
    val message: String,
    val adminMessage: String?
) {
    val adminName: String
        get() = "Администратор"
}