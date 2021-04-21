package com.cloudinteractiveHungChiHang.presenter;

public class ListPresenter {

    private ListPresenter.View view;

    public ListPresenter(View view) {

        this.view = view;
    }

    public void RequestApi(){
        view.webapi_call();
    }

    public interface View{

        void webapi_call();

    }

}
