package com.lazarev.utils;

import com.lazarev.dto.request.PetBodyModel;
import com.lazarev.dto.request.OrderBodyModel;
import java.util.List;

import static com.lazarev.utils.TestData.*;

/**
 * Класс, в котором хранятся методы для формирования тела запоса
 */
public class BuilderBody {

    /**
     * Метод для формирования тела запроса создания питомца
     * @param id идентификатор питомца
     * @return тело запроса
     */
    public static PetBodyModel getAddNewPetModel(String id) {
        return PetBodyModel.builder()
                .id(id)
                .category(PetBodyModel.CategoryAndTagsItem.builder()
                        .id(VALID_CATEGORY_ID)
                        .name(getRandomCategoryName())
                        .build())
                .name(getRandomPetName())
                .photoUrls(List.of(getRandomUrl()))
                .tags(List.of(PetBodyModel.CategoryAndTagsItem.builder()
                        .id(VALID_TAG_ID)
                        .name(VALID_TAG_NAME)
                        .build()))
                .status(VALID_STATUS)
                .build();
    }

    /**
     * Метод для формирования тела запроса на создание заказа
     * @param id идентификатор заказа
     * @return тело запроса
     */
    public static OrderBodyModel getNewOrderModel(String id) {
        return OrderBodyModel.builder()
                .id(id)
                .petId(VALID_TEST_PET_ID)
                .quantity(QUANTITY)
                .shipDate(SHIP_DATE)
                .complete(COMPLETE)
                .status(STATUS_ORDER)
                .build();
    }


}
