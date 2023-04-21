package com.lazarev.utils;

import com.github.javafaker.Faker;

/**
 * Класс,хранящий тестовые данные
 */
public class TestData {

    // Валидный идентификатор питомца
    public static String VALID_TEST_PET_ID = "109";

    //Не валидный идентификатор питомца
    public static final String NOT_VALID_PET_ID = "ddd";

    //Валидный идентификатор категории
    public static final int VALID_CATEGORY_ID = 1;

    //Валидный идентификатор тэга
    public static final int VALID_TAG_ID = 2;

    //Валидное имя тэга
    public static final String VALID_TAG_NAME = "#DoogyStyle";

    //Валидный статус питомца
    public static final String VALID_STATUS = "available";

    //Статус код успешного результата запроса
    public static final int STATUS_CODE_OK = 200;

    //Статус код не успешного результата запроса
    public static final int STATUS_CODE_ERROR_404 = 404;

    //Статус код не успешного результата запроса
    public static final int STATUS_CODE_ERROR_500 = 500;

    //Экземпляр класса Faker
    private static final Faker faker = new Faker();

    //Регулярное выражениек для генерации случайного URL
    private static final String REGEXP_PHOTO_URL = "http:\\\\[a-z]{5}";

    //Регулярное выражениек для генерации случайного не валидного статуса
    private static final String REGEXP_NOT_VALID_STATUS = "[a-z]{20}";

    /**
     * Метод получения случайного имени питомца
     *
     * @return случайное имя питомца
     */
    public static String getRandomPetName() {
        return faker.dog().name();
    }

    /**
     * Метод получения случайного имени категории
     *
     * @return случайное имя категории
     */
    public static String getRandomCategoryName() {
        return faker.dog().breed();
    }

    /**
     * Метод получения случайного URL
     *
     * @return случайное URL
     */
    public static String getRandomUrl() {
        return faker.regexify(REGEXP_PHOTO_URL);
    }

    //Количество питомцев в заказе
    public static Integer QUANTITY = (int) (Math.random() * (100 - 1)) + 1;

    //Статус заказа
    public static String STATUS_ORDER = "placed";

    //Сведения о выполнении заказа
    public static Boolean COMPLETE = Math.random() < 0.5;

    //Дата заказа
    public static String SHIP_DATE = "2022-11-13T21:58:55.577Z";
}