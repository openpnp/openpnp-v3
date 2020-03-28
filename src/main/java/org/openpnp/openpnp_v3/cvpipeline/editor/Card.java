package org.openpnp.openpnp_v3.cvpipeline.editor;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Card extends VBox {
    @FXML
    private Label name;
    
    @FXML
    private VBox inputs;

    @FXML
    private VBox outputs;

    @FXML
    private ImageView image;

    public Card() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Card.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
        
        inputs.getChildren().add(new Input());
        inputs.getChildren().add(new Input());
        inputs.getChildren().add(new Input());
        
        outputs.getChildren().add(new Output());
        outputs.getChildren().add(new Output());
        outputs.getChildren().add(new Output());
        
        Image im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-28 at 12.42.18 AM.png");
//        Image im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-27 at 5.34.48 PM.png");
//        Image im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-27 at 12.11.10 PM.png");
        image.setImage(im);
    }
}
