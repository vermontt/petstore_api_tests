package com.lazarev.orderTest;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.OrderBodyModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderBody.getNewOrderModel;
import static com.lazarev.utils.TestData.*;

@Epic("Store контроллер")
@Feature("Поиск заказа по айди")
public class FindOrderByID extends BaseTest {

    @Test
    @Story("Поиск заказа по валидному айди. Позитивный сценарий")
    public void testFindOrderByIDPositive() {

        OrderBodyModel orderModel = getNewOrderModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperPost = steps.createNewOrder(orderModel);

        ResponseWrapper responseWrapperGet = steps.findOrderByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperGet.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(responseWrapperGet.as(OrderBodyModel.class))
                            .withFailMessage("Response body doesn`t match")
                            .isEqualTo(responseWrapperPost.as(OrderBodyModel.class));
                }
        );
    }

    @Test
    @Story("Поиск заказа по невалидному айди. Негативный сценарий")
    public void testFindPetByIDNegative() {

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
