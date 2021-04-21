package com.cloudinteractiveHungChiHang.presenter;

public class ItemPresenter {
    private ItemPresenter.View view;

    public ItemPresenter(View view) {

        this.view = view;
    }

    public void setData(){
        view.setdetail();
    }

    public interface View{

        void setdetail();


    }
}
