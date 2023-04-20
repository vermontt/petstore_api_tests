package com.lazarev.utils;

import com.lazarev.dto.response.ErrorResponseModel;
import com.lazarev.dto.response.OkResponseModel;

import static com.lazarev.utils.TestData.VALID_TEST_PET_ID;

/**
 * Класс, отвечающий за построение ожидаемог результата
 */
public class BuilderExpectedResponse {

    //Код неизвестной ошибки
    private static final int UNKNOWN_CODE = 500;

    //Тип неизвестной ошибки
    private static final String UNKNOWN_TYPE = "unknown";

    // Сообщение неизвестной ошибки
    private static final String UNKNOWN_MESSAGE = "something bad happened";

    /**
     * Метод формирования ожидаемого результата неизвестой ошибки
     * @return тело ошибки
     */
    public static ErrorResponseModel getUnknownErrorResponse(){
        return ErrorResponseModel.builder()
                .code(UNKNOWN_CODE)
                .type(UNKNOWN_TYPE)
                .message(UNKNOWN_MESSAGE)
                .build();
    }

    //Код ответа ОК
    private static final int CODE_OK = 200;

    //Сообщение статуса ОК
    private static final String MESSAGE_OK = VALID_TEST_PET_ID;

    /**
     * Метод формирования ожидаемого результата успешного сообщения
     * @return тело сообщения
     */
    public static OkResponseModel getOkResponse(){
        return OkResponseModel.builder()
                .code(CODE_OK)
                .type(UNKNOWN_TYPE)
                .message(MESSAGE_OK)
                .build();
    }

}
