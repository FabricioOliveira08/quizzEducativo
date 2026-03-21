package br.quizeducativo.model;

import javafx.scene.Node;

public interface QuestaoInterativa {
    Node exibirPergunta();

    boolean temRespostaSelecionada();

    int obterRespostaSelecionada();

    boolean validarResposta();

    void travarInteracao();
}
