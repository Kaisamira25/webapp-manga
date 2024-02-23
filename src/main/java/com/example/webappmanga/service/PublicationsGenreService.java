package com.example.webappmanga.service;

import com.example.webappmanga.dto.request.RPublicationsGenreDTO;
import com.example.webappmanga.model.Genre;
import com.example.webappmanga.model.Publications;
import com.example.webappmanga.repository.GenreRepository;
import com.example.webappmanga.repository.PublicationsRepository;
import com.example.webappmanga.service.serviceInterface.IPublicationsGenre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationsGenreService implements IPublicationsGenre {

    private final PublicationsRepository publicationsRepo;

    private final GenreRepository genreRepo;

    private final PublicationsService publicationsService;

    private final GenreService genreService;

    // Get All
    @Override
    public List<RPublicationsGenreDTO> getAllPublicationsGenres() {
        return publicationsRepo.findAllPublicationsGenres();
    }

    // Create
    @Override
    public Publications addPublicationsToGenre(RPublicationsGenreDTO publicationsGenreDTO) {
        // Tìm Publications và Genre dựa trên ID từ publicationsGenreDTO
        Publications publications = publicationsService.getByPublicationsId(publicationsGenreDTO.publicationsID());
        Genre genre = genreService.getByGenreId(publicationsGenreDTO.genreID());

        // Kiểm tra xem liệu Publications và Genre có tồn tại không
        if (publications != null && genre != null) {
            // Nếu cả hai đều tồn tại, thêm Genre vào tập hợp genres của Publications
            publications.getGenres().add(genre);

            // Lưu Publications đã được cập nhật
            publicationsRepo.save(publications);
            log.info(">>>>>> PublicationsGenreService:create | Added a Genre with ID:{} to a Publications with ID:{} ", genre.getGenreID(), publications.getPublicationsID());
            return publications;
        }

        // Nếu Publications hoặc Genre không tồn tại, trả về null
        return null;
    }

    // Delete
    @Override
    public boolean removePublicationsFromGenre(RPublicationsGenreDTO publicationsGenreDTO) {
        // Tìm Publications dựa trên ID từ publicationsGenreDTO
        Publications publications = publicationsService.getByPublicationsId(publicationsGenreDTO.publicationsID());

        // Kiểm tra xem liệu Publications có tồn tại không
        if (publications == null) {
            log.error(">>>>>> PublicationsGenreService:remove | No Publications found with ID:{} ", publicationsGenreDTO.publicationsID());
            return false;
        }

        // Duyệt qua tập hợp genres của Publications
        for (Genre genre : new ArrayList<>(publications.getGenres())) {
            // Nếu Genre hiện tại có ID giống với genreID, xóa nó khỏi tập hợp
            if (genre.getGenreID().equals(publicationsGenreDTO.genreID())) {
                publications.getGenres().remove(genre);

                // Lưu Publications đã được cập nhật
                publicationsRepo.save(publications);

                // Xóa thành công thể loại có id: ... trong ấn phẩm có id: ...
                log.info(">>>>>> PublicationsGenreService:remove | Removed a Genre with ID:{} in a Publications with ID:{} ", publicationsGenreDTO.genreID(), publications.getPublicationsID());
                return true;
            }
        }

        // Nếu không tìm thấy Genre, log một thông báo lỗi
        log.error(">>>>>> PublicationsGenreService:remove | No Genre found with ID:{} in a Publications with ID:{} ", publicationsGenreDTO.genreID(), publicationsGenreDTO.publicationsID());
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



//    // Delete
//    @Override
//    public boolean removePublicationFromGenre(RPublicationsGenreDTO publicationsGenreDTO) {
//        // Tìm Publications và Genre dựa trên ID từ publicationsGenreDTO
//        Publications publications = publicationsService.getByPublicationsId(publicationsGenreDTO.publicationsID());
//        Genre genre = genreService.getByGenreId(publicationsGenreDTO.genreID());
//
//        // Kiểm tra xem liệu Publications và Genre có tồn tại không
//        if (publications != null && genre != null) {
//            // Nếu cả hai đều tồn tại, xóa Genre khỏi tập hợp genres của Publications
//            if (publications.getGenres().remove(genre)) {
//                // Lưu Publications đã được cập nhật
//                publicationsRepo.save(publications);
//
//                // Xóa thành công thể loại có id: ... trong ấn phẩm có id: ...
//                log.info(">>>>>> PublicationsGenreService:remove | Removed a Genre with ID:{} in a Publication with ID:{} ", genre.getGenreID(), publications.getPublicationsID());
//                return true;
//            } else {
//                // Không tìm thấy thể loại có id: ... trong ấn phẩm có id: ...
//                log.error(">>>>>> PublicationsGenreService:remove | No Genre found with ID:{} in a Publication with ID:{} ", genre.getGenreID(), publications.getPublicationsID());
//                return false;
//            }
//        }
//        log.error(">>>>>> PublicationsGenreService:remove | Publications or Genre not found ");
//        return false;
//    }



