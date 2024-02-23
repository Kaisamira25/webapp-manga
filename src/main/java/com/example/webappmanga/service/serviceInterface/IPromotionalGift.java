package com.example.webappmanga.service.serviceInterface;


import com.example.webappmanga.model.PromotionalGift;

import java.util.List;
import java.util.Optional;

public interface IPromotionalGift {

    PromotionalGift createGift(PromotionalGift giftDTO);
    PromotionalGift updateGift(Integer id, PromotionalGift giftDTO);
    boolean deleteGift(Integer id);

    Optional<PromotionalGift> findByPromotionalGiftName(String giftName);
    PromotionalGift getByGiftId(Integer id);
    List<PromotionalGift> getAllGift();
}
