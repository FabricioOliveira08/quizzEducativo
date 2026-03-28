package br.quizeducativo.model;

public class Jogador {
    private String nome;
    private int pontuacaoFinal;
    private int acertos;
    private int erros;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacaoFinal = 0;
        this.acertos = 0;
        this.erros = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacaoFinal() {
        return pontuacaoFinal;
    }

    public void setPontuacaoFinal(int pontuacaoFinal) {
        this.pontuacaoFinal = pontuacaoFinal;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public void registrarDesempenho(int acertos, int erros, int pontuacaoFinal) {
        this.acertos = acertos;
        this.erros = erros;
        this.pontuacaoFinal = pontuacaoFinal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Jogador outroJogador = (Jogador) obj;
        return this.nome.equals(outroJogador.getNome());
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
