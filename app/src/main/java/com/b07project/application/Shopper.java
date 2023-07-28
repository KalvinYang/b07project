package com.b07project.application;

class Shopper extends User{
    // Each user can have only 1 cart at a time they can add to, if the app is turned off
    // with a cart pending it does not save. When a shopper clicks on "order", then the cart is
    // saved to a shoppers "my orders" page and current_cart is returned to null.
    Cart current_cart = null;

    Shopper(String username, String password) {
        super(username, password);
    }

    void clearcart() {
        current_cart = null;
    }
}
