package com.lazarev.orderTest;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.OrderBodyModel;
import com.lazarev.dto.response.ErrorResponseModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import static com.lazarev.utils.BuilderExpectedResponse.getUnknownErrorResponse;
import static com.lazarev.utils.BuilderBody.getNewOrderModel;
import static com.lazarev.utils.TestData.*;

@Epic("Store контроллер")
@Feature("Добавление нового заказа")
public class AddOrderPet extends BaseTest {

    @Test
    @Story("Добавление нового заказа. Позитивный сценарий")
    public void testAddOrderPositive() {

        OrderBodyModel orderModel = getNewOrderModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperPost = steps.createNewOrder(orderModel);

        ResponseWrapper responseWrapperGet = steps.findOrderByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperPost.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(responseWrapperPost.as(OrderBodyModel.class))
                            .withFailMessage("Body doesn`t match")
                            .isEqualTo(responseWrapperGet.as(OrderBodyModel.class));

                }

        );
    }


    @Test
    @Story("Добавление нового заказа по невалдиному айди. Негативный сценарий")
    public void testAddOrderNegative() {
        OrderBodyModel orderModel = getNewOrderModel(NOT_VALID_PET_ID);

        ResponseWrapper responseWrapper = steps.createNewOrder(orderModel);
        ErrorResponseModel error = responseWrapper.as(ErrorResponseModel.class);
        ErrorResponseModel errorResponse = getUnknownErrorResponse();

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


