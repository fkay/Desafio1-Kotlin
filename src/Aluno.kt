package Desafio1

class Aluno(nome: String, sobrenome: String, codigo: Int):
    Pessoa(nome, sobrenome, codigo) {
    val certificados = mutableListOf<String>()

    // Certificado está como String, mas pode evoluir para uma classe, indicando curso e data de conclusão
    fun addCertificado(certificado: String) {
        certificados.add(certificado)
    }

}