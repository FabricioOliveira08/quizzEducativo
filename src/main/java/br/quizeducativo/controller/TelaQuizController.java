package br.quizeducativo.controller;

import br.quizeducativo.exception.ArquivoPerguntasException;
import br.quizeducativo.model.Jogador;
import br.quizeducativo.model.Pergunta;
import br.quizeducativo.model.Quiz;
import br.quizeducativo.util.LeitorPergunta;
import br.quizeducativo.util.SomUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class TelaQuizController {
    @FXML
    private Label labelJogador;
    @FXML
    private Label labelPontos;
    @FXML
    private VBox containerPergunta;
    @FXML
    private Button btnNext;

    private Jogador jogador;
    private Quiz quiz;
    private Pergunta perguntaAtual;
    private int acertos = 0;
    private int erros = 0;

    public void iniciarJogo(Jogador jogador) {
        this.jogador = jogador;
        this.labelJogador.setText("Jogador: " + jogador.getNome());
        this.labelPontos.setText("Pontos: 0");

        try{
            List<Pergunta> listaPerguntas = LeitorPergunta.carregarPerguntas();
            this.quiz = new Quiz(listaPerguntas, true);
            carregarProximaPergunta();
        } catch(ArquivoPerguntasException e) {
            mostrarErro("Erro ao carregar o jogo", e.getMessage());
        }
    }

    private void carregarProximaPergunta() {
        containerPergunta.getChildren().clear();

        if (!quiz.terminou()) {
            this.perguntaAtual = quiz.proximaPergunta();

            containerPergunta.getChildren().add(this.perguntaAtual.exibirPergunta());
            atualizarPlacar();
        } else {
            finalizarQuiz();
        }
    }

    @FXML
    public void acaoBotaoNext(ActionEvent event) {
        if(this.perguntaAtual == null) return;

        int indiceMercado = this.perguntaAtual.obterRespostaSelecionada();

        if(indiceMercado == -1) {
            mostrarErro("Atenção", "Por favor, selecione uma alternativa antes de avançar!");
            return;
        }
        boolean acertou = quiz.verificarResposta(indiceMercado);

        if(acertou){
            this.acertos++;
            SomUtils.tocarAcerto();
        } else {
            this.erros++;
            SomUtils.tocarErro();
        }
        carregarProximaPergunta();
    }

    private void atualizarPlacar() {
        labelPontos.setText("Pontos: " + quiz.getPontuacao());
    }

    private void finalizarQuiz() {
       jogador.registrarDesempenho(this.acertos, this.erros, quiz.getPontuacao());

       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaResultado.fxml"));
           Parent root = loader.load();

           TelaResultadoController controllerDestino = loader.getController();
           controllerDestino.inicializarResultados(jogador, quiz.getTotalPerguntas());

           Stage stage = (Stage) btnNext.getScene().getWindow();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }  catch (IOException e) {
           System.err.println("Erro ao carregar a Tela de Resultado: " + e.getMessage());
           e.printStackTrace();
       }
    }

    private void mostrarErro(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    private void mudarParaTelaQuiz(ActionEvent event, Jogador jogador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaQuiz.fxml"));
            Parent root = loader.load();

            // Pega o controller da tela do Quiz e envia o jogador pra lá
            TelaQuizController controllerDestino = loader.getController();
            controllerDestino.iniciarJogo(jogador);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a Tela do Quiz: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
