package com.lazarev.petTest;

import com.lazarev.base.BaseTest;
import com.lazarev.dto.request.PetBodyModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.lazarev.utils.ResponseWrapper;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static com.lazarev.utils.BuilderBody.getAddNewPetModel;
import static com.lazarev.utils.TestData.*;

@Epic("Pet контроллер")
@Feature("Поиск питомца по айди")
public class FindPetByID extends BaseTest {

    @Test
    @Story("Поиск питомца по айди. Позитивный сценарий")
    public void testFindPetByIDPositive() {

        PetBodyModel petModelRequest = getAddNewPetModel(VALID_TEST_PET_ID);

        ResponseWrapper responseWrapperPost = steps.createNewPetStore(petModelRequest);

        ResponseWrapper responseWrapperGet = steps.findPetByID(VALID_TEST_PET_ID);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(responseWrapperGet.getStatusCode())
                            .withFailMessage("Status code error")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(responseWrapperGet.as(PetBodyModel.class))
                            .withFailMessage("Response body doesn`t math")
                            .isEqualTo(responseWrapperPost.as(PetBodyModel.class));
                }
        );

    }


    @Test
    @Story("Поиск питомца по невалидному айди. Негативный сценарий")
    public void testFindPetByIDNegative() {

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
