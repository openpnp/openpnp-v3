package org.openpnp.openpnp_v3.cvpipeline.editor;

import org.openpnp.openpnp_v3.GroupComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Card extends GroupComponent {
    @FXML
    private Label name;
    
    @FXML
    private VBox inputs;

    @FXML
    private VBox outputs;

    @FXML
    private ImageView image;
    
    public Card() {
        super();
        
        inputs.getChildren().add(new Input("settle?"));
        inputs.getChildren().add(new Input("camera"));
        inputs.getChildren().add(new Input("average"));
        
        outputs.getChildren().add(new Output("image"));
        
        Image im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-28 at 12.42.18 AM.png");
//        Image im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-27 at 5.34.48 PM.png");
//        Image im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-27 at 12.11.10 PM.png");
        image.setImage(im);
    }
}
