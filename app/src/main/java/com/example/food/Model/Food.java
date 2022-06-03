package com.example.food.Model;

public class Food {

    private String image, menuID, name;
    private int price, discount;

    public Food(){}

    public Food(String image, String menuID, String name, int price, int discount) {
        this.image = image;
        this.menuID = menuID;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
