package Desafio1

fun main() {
    println("Bem vindo ao desafio 1!")

    val dhManager = DigitalHouseManager()

    dhManager.matricularAluno("Fabricio", "Kassardjian", 123)
    dhManager.matricularAluno("João", "das Couves", 124)
    dhManager.matricularAluno("Maria", "das Dores", 123)
    dhManager.matricularAluno("Maria", "das Dores", 125)

    dhManager.printAlunos()

    dhManager.registrarProfessorTitular("Ludovico", "Van Strauss", 123, "Fullstack")
    dhManager.registrarProfessorTitular("Pardal", "Gadget", 124, "Java")
    dhManager.registrarProfessorTitular("Juca", "Bala", 125,"Android/Kotlin")

    dhManager.registrarProfessorAdjunto("Huguinho", "Pato", 201, 15)
    dhManager.registrarProfessorAdjunto("Zezinho", "Pato", 202, 10, "2020-09-20")
    dhManager.registrarProfessorAdjunto("Luizinho", "Pato", 203, 25, "2020-09-34")

    dhManager.printProfessores()

    dhManager.registrarCurso("Mobile - Android", 1, 2)
    dhManager.registrarCurso("FullStack", 2, 10)

    dhManager.alocarProfessores(1,125,201)

    dhManager.printCursos()

    dhManager.excluirProfessor(1000)
    dhManager.excluirProfessor(125)
    dhManager.excluirProfessor(203)

    dhManager.printProfessores()

}