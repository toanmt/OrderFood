package com.example.food.Model;

public class Menu {
    private String name;
    private String image;

    public Menu(){}

    public Menu(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
