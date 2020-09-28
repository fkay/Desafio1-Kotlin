package Desafio1

class DigitalHouseManager {
    val alunos = mutableListOf<Aluno>()
    val professores = mutableListOf<Professor>()
    val cursos = mutableListOf<Curso>()
    val matriculas = mutableListOf<Matricula>()

    fun registrarCurso(nome: String, codigo: Int, qtdMaxAlunos: Int) {
        val curso = Curso(nome, codigo, qtdMaxAlunos)
        cursos.add(curso)
        println("Curso ${nome} registrado com sucesso.")
    }

    fun excluirCurso(codigo: Int) {
        val curso = cursos.firstOrNull {
            it.codigo == codigo
        }
        curso?.let {
            cursos.remove(curso)
            println("Curso ${it.nome} removido.")
        } ?: run { println("Curso com codigo ${codigo} não encontrado!")}
    }

    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigo: Int, qtdHorasMonitoria: Int) {
        val professor = ProfessorAdjunto(nome, sobrenome, codigo, qtdHorasMonitoria)
        professores.add(professor)
        println("Professor ${nome} ${sobrenome} registrado.")
    }

    fun registrarProfessorTitular(nome: String, sobrenome: String, codigo: Int, especialidade: String) {
        val professor = ProfessorTitular(nome, sobrenome, codigo, especialidade)
        professores.add(professor)
        println("Professor ${nome} ${sobrenome} registrado.")
    }

    fun excluirProfessor(codigo: Int) {
        professores.firstOrNull {
            it.codigo == codigo
        } ?.let { prof ->
            cursos.firstOrNull{curso ->
                curso.professorAdjunto?.codigo == prof.codigo || curso.professorTitular?.codigo == prof.codigo
            } ?.let {curso ->
                println("Professor ${prof.nome} ${prof.sobrenome} " +
                        "está alocado no curso ${curso.nome} e não pode ser removido")
                } ?: run {
                    professores.remove(prof)
                    println("Professor ${prof.nome} ${prof.sobrenome} removido com sucesso")
                }
        } ?: run { println("Professor com código ${codigo} não encontrado") }
    }

    fun matricularAluno(nome: String, sobrenome: String, codigo: Int) {
        val aluno = Aluno(nome, sobrenome, codigo)
        alunos.add(aluno)
        println("Aluno ${nome} ${sobrenome} registrado com sucesso")
    }

    fun matricularAluno(codAluno: Int, codCurso: Int) {
        // primeiro tenta incluir o aluno no Curso
        cursos.firstOrNull{curso ->
            curso.codigo == codCurso
        } ?.let { curso ->
            alunos.firstOrNull { aluno ->
                aluno.codigo == codAluno
            } ?. let { aluno ->
                if(curso.addAluno(aluno)) {
                    // agora gera a matricula do aluno e adiciona na lista
                    val matricula = Matricula(codAluno, codCurso)
                    matriculas.add(matricula)
                    println("Aluno ${aluno.nome} ${aluno.sobrenome} matriculado com sucesso" +
                            " no curso ${curso.nome}")
                }
            } ?: run { println("Aluno com codigo ${codAluno} não encontrado!")}
        } ?: run { println("Curso com codigo ${codCurso} não encontrado!")}
    }

    fun alocarProfessores(codCurso: Int, codProfTitular: Int, codProfAdjunto: Int) {
        var profTitular: ProfessorTitular? = null
        var profAdjunto: ProfessorAdjunto? = null
        cursos.firstOrNull { curso ->
            curso.codigo == codCurso
        } ?.let { curso ->
            professores.firstOrNull{
                it.codigo == codProfTitular
            } ?.let {
                try {
                    // fazendo com exceção somente a titulo de demonstracao, poderiamos fazer o safe typecast com as?
                    // e verificando usando os recursos de nullsafety do Kotlin
                    profTitular = it as ProfessorTitular
                } catch(e: ClassCastException) {
                    println("Professor com código ${codProfTitular} não é titular")
                }
            } ?: run {
                println("Professor com codigo ${codProfTitular} não encontrado")
            }

            professores.firstOrNull {
                it.codigo == codProfAdjunto
            } ?. let {
                // nesse usarei o safe typecast, assim não é necessário o tratamento de exceção
                profAdjunto = it as? ProfessorAdjunto
            } ?: run {
                println("Professor com codigo ${codProfAdjunto} não encontrado")
            }

            profAdjunto?.let {profAdj ->
                profTitular?.let { profTit ->
                    curso.professorAdjunto = profAdj
                    curso.professorTitular = profTit

                    println("Professores ${profTit.nome} ${profTit.sobrenome} e " +
                            "${profAdj.nome} ${profAdj.sobrenome} alocados no curso ${curso.nome}")
                }
            } ?: run { println("Professor com código ${codProfAdjunto} não é adjunto") }

        } ?: run { println("Curso com código ${codCurso} não encontrado") }
    }

}