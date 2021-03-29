package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {

    @FXML private TextField itemTxtbox;
    @FXML private TextField servingsTxtbox;
    @FXML private TextField qtyTxtbox;
    @FXML private ChoiceBox typeChoicebox;
    @FXML private DatePicker xpirationDatepicker;
    @FXML private ListView productList;

    ArrayList <Item>


    public void addToListButton(ActionEvent actionEvent) {
    }

    public void subtractServingsButton(ActionEvent actionEvent) {
    }

    public void backToMenuButton(ActionEvent actionEvent) {
    }

    public void removeFromList(ActionEvent actionEvent) {
    }
}
