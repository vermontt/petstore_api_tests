package com.lazarev.petTests;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.PetDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderBody.getNewPetDto;
import static com.lazarev.utils.TestData.*;

@Epic("Pet контроллер")
@Feature("Поиск питомца")
public class FindPetTests extends BaseTest {

    @Test
    @DisplayName("Поиск питомца по айди. Позитивный сценарий")
    public void findPetPositiveTest() {

        PetDto petModelRequest = getNewPetDto(VALID_TEST_PET_ID);
        ResponseWrapper responseWrapperPost = steps.createNewPet(petModelRequest);
        ResponseWrapper responseWrapperGet = steps.findPetByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperGet.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(responseWrapperGet.as(PetDto.class))
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(responseWrapperPost.as(PetDto.class));
                }
        );
    }

    @Test
    @DisplayName("Поиск питомца по невалидному айди. Негативный сценарий")
    public void findPetNegativeTest() {

        ResponseWrapper responseWrapper = steps.findPetByID(NOT_VALID_PET_ID);

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