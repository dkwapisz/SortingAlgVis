module org.sorting {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.sorting to javafx.fxml;
    exports org.sorting;
}