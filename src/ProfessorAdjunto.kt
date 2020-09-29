package Desafio1

import java.time.LocalDate

class ProfessorAdjunto(nome: String, sobrenome: String, codigo: Int,
                       dataAdmissao: LocalDate, val horasMonitoria: Int):
    Professor(nome, sobrenome, codigo, dataAdmissao) {
    constructor(nome: String, sobrenome: String, codigo: Int, horasMonitoria: Int):
    this(nome, sobrenome, codigo, LocalDate.now(), horasMonitoria) {

    }

    // metodos especializados se precisar

    override fun toString(): String {
        return "Professor Adjunto: " + super.toString() + "\nHoras Monitoria: ${horasMonitoria}"
    }
}