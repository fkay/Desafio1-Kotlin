package Desafio1

class Curso(val nome: String, val codigo: Int, var maxAlunos: Int) {
    val alunos = mutableListOf<Aluno>()
    var professorTitular: ProfessorTitular? = null
    var professorAdjunto: ProfessorAdjunto? = null

    fun addAluno(aluno: Aluno): Boolean {
        if(alunos.size == maxAlunos) {
            println("Curso já atingiu capacidade máxima")
            return false
        }
        alunos.add(aluno)
        println("Aluno cadastrado")
        return true
    }

    fun excluirAluno(aluno: Aluno): Boolean {
        return alunos.remove(aluno).also{
            println(if(it) "Aluno excluido" else "Aluno não encontrado")
        }
    }
}