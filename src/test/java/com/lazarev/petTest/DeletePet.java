package com.lazarev.petTest;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.PetBodyModel;
import com.lazarev.dto.response.OkResponseModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static com.lazarev.utils.BuilderExpectedResponse.getOkResponse;
import static com.lazarev.utils.BuilderBody.getAddNewPetModel;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.TestData.*;

@Epic("Pet контроллер")
@Feature("Удаление питомца")
public class DeletePet extends BaseTest {


    @Test
    @Story("Удаление питомца по айди. Позитивный сценарий")
    public void testDeletePetPositive() {

        PetBodyModel petModelRequest = getAddNewPetModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperPost = steps.createNewPetStore(petModelRequest);

        ResponseWrapper responseWrapperDelete = steps.deletePetByID(VALID_TEST_PET_ID);

        OkResponseModel okResponse = responseWrapperDelete.as(OkResponseModel.class);

        OkResponseModel okResponseExpected = getOkResponse();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(okResponse.getCode())
                            .withFailMessage("Error code status")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(okResponse)
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(okResponseExpected);
                }
        );
    }

    @Test
    @Story("Удаление питомца по невалидному айди. Негативный сценарий")
    public void testDeletePetNegative() {

        ResponseWrapper responseWrapper = steps.deletePetByID(NOT_VALID_PET_ID);

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

