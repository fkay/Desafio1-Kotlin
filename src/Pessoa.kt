package Desafio1

abstract class Pessoa(val nome: String, val sobrenome: String, val codigo: Int) {
    // o codigo deve ser unico, portanto se tiver dois codigos iguais os objetos serão considerados iguais
    override fun equals(other: Any?): Boolean {
        if (this === other) return true                     // mesma referencia

        if (javaClass != other?.javaClass) return false     // mesma classe?

        other as Pessoa                                     // casting da classe

        return (codigo == other.codigo)
    }

    // hashcode definido somente pelo codigo
    override fun hashCode(): Int {
        return codigo
    }

    override fun toString(): String {
        return "${codigo} - ${nome} ${sobrenome}"
    }
}