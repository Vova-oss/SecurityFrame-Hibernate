package com.example.securityframe.AuxiliaryClasses;

import com.example.securityframe.ReqResContextSettings.ReqResContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StaticMethods {

    /**
     * Создание ответа
     * @param status - статус ответа
     * @param info - инфорация, которая будет прописана под полем "info"
     */
    public static void createResponse(int status,
                                      String info){

        ReqResContext context = ReqResContext.getCurrentInstance();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();


        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);
//        response.addHeader("Access-Control-Allow-Origin", "*" );

        final Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("info", info);
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getOutputStream(), body);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Получение конкретного поля из json
     * @param body - изначальный json
     * @param field - поле, которое необходимо получить из этого json
     * @return field/null
     *
     * @code 400 - Incorrect JSON
     */
    public static String parsingJson (String body,
                                      String field,
                                      HttpServletRequest request ,
                                      HttpServletResponse response) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            field = jsonObject.getString(field);
        } catch (JSONException e) {
            StaticMethods.createResponse(HttpServletResponse.SC_BAD_REQUEST, "Incorrect JSON");
            return null;
        }
        return field;
    }


}
