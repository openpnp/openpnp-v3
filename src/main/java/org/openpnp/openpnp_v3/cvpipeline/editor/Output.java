package org.openpnp.openpnp_v3.cvpipeline.editor;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class Output extends HBox {
    @FXML
    private Circle connector;

    @FXML
    private Label label;
    
    public Output() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    
    public Circle getConnector() {
        return connector;
    }
    
    public Label getLabel() {
        return label;
    }
}
