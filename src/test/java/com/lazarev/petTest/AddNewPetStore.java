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

@Epic("Pet контроллер")
@Feature("Добавление  нового питомца")
public class AddNewPetStore extends BaseTest {

    @Test
    @Story("Добавление нового питомца. Позитивный сценарий")
    public void testAddNewPetPositive() {

        PetBodyModel petModelRequest = getAddNewPetModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperPost = steps.createNewPetStore(petModelRequest);

        ResponseWrapper responseWrapperGet = steps.findPetByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperPost.as(PetBodyModel.class))
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(responseWrapperGet.as(PetBodyModel.class));
                    softAssertions
                            .assertThat(responseWrapperPost.getStatusCode())
                            .withFailMessage("Status code doesn`t match")
                            .isEqualTo(STATUS_CODE_OK);
                }
        );
    }

    @Test
    @Story("Добавление нового питомца с невалидным айди. Негативный сценарий")
    public void testAddNewPetNegative() {
        PetBodyModel petModelRequest = getAddNewPetModel(NOT_VALID_PET_ID);

        ResponseWrapper responseWrapper = steps.createNewPetStore(petModelRequest);
        ErrorResponseModel error = responseWrapper.as(ErrorResponseModel.class);
        ErrorResponseModel errorResponse = getUnknownErrorResponse();

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
