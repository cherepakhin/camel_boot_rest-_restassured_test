### Behave тестирование RestAssured

Behave тестирование проекта [https://github.com/cherepakhin/camel_boot_rest](https://github.com/cherepakhin/camel_boot_rest).

Для проведения тестов использован RestAssured<br/>
 [https://github.com/rest-assured/rest-assured](https://github.com/rest-assured/rest-assured/wiki/GettingStarted).

Для просмотра отчетов Allure [https://docs.qameta.io/allure/](https://docs.qameta.io/allure/)

Скрипты выполнять из корневой папки проекта. 
Перед запуском тестов нужно запустить сам проект:
[https://github.com/cherepakhin/camel_boot_rest](https://github.com/cherepakhin/camel_boot_rest)

Проведение теста:

```shell
$ ./mvnw clean test
```

Просмотр отчета в браузере:

```shell
$ allure serve target/surefire-reports/
```

![allure_results.png](https://github.com/cherepakhin/camel_boot_rest_restassured_test/blob/main/doc/allure_results.png)

![allure_tests.png](https://github.com/cherepakhin/camel_boot_rest_restassured_test/blob/main/doc/allure_tests.png)

### Повторяемость

Важным условием тестирования является ПОСТОРЯЕМОСТЬ. В случае UNIT тестов это несложно. В случае интеграционных могут быть проблемы. Mock нет( Нужно обеспечить какое-то начальное состояние. В данном случае, в удаленной системе сделал служебный URL /reset_db. Этот URL вызывается перед каждым тестом в ru.perm.v.spring.camel.restassured.GetAllOrdersTest:

````java
    @BeforeEach
    void resetDB() {
        given().when().get(VARS.HOST + "/reset_db");
    }
````

````shell
$ http POST :9090/addOrder < doc/new_order.json

{
"id": 70,
"name": "Shoes",
"price": 70000.0
}

````

Ошибочный json:

````shell
$ http POST :9090/addOrder < doc/bad_new_order.json

{
"id": 70,
"name": "Shoes",
"price": 70000.0
}

````


### Закладки

[https://docs.qameta.io/allure/](https://docs.qameta.io/allure/)
[https://allure-framework.github.io/allure-demo/5/#suites/a2891ce60e520f56ae25e6caf68ea773/448aea45096280d4/](https://allure-framework.github.io/allure-demo/5/#suites/a2891ce60e520f56ae25e6caf68ea773/448aea45096280d4/)

[Иерархия тестов в Allure Report](https://v.perm.ru/index.php/component/content/article/hierarchy-test?catid=16&Itemid=101)

![Epic-Feature-Story](doc/hierarchy.png)

