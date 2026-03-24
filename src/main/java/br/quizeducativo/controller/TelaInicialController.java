package br.quizeducativo.controller;

import br.quizeducativo.model.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.util.List;
import br.quizeducativo.util.dao.RankingDAO;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private TextField campoNome;

    @FXML
    public void initialize() {
       campoNome.textProperty().addListener((observavel, valorAntigo, valorNovo) -> {
            if (!valorNovo.matches("[a-zA-ZÀ-ÿ ]*")) {
                campoNome.setText(valorNovo.replaceAll("[^a-zA-ZÀ-ÿ ]", ""));
            }
        });
    }

    @FXML
    private Button btnIniciar;

    @FXML
    public void irParaIntroducao(ActionEvent event) {
        String nomeDigitado = campoNome.getText();
        if(nomeDigitado == null || nomeDigitado.trim().isEmpty()) {
            mostrarAlerta("Atenção", "Por favor, digite seu nome para começar o quiz!");
            return;
        }

        Jogador jogador = new Jogador(nomeDigitado);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaIntroducao.fxml"));
            Parent root = loader.load();

            TelaIntroducaoController controller = loader.getController();
            controller.receberJogador(jogador);

            Stage stage = (Stage) btnIniciar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a introdução: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void mudarParaTelaQuiz(ActionEvent event, Jogador jogador) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaQuiz.fxml"));
            Parent root = loader.load();

            TelaQuizController controllerDestino = loader.getController();
            controllerDestino.iniciarJogo(jogador);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            System.err.println("Erro ao carregar a Tela do Quiz: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void verRanking(ActionEvent event) {
        RankingDAO dao = new RankingDAO();
        List<String> top5 = dao.obterTop5Ranking();

        Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle("Top 5 Jogadores");
        alerta.setHeaderText("🏆 Ranking dos Melhores Jogadores 🏆");

        if (top5.isEmpty()) {
            alerta.setContentText("Ainda não há registos. Seja o primeiro a jogar!");
        } else {
            String textoRanking = String.join("\n", top5);
            alerta.setContentText(textoRanking);
        }

        alerta.showAndWait();
    }
    @FXML
    public void abrirIntroducao(ActionEvent event) {
        String nomeDigitado = campoNome.getText();
        if(nomeDigitado == null || nomeDigitado.trim().isEmpty()) {
            mostrarAlerta("Atenção", "Por favor, digite seu nome para começar o quiz!");
            return;
        }

        Jogador jogador = new Jogador(nomeDigitado);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaIntroducao.fxml"));
            Parent root = loader.load();

            TelaIntroducaoController controller = loader.getController();
            controller.receberJogador(jogador);

            Stage stage = (Stage) btnIniciar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao abrir introdução: " + e.getMessage());
        }
    }
}
