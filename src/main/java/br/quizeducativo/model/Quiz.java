package br.quizeducativo.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {

    private List<Pergunta> perguntas;
    private boolean modoAleatorio;
    private int indicePerguntaAtual;
    private int pontuacao;

    public Quiz(List<Pergunta> perguntas, boolean modoAleatorio) {
        this.perguntas = new ArrayList<>(perguntas);
        if (modoAleatorio) {
            Collections.shuffle(this.perguntas);
        }
        this.indicePerguntaAtual = 0;
        this.pontuacao = 0;
    }

    public Pergunta proximaPergunta(){
        if(indicePerguntaAtual < perguntas.size()){
            return perguntas.get(indicePerguntaAtual++);
        }
        return null;
    }

    public boolean verificarResposta(int respostaUsuario){
        Pergunta perguntaAtual = perguntas.get(indicePerguntaAtual - 1);
        boolean acertou = perguntaAtual.verificarResposta(respostaUsuario);
        if(acertou){
            pontuacao++;
        }
        return acertou;
    }

    public int getPontuacao() {
        return pontuacao;
    }
    public int getTotalPerguntas() {
        return perguntas.size();
    }
    public boolean terminou(){
        return indicePerguntaAtual >= perguntas.size();
    }
}

