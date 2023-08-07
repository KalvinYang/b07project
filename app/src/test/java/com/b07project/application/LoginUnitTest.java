package com.b07project.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.b07project.application.MVP.LoginPresenter;
import com.b07project.application.MVP.LoginView;
import com.b07project.application.MVP.LoginModel;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginUnitTest {
    @Mock
    LoginView view;

    @Mock
    LoginModel model;
    @Test
    public void testPresenterEmailBad(){
        when(view.getEmail()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter(view, model);
        assertEquals(presenter.checkEmail(), false);
        verify(view).makeToast(anyString());
    }
    @Test
    public void testPresenterValidEmail(){
        when(view.getEmail()).thenReturn("test@test.com");
        LoginPresenter presenter = new LoginPresenter(view, model);
        assertEquals(presenter.checkEmail(), true);
    }
    @Test
    public void testPresenterPasswordEmpty(){
        when(view.getPassword()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter(view, model);
        assertEquals(presenter.checkPassword(), false);
        verify(view).makeToast(anyString());
    }
    @Test
    public void testPresenterValidPassword(){
        when(view.getPassword()).thenReturn("passwordpassword");
        LoginPresenter presenter = new LoginPresenter(view, model);
        assertEquals(presenter.checkPassword(), true);
    }
    @Test
    public void testPresenterLogin(){
        when(view.getEmail()).thenReturn("test@testfail.com");
        when(view.getPassword()).thenReturn("passwordpassword");
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.login();
        verify(model).login("test@testfail.com", "passwordpassword", presenter);
    }
    @Test
    public void testModelLoginError(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.accountError();
        verify(view).makeToast(anyString());
    }
    @Test
    public void testSuccessOwnerLogin(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.navigate(true);
        verify(view).moveToStoreOwner();
    }
    @Test
    public void testSuccessShopperLogin(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.navigate(false);
        verify(view).moveToShopper();
    }
}