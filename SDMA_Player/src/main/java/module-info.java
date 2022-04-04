module com.example.sdma_player {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sdma_player to javafx.fxml;
    exports com.example.sdma_player;
}