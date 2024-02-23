package com.example.webappmanga.service;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.dto.request.RPublicationsGiftDTO;
import com.example.webappmanga.model.PromotionalGift;
import com.example.webappmanga.model.Publications;
import com.example.webappmanga.repository.PromotionalGiftRepository;
import com.example.webappmanga.repository.PublicationsRepository;
import com.example.webappmanga.service.serviceInterface.IPublicationsGift;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationsGiftService implements IPublicationsGift {

    private final PublicationsRepository publicationsRepo;

    private final PromotionalGiftRepository promotionalGiftRepo;

    private final PublicationsService publicationsService;

    private final PromotionalGiftService promotionalGiftService;

    // Get All
    @Override
    public List<RPublicationsGiftDTO> getAllPublicationsGifts() {
        return publicationsRepo.findAllPublicationsGifts();
    }

    // Create
    @Override
    public Publications addPublicationsToGift(RPublicationsGiftDTO publicationsGiftDTO) {
        // Tìm Publications và Gift dựa trên ID từ publicationsGenreDTO
        Publications publications = publicationsService.getByPublicationsId(publicationsGiftDTO.publicationsID());
        PromotionalGift promotionalGift = promotionalGiftService.getByGiftId(publicationsGiftDTO.promotionalGiftID());

        // Kiểm tra xem liệu Publications và Gift có tồn tại không
        if (publications != null && promotionalGift != null) {
            // Nếu cả hai đều tồn tại, thêm Gift vào tập hợp genres của Publications
            publications.getGifts().add(promotionalGift);

            // Lưu Publications đã được cập nhật
            publicationsRepo.save(publications);
            log.info(">>>>>> PublicationsGenreService:create | Added a Gift with ID:{} to a Publications with ID:{} ", promotionalGift.getPromotionalGiftID(), publications.getPublicationsID());
            return publications;
        }

        // Nếu Publications hoặc Genre không tồn tại, trả về null
        return null;
    }

    @Override
    public boolean removePublicationsFromGift(RPublicationsGiftDTO publicationsGiftDTO) {
        // Tìm Publications dựa trên ID từ publicationsGenreDTO
        Publications publications = publicationsService.getByPublicationsId(publicationsGiftDTO.publicationsID());

        // Kiểm tra xem liệu Publications có tồn tại không
        if (publications != null) {
            // Duyệt qua tập hợp genres của Publications
            for (PromotionalGift promotionalGift : new ArrayList<>(publications.getGifts())) {
                // Nếu Genre hiện tại có ID giống với genreID, xóa nó khỏi tập hợp
                if (promotionalGift.getPromotionalGiftID().equals(publicationsGiftDTO.promotionalGiftID())) {
                    publications.getGifts().remove(promotionalGift);

                    // Lưu Publications đã được cập nhật
                    publicationsRepo.save(publications);

                    // Xóa thành công thể loại có id: ... trong ấn phẩm có id: ...
                    log.info(">>>>>> PublicationsGenreService:remove | Removed a Genre with ID:{} in a Publications with ID:{} ", publicationsGiftDTO.promotionalGiftID(), publications.getPublicationsID());
                    return true;
                }
            }
        }

        // Nếu không tìm thấy Genre hoặc Publications không tồn tại, log một thông báo lỗi
        log.error(">>>>>> PublicationsGenreService:remove | No Genre found with ID:{} in a Publications with ID:{} ", publicationsGiftDTO.promotionalGiftID(), publicationsGiftDTO.publicationsID());
        return false;
    }

    /**
     * 1. Vòng lặp for-each: for (Genre genre : new ArrayList<>(publications.getGenres())) {...}.
     * - Đây là một vòng lặp for-each, nó sẽ duyệt qua từng phần tử trong tập hợp publications.getGenres().
     * - Mỗi lần lặp, nó sẽ gán phần tử hiện tại cho biến genre.
     *
     * 2. Tạo một bản sao của tập hợp genres: new ArrayList<>(publications.getGenres()).
     * - Đây là một cách để tạo một bản sao của tập hợp genres. Bạn cần làm điều này vì bạn không thể xóa một phần tử khỏi tập hợp trong khi đang duyệt qua nó (điều này sẽ gây ra lỗi ConcurrentModificationException).
     * - Bằng cách tạo một bản sao, bạn có thể xóa phần tử khỏi tập hợp gốc mà không ảnh hưởng đến vòng lặp.
     *
     * 3. Điều kiện if: if (genre.getGenreID().equals(publicationsGenreDTO.genreID())) {...}.
     * - Điều kiện này kiểm tra xem ID của Genre hiện tại có giống với genreID bạn muốn xóa không. Nếu có, thì nó sẽ thực hiện các lệnh bên trong khối if.
     */

}


