package com.b07project.application;

public class Item {
    String name;
    String description;
    float price;
    String brand;
    String specifications;

    //image field may be introduced

    Item(String name, String description, float price, String brand, String specifications) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.specifications = specifications;
    }

    //Following methods need to reflect changes in firebase

    void Modify_name(String name){
        this.name = name;
    }

    void Modify_description(String description){
        this.description = description;
    }

    void Modify_price(float price){
        this.price = price;
    }

    void Modify_brand(String brand){
        this.brand = brand;
    }

    void Modify_specifications(String Specifications){
        this.specifications = specifications;
    }

}
