package com.github.handioq.diberapp.base.event;

import com.github.handioq.diberapp.model.dvo.ShopDvo;

public class RemoveShopEvent {

    private final ShopDvo shopDvo;

    public RemoveShopEvent(ShopDvo addressDvo) {
        this.shopDvo = addressDvo;
    }

    public ShopDvo getShopDvo() {
        return shopDvo;
    }

}
