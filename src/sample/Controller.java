package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    @FXML private DatePicker outputExpirationDatePicker;
    @FXML private TextField subtractTxtBox;

    // Panels

    @FXML private AnchorPane mainPanel;
    @FXML private AnchorPane itemPanel;

    // Main Menu
    @FXML private TextField itemTxtbox;
    @FXML private TextField servingsPerItemTxtbox;
    @FXML private TextField qtyTxtbox;
    @FXML private ChoiceBox typeChoicebox;
    @FXML private DatePicker expirationDatepicker;
    @FXML private ListView productList;

    // Item Panel
    @FXML private TextField outputNameTxtbox;
    @FXML private TextField outputServingsTxtbox;
    @FXML private TextField outputTypeTxtbox;
    @FXML private TextField outputQtyTxtbox;
    @FXML private TextField outputExpirationTxtbox;
    @FXML private TextField outputExpirationTxtbox1;

    ArrayList <Item> items = new ArrayList<Item>();

    public void initialize(){ //this is like the MAIN where everythin gis set up..
        typeChoicebox.getItems().add(0,"Loose item");
        typeChoicebox.getItems().add(1,"Packaged item");
        mainPanel.setVisible(true);
        itemPanel.setVisible(false); // Switches item panel to main panel by setting it to invisible
        LocalDate today = LocalDate.now();
        System.out.println("Today's date is: " + LocalDate.now());
        System.out.println("Succesfully initialized!");
    }

    public void addToListButton(ActionEvent actionEvent) {
        //LocalDate date =  expirationDatepicker.getValue();

        items.add(new Item(itemTxtbox.getText(), Integer.parseInt(servingsPerItemTxtbox.getText()), Integer.parseInt(qtyTxtbox.getText()), typeChoicebox.getSelectionModel().getSelectedItem().toString(), expirationDatepicker.getValue()));
    System.out.println(items);
        updateListfunction();
        System.out.println("Succesfully added item!");

    }

    public void updateListfunction(){
        productList.getItems().clear(); // Makes sure the list is cleared before adding new person to the list (avoids printing past items again in the list)
        items.forEach((i)->productList.getItems().add(i.getItem()));

    }


    public void subtractServingsButton(ActionEvent actionEvent) {
       // int servings.
        int currentServings = items.get(pIndex).getServings();
        try {
            items.get(pIndex).setServings(currentServings - Integer.parseInt(subtractTxtBox.getText())); //change the number of servings.
            items.get(pIndex).setQuantity((int) Math.ceil((double)items.get(pIndex).getServings() / items.get(pIndex).sPerItem));      //quantity =servings / sPerItem ;
                //ceil rounds up to the ceiling. to rounding up. This makes sure that if there is slightly less than a single bottle there is still a bottle.

            outputQtyTxtbox.setText(String.valueOf(items.get(pIndex).getQuantity()));
            if(currentServings <= 0) {
                items.remove(pIndex);
                updateListfunction();
                mainPanel.setVisible(true);
                itemPanel.setVisible(false);
            }

        }catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect servings amount. please enter a number.");
            alert.setHeaderText(null);
            alert.setContentText("All 3 boxes need filling. If there is no subtopic, type the same topic.");
            alert.showAndWait();
        }


        outputServingsTxtbox.setText(Integer.toString(items.get(pIndex).getServings()));
    }

    public void backToMenuButton(ActionEvent actionEvent) {
        mainPanel.setVisible(true);
        itemPanel.setVisible(false); // Switches item panel to main panel by setting it to invisible
        System.out.println("Succesfully returned to main menu!");
    }

    public void removeFromList(ActionEvent actionEvent) {
        mainPanel.setVisible(true);
        itemPanel.setVisible(false); // Switches item panel to main panel by setting it to invisible
        items.remove(pIndex);
        updateListfunction();
        System.out.println("Succesfully removed item!");
    }

   private int pIndex;
    public void itemListClicked(MouseEvent mouseEvent) {
        mainPanel.setVisible(false);
        itemPanel.setVisible(true); // Switches menu panel to item panel by setting it to invisible

        pIndex = productList.getSelectionModel().getSelectedIndex();
        outputNameTxtbox.setText(items.get(pIndex).getItem());
        outputServingsTxtbox.setText(Integer.toString(items.get(pIndex).getServings()));
        outputTypeTxtbox.setText(items.get(pIndex).getType());
        outputQtyTxtbox.setText(Integer.toString(items.get(pIndex).getQuantity()));
        outputExpirationTxtbox.setText(items.get(pIndex).getExpirationDate().toString());
        outputExpirationDatePicker.setValue(items.get(pIndex).getExpirationDate());
        outputExpirationTxtbox1.setText(items.get(pIndex).getExpirationDate().toString());
        if (items.get(pIndex).getExpirationDate().isAfter(LocalDate.now())) {
            System.out.println("Item expired");
        } else {
            System.out.println("Item not expired");
        }

        System.out.println("Succesfully selected item");

    }
}

