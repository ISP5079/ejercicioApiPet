package com.isp.pet.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

public class Constants {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static final String FORMAT_PRINT_REQUEST_RESPONSE = "\n----- %s -----\n%s";
    public static final String MESSAGE_400 = "Parámetros no válidos";
    public static final String RESPONSE = "RESPONSE";
}
