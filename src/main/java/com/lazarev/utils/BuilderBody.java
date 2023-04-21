package com.lazarev.utils;

import com.lazarev.dto.request.PetDto;
import com.lazarev.dto.request.OrderDto;

import java.util.List;

import static com.lazarev.utils.TestData.*;

/**
 * Класс, в котором хранятся методы для формирования тела запоса
 */
public class BuilderBody {

    /**
     * Метод для формирования тела запроса создания питомца
     *
     * @param id идентификатор питомца
     * @return тело запроса
     */
    public static PetDto getNewPetDto(String id) {
        return PetDto.builder()
                .id(id)
                .category(PetDto.CategoryAndTagsDto.builder()
                        .id(VALID_CATEGORY_ID)
                        .name(getRandomCategoryName())
                        .build())
                .name(getRandomPetName())
                .photoUrls(List.of(getRandomUrl()))
                .tags(List.of(PetDto.CategoryAndTagsDto.builder()
                        .id(VALID_TAG_ID)
                        .name(VALID_TAG_NAME)
                        .build()))
                .status(VALID_STATUS)
                .build();
    }

    /**
     * Метод для формирования тела запроса на создание заказа
     *
     * @param id идентификатор заказа
     * @return тело запроса
     */
    public static OrderDto getNewOrderDto(String id) {
        return OrderDto.builder()
                .id(id)
                .petId(VALID_TEST_PET_ID)
                .quantity(QUANTITY)
                .shipDate(SHIP_DATE)
                .complete(COMPLETE)
                .status(STATUS_ORDER)
                .build();
    }
}