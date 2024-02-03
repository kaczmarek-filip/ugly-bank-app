package com.example.bank;

import android.content.Context;
import android.content.ContextWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class UserJSON extends ContextWrapper {
    private final String filename = "passy.json";
    private FileWriter fileWriter;
    private File file;

    public UserJSON(Context base) {
        super(base);
    }

    public JSONObject toJSON(User user) throws JSONException {

        JSONObject jsonObject = new JSONObject()
                .put("login", user.getLogin())
                .put("password", user.getPassword())
                .put("balance", user.getBalance())
                .put("deposit", user.getDeposit())
                .put("cardNumber", user.getCardNumber());

        return jsonObject;
    }

    public void writeToFile(JSONObject jsonObject) throws IOException {

        file = new File(getFilesDir(), "passy.json");

        fileWriter = new FileWriter(file);
        fileWriter.write(String.valueOf(jsonObject));
        fileWriter.close();
    }
}
