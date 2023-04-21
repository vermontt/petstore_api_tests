package com.lazarev.orderTests;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.OrderDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderBody.getNewOrderDto;
import static com.lazarev.utils.TestData.*;

@Epic("Store контроллер")
@Feature("Поиск заказа по айди")
public class FindOrderTests extends BaseTest {

    @Test
    @DisplayName("Поиск заказа по валидному айди. Позитивный сценарий")
    public void findOrderPositiveTest() {

        OrderDto orderModel = getNewOrderDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperPost = steps.createNewOrder(orderModel);
        ResponseWrapper responseWrapperGet = steps.findOrderByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperGet.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(responseWrapperGet.as(OrderDto.class))
                            .withFailMessage("Response body doesn`t match")
                            .isEqualTo(responseWrapperPost.as(OrderDto.class));
                }
        );
    }

    @Test
    @DisplayName("Поиск заказа по невалидному айди. Негативный сценарий")
    public void findPetNegativeTest() {

        ResponseWrapper responseWrapper = steps.findOrderByID(NOT_VALID_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapper.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_ERROR_404);
                }
        );
    }
}