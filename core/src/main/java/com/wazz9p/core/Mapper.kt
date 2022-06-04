package com.wazz9p.core

interface Mapper<S, R> {

    fun mapToDomain(data: S): R

    fun mapToData(data: R): S?
}