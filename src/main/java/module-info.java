module com.example.arbolbinarioapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.arbolbinarioapp to javafx.fxml;
    exports com.example.arbolbinarioapp;
}