package Main;
import java.util.ArrayList;
/*Victor: Classe cursos responsável por gerenciar os objetos do tipo curso*/
public class Curso {
    
    //Declaração de atributos:
    //ArrayList que Armazena todos os cursos
    private static ArrayList<Curso> cursos = new ArrayList<Curso>(); 
    private String nome;
    private String nivel;
    private int ano;
    
    
    //Constructor
    public Curso(String aNome, String aNivel, int aAno){
        this.nome = aNome;
        this.nivel = aNivel;
        this.ano = aAno;
        cursos.add(new Curso(this.nome,this.nivel,this.ano,true));
    }
    /*Constructor com flag utilizado para não entrar em loop infinito
      na hora de adicionar o curso no ArrayList
    */
    public Curso(String aNome, String aNivel, int aAno, boolean flag){
        this.nome = aNome;
        this.nivel = aNivel;
        this.ano = aAno;
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String aNome) {
        this.nome = aNome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String aNivel) {
        this.nivel = aNivel;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int aAno) {
        this.ano = aAno;
    }
    
    public static ArrayList<Curso> getCursos(){
        return cursos;
    }
    //Métodos:
    
    //Método que cria uma String com a listagem de todos os cursos
    public static String listaCursos(){
        String mensagem = "";
        for(Curso curso: cursos){
            mensagem += "Nome: "+curso.getNome()+"\n";
            mensagem += "Nível: "+curso.getNivel()+"\n";
            mensagem += "Ano: "+curso.getAno()+"\n\n";
        }
        
        if(mensagem==""){
            mensagem = "Nenhum Curso Cadastrado!\n";
        }
        return mensagem;
    }
}
