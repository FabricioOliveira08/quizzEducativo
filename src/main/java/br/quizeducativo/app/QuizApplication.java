package br.quizeducativo.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Aponta para o arquivo FXML que criamos na pasta resources
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInicial.fxml"));
            Parent root = loader.load();

            // Cria a "cena" e define um tamanho padrão (600x400)
            Scene scene = new Scene(root);

            // Configurações da Janela Principal
            stage.setTitle("Quiz Educativo - Laços de Repetição");
            stage.setScene(scene);
            stage.setResizable(false); // Trava o tamanho da janela para não desorganizar o layout
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro crítico: Não foi possível carregar a Tela Inicial.");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Erro crítico: Arquivo FXML não encontrado. Verifique se ele está exatamente em src/main/resources/fxml/TelaInicial.fxml");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
