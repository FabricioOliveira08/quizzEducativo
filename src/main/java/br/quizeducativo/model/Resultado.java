package br.quizeducativo.model;

public class Resultado {
    private Jogador jogador;
    private int totalPerguntas;

    // Construtor tradicional recebendo o jogador e o total de perguntas do quiz
    public Resultado(Jogador jogador, int totalPerguntas) {
        this.jogador = jogador;
        this.totalPerguntas = totalPerguntas;
    }

    // Método tradicional para calcular a porcentagem de acertos
    public double calcularPercentualAcerto() {
        if (totalPerguntas == 0) {
            return 0.0; // Evita erro de divisão por zero
        }

        // Convertemos para double (cast tradicional) para não perder as casas decimais
        return ((double) jogador.getAcertos() / totalPerguntas) * 100;
    }

    // Método com if/else tradicional para gerar a mensagem de feedback
    public String gerarMensagemFinal() {
        double percentual = calcularPercentualAcerto();
        String nomeAluno = jogador.getNome();

        if (percentual >= 80.0) {
            return "Excelente, " + nomeAluno + "! Você dominou os laços de repetição.";
        } else if (percentual >= 50.0) {
            return "Muito bem, " + nomeAluno + "! Você está no caminho certo, mas vale a pena revisar alguns conceitos.";
        } else {
            return "Não desanime, " + nomeAluno + "! A programação exige bastante prática. Tente jogar novamente!";
        }
    }

    // Getters tradicionais, caso a interface gráfica precise acessar os objetos depois
    public Jogador getJogador() {
        return jogador;
    }

    public int getTotalPerguntas() {
        return totalPerguntas;
    }
}
