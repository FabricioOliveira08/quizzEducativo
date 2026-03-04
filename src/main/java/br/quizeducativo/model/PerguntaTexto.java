package br.quizeducativo.model;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

public class PerguntaTexto extends Pergunta{

    public PerguntaTexto(String enunciado,
                         List<String> alternativas,
                         int respostaCorreta) {
        super(enunciado, alternativas, respostaCorreta);
    }
    @Override
    public Node exibirPergunta(){

        VBox layout = new VBox(10);
        Label labelEnunciado = new Label(enunciado);
        layout.getChildren().add(labelEnunciado);

        for (int i = 0; i < alternativas.size(); i++) {
            Button botaoAlternativa = new Button(alternativas.get(i));
            layout.getChildren().add(botaoAlternativa);
        }
        return layout;
    }

}
