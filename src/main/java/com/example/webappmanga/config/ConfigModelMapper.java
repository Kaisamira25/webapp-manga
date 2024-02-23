package com.example.webappmanga.config;

import com.example.webappmanga.dto.request.PublicationsDTO;
import com.example.webappmanga.model.Publications;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hibernate.Hibernate.map;

@Configuration
public class ConfigModelMapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Ignore potential ambiguities
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        // Specify the mapping
        modelMapper.addMappings(new PropertyMap<PublicationsDTO, Publications>() {
            @Override
            protected void configure() {
                map().setPublicationsID(source.getPublicationsType().getPublicationsTypeId());
                // Add other mappings as needed
            }
        });

        return modelMapper;
    }
}

/*
    1. `@Configuration`: Đây là một annotation của Spring Framework, nó chỉ ra rằng lớp này chứa một hoặc nhiều phương thức `@Bean` và có thể được xử lý bởi Spring container để tạo ra các định nghĩa bean và dịch vụ yêu cầu cho những bean đó.

    2. `public class ConfigModelMapper`: Đây là tên của lớp cấu hình. Bạn có thể đặt tên tùy ý cho lớp này.

    3. `@Bean`: Đây là một annotation của Spring Framework, nó chỉ ra rằng một phương thức tạo ra một đối tượng cần được quản lý bởi Spring container.

    4. `public ModelMapper modelMapper()`: Đây là một phương thức tạo ra một đối tượng `ModelMapper`. `ModelMapper` là một thư viện Java giúp tự động ánh xạ giữa các đối tượng DTO và Model.

    5. `modelMapper.getConfiguration().setAmbiguityIgnored(true);`: Đây là một cấu hình cho ModelMapper để bỏ qua những trường hợp mơ hồ khi tìm thấy nhiều hệ thống thuộc tính nguồn.

    6. `modelMapper.addMappings(new PropertyMap<PublicationsDTO, Publications>() {...});`: Đây là một cấu hình cho ModelMapper để chỉ định rõ ràng ánh xạ giữa các thuộc tính nguồn và đích.

    7. `map().setPublicationsID(source.getPublicationsType().getPublicationsTypeId());`: Đây là một cấu hình chỉ định rõ ràng rằng `setPublicationsID()` sẽ được ánh xạ từ `getPublicationsType().getPublicationsTypeId()`.

    8. `return modelMapper;`: Phương thức trả về đối tượng `ModelMapper` đã được cấu hình.

 */

