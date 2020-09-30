package Desafio1

import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        val curso = cursos.find {
            it.codigo == codigo
        }
        curso?.let {
            cursos.remove(curso)
            println("Curso ${it.nome} removido.")
        } ?: run { println("Curso com codigo ${codigo} não encontrado!")}
    }

    // metodo aceita string da data de admissao como parametro adicional
    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigo: Int,
                                  qtdHorasMonitoria: Int, dataAdmissao: String? = null) {
        var dataAdmi: LocalDate? = null
        // exemplo do try catch para pegar excecao de entrada invalida de data
        try {
            dataAdmi = dataAdmissao?.let { LocalDate.parse(dataAdmissao) }
        } catch(e: DateTimeException) {
            println("Data invalida, usando data de hoje")
        }

        val professor = dataAdmi?.let {
            ProfessorAdjunto(nome, sobrenome, codigo, dataAdmi, qtdHorasMonitoria)
        } ?: run { ProfessorAdjunto(nome, sobrenome, codigo, qtdHorasMonitoria) }

        professores.add(professor)
        println("Professor ${nome} ${sobrenome} registrado.")
    }



    fun registrarProfessorTitular(nome: String, sobrenome: String, codigo: Int,
                                  especialidade: String, dataAdmissao: String? = null) {
        var dataAdmi: LocalDate? = null
        // exemplo do try catch para pegar excecao de entrada invalida de data
        try {
            dataAdmi = dataAdmissao?.let { LocalDate.parse(dataAdmissao) }
        } catch(e: DateTimeException) {
            println("Data invalida, usando data de hoje")
        }
        val professor = dataAdmi?.let {
            ProfessorTitular(nome, sobrenome, codigo, dataAdmi, especialidade)
        } ?: run { ProfessorTitular(nome, sobrenome, codigo, especialidade) }

        professores.add(professor)
        println("Professor ${nome} ${sobrenome} registrado.")
    }

    fun excluirProfessor(codigo: Int) {
        professores.find {
            it.codigo == codigo
        } ?.let { prof ->
            cursos.find{curso ->
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

    fun registrarAluno(nome: String, sobrenome: String, codigo: Int) {
        val aluno = Aluno(nome, sobrenome, codigo)

        if (alunos.contains(aluno)) {
            println("Aluno ${nome} ${sobrenome} não registrado. Código de aluno já usado")
            return
        }

        alunos.add(aluno)
        println("Aluno ${nome} ${sobrenome} registrado com sucesso")
    }

    fun matricularAluno(codAluno: Int, codCurso: Int) {
        // primeiro tenta incluir o aluno no Curso
        cursos.find{curso ->
            curso.codigo == codCurso
        } ?.let { curso ->
            alunos.find { aluno ->
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
        cursos.find { curso ->
            curso.codigo == codCurso
        } ?.let { curso ->
            professores.find{
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

            professores.find {
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

    // funcoes para apresentar as listas
    fun printAlunos() {
        println("\n*** Lista de Alunos ***")
        alunos.forEach(::println)
    }

    fun printProfessores() {
        println("\n*** Lista de Professores ***")
        professores.forEach(::println)
    }

    fun printCursos() {
        println("\n*** Lista de Cursos ***")
        cursos.forEach(::println)
    }

    fun printMatriculas() {
        println("\n*** Lista de Matriculas ***")
        matriculas.forEachIndexed{ index, matricula ->
            val aluno = alunos.find{
                it.codigo == matricula.codAluno
            }
            val curso = cursos.find{
                it.codigo == matricula.codCurso
            }
            println("Matricula ${index} - ${matricula.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}")
            aluno?.let(::println)
            curso?.let {
                println("Curso ${it.codigo} - ${it.nome}")
            }
        }
    }

}