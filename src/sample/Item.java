package sample;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Item {

    private String item;
    int sPerItem;
    private int servings;
    private int quantity;
    private LocalDate expirationDate;
    private String type;  //string or Loose

    public String getItem() {
        return item;
    }

    public void setItem(String item) {

        this.item = item;

    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Item(String item, int sPerItem, int quantity, String type, LocalDate expirationDate ) {
        this.item = item;
        this.sPerItem = sPerItem;
        this.expirationDate = expirationDate;
        this.type = type;
        this.quantity = quantity;

        this.setServings(sPerItem*quantity);
    }




}
