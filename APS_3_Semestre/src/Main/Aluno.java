package Main;
/* Victor: Classe Alunos responsável por gerenciar os objetos do tipo Aluno*/
public class Aluno {
    
    //Declaração de atributos
    private String id;
    private String nome;
    
    //Constructor
    public Aluno(String aId,String aNome){
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
    
}
