package com.b07project.application.MVP;

public class LoginPresenter implements LoginModel.LoginListener{
    public LoginModel model;
    public LoginView loginView;
    public LoginPresenter(LoginView loginView, LoginModel model)
    {
        this.model = model;
        this.loginView = loginView;
    }
    public boolean checkEmail()
    {
        String check = loginView.getEmail();
        if (check.isEmpty())
        {
            loginView.makeToast("Enter email!");
            return false;
        }
        return true;
    }
    public boolean checkPassword()
    {
        String check = loginView.getPassword();
        if (check.length() < 6){
            loginView.makeToast("Enter valid password! (longer than 6 characters!)");
            return false;
        }
        return true;
    }
    public void accountError()
    {
        if (loginView != null)
            loginView.makeToast("The email or password you entered is incorrect. Please try again.");
    }
    public void navigate(boolean owner)
    {
        if (loginView != null)
            if (owner) loginView.moveToStoreOwner();
            else loginView.moveToShopper();
    }

    public void login(){
        if(loginView != null && checkEmail() && checkPassword()){
            model.login(loginView.getEmail(), loginView.getPassword(), this);
        }
    }
}
