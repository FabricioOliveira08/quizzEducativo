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
        labelEnunciado.setMaxWidth(450);  // Define a largura máxima para ele saber a hora de quebrar
        labelEnunciado.setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE); // Impede o Java de cortar a altura
        labelEnunciado.getStyleClass().add("enunciado-pergunta");
        caixaPergunta.getChildren().add(labelEnunciado);

        // 2. Imagem (Lendo da pasta resources)
        try {
            Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
            ImageView imageView = new ImageView(imagem);
            imageView.setFitWidth(400);
            imageView.setFitHeight(180);
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
            rb.getStyleClass().add("alternativa-radio");
            rb.setUserData(i); // Guarda o índice para checagem
            caixaPergunta.getChildren().add(rb);
        }

        return caixaPergunta;
    }

    @Override
    public boolean temRespostaSelecionada() {
        return this.grupoOpcoes != null && this.grupoOpcoes.getSelectedToggle() != null;
    }

    @Override
    public int obterRespostaSelecionada() {
        if (!temRespostaSelecionada()) {
            return -1;
        }
        return (int) grupoOpcoes.getSelectedToggle().getUserData();
    }

    @Override
    public boolean validarResposta() {
        return obterRespostaSelecionada() == getRespostaCorreta();
    }

    @Override
    public void travarInteracao() {
        if (this.grupoOpcoes != null) {
            for (javafx.scene.control.Toggle toggle : this.grupoOpcoes.getToggles()) {
                ((javafx.scene.control.RadioButton) toggle).setDisable(true);
            }
        }
    }


}
