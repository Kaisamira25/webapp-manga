package com.example.webappmanga.service;

import com.example.webappmanga.model.PublicationsCover;
import com.example.webappmanga.repository.PublicationsCoverRepository;
import com.example.webappmanga.service.serviceInterface.IPublicationsCover;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationsCoverService implements IPublicationsCover {

    private final PublicationsCoverRepository coverRepo;

    @Override
    public PublicationsCover createCover(PublicationsCover bookCoverDTO) {
        PublicationsCover cover = new PublicationsCover();
        cover.setCoverType(bookCoverDTO.getCoverType());
        coverRepo.save(cover);
        log.info(">>>>>> PublicationsCoverServiceImp:save | Create a PublicationsCover with name:{} ", cover.getCoverType());
        return cover;
    }

    @Override
    @Cacheable(cacheNames = "PublicationsCover", key = "'#id'")
    public PublicationsCover updateCover(Integer id, PublicationsCover bookCoverDTO) {
        PublicationsCover cover = getByCoverId(id);
        if (cover != null) {
            cover.setCoverType(bookCoverDTO.getCoverType());
            coverRepo.save(cover);
            log.info(">>>>>> PublicationsCoverServiceImp:update | Update a PublicationsCover with name:{} ", cover.getCoverType());
            return cover;
        }
        log.error(">>>>>>>PublicationsCoverServiceImp:update | No PublicationsCover found to update with id: {} ",id);
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "BookCover", key = "'#id'", allEntries = true)
    public boolean deleteCover(Integer id) {
        PublicationsCover cover = getByCoverId(id);
        if (cover != null) {
            coverRepo.delete(cover);
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "PublicationsCover", key = "'#id'")
    public PublicationsCover getByCoverId(Integer id) {
        return coverRepo.findById( (Integer) id).orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "PublicationsCover", key = "'#publicationsCoverList'")
    public List<PublicationsCover> getAllCover() {
        return coverRepo.findAll();
    }

    @Cacheable(cacheNames = "PublicationsCover", key = "'#PublicationsCover'")
    @Override
    public Optional<PublicationsCover> findByCoverType(String coverType) {
        return coverRepo.findByCoverType(coverType);
    }

}
