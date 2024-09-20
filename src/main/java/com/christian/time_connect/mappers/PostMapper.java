package com.christian.time_connect.mappers;

import com.christian.time_connect.dto.PostRequest;
import com.christian.time_connect.entities.PostEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public PostEntity toPostEntity(PostRequest postRequest) {
        return PostEntity.builder()
                .description(postRequest.description())
                .build();
    }
}
