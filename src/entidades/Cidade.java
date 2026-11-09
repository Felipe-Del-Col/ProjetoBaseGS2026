
package entidades;

public class Cidade {

    private String nome;
    private double porcentagem;
    private int nCasos;

    public Cidade(String nome, double porcentagem, int nCasos) {
        this.nome = nome;
        this.porcentagem = porcentagem;
        this.nCasos = nCasos;
    }

    public String getNome() {
        return nome;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public int getNCasos() {
        return nCasos;
    }

    public void apresentar() {
        System.out.println("Cidade: " + nome);
        System.out.println("Cobertura vacinal: " + porcentagem + "%");
        System.out.println("Numero de casos: " + nCasos);
        System.out.println("-------------------------");
    }
}

