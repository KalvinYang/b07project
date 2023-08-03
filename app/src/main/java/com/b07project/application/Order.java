package com.b07project.application;

import java.util.HashMap;

class Order extends ObjectsToSave{
    String shopper;
    String brand;
    String i_name;
    float price;
    int itemNumber;
    // Status can one of the following: Cart (Only shopper can see) > Ordered > Complete | Canceled
    String status;
    // Get cartNumber when added to cart
    int cartNumber;

    // private DatabaseReference ref = MainActivity.db.getReference(this.getClass().getSimpleName());

    // When someone makes an order (Adds something to cart)
    Order(String shopper, String brand, String i_name, float price, int itemNumber) {
        super(Order.class);
        this.shopper = shopper;
        this.brand = brand;
        this.status = "Cart";
        this.price = price;
        this.i_name = i_name;
        this.itemNumber = itemNumber;
        this.cartNumber = 0;
    }

    // Taking information from database.
    Order(String shopper, String brand, String i_name, String status, int orderNumber, float price, int itemNumber, int cartNumber) {
        super(Order.class);
        this.shopper = shopper;
        this.brand = brand;
        this.status = status;
        this.i_name = i_name;
        this.itemNumber = itemNumber;
        this.cartNumber = cartNumber;
    }

    String changeStatus(User a) {
        if (a instanceof Shopper && status.equals("Cart")) {
            status = "Ordered";
        }
        else if (a instanceof StoreOwner) {
            status = "Complete";
        }
        return updateObject(createHashMap());
    }
    String cancelOrder() {
        status = "Canceled";
        return updateObject(createHashMap());
    }

    @Override
    HashMap<String, Object> createHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("shopper",this.shopper);
        map.put("brand",this.brand);
        map.put("i_name",this.i_name);
        map.put("status",this.status);
        map.put("price",this.price);
        map.put("itemNumber",this.itemNumber);
        map.put("cartNumber",this.cartNumber);
        return map;
    }

    //@Override
    //    public void updateNumber() {
    //        Query query = ref.child("current");
    //
    //        query.addListenerForSingleValueEvent(new ValueEventListener() {
    //            @Override
    //            public void onDataChange(@NonNull DataSnapshot snapshot) {
    //                int Num = Integer.parseInt(snapshot.getValue().toString());
    //                orderNumber = Num;
    //                Num++;
    //                ref.child("current").setValue(Num);
    //                updateObject();
    //            }
    //
    //            @Override
    //            public void onCancelled(@NonNull DatabaseError error) {
    //                return;
    //            }
    //        });
    //    }
    //
    //    @Override
    //    public void updateObject() {
    //        ref.child(Integer.toString(this.orderNumber)).child("shopper").setValue(this.shopper);
    //        ref.child(Integer.toString(this.orderNumber)).child("brand").setValue(this.brand);
    //        ref.child(Integer.toString(this.orderNumber)).child("i_name").setValue(this.i_name);
    //        ref.child(Integer.toString(this.orderNumber)).child("status").setValue(this.status);
    //        ref.child(Integer.toString(this.orderNumber)).child("orderNumber").setValue(this.orderNumber);
    //        ref.child(Integer.toString(this.orderNumber)).child("price").setValue(this.price);
    //        ref.child(Integer.toString(this.orderNumber)).child("itemNumber").setValue(this.itemNumber);
    //        ref.child(Integer.toString(this.orderNumber)).child("cartNumber").setValue(this.cartNumber);
    //    }
    //
    //    @Override
    //    public void deleteObject() {
    //        ref.child(Integer.toString(this.orderNumber)).removeValue();
    //    }
}
