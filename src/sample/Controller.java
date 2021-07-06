package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
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
        System.out.println("Successfully initialized!");

     
        //https://mkyong.com/java/how-to-parse-json-with-gson/
        //gson from google is a json reader and wrtier library which makes it easy and convenity to do this task.
        Gson gson = new Gson();

        try (Reader reader = new FileReader("items.json")) {

            // Convert JSON File to Java Object

           items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {}.getType()); //change from list to array
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error with  Json file.");
            alert.setHeaderText(null);
            alert.setContentText("Unable to open the Json save file, A new one will be created upon next save.");
            alert.showAndWait();

        }
        updateListfunction();

    }

    public void addToListButton(ActionEvent actionEvent) {

        //if the item already exists, dont add it again.
        items.add(new Item(itemTxtbox.getText(), Integer.parseInt(servingsPerItemTxtbox.getText()), Integer.parseInt(qtyTxtbox.getText()), typeChoicebox.getSelectionModel().getSelectedItem().toString(), expirationDatepicker.getValue()));
    System.out.println(items);
    //end list
        updateListfunction();
        System.out.println("Successfully added item!");

    }

    public void updateListfunction(){
        productList.getItems().clear(); // Makes sure the list is cleared before adding new person to the list (avoids printing past items again in the list)
        items.forEach((i)->productList.getItems().add(i.getItem()));

        //for (int i +0 ; I<items.size(); i++)  easier to make msitakes with the size, etc.

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
            alert.setContentText("Please enter a valid int.");
            alert.showAndWait();

        }


        outputServingsTxtbox.setText(Integer.toString(items.get(pIndex).getServings()));
    }

    public void backToMenuButton(ActionEvent actionEvent) {
        mainPanel.setVisible(true);
        itemPanel.setVisible(false); // Switches item panel to main panel by setting it to invisible
        System.out.println("Successfully returned to main menu!");
    }

    public void removeFromList(ActionEvent actionEvent) {
        mainPanel.setVisible(true);
        itemPanel.setVisible(false); // Switches item panel to main panel by setting it to invisible
        items.remove(pIndex);
        updateListfunction();
        System.out.println("Successfully removed item!");
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

        System.out.println("Successfully selected item");

    }

    public void saveButton(ActionEvent actionEvent) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Java objects to String
        String json = gson.toJson(items);

        //System.out.println(json);

        // Java objects to File
        try (FileWriter writer = new FileWriter("items.json")) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

