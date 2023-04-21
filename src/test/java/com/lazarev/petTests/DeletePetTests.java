package com.lazarev.petTests;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.PetDto;
import com.lazarev.dto.response.OkResponseDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static com.lazarev.utils.BuilderExpectedResponse.getOkResponse;
import static com.lazarev.utils.BuilderBody.getNewPetDto;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.TestData.*;

@Epic("Pet контроллер")
@Feature("Удаление питомца")
public class DeletePetTests extends BaseTest {


    @Test
    @DisplayName("Удаление питомца по айди. Позитивный сценарий")
    public void deletePetPositiveTest() {

        PetDto petModelRequest = getNewPetDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperPost = steps.createNewPet(petModelRequest);
        ResponseWrapper responseWrapperDelete = steps.deletePetByID(VALID_TEST_PET_ID);
        OkResponseDto okResponse = responseWrapperDelete.as(OkResponseDto.class);
        OkResponseDto okResponseExpected = getOkResponse();

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
    @DisplayName("Удаление питомца по невалидному айди. Негативный сценарий")
    public void deletePetNegativeTest() {

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