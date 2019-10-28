package com.example.apirestapp;

public class Constantes {


    /**
     * Connection timeout duration
     */
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    /**
     * Connection Read timeout duration
     */
    public static final int READ_TIMEOUT = 60 * 1000;
    /**
     * Connection write timeout duration
     */
    public static final int WRITE_TIMEOUT = 60 * 1000;

    /*CONSUMIENDO SERVICIO PHP*/
    public static final String BASE_URL_PHP="http://192.168.137.2:8000";
    /*CONSUMIENDO SERVICIO NODE*/
    public static final String BASE_URL_NODE="http://192.168.137.2:3000";
    /*CONSUMIENDO SERVICIO SPRING*/
    public static final String BASE_URL_PYTHON="http://192.168.137.2:5000";
    /*CONSUMIENDO SERVICIO SPRING*/
    public static final String BASE_URL_SPRING="http://192.168.137.2:8080";

}
