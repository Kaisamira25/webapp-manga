package com.example.webappmanga.service;



import com.example.webappmanga.model.PublicationsType;
import com.example.webappmanga.repository.PublicationsTypeRepository;

import com.example.webappmanga.service.serviceInterface.IPublicationsType;
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
public class PublicationsTypeService implements IPublicationsType {

    private final PublicationsTypeRepository typeRepo;

    @Override
    public PublicationsType createType(PublicationsType typeDTO) {
        PublicationsType type = new PublicationsType();
        type.setPublicationsType(typeDTO.getPublicationsType());
        typeRepo.save(type);
        log.info(">>>>>> PublicationsTypeServiceImp:save | Create a PublicationsType with name:{} ", type.getPublicationsType());
        return type;
    }

    @Override
    @Cacheable(cacheNames = "BookType", key = "'#id'")
    public PublicationsType updateType(Integer id, PublicationsType typeDTO) {
        PublicationsType type = getByTypeId(id);
        if (type != null) {
            type.setPublicationsType(typeDTO.getPublicationsType());
            typeRepo.save(type);
            log.info(">>>>>> PublicationTypeServiceImp:update | Update a PublicationType with name:{} ", type.getPublicationsType());
            return type;
        }
        log.error(">>>>>>> PublicationTypeServiceImp:update | No PublicationType found to update with id: {} ",id);
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "PublicationsType", key = "'#id'", allEntries = true)
    public boolean deleteType(Integer id) {
        PublicationsType bookType = getByTypeId(id);
        if (bookType != null) {
            typeRepo.delete(bookType);
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "PublicationsType", key = "'#id'")
    public PublicationsType getByTypeId(Integer id) {
        return typeRepo.findById( (Integer) id).orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "PublicationType", key = "'#categoriesList'")
    public List<PublicationsType> getAllType() {
        return typeRepo.findAll();
    }

    @Cacheable(cacheNames = "PublicationType", key = "'#publicationType'")
    @Override
    public Optional<PublicationsType> findByType(String publicationsType) {
        return typeRepo.findByPublicationsType(publicationsType);
    }

}
