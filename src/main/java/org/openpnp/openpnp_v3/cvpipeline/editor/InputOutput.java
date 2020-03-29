package org.openpnp.openpnp_v3.cvpipeline.editor;

import java.awt.image.BufferedImage;

import org.openpnp.openpnp_v3.FxmlComponent;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public abstract class InputOutput extends FxmlComponent {
    @FXML
    private Circle connector;

    @FXML
    private Label name;
    
    private Class<?> type;
    private boolean active;
    private boolean highlighted;
    
    public InputOutput(String name, Class<?> type) {
        this.name.setText(name);
        this.type = type;
        
        if (type.isAssignableFrom(BufferedImage.class)) {
            connector.getStyleClass().add("connector-type-image");
        }
        else if (type.isAssignableFrom(Integer.class)) {
            connector.getStyleClass().add("connector-type-number");
        }
        else {
            connector.getStyleClass().add("connector-type-object");
        }
    }
    
    @FXML
    private void connect(MouseEvent e) {
        if (this instanceof Output) {
            getEditor().startConnect((Output) this);
        }
        else {
            getEditor().finishConnect((Input) this);
        }
    }
    
    @FXML
    private void mouseEntered(MouseEvent e) {
        setHighlighted(true);
    }
    
    @FXML
    private void mouseExited(MouseEvent e) {
        setHighlighted(false);
    }
    
    public String getName() {
        return name.getText();
    }
    
    public Circle getConnector() {
        return connector;
    }
    
    public Class<?> getType() {
        return type;
    }
    
    public Editor getEditor() {
        Parent parent = this;
        while ((parent = parent.getParent()) != null)
            if (parent instanceof Editor) {
                return (Editor) parent;
            }
        return null;
    }
    
    public Card getCard() {
        Parent parent = this;
        while ((parent = parent.getParent()) != null)
            if (parent instanceof Card) {
                return (Card) parent;
            }
        return null;
    }
    
    private void updateStateStyle() {
        if (highlighted) {
            while (connector.getStyleClass().remove("connector-active"));
            connector.getStyleClass().add("connector-highlighted");
        }
        else if (active) {
            connector.getStyleClass().add("connector-active");
            while (connector.getStyleClass().remove("connector-highlighted"));
        }
        else {
            while(connector.getStyleClass().remove("connector-active"));
            while(connector.getStyleClass().remove("connector-highlighted"));
        }
        System.out.println(connector.getStyleClass());
    }
    
    public void setActive(boolean active) {
        this.active = active;
        updateStateStyle();
    }
    
    public void setHighlighted(boolean highlighted) {
        // TODO STOPSHIP something doesn't quite work right - highlight stays on after
        // making a connection
        if (this instanceof Input && !active) {
            return;
        }
        this.highlighted = highlighted;
        updateStateStyle();
    }
}



