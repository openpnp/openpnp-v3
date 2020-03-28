package org.openpnp.openpnp_v3;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

/**
 * Base class for simple components that just need to load an FXML of the same name as the class
 * and set the root. Classes should extend this class and call super(), and should have a matching
 * FXML file that has fx:root set. FXML components should have a Group wrapper as their root
 * component.
 */
public class GroupComponent extends Group {
    public GroupComponent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(getClass().getSimpleName() + ".fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
