public class Correntista {

    /*
    * nome
    * idade
    * cpf
    * dataNascimento
    * renda
    * */

    private String nome;
    private int idade;
    private String cpf;
    private String dataNascimento;
    private double renda;

    Correntista(String nome, int idade, String cpf, String dataNacimento, double renda){
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.dataNascimento = dataNacimento;
        this.renda = renda;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public double getRenda() {
        return renda;
    }
}
