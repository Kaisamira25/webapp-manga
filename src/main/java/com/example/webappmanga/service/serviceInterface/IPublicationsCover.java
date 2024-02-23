package com.example.webappmanga.service.serviceInterface;

import com.example.webappmanga.model.PublicationsCover;

import java.util.List;
import java.util.Optional;

public interface IPublicationsCover {

    PublicationsCover createCover(PublicationsCover coverDTO);
    PublicationsCover updateCover(Integer id, PublicationsCover coverDTO);
    boolean deleteCover(Integer id);
    PublicationsCover getByCoverId(Integer id);
    Optional<PublicationsCover> findByCoverType(String coverType);
    List<PublicationsCover> getAllCover();
}
