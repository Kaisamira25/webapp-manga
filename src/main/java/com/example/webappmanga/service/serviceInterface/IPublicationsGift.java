package com.example.webappmanga.service.serviceInterface;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.request.RPublicationsGiftDTO;
import com.example.webappmanga.model.Publications;

import java.util.List;

public interface IPublicationsGift {
    Publications addPublicationsToGift(RPublicationsGiftDTO publicationsGiftDTO);
    boolean removePublicationsFromGift(RPublicationsGiftDTO publicationsGiftDTO);

    List<RPublicationsGiftDTO> getAllPublicationsGifts();
}

