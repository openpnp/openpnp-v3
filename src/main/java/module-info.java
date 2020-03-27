module org.openpnp.openpnp_v3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openpnp.openpnp_v3 to javafx.fxml;
    exports org.openpnp.openpnp_v3;
}