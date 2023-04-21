package com.lazarev.steps;

import com.lazarev.dto.request.PetDto;
import com.lazarev.dto.request.OrderDto;
import io.qameta.allure.Step;
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
    private static final String PET_URL = "pet/";

    //Часть URL для запросов POST и PUT
    private static final String PET_URL_POST = "pet";

    //Часть URL для запросов GET и DELETE (контроллер Store)
    private static final String STORE_URL = "store/order/";

    //Часть URL для запроса POST (контроллер Store)
    private static final String STORE_URL_POST = "store/order";

    /**
     * Конструктор для создания экземпляра класса
     *
     * @param requestSpecification спецификации RestAssured
     */
    public Steps(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    /**
     * Метод создания питомца
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    @Step("Создание питомца в магазине")
    public ResponseWrapper createNewPet(PetDto request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(PET_URL_POST)
                .andReturn());
    }

    /**
     * Метод поиска питомца по ID
     *
     * @param id айди питомца
     * @return оболочка для работы с ответом
     */
    @Step("Найти питомца по ID")
    public ResponseWrapper findPetByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .get(PET_URL + id)
                .andReturn());
    }

    /**
     * Метод удаления питомца по ID
     *
     * @param id айди питомца
     * @return оболочка для работы с ответом
     */
    @Step("Удалить питомца по айди")
    public ResponseWrapper deletePetByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .delete(PET_URL + id)
                .andReturn());
    }

    /**
     * Метод обновления питомца
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    @Step("Обновить данные о питомце")
    public ResponseWrapper updatePet(PetDto request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .put(PET_URL_POST)
                .andReturn());
    }

    /**
     * Метод создания нового заказа
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    @Step("Создать заказ на питомца")
    public ResponseWrapper createNewOrder(OrderDto request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(STORE_URL_POST)
                .andReturn());
    }

    /**
     * Метод удаления заказа по ID
     *
     * @param id айди заказа
     * @return оболочка для работы с ответом
     */
    @Step("Удалить заказ на питомца по ID")
    public ResponseWrapper deleteOrderByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .delete(STORE_URL + id)
                .andReturn());
    }

    /**
     * Метод поиска заказа по ID
     *
     * @param id айди заказа
     * @return оболочка для работы с ответом
     */
    @Step("Найти заказ по ID")
    public ResponseWrapper findOrderByID(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .get(STORE_URL + id)
                .andReturn());
    }
}