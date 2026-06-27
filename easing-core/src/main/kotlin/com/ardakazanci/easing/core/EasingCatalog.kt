package com.ardakazanci.easing.core

object EasingCatalog {
    val all: List<EasingDefinition> = StandardEasings.all

    val families: List<EasingFamily> = all
        .map { it.family }
        .distinct()

    fun findById(id: String): EasingDefinition? {
        return all.firstOrNull { it.id == id }
    }

    fun byFamily(family: EasingFamily?): List<EasingDefinition> {
        return if (family == null) {
            all
        } else {
            all.filter { it.family == family }
        }
    }
}
