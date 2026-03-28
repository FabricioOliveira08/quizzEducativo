package br.quizeducativo.app;

import br.quizeducativo.exception.ArquivoPerguntasException;
import br.quizeducativo.model.Pergunta;
import br.quizeducativo.model.PerguntaImagem;
import br.quizeducativo.util.LeitorPergunta;

import java.util.List;

public class TestePergunta {
    public static void main(String[] args) {

        try{
            List<Pergunta> perguntasCarregadas = LeitorPergunta.carregarPerguntas();
            System.out.println("Sucesso! Foram carregadas " + perguntasCarregadas.size() + " perguntas.\n");

            for(int i = 0; i < perguntasCarregadas.size(); i++) {
                Pergunta p = perguntasCarregadas.get(i);

                System.out.println("--- Pergunta " + (i + 1) + " ---");
                System.out.println("Enunciado: " + p.getEnunciado());
                System.out.println("Alternativas: " + p.getAlternativas());
                System.out.println("Índice Resposta Correta: " + p.getRespostaCorreta());

                if(p instanceof PerguntaImagem) {
                    System.out.println("Tipo detectado: IMAGEM");
                }else{
                        System.out.println("Tipo detectado: TEXTO");
                }
                System.out.println("----------------------\n");
            }
        }catch (ArquivoPerguntasException e) {
            System.err.println("Erro ao carregar as perguntas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
