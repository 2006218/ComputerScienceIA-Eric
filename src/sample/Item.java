package sample;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class Item {

    private String item;

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public DatePicker getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(DatePicker expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ChoiceBox getType() {
        return type;
    }

    public void setType(ChoiceBox type) {
        this.type = type;
    }

    private int servings;
    private String quantity;
    private DatePicker expirationDate;
    private ChoiceBox type;

    public Item(String item, int servings, String quantity, DatePicker expirationDate, ChoiceBox type) {
        this.item = item;
        this.servings = servings;
        this.expirationDate = expirationDate;
        this.type = type;

    }


}
