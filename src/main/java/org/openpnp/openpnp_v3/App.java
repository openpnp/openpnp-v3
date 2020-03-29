package org.openpnp.openpnp_v3;

import java.io.IOException;

import org.openpnp.openpnp_v3.cvpipeline.editor.Editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(new Editor(), 1280, 1024);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}