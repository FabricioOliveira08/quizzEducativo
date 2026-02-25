package br.quizeducativo.model;
import java.util.List;

public class Quiz {

    private List<Pergunta> perguntas;
    private int indicePerguntaAtual;
    private int pontuacao;

    public Quiz(List<Pergunta> perguntas){
        this.perguntas = perguntas;
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
        if(perguntaAtual.verificarResposta(respostaUsuario)){
            pontuacao++;
            return true;
        }
        return false;
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

