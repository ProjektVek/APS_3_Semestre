package Main;

import java.util.ArrayList;

/* Victor: Classe Alunos responsável por gerenciar os objetos do tipo Aluno*/
public class Aluno {
    
    //Declaração de atributos
    private String id;
    private String nome;
    //ArrayList que armazena todos os alunos
    private static ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    
    //Constructor
    public Aluno(String aId,String aNome){
        this.id = aId;
        this.nome = aNome;
        alunos.add(new Aluno(this.id,this.nome,true));
    }
    
    /*Constructor com flag utilizado para não entrar em loop infinito
      na hora de adicionar o aluno no ArrayList
    */
     public Aluno(String aId,String aNome, boolean flag){
        this.id = aId;
        this.nome = aNome;
    }
    
    //Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String aId) {
        this.id = aId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String aNome) {
        this.nome = aNome;
    }
    
    public static ArrayList<Aluno> getAlunos(){
        return alunos;
    }
    
    //Métodos:
    //Método que lista todos os alunos
    public static String listaAlunos(){
        String mensagem = "";
        for(Aluno aluno: alunos){
            mensagem += "ID: "+aluno.getId()+"\n";
            mensagem += "Nome: "+aluno.getNome()+"\n\n";
        }
        
        if(mensagem==""){
            mensagem = "Nenhum Aluno Cadastrado!\n";
        }
        return mensagem;
    }
    
}
