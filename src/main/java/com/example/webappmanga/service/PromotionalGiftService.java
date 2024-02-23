package com.example.webappmanga.service;

import com.example.webappmanga.model.Genre;
import com.example.webappmanga.model.PromotionalGift;
import com.example.webappmanga.repository.GenreRepository;
import com.example.webappmanga.repository.PromotionalGiftRepository;
import com.example.webappmanga.service.serviceInterface.IGenre;
import com.example.webappmanga.service.serviceInterface.IPromotionalGift;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionalGiftService implements IPromotionalGift {

    private final PromotionalGiftRepository giftRepo;

    @Override
    public PromotionalGift createGift(PromotionalGift giftDTO) {
        PromotionalGift gift = new PromotionalGift();
        gift.setPromotionalGiftName(giftDTO.getPromotionalGiftName());
        gift.setPromotionalGiftType(giftDTO.getPromotionalGiftType());
        giftRepo.save(gift);
        log.info(">>>>>> GenreServiceImp:save | Create a Genre with name:{} ", gift.getPromotionalGiftName());
        return gift;
    }

    @Override
    @Cacheable(cacheNames = "Gift", key = "'#id'")
    public PromotionalGift updateGift(Integer id, PromotionalGift giftDTO) {
        PromotionalGift gift = getByGiftId(id);
        if (gift != null) {
            gift.setPromotionalGiftName(giftDTO.getPromotionalGiftName());
            gift.setPromotionalGiftType(giftDTO.getPromotionalGiftType());
            giftRepo.save(gift);
            log.info(">>>>>> GenreServiceImp:update | Update a Genre with name:{} ", gift.getPromotionalGiftName());
            return gift;
        }
        log.error(">>>>>>> GenreServiceImp:update | No Genre found to update with id: {} ",id);
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "Gift", key = "'#id'", allEntries = true)
    public boolean deleteGift(Integer id) {
        PromotionalGift gift = getByGiftId(id);
        if (gift != null) {
            giftRepo.delete(gift);
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "Genre", key = "'#id'")
    public PromotionalGift getByGiftId(Integer id) {
        return giftRepo.findById( (Integer) id).orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "Gift", key = "'#giftList'")
    public List<PromotionalGift> getAllGift() {
        return giftRepo.findAll();
    }

    @Cacheable(cacheNames = "Gift", key = "'#gift'")
    @Override
    public Optional<PromotionalGift> findByPromotionalGiftName(String gift) {
        return giftRepo.findByPromotionalGiftName(gift);
    }
}
