package br.quizeducativo.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;

public class PerguntaImagem extends Pergunta {
    private String caminhoImagem;
    private ToggleGroup grupoOpcoes;

    public PerguntaImagem(String enunciado,
                          List<String> alternativas,
                          int indiceRespostaCorreta,
                          String caminhoImagem) {
        super(enunciado, alternativas, indiceRespostaCorreta);
        this.caminhoImagem = caminhoImagem;
    }
    @Override
    public Node exibirPergunta() {
        VBox caixaPergunta = new VBox(15);
        caixaPergunta.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        // 1. Enunciado
        Label labelEnunciado = new Label(getEnunciado());
        labelEnunciado.setWrapText(true);
        labelEnunciado.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        caixaPergunta.getChildren().add(labelEnunciado);

        // 2. Imagem (Lendo da pasta resources)
        try {
            Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
            ImageView imageView = new ImageView(imagem);
            imageView.setFitWidth(400); // Ajuste conforme seu wireframe
            imageView.setPreserveRatio(true);
            caixaPergunta.getChildren().add(imageView);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + caminhoImagem);
        }

        // 3. RadioButtons das Alternativas
        this.grupoOpcoes = new ToggleGroup();
        List<String> alternativas = getAlternativas();

        for (int i = 0; i < alternativas.size(); i++) {
            RadioButton rb = new RadioButton(alternativas.get(i));
            rb.setToggleGroup(grupoOpcoes);
            rb.setStyle("-fx-font-size: 14px;");
            rb.setUserData(i); // Guarda o índice para checagem
            caixaPergunta.getChildren().add(rb);
        }

        return caixaPergunta;
    }

    @Override
    public int obterRespostaSelecionada() {
        if (grupoOpcoes == null || grupoOpcoes.getSelectedToggle() == null) {
            return -1;
        }
        return (int) grupoOpcoes.getSelectedToggle().getUserData();
    }

    // getCaminhoImagem()...
}
