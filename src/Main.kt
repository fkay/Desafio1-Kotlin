package Desafio1

fun main() {
    println("Bem vindo ao desafio 1!")

    val dhManager = DigitalHouseManager()

    println("Teste cadastro aluno 1")
    dhManager.registrarAluno("Fabricio", "Kassardjian", 123)
    println("Teste cadastro aluno 2")
    dhManager.registrarAluno("João", "das Couves", 124)
    println("Teste cadastro aluno com mesmo codigo")
    dhManager.registrarAluno("Maria", "das Dores", 123)
    println("Teste cadastro aluno 3")
    dhManager.registrarAluno("Maria", "das Dores", 125)

    dhManager.printAlunos()

    println("\nTeste cadastro professor titular 1")
    dhManager.registrarProfessorTitular("Ludovico", "Van Strauss", 123, "Fullstack")
    println("Teste cadastro professor titular 2 - com data de admissao mais antiga")
    dhManager.registrarProfessorTitular("Pardal", "Gadget", 124, "Java", "2020-08-01")
    println("Teste cadastro professor titular 3")
    dhManager.registrarProfessorTitular("Juca", "Bala", 125,"Android/Kotlin")

    println("\nTeste cadastro professor adjunto 1")
    dhManager.registrarProfessorAdjunto("Huguinho", "Pato", 201, 15)
    println("Teste cadastro professor adjunto 2 - data de admissão mais antiga")
    dhManager.registrarProfessorAdjunto("Zezinho", "Pato", 202, 10, "2020-09-20")
    println("Teste cadastro professor adjunto 3 - data de admissão inválida")
    dhManager.registrarProfessorAdjunto("Luizinho", "Pato", 203, 25, "2020-09-34")

    dhManager.printProfessores()

    println("\nTeste cadastro curso 1")
    dhManager.registrarCurso("Mobile - Android", 1, 2)
    println("Teste cadastro curso 2")
    dhManager.registrarCurso("FullStack", 2, 10)

    println("\nTeste alocando professores 1")
    dhManager.alocarProfessores(1,125,201)
    println("Teste alocando professores 2 - 2 titulares")
    dhManager.alocarProfessores(1,125,123)
    println("Teste alocando professores 3 - 2 adjuntos")
    dhManager.alocarProfessores(1,201,202)
    println("Teste alocando professores 3 - curso inválido")
    dhManager.alocarProfessores(1000,125,201)

    dhManager.printCursos()

    println("\nTeste excluindo professores 1 - professor invalido")
    dhManager.excluirProfessor(1000)
    println("Teste excluindo professores 2 - professor alocado")
    dhManager.excluirProfessor(125)
    println("Teste excluindo professores 3")
    dhManager.excluirProfessor(203)

    dhManager.printProfessores()

    println("\nTeste matriculando aluno 1")
    dhManager.matricularAluno(123, 1)
    println("Teste matriculando aluno 2")
    dhManager.matricularAluno(124, 1)
    println("Teste matriculando aluno 3 - lotacao maxima")
    dhManager.matricularAluno(125, 1)

    dhManager.printCursos()

    dhManager.printMatriculas()

    println("\nTeste excluindo curso")
    dhManager.excluirCurso(2)

    dhManager.printCursos()
}