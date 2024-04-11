package org.example
import main.kotlin.*
import data.* // почему когда убираешь эту строчку, он не видит Level, хотя в другом файле, который здесь его импортили

fun main() {
        val companies = readCompaniesFromJsonFile("././././data-samples/listOfCompanies.json")
    val field = selectField(companies)
    val prof = selectProf(field.second ,field.first)
    val level = selectLevel(prof.second, prof.first)
    selectSalary(level.second, level.first)
}


fun selectField(companies:List<Company>):Pair<List<Company>, String>  {
    println("Select a field of activity:\n1. IT\n2. Banking\n3. Public services\n4. All\n")
    val field = readln().toIntOrNull()
    return when (field) {
        1->Pair( companies.filter {it.field == FieldOfActivity.IT}, FieldOfActivity.IT.trueName)
        2->Pair(companies.filter {it.field == FieldOfActivity.BANKING},FieldOfActivity.BANKING.trueName )
        3->Pair(companies.filter {it.field == FieldOfActivity.PUBLIC_SERVICES},FieldOfActivity.PUBLIC_SERVICES.trueName)
        4 -> Pair(companies, "All")
        else-> {
            print("Try again. ")
            selectField(companies)
        }
    }
}

fun selectProf(fieldOfActivity: String ,companies:List<Company>): Pair<List<Company>, String>  {
    println("\n$fieldOfActivity. Select a profession:\n1. Developer\n2. QA\n3. Project Manager\n4. Analyst\n5. Designer\n6. All\n")
    val prof = readln().toIntOrNull()

    return when(prof) {
        1-> {
            Pair(byProfession(companies,Profession.DEVELOPER), "$fieldOfActivity. ${Profession.DEVELOPER.trueName}")
        }
        2-> {
            Pair(byProfession(companies,Profession.QA), "$fieldOfActivity. ${Profession.QA.trueName}")

        }
        3->{
            Pair(byProfession(companies,Profession.PROJECT_MANAGER), "$fieldOfActivity. ${Profession.PROJECT_MANAGER.trueName}")
        }
        4->{
            Pair(byProfession(companies,Profession.ANALYST), "$fieldOfActivity. ${Profession.ANALYST.trueName}")

        }
        5->Pair(byProfession(companies,Profession.DESIGNER), "$fieldOfActivity. ${Profession.DESIGNER.trueName}")
        6->Pair(companies, "$fieldOfActivity. All")
        else -> {
            println("Try again.")
            selectProf(fieldOfActivity, companies)
        }
    }
}

fun selectLevel(prof: String ,companies:List<Company>): Pair<List<Company>, String>  {
    println("\n$prof. Select the level of a candidate:\n1. Junior\n2. Middle\n3. Senior\n4. All\n")
    val level = readln().toIntOrNull()

    return when(level) {
        1-> {
            Pair(byLevel(companies,Level.JUNIOR), "$prof. ${Level.JUNIOR.trueName}")
        }
        2-> {
            Pair(byLevel(companies,Level.MIDDLE), "$prof. ${Level.MIDDLE.trueName}")

        }
        3->{
            Pair(byLevel(companies,Level.SENIOR), "$prof. ${Level.SENIOR.trueName}")
        }
        4->{
            Pair(companies, "$prof. All")

        }
        else -> {
            println("Try again.")
            selectLevel(prof, companies)
        }
    }
}




fun byProfession(companies:List<Company>, profession: Profession): List<Company> {
    return companies.filter { company ->
        company.vac.any { it.profession == profession }
    }.map { company ->
        company.apply {
            vac = vac.filter { it.profession == profession }
        }
    }
}

fun byLevel(companies:List<Company>, level: Level): List<Company> {
    return companies.filter { company ->
        company.vac.any { it.level == level }
    }.map { company ->
        company.apply {
            vac = vac.filter { it.level == level }
        }
    }
}


fun selectSalary(level: String ,companies:List<Company>) {
    println("\n$level. Select a salary level:\n1. < 100000\n2. 100000 - 150000\n3. > 150000\n4. All\n")
    val salary = readln().toIntOrNull()

    when(salary) {
        1-> {
            result("$level. < 100000", companies.filter { company ->
                company.vac.any { it.salary < 100000 }
            }.map { company ->
                company.apply {
                    vac = vac.filter { it.salary < 100000 }
                }
            })
        }
        2-> {
            result("$level. 100000 - 150000", companies.filter { company ->
                company.vac.any { it.salary in 100000..150000 }
            }.map { company ->
                company.apply {
                    vac = vac.filter { it.salary in 100000..150000 }
                }
            })
        }
        3->{
            result("$level. > 150000", companies.filter { company ->
                company.vac.any { it.salary > 150000 }
            }.map { company ->
                company.apply {
                    vac = vac.filter { it.salary > 150000 }
                }
            })
        }
        4->{
            result("$level. All",companies)
        }
        else -> {
            println("Try again")
            selectSalary(level, companies)
        }
    }
}


fun result(level: String ,companies:List<Company>){
    if (companies.isNotEmpty()) {
        println("\n$level\nThe list of suitable vacancies:\n")
        var count = 1
        for (company in companies) {
            for (vac in company.vac) {
                println("$count.\n${vac.level.trueName} ${vac.profession.trueName}     ---     ${vac.salary}")
                println("\t${company.name}")
                println("\t${company.field}")
                println("---------------------------------------\n")
                count++
            }
        }
    }
    else {
        println("\n$level\nThe list of suitable vacancies is empty\n")
    }
}
