package org.openpnp.openpnp_v3.cvpipeline.editor;

import java.awt.image.BufferedImage;

import org.openpnp.openpnp_v3.FxmlComponent;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
        connector.setFill(getTypeColor(type));
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
    
    public Color getTypeColor(Class<?> type) {
        if (type.isAssignableFrom(BufferedImage.class)) {
            return new Color(0xff / 255.0, 0x77 / 255.0, 0x6f / 255.0, 1.0);
        }
        else if (type.isAssignableFrom(Integer.class)) {
            return new Color(0x89 / 255.0, 0xd5 / 255.0, 0xff / 255.0, 1.0);
        }
        else {
            return new Color(0x83 / 255.0, 0x6f / 255.0, 0xff / 255.0, 1.0);
        }
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
    
    // TODO STOPSHIP binding?
    // TODO STOPSHIP CSS
    private void updateStroke() {
        if (highlighted) {
            getConnector().setStroke(Color.GREENYELLOW);
            getConnector().setStrokeWidth(2);
        }
        else if (active) {
            getConnector().setStroke(Color.ANTIQUEWHITE);
            getConnector().setStrokeWidth(2);
        }
        else {
            getConnector().setStroke(null);
        }
    }
    
    public void setActive(boolean active) {
        this.active = active;
        updateStroke();
    }
    
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        updateStroke();
    }
}



