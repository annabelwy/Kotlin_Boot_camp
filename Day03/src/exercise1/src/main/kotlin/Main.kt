package org.example

import main.kotlin.RevolverDrum

fun main() {
    val revolverDrum = input()
    val result = StringBuilder("1. Adding elements\n$revolverDrum")
    revolverDrum.scroll()
    result.append("\n\n2. Scroll\n$revolverDrum")
    repeat(4) {revolverDrum.shoot()}
    result.append("\n\n3. Deletion\n$revolverDrum")
    val supplyList: MutableList<Int?> = mutableListOf(4, 6, 3, 22, 77, 43, 76, 5)
    result.append("\n\n4. Supply collection${outSupply(revolverDrum,supplyList)}")
    val extractedList = revolverDrum.unloadAll()
    result.append("\n\n5. Extraction\nThe extracted list: $extractedList\nsize: ${extractedList.size}\n\n$revolverDrum\nsize:${revolverDrum.countElements()}")
    result.append("\n\n6. Supply collection 2${outSupply(revolverDrum,supplyList)}")
    val revolverDrumForEq = input()
    result.append("\n\n7. Equals\n$revolverDrum\n\n$revolverDrumForEq\n\n")
    result.append("Result: ${if (revolverDrum!=revolverDrumForEq) "not" else "" } equals")
    println(result)
}

fun input():RevolverDrum<Int>{
    val revolverDrum:RevolverDrum<Int> = RevolverDrum()
    for (i in 0 until revolverDrum.volume) {
        revolverDrum.add((0..56).random())
    }
    return revolverDrum
}

fun outSupply(revolverDrum: RevolverDrum<Int>, supplyList: MutableList<Int?>):StringBuilder{
    val result = StringBuilder("\nBefore:\nSupply collection: $supplyList\n\n")
    result.append("$revolverDrum\n\n")
    revolverDrum.addMissingElements(supplyList)
    result.append("After add operation performed:\nSupply collection: $supplyList\n\n")
    result.append(revolverDrum)
    return result
}