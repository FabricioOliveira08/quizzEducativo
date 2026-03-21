package br.quizeducativo.model;
import javafx.scene.Node;

import java.util.List;

public abstract class Pergunta implements QuestaoInterativa {
    public abstract int obterRespostaSelecionada();
    protected String enunciado;
    protected List<String> alternativas;
    protected int respostaCorreta;


    public Pergunta(String enunciado, List<String> alternativas, int respostaCorreta) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
    }

    public String getEnunciado() {
        return enunciado;
    }
    public  List<String> getAlternativas() {
        return alternativas;
    }
    public int getRespostaCorreta(){
        return respostaCorreta;
    }


    public boolean verificarResposta(int respostaUsuario) {
        return respostaUsuario == respostaCorreta;
    }

    public abstract Node exibirPergunta();
}
