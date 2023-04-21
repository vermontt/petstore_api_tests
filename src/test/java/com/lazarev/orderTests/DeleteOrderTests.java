package com.lazarev.orderTests;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.OrderDto;
import com.lazarev.dto.response.OkResponseDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderExpectedResponse.getOkResponse;
import static com.lazarev.utils.BuilderBody.getNewOrderDto;
import static com.lazarev.utils.TestData.*;


@Epic("Store контроллер")
@Feature("Удаление заказа")
public class DeleteOrderTests extends BaseTest {

    @Test
    @DisplayName("Удаление заказа по айди. Позитивный сценарий")
    public void deleteOrderPositiveTest() {

        OrderDto orderModel = getNewOrderDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperPost = steps.createNewOrder(orderModel);
        ResponseWrapper responseWrapperDelete = steps.deleteOrderByID(VALID_TEST_PET_ID);
        OkResponseDto okResponse = responseWrapperDelete.as(OkResponseDto.class);
        OkResponseDto okResponseExpected = getOkResponse();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(okResponse.getCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(okResponse)
                            .withFailMessage("Response body doesn`t match")
                            .isEqualTo(okResponseExpected);
                }
        );
    }

    @Test
    @DisplayName("Удаление заказа по невалидному айди. Негативный сценарий")
    public void deletePetNegativeTest() {

        ResponseWrapper responseWrapper = steps.deleteOrderByID(NOT_VALID_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapper.getStatusCode())
                            .withFailMessage("Error code status")
                            .isEqualTo(STATUS_CODE_ERROR_404);
                }
        );
    }
}