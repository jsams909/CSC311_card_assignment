module com.example.csc311_card_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.smartcardio;


    opens com.example.csc311_card_assignment to javafx.fxml;
    exports com.example.csc311_card_assignment;
}