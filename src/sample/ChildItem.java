package sample;

import java.time.LocalDate;

public class ChildItem {


    int servings;
    private LocalDate expirationDate;

    public ChildItem(int servings, LocalDate expirationDate) {
        this.servings = servings;
        this.expirationDate = expirationDate;
    }


    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


}
