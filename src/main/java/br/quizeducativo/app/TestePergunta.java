package br.quizeducativo.app;
import br.quizeducativo.model.*;
import java.util.List;

public class TestePergunta {
    public static void main(String[] args) {

        Pergunta p1 = new PerguntaTexto("Qual é a capital da frança?",List.of("Paris", "Londres", "Roma", "Berlim"), 0);

        Pergunta p2 = new PerguntaTexto("quanto é 5 + 3?", List.of("6", "7", "8", "9"), 2);

        Pergunta p3 = new PerguntaTexto("Qual linguagem estamos usando?", List.of("Python", "Java", "C++", "PHP"), 1);


        List<Pergunta> listaPerguntas = List.of(p1, p2, p3);

        Quiz quiz = new Quiz(listaPerguntas, true);

        System.out.println("__________ iniciando quiz _____________");

        while(!quiz.terminou()) {
            Pergunta perguntaAtual = quiz.proximaPergunta();

            if(perguntaAtual != null) {
                perguntaAtual.exibirPergunta();

                int respostaSimulada = 1;

                System.out.println("Resposta escolhida: " + respostaSimulada);
                boolean acertou = quiz.verificarResposta(respostaSimulada);

                if(acertou) {
                    System.out.println("Resposta correta");
                }else{
                    System.out.println("Resposta errada");
                }
            }
        }

        System.out.println("Quiz finalizado!");
        System.out.println("Pontuação final: " + quiz.getPontuacao());
        System.out.println("Total de perguntas: " + quiz.getTotalPerguntas());
    }
}
