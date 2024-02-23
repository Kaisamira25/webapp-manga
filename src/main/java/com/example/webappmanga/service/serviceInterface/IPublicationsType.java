package com.example.webappmanga.service.serviceInterface;


import com.example.webappmanga.model.PublicationsType;

import java.util.List;
import java.util.Optional;

public interface IPublicationsType {

    PublicationsType createType(PublicationsType typeDTO);
    PublicationsType updateType(Integer id, PublicationsType typeDTO);
    boolean deleteType(Integer id);
    PublicationsType getByTypeId(Integer id);
    List<PublicationsType> getAllType();

    Optional<PublicationsType> findByType(String publicationsType);
}
