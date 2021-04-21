package com.cloudinteractiveHungChiHang.presenter;


public class MainPresenter {


    private View view;

    public MainPresenter(View view) {

        this.view = view;
    }

    public void RequestApi(){
        view.goToListPage();
    }

    public interface View{

        void goToListPage();

    }
}