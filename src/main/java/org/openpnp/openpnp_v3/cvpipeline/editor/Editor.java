package org.openpnp.openpnp_v3.cvpipeline.editor;

import java.awt.image.BufferedImage;

import org.openpnp.openpnp_v3.FxmlComponent;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        card.addInput("Rectangle", Rectangle.class);
        card.addOutput("Image", BufferedImage.class);
        card.addOutput("Amount", Integer.class);
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
        
        /**
         * TODO STOPSHIP
         * - Identify and highlight valid inputs.
         * - Highlight the selected output.
         * - Create the curve immediately and connect it to the mouse
         *   so the user can see what they are doing.
         */
        // TODO STOPSHIP this should be CSS
        // TODO STOPSHIP actually this is all mixing concerns. We should just be setting a
        // property on the input or output to say it's active.  We also need mouse in and mouse
        // out to set a second property of highlighted. And these control the colors.
        output.setActive(true);
        
        for (var node : getChildren()) {
            if (node instanceof Card) {
                Card card = (Card) node;
                if (card == output.getCard()) {
                    continue;
                }
                for (var node1 : card.getInputs()) {
                    if (node1 instanceof Input) {
                        Input input = (Input) node1;
                        if (input.getType().isAssignableFrom(output.getType())) {
                            input.setActive(true);
                        }
                    }
                }
            }
        }
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
        connectOutput.getConnector().setStroke(null);
        
        for (var node : getChildren()) {
            if (node instanceof Card) {
                Card card = (Card) node;
                for (var node1 : card.getInputs()) {
                    if (node1 instanceof Input) {
                        Input input1 = (Input) node1;
                        input1.setActive(false);
                    }
                }
            }
        }
        
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
