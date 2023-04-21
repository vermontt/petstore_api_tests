package com.lazarev.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = "id")
public class PetDto {

    @JsonProperty("id") //для сходства имени с исходником джсон, для каждого параметра отдельная аннотация
    private final String id;
    private final CategoryAndTagsDto category;
    private final List<CategoryAndTagsDto> tags;

    @Data
    @Builder
    public static class CategoryAndTagsDto {
        private final String name;
        private final int id;
    }

    private final String name;
    private final List<String> photoUrls;
    private final String status;
}