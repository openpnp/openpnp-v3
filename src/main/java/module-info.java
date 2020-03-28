module org.openpnp.openpnp_v3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;

    opens org.openpnp.openpnp_v3 to javafx.fxml;
    opens org.openpnp.openpnp_v3.cvpipeline.editor to javafx.fxml;
    exports org.openpnp.openpnp_v3;
}