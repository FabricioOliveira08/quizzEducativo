package br.quizeducativo.controller;

import br.quizeducativo.model.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaIntroducaoController {

    @FXML
    private Button btnIniciarQuiz;

    private Jogador jogadorAtual;

    public void receberJogador(Jogador jogador) {
        this.jogadorAtual = jogador;
    }

    @FXML
    public void iniciarQuiz(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaQuiz.fxml"));
            Parent root = loader.load();
            TelaQuizController controller = loader.getController();
            controller.iniciarJogo(jogadorAtual);
            Stage stage = (Stage) btnIniciarQuiz.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao voltar: " + e.getMessage());
        }
    }

}
