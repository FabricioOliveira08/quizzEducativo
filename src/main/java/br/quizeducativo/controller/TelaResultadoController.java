package br.quizeducativo.controller;

import br.quizeducativo.model.Jogador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaResultadoController {

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSuaPontuacao;

    @FXML
    private Button btnVoltarMenu;

    public void inicializarResultados(Jogador jogador, int totalPerguntas){
        labelNome.setText(jogador.getNome());

        labelSuaPontuacao.setText(jogador.getPontuacaoFinal() + " / " + totalPerguntas);
    }

    @FXML
    public void voltarAoMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInicial.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVoltarMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao voltar para o menu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
