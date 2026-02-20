package br.quizeducativo.model;
import java.util.List;

public class PerguntaTexto extends Pergunta{

    public PerguntaTexto(String enunciado, List<String> alternativas, int respostaCorreta) {
        super(enunciado, alternativas, respostaCorreta);
    }
    @Override
    public void exibirPergunta(){
        System.out.println(enunciado);

        for(int i = 0; i < alternativas.size(); i++ ){
            System.out.println(i + " - " + alternativas.get(i));
        }
    }
}
