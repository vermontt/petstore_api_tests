package com.lazarev.petTests;

import com.lazarev.BaseTest;
import com.lazarev.dto.request.PetDto;
import com.lazarev.dto.response.ErrorResponseDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderExpectedResponse.getUnknownErrorResponse;
import static com.lazarev.utils.BuilderBody.getNewPetDto;
import static com.lazarev.utils.TestData.*;

@Epic("Pet контроллер")
@Feature("Добавление  нового питомца")
public class AddPetTests extends BaseTest {

    @Test
    @DisplayName("Добавление нового питомца. Позитивный сценарий")
    public void addNewPetPositiveTest() {

        PetDto petModelRequest = getNewPetDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperPost = steps.createNewPet(petModelRequest);
        ResponseWrapper responseWrapperGet = steps.findPetByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperPost.as(PetDto.class))
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(responseWrapperGet.as(PetDto.class));
                    softAssertions
                            .assertThat(responseWrapperPost.getStatusCode())
                            .withFailMessage("Status code doesn`t match")
                            .isEqualTo(STATUS_CODE_OK);
                }
        );
    }

    @Test
    @DisplayName("Добавление нового питомца с невалидным айди. Негативный сценарий")
    public void addNewPetNegativeTest() {

        PetDto petModelRequest = getNewPetDto(NOT_VALID_PET_ID);
        ResponseWrapper responseWrapper = steps.createNewPet(petModelRequest);
        ErrorResponseDto error = responseWrapper.as(ErrorResponseDto.class);
        ErrorResponseDto errorResponse = getUnknownErrorResponse();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapper.getStatusCode())
                            .withFailMessage("Status code doesn`t match")
                            .isEqualTo(STATUS_CODE_ERROR_500);
                    softAssertions
                            .assertThat(error)
                            .withFailMessage("Error body doesn`t math")
                            .isEqualTo(errorResponse);
                }
        );
    }
}