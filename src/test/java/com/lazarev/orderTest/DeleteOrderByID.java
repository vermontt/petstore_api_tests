package com.lazarev.orderTest;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.OrderBodyModel;
import com.lazarev.dto.response.OkResponseModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderExpectedResponse.getOkResponse;
import static com.lazarev.utils.BuilderBody.getNewOrderModel;
import static com.lazarev.utils.TestData.*;


@Epic("Store контроллер")
@Feature("Удаление заказа")
public class DeleteOrderByID extends BaseTest {

    @Test
    @Story("Удаление заказа по айди. Позитивный сценарий")
    public void testDeleteOrderPositive() {

        OrderBodyModel orderModel = getNewOrderModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperPost = steps.createNewOrder(orderModel);

        ResponseWrapper responseWrapperDelete = steps.deleteOrderByID(VALID_TEST_PET_ID);

        OkResponseModel okResponse = responseWrapperDelete.as(OkResponseModel.class);

        OkResponseModel okResponseExpected = getOkResponse();

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
    @Story("Удаление заказа по невалидному айди. Негативный сценарий")
    public void testDeletePetNegative() {

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
