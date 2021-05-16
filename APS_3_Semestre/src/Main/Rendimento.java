package Main;
import java.util.ArrayList;
/* Classe Rendimento responsável por armazenar a notas de determinado aluno
e em determinado curso*/
public class Rendimento {
    
    //Declaração de Atributos
    private Aluno aluno;
    private Curso curso;
    private Nota np1;
    private Nota np2;
    private Nota reposicao;
    private Nota exame;
    private Nota media;
    //ArrayList que armazena os Rendimentos:
    private static ArrayList<Rendimento> rendimentos = new ArrayList<Rendimento>();
    
    //Constructor
    public Rendimento(Aluno aAluno, Curso aCurso, Nota aNP1, Nota aNP2,
            Nota aReposicao, Nota aExame){
        this.aluno = aAluno;
        this.curso = aCurso;
        this.np1 = aNP1;
        this.np2 = aNP2;
        this.reposicao = aReposicao;
        this.exame = aExame;
        rendimentos.add(new Rendimento(this.aluno,this.curso,this.np1,this.np2,this.reposicao,
        this.exame,true));
    }
    
    /*Constructor com flag utilizado para não entrar em loop infinito
      na hora de adicionar o aluno no ArrayList
    */
        public Rendimento(Aluno aAluno, Curso aCurso, Nota aNP1, Nota aNP2,
            Nota aReposicao, Nota aExame, boolean flag){
        this.aluno = aAluno;
        this.curso = aCurso;
        this.np1 = aNP1;
        this.np2 = aNP2;
        this.reposicao = aReposicao;
        this.exame = aExame;
    }
    
    //Getters e Setters

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aAluno) {
        this.aluno = aAluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso aCurso) {
        this.curso = aCurso;
    }

    public Nota getNp1() {
        return np1;
    }

    public void setNp1(Nota aNP1) {
        this.np1 = aNP1;
    }

    public Nota getNp2() {
        return np2;
    }

    public void setNp2(Nota aNP2) {
        this.np2 = aNP2;
    }

    public Nota getReposicao() {
        return reposicao;
    }

    public void setReposicao(Nota aReposicao) {
        this.reposicao = aReposicao;
    }

    public Nota getExame() {
        return exame;
    }

    public void setExame(Nota aExame) {
        this.exame = aExame;
    }
    
    public static ArrayList<Rendimento> getRendimentos(){
        return rendimentos;
    }
    
    //Métodos
    
    /*Victor: Método que calcula a média, alterando o valor do atributo
    media e retornando o valor da Média como double*/;
    public double calculaMedia(){
        /*Victor: Verificando se a reposição é maior de que a NP1 ou a NP2.
        Se sim, substitui a menor nota;
        */
        
        double nota1 = this.np1.getValor();
        double nota2 = this.np2.getValor();
        double reposicao = this.reposicao.getValor();
        double exame = this.exame.getValor();
        
        if(reposicao>nota1||reposicao>nota2){
            if(nota1>nota2){
                nota2 = reposicao;
            } else {
                nota1 = reposicao;
            }
        }
        
        double mediaInicial = (nota1+nota2)/2;
        double mediaFinal;
        
        //Calculando a média final de acordo com o nível do curso
        if(curso.getNivel()=="GRADUACAO"){
            if(mediaInicial>=7){
                mediaFinal = mediaInicial;
            } else {
                mediaFinal = (mediaInicial+exame)/2;
            }
        } else {
            if(mediaInicial>=5){
                mediaFinal = mediaInicial;
            } else {
                mediaFinal = (mediaInicial+exame)/2;
            }
        }
        
        this.media = new Nota(mediaFinal);
        return mediaFinal;
    }
    
    public boolean aprovado(){
        if(this.media.getValor()>=5){
            return true;
        } else {
            return false;
        }
    }
}
