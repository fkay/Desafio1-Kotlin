package Desafio1

import java.time.LocalDate

class Matricula(val codAluno: Int, val codCurso: Int, val data: LocalDate) {
    constructor(codAluno: Int, codCurso: Int): this(
        codAluno,
        codCurso,
        LocalDate.now()
    )
}