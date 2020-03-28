package org.openpnp.openpnp_v3;

import java.io.IOException;

import org.openpnp.openpnp_v3.cvpipeline.editor.Card;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        var pane = new Pane();
        scene = new Scene(pane, 1280, 1024);
        
        Card card1 = new Card();
        card1.setLayoutX(60);
        card1.setLayoutY(60);
        pane.getChildren().add(card1);
        
        Card card2 = new Card();
        card2.setLayoutX(420);
        card2.setLayoutY(420);
        pane.getChildren().add(card2);
        
        /**
         * Curves should reference the dot, not the card.
         * Try using a StackPane as the root so we can make "layers". The curves will be on a 
         * lower level than the cards.
         */
        var curve = new CubicCurve();
        
        curve.startXProperty().bind(card1.translateXProperty().add(card1.getLayoutX()).add(14));
        curve.startYProperty().bind(card1.translateYProperty().add(card1.getLayoutY()).add(45));
        
        curve.controlX1Property().bind(card1.translateXProperty().add(card1.getLayoutX()).add(14).add(-150));
        curve.controlY1Property().bind(card1.translateYProperty().add(card1.getLayoutY()).add(45));

        curve.controlX2Property().bind(card2.translateXProperty().add(card2.getLayoutX()).add(14).add(-150));
        curve.controlY2Property().bind(card2.translateYProperty().add(card2.getLayoutY()).add(45));
        
        curve.endXProperty().bind(card2.translateXProperty().add(card2.getLayoutX()).add(14));
        curve.endYProperty().bind(card2.translateYProperty().add(card2.getLayoutY()).add(45));
        
        curve.setFill(null);
        curve.setStroke(new Color(0xe2 / 255., 0x6b / 255., 0xf2 / 255., 1.0));
        curve.setStrokeWidth(3);
        pane.getChildren().add(curve);
        
        new Draggable.Nature(card1); 
        new Draggable.Nature(card2); 
        
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        var fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}