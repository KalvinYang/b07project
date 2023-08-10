package com.b07project.application.MVP;

public interface LoginView {
    String getEmail();
    String getPassword();
    void moveToStoreOwner();
    void moveToShopper ();
    void makeToast (String msg);
}
