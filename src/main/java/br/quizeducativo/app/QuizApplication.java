package br.quizeducativo.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class QuizApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Quizz Educativo");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}
