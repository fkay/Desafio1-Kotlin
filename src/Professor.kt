package Desafio1

import java.time.LocalDate
import java.time.temporal.ChronoUnit

abstract class Professor(nome: String, sobrenome: String, codigo: Int, val dataAdmissao: LocalDate):
    Pessoa(nome, sobrenome, codigo) {
        constructor(nome: String, sobrenome: String, codigo: Int):
                this(nome, sobrenome, codigo, LocalDate.now())

    fun obterTempoCasa(): Long {
        val hoje = LocalDate.now()
        return(dataAdmissao.until(hoje, ChronoUnit.DAYS))
    }

    override fun toString(): String {
        return super.toString() +
                "\nAdmissao: ${dataAdmissao}\nTempo de casa: ${obterTempoCasa()} dias"
    }
}