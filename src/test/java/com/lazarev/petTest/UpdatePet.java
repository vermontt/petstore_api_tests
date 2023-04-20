package com.lazarev.petTest;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.PetBodyModel;
import com.lazarev.dto.response.ErrorResponseModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderExpectedResponse.getUnknownErrorResponse;
import static com.lazarev.utils.BuilderBody.getAddNewPetModel;
import static com.lazarev.utils.TestData.*;
import static com.lazarev.utils.TestData.STATUS_CODE_ERROR_500;

@Epic("Pet контроллер")
@Feature("Обновление питомца")
public class UpdatePet extends BaseTest {

    @Test
    @Story("Обновление питомца. Позитивный сценарий")
    public void testUpdatePetPositive() {

        PetBodyModel petModelRequest = getAddNewPetModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperUpdate = steps.updatePet(petModelRequest);

        ResponseWrapper responseWrapperGet = steps.findPetByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperUpdate.as(PetBodyModel.class))
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(responseWrapperGet.as(PetBodyModel.class));
                    softAssertions
                            .assertThat(responseWrapperUpdate.getStatusCode())
                            .withFailMessage("Status code doesn`t match")
                            .isEqualTo(STATUS_CODE_OK);
                }
        );

    }


    @Test
    @Story("Обновление питомца по невалидному айди. Негативный сценарий")
    public void testUpdatePetNegative() {
        PetBodyModel petModelRequest = getAddNewPetModel(NOT_VALID_PET_ID);

        ResponseWrapper responseWrapper = steps.updatePet(petModelRequest);
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
