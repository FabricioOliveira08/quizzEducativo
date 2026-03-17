package br.quizeducativo.model;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PerguntaTexto extends Pergunta{

    public PerguntaTexto(String enunciado,
                         List<String> alternativas,
                         int respostaCorreta) {
        super(enunciado, alternativas, respostaCorreta);
    }

    private ToggleGroup grupoOpcoes;

    @Override
    public Node exibirPergunta(){

        VBox caixaPergunta = new VBox(15);
        Label labelEnunciado = new Label(getEnunciado());
        labelEnunciado.setWrapText(true);
        labelEnunciado.setMaxWidth(450);  // Define a largura máxima para ele saber a hora de quebrar
        labelEnunciado.setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE); // Impede o Java de cortar a altura
        labelEnunciado.getStyleClass().add("enunciado-pergunta");
        caixaPergunta.getChildren().add(labelEnunciado);

        this.grupoOpcoes = new ToggleGroup();

        alternativas = getAlternativas();
        for (int i = 0; i < alternativas.size(); i++) {
            RadioButton rb = new RadioButton(alternativas.get(i));
            rb.setToggleGroup(grupoOpcoes);
            rb.getStyleClass().add("alternativa-radio");
            rb.setUserData(i);
            caixaPergunta.getChildren().add(rb);
        }
        return caixaPergunta;
    }

    public int obterRespostaSelecionada() {
        if (grupoOpcoes == null || grupoOpcoes.getSelectedToggle() == null) {
            return -1; // Nenhuma opção foi marcada
        }
        // Recupera aquele índice que escondemos no setUserData
        return (int) grupoOpcoes.getSelectedToggle().getUserData();
    }
}
