package com.example.myapplicationns

data class Asset(
    val id: String = "",
    val name: String = "",
    val serialNumber: String = "",
    val category: String = "",
    val condition: String = "",
    val notes: String = "",
    val lastChecked: String = ""
)

object ConditionOptions {
    const val WORKING = "Working"
    const val NEEDS_REPAIR = "Needs Repair"
    const val BROKEN = "Broken"
    val all = listOf(WORKING, NEEDS_REPAIR, BROKEN)
}
