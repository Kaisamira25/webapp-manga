package com.example.webappmanga.service;

import com.example.webappmanga.model.Genre;
import com.example.webappmanga.model.Publications;
import com.example.webappmanga.model.PublicationsCover;;
import com.example.webappmanga.model.PublicationsType;
import com.example.webappmanga.repository.GenreRepository;
import com.example.webappmanga.repository.PublicationsCoverRepository;
import com.example.webappmanga.repository.PublicationsRepository;
import com.example.webappmanga.repository.PublicationsTypeRepository;
import com.example.webappmanga.service.serviceInterface.IPublications;
import com.example.webappmanga.utilities.Time.GetTheTime;
import com.example.webappmanga.utilities.Time.WhatTime;
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
public class PublicationsService implements IPublications {

    private final PublicationsRepository publicationsRepo;

    private final PublicationsCoverRepository coverRepo;

    private final PublicationsTypeRepository typeRepo;

    private final GenreRepository genresRepo;

    @Override
    public Publications createPublications(Publications publicationsDTO) {
        Publications publications = new Publications();
        publications.setTitle(publicationsDTO.getTitle());
        publications.setUnitPrice(publicationsDTO.getUnitPrice());
        publications.setStock(publicationsDTO.getStock());
        publications.setAuthor(publicationsDTO.getAuthor());
        publications.setPublisher(publicationsDTO.getPublisher());
        publications.setPublicationYear(publicationsDTO.getPublicationYear());
        publications.setSummary(publicationsDTO.getSummary());
        publications.setArrivalDay(WhatTime.getTheTimeRightNow());

        // Kiểm tra xem trong publicationsDTO 2 thằng PublicationsCover và PublicationType có null hay không
        if (publicationsDTO.getPublicationsCover() != null && publicationsDTO.getPublicationsType() != null) {

            // Nếu cả hai đều khác null, mã sẽ lấy publicationsCoverId và publicationTypeId từ publicationsDTO.
            Integer publicationsCoverId = publicationsDTO.getPublicationsCover().getPublicationsCoverId();
            Integer publicationsTypeId = publicationsDTO.getPublicationsType().getPublicationsTypeId();

            // sử dụng coverRepo và typeRepo để tìm PublicationsCover và PublicationType tương ứng với các ID đã cho.
            Optional<PublicationsCover> optionalCover = coverRepo.findById(publicationsCoverId);
            Optional<PublicationsType> optionalType = typeRepo.findById(publicationsTypeId);

            // Nếu PublicationsCover và PublicationType tương ứng với các ID đã cho được tìm thấy
            if (optionalCover.isPresent() && optionalType.isPresent()) {
                // Sẽ thiết lập chúng cho đối tượng publications.
                PublicationsCover cover = optionalCover.get();
                PublicationsType type = optionalType.get();
                publications.setPublicationsCover(cover);
                publications.setPublicationsType(type);
            } else {
                // Handle the case where no BookCover or BookType was found
                throw new RuntimeException("PublicationsCover or PublicationType not found with provided id");
            }
        } else {
            // Handle the case where no BookCover or BookType was provided
            throw new RuntimeException("PublicationsCover or PublicationType not provided in the request");
        }

        publicationsRepo.save(publications);
        log.info(">>>>>> PublicationsServiceImp:save | Create a Publications with name:{} ", publications.getTitle());
        return publications;
    }

    @Override
    @Cacheable(cacheNames = "Publications", key = "'#id'")
    public Publications updatePublications(Integer id, Publications publicationsDTO) {
        Publications publications = getByPublicationsId(id);
        if (publications != null) {
            publications.setTitle(publicationsDTO.getTitle());
            publications.setUnitPrice(publicationsDTO.getUnitPrice());
            publications.setStock(publicationsDTO.getStock());
            publications.setAuthor(publicationsDTO.getAuthor());
            publications.setPublisher(publicationsDTO.getPublisher());
            publications.setPublicationYear(publicationsDTO.getPublicationYear());
            publications.setSummary(publicationsDTO.getSummary());
            publications.setArrivalDay(WhatTime.getTheTimeRightNow());
            publications.setPublicationsCover(publicationsDTO.getPublicationsCover());
            publications.setPublicationsType(publicationsDTO.getPublicationsType());
            publicationsRepo.save(publications);
            log.info(">>>>>> PublicationsServiceImp:update | Update a Publications with name:{} ", publications.getTitle());
            return publications;
        }
        log.error(">>>>>>> PublicationsServiceImp:update | No Publications found to update with id: {} ",id);
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "Publications", key = "'#id'", allEntries = true)
    public boolean deletePublications(Integer id) {
        Publications publications = getByPublicationsId(id);
        if (publications != null) {
            publicationsRepo.delete(publications);
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "Publications", key = "'#id'")
    public Publications getByPublicationsId(Integer id) {
        return publicationsRepo.findById( (Integer) id).orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "Publications", key = "'#publicationsList'")
    public List<Publications> getAllPublications() {
        return publicationsRepo.findAll();
    }

    @Cacheable(cacheNames = "Publications", key = "'#title'")
    @Override
    public Optional<Publications> findByTitle(String title) {
        return publicationsRepo.findByTitle(title);
    }


}

