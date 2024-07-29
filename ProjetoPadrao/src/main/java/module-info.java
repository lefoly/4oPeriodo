module start {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens controller to javafx.fxml;
    exports controller;
    exports start;
}
