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

    ArrayList <Item> items = new ArrayList<Item>();

    public void initialize(){ //this is like the MAIN where everythin gis set up..
        typeChoicebox.getItems().add(0,"Loose item");
        typeChoicebox.getItems().add(1,"Packaged item");
    }

    public void addToListButton(ActionEvent actionEvent) {
        items.add(new Item(itemTxtbox.getText(), Integer.parseInt(servingsTxtbox.getText()), Integer.parseInt(qtyTxtbox.getText()), typeChoicebox.getSelectionModel().getSelectedItem().toString()));
    System.out.println(items);
    }

    public void subtractServingsButton(ActionEvent actionEvent) {
    }

    public void backToMenuButton(ActionEvent actionEvent) {
    }

    public void removeFromList(ActionEvent actionEvent) {
    }
}
