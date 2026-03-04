package br.quizeducativo.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.List;

public class PerguntaImagem extends Pergunta {
    private String caminhoImagem;

    public PerguntaImagem(String enunciado,
                          List<String> alternativas,
                          int indiceRespostaCorreta,
                          String caminhoImagem) {
        super(enunciado, alternativas, indiceRespostaCorreta);
        this.caminhoImagem = "caminhoImagem";
    }
    @Override
    public Node exibirPergunta() {

        VBox layout = new VBox(10);

        Label labelEnunciado = new Label(enunciado);

        Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
        ImageView imageView = new ImageView(imagem);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        layout.getChildren().addAll(labelEnunciado, imageView);

        return layout;
    }
}
