package org.example
fun main() {
    val candidate = readCandidateInfoFromJsonFile("src/exercise2/src/files/resume.json")
    println("Block 1\n...\n")
    println(candidate.candInfo)
    println("Block 2\n...\n")
    println(candidate.education)
    println("Block 3\n...\n")
    println(candidate.jobExperience)
    println("Block 4\n...\n")
    println(candidate.freeForm)
}