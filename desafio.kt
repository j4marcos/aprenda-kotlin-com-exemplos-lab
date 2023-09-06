enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, var idade: Int) {
    
val formacoes: MutableSet<Formacao> = mutableSetOf()
val conteudos: MutableSet<ConteudoEducacional> = mutableSetOf()

fun curriculo() {
    print("curriculo de: ${this.nome} \n")
    if (formacoes.isEmpty()) {print("não tenho formações")}
    else {
    print("formações: \n")
    
    formacoes.forEach({ it ->
        val porcentagem = (conteudos.sumOf({it.duracao})) / (it.duracao).toFloat() * 100
        print("${it.nome}, ${porcentagem}% \n")})
    
    conteudos.forEach({print("${it.nome};\n")})
    print("\n")
    }
    }

}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60) 

data class Formacao(val nome: String,val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {
    
    val duracao: Int = conteudos.sumOf{it.duracao}
    val inscritos: MutableList<Usuario> = mutableListOf()
    
    fun matricular( usuario: Usuario) {
        inscritos.add(usuario)
        usuario.formacoes.add(this)
    }
    
    fun progresso(usuario: Usuario, cursosCompletos:Int) {
        for (curso in 0..cursosCompletos - 1) {
            usuario.conteudos.add(conteudos[curso])
        }
    }
}

fun main() {
    val cursoGit = ConteudoEducacional("Curso de git", 30)
    val cursoHTML = ConteudoEducacional("Curso de HTML", 120)
    val cursoCSS = ConteudoEducacional("Curso de CSS", 180)
    var cursoJS = ConteudoEducacional("Curso de JavaScript", 180)
    
    var cursos = listOf(cursoGit, cursoHTML, cursoCSS, cursoJS)
    
    val formacaoWeb = Formacao("Formação front end",Nivel.INTERMEDIARIO, cursos)
    
    val fernanda = Usuario("Fernanda",23)
    val joao = Usuario("João",19)
    val gabriel = Usuario("gabriel",16)
    
    formacaoWeb.matricular(fernanda)
    formacaoWeb.progresso(fernanda,2)
    
    formacaoWeb.matricular(joao)
    formacaoWeb.progresso(joao,4)
    
    println(formacaoWeb)
    println()
    
    fernanda.curriculo()
    joao.curriculo()
    gabriel.curriculo()
    
    
    
}
