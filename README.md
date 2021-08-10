# labinvent-task-java-app-bakulin
Backend application. Архитектура REST.

Версия Java SDK: 13.0.2.
Spring Boot: 2.5.2 (Spring WEB, Spring Security, Spring Data JPA)
Hibernate ORM
java-jwt v3.17.0
commons-lang3 v3.9
javax.mail v1.6.2

DataBase: MySQL v5.6 x64. Для запуска БД использовался Open Server.
Приложение разрабатывалось в IDE IntelliJ IDEA Standart edition.

Для запуска backend приложения необходимо выполнить следующие действия:

1) Выполнить инициализацию MySQL базы данных: CREATE DATABASE IF NOT EXISTS  invent_lab_test_task_bakulin;
2) В корне ветви full_project находится документ "invent_lab_test_task_bakulin.sql", в нем все необходимые скрипты для создания таблиц и заполнения информацией. В процессе разработки база данных была сгененрирована с помощью Hibernate ORM.
