package org.openpnp.openpnp_v3.cvpipeline.editor;

import org.openpnp.openpnp_v3.GroupComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public abstract class InputOutput extends GroupComponent {
    @FXML
    private Circle connector;

    @FXML
    private Label name;

    public InputOutput(String name) {
        this.name.setText(name);
    }
}
