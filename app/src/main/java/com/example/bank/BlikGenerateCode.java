package com.example.bank;

import java.util.Random;

public class BlikGenerateCode {

    static String generateCode(){
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < 6; i++){
            Random random = new Random();
            int randInt = random.nextInt(10);
            code.append(randInt);

            if(i == 2){
                code.append(" ");
            }
        }


        return code.toString();
    }
}
