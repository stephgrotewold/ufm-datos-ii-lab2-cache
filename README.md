# Laboratorio 2 - Datos II

El objetivo de este laboratorio es que los participantes desarrollen una solución REST con **Spring Boot,** creen un repositorio en Docker utilizando **Docker Compose**, implementen la persistencia de datos y optimicen las lecturas mediante el uso de un gestor de bases de datos y **Redis** como administrador de caché.


## Pasos

- Descargar un proyecto nuevo desde https://start.spring.io/ con las librerias de MySQL, Redis y Web. 

- Actualizar y descargar gradle

- Configurar el docker compose del proyecto
    ```sh
    services:
      db:
        image: mysql:8.0
        restart: always
        environment:
          MYSQL_DATABASE: 'employees'
          MYSQL_USER: 'test'
          MYSQL_PASSWORD: 'test'
          MYSQL_ROOT_PASSWORD: 'test'
        ports:
          - '3306:3306'
    
      redis:
        image: redis:7.0.7
        ports:
          - "6379:6379"
    ```
- Descargar backup de la base de datos de el siguiente repositorio https://github.com/datacharmer/test_db dentro del contenedor de MySQL.
    ```sh
    docker cp test_db lab2-db-1:/backup
    docker exec -it  my_sql_container /bin/bash
    cd bash
    mysql -u root -p < employees.sql 
    ```
- Configurar la base de datos dentro el proyecto en databases
  ![image](https://github.com/user-attachments/assets/9ce989db-383e-46a8-8f54-bfd5c06ca8fd)


- Para poder hacer test del proyecto, puede ejecutar los siguientes requests
  ```sh
    curl --location 'http://localhost:8080/api/v1/employee/10012'
  ```
  ```sh
    curl --location 'http://localhost:8080/api/v1/employee/150000/Senior'
  ```
