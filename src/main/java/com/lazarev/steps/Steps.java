package com.lazarev.steps;

import com.lazarev.dto.request.PetBodyModel;
import com.lazarev.dto.request.OrderBodyModel;
import io.restassured.specification.RequestSpecification;
import com.lazarev.utils.ResponseWrapper;

import static io.restassured.RestAssured.given;

/**
 * Класс, содержащий методы HTTP запросов
 */
public class Steps {

    //Экземпляр спецификации RestAssured
    private final RequestSpecification requestSpecification;

    //Часть URL для запросов GET и DELETE
    private static final String PET_URL  = "pet/";

    //Часть URL для запросов POST и PUT
    private static final String PET_URL_POST  = "pet";

    /**
     * Конструктор для создания экземпляра класса
     * @param requestSpecification спецификации RestAssured
     */
    public Steps (RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }


    /**
     * Метод создания питомца
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper createNewPetStore(PetBodyModel request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(PET_URL_POST)
                .andReturn());
    }

    /**
     * Метод поиска питомца по ID
      * @param id айди питомца
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper findPetByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .get(PET_URL + id)
                .andReturn());
    }

    /**
     * Метод удаления питомца по ID
     * @param id айди питомца
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper deletePetByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .delete(PET_URL + id)
                .andReturn());
    }

    /**
     * Метод обновления питомца
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper updatePet(PetBodyModel request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .put(PET_URL_POST)
                .andReturn());
    }

    //Часть URL для запросов GET и DELETE (контроллер Store)
    private static final String STORE_URL  = "store/order/";

    //Часть URL для запроса POST (контроллер Store)
    private static final String STORE_URL_POST  = "store/order";

    /**
     * Метод создания нового заказа
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper createNewOrder(OrderBodyModel request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(STORE_URL_POST)
                .andReturn());
    }

    /**
     * Метод удаления заказа по ID
     * @param id айди заказа
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper deleteOrderByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .delete(STORE_URL + id)
                .andReturn());
    }

    /**
     * Метод поиска заказа по ID
     * @param id айди заказа
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper findOrderByID(String id) {

        return new ResponseWrapper(given(requestSpecification)
                .when()
                .get(STORE_URL + id)
                .andReturn());
    }

}
