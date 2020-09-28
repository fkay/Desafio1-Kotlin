package Desafio1

import java.time.LocalDate

class ProfessorTitular(nome: String, sobrenome: String, codigo: Int,
                       dataAdmissao: LocalDate, val especialidade: String):
    Professor(nome, sobrenome, codigo, dataAdmissao) {
    constructor(nome: String, sobrenome: String, codigo: Int, especialidade: String):
            this(nome, sobrenome, codigo, LocalDate.now(), especialidade)

    // Se precisar, algum metodo especializado da classe
}