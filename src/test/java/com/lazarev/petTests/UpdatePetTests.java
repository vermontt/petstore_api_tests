package com.lazarev.petTests;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.PetDto;
import com.lazarev.dto.response.ErrorResponseDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderExpectedResponse.getUnknownErrorResponse;
import static com.lazarev.utils.BuilderBody.getNewPetDto;
import static com.lazarev.utils.TestData.*;
import static com.lazarev.utils.TestData.STATUS_CODE_ERROR_500;

@Epic("Pet контроллер")
@Feature("Обновление питомца")
public class UpdatePetTests extends BaseTest {

    @Test
    @DisplayName("Обновление питомца. Позитивный сценарий")
    public void updatePetPositiveTest() {

        PetDto petModelRequest = getNewPetDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperUpdate = steps.updatePet(petModelRequest);
        ResponseWrapper responseWrapperGet = steps.findPetByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperUpdate.as(PetDto.class))
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(responseWrapperGet.as(PetDto.class));
                    softAssertions
                            .assertThat(responseWrapperUpdate.getStatusCode())
                            .withFailMessage("Status code doesn`t match")
                            .isEqualTo(STATUS_CODE_OK);
                }
        );
    }

    @Test
    @DisplayName("Обновление питомца по невалидному айди. Негативный сценарий")
    public void updatePetNegativeTest() {

        PetDto petModelRequest = getNewPetDto(NOT_VALID_PET_ID);
        ResponseWrapper responseWrapper = steps.updatePet(petModelRequest);
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