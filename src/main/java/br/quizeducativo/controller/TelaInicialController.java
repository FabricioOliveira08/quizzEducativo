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

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private TextField campoNome;

    @FXML
    private Button btnIniciar;

    @FXML
    public void iniciarQuiz(ActionEvent event) {
        String nomeDigitado = campoNome.getText();

        if(nomeDigitado == null || nomeDigitado.trim().isEmpty()) {
            mostrarAlerta("Atenção", "Por favor, digite seu nome para começar o quiz!");
            return;
        }

        Jogador jogadorAtual = new Jogador(nomeDigitado.trim());
        System.out.println("Iniciando partida para o jogador: " + jogadorAtual.getNome());

        mudarParaTelaQuiz(event, jogadorAtual);
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

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            System.err.println("Erro ao carregar a Tela do Quiz: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
