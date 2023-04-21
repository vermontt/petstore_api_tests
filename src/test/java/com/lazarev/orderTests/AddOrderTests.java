package com.lazarev.orderTests;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.OrderDto;
import com.lazarev.dto.response.ErrorResponseDto;
import com.lazarev.utils.ResponseWrapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.lazarev.utils.BuilderBody.getNewOrderDto;
import static com.lazarev.utils.BuilderExpectedResponse.getUnknownErrorResponse;
import static com.lazarev.utils.TestData.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Epic("Store контроллер")
@Feature("Добавление нового заказа")
public class AddOrderTests extends BaseTest {

    @Test
    @DisplayName("Добавление нового заказа. Позитивный сценарий")
    public void addOrderPositiveTest() {

        OrderDto orderModel = getNewOrderDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperPost = steps.createNewOrder(orderModel);
        ResponseWrapper responseWrapperGet = steps.findOrderByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperPost.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(responseWrapperPost.as(OrderDto.class))
                            .withFailMessage("Body doesn`t match")
                            .isEqualTo(responseWrapperGet.as(OrderDto.class));
                }
        );
    }

    @Test
    @DisplayName("Добавление нового заказа по невалдиному айди. Негативный сценарий")
    public void addOrderNegativeTest() {
        OrderDto orderModel = getNewOrderDto(NOT_VALID_PET_ID);

        ResponseWrapper responseWrapper = steps.createNewOrder(orderModel);
        ErrorResponseDto error = responseWrapper.as(ErrorResponseDto.class);
        ErrorResponseDto errorResponse = getUnknownErrorResponse();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapper.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_ERROR_500);
                    softAssertions
                            .assertThat(error)
                            .withFailMessage("Error body doesn`t math")
                            .isEqualTo(errorResponse);
                }
        );
    }
}