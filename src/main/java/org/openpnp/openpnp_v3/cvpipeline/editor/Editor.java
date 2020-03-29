package org.openpnp.openpnp_v3.cvpipeline.editor;

import java.awt.image.BufferedImage;

import org.openpnp.openpnp_v3.FxmlComponent;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;

public class Editor extends FxmlComponent {
    public Editor() { 
    }
    
    @FXML
    private void addCard(MouseEvent e) {
        if (e.getClickCount() < 2) {
            return;
        }
        var card = new Card("New Stage");
        card.addInput("Image", BufferedImage.class);
        card.addInput("Amount", Integer.class);
        card.addOutput("Image", BufferedImage.class);
        card.addOutput("Rectangle", Rectangle.class);
        card.setLayoutX(0);
        card.setLayoutY(0);
        getChildren().add(card);
    }
    
    public void connect(Card outputCard, String outputName, Card inputCard, String inputName) {
        var output = outputCard.getOutput(outputName);
        var input = inputCard.getInput(inputName);
        
        var curve = new CubicCurve();
        curve.setFill(null);
        curve.setStroke(output.getConnector().getFill());
        curve.setStrokeWidth(3);

        outputCard.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            updateConnection(curve, input, output);
        });
        inputCard.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            updateConnection(curve, input, output);
        });
        
        ((Pane) outputCard.getParent()).getChildren().add(curve);
        updateConnection(curve, input, output);
    }
    
    private Output connectOutput;
    public void startConnect(Output output) {
        this.connectOutput = output;
    }
    
    public void finishConnect(Input input) {
        if (this.connectOutput == null) {
            return;
        }
        connect(
                connectOutput.getCard(), 
                connectOutput.getName(), 
                input.getCard(), 
                input.getName());
        this.connectOutput = null;
    }
    
    static void updateConnection(CubicCurve curve, Input input, Output output) {
        var outputConnector = output.getConnector();
        var inputConnector = input.getConnector();

        var ob = outputConnector.localToScene(outputConnector.getBoundsInLocal());
        var ib = inputConnector.localToScene(inputConnector.getBoundsInLocal());
        
        curve.setStartX(ob.getCenterX());
        curve.setStartY(ob.getCenterY());
        
        curve.setControlX1(ob.getCenterX() + 100);
        curve.setControlY1(ob.getCenterY());
        
        curve.setControlX2(ib.getCenterX() - 100);
        curve.setControlY2(ib.getCenterY());
        
        curve.setEndX(ib.getCenterX());
        curve.setEndY(ib.getCenterY());
    }
}
