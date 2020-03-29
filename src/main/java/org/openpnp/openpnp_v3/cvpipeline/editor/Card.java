package org.openpnp.openpnp_v3.cvpipeline.editor;

import org.openpnp.openpnp_v3.Draggable;
import org.openpnp.openpnp_v3.FxmlComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Card extends FxmlComponent {
    @FXML
    private Label name;
    
    @FXML
    private VBox inputs;

    @FXML
    private VBox outputs;

    @FXML
    private ImageView image;
    
    public Card(String name) {
        super();
        
        this.name.setText(name);
        
        var im = new Image("file:///Users/jason/Desktop/Screen Shot 2020-03-28 at 12.42.18 AM copy.png");
        image.setImage(im);
        
        new Draggable.Nature(this);
    }
    
    public void addInput(String name, Class<?> type) {
        inputs.getChildren().add(new Input(name, type));
    }
    
    public void addOutput(String name, Class<?> type) {
        outputs.getChildren().add(new Output(name, type));
    }
    
    public Input getInput(String name) {
        // TODO STOPSHIP there has to be a more modern way to do this, and maybe
        // it would be nice if name were a Property.
        for (var node : inputs.getChildren()) {
            if (node instanceof Input) {
                var input = (Input) node;
                if (input.getName().equals(name)) {
                    return input;
                }
            }
        }
        return null;
    }
    
    public Output getOutput(String name) {
        // TODO STOPSHIP there has to be a more modern way to do this, and maybe
        // it would be nice if name were a Property.
        for (var node : outputs.getChildren()) {
            if (node instanceof Output) {
                var output = (Output) node;
                if (output.getName().equals(name)) {
                    return output;
                }
            }
        }
        return null;
    }
}
