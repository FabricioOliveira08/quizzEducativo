package br.quizeducativo.model;
import java.util.List;

public abstract class Pergunta {

    protected String enunciado;
    protected List<String> alternativas;
    protected int respostaCorreta;

    //Construtor
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

    // metodo para verificar se a resposta está correta
    public boolean verificarResposta(int respostaUsuario) {
        return respostaUsuario == respostaCorreta;
    }

    public abstract void exibirPergunta();
}
