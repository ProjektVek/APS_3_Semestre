package Main;
/*Victor: Classe cursos responsável por gerenciar os objetos do tipo curso*/
public class Curso {
    
    //Declaração de atributos
    private String nome;
    private String nivel;
    private int ano;
    
    //Constructor
    public Curso(String aNome, String aNivel, int aAno){
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
    
}
