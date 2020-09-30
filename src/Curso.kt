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
        if(alunos.contains(aluno)) {
            println("Aluno já estava cadastrado nesse curso")
            return false
        }

        alunos.add(aluno)
        //println("Aluno cadastrado")
        return true
    }

    fun excluirAluno(aluno: Aluno): Boolean {
        return alunos.remove(aluno).also{
            println(if(it) "Aluno excluido" else "Aluno não encontrado")
        }
    }

    // o codigo deve ser unico, portanto se tiver dois codigos iguais os objetos serão considerados iguais
    override fun equals(other: Any?): Boolean {
        if (this === other) return true  // mesma referencia
        if (javaClass != other?.javaClass) return false  // classes diferentes

        other as Curso  // faz o casting

        return (codigo == other.codigo)
    }

    override fun hashCode(): Int {
        return codigo
    }

    override fun toString(): String {
        val profTit = professorTitular?.toString() ?: "Sem professor titular alocado"
        val profAdj = professorAdjunto?.toString() ?: "Sem professor adjunto alocado"
        var retorno = "Curso $codigo - $nome\n" + profTit + "\n" + profAdj
        retorno += "\n ${alunos.size} Alunos:"
        alunos.forEach {
            retorno += "\n  " + it.toString()
        }
        return retorno
    }

}