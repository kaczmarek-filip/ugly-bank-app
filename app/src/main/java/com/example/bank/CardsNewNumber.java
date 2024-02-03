package com.example.bank;

import java.util.Random;

public class CardsNewNumber {



    /**
     * @param newCardNumber Przyjmuje numer wpisany przez użytkownika
     * @return Zwraca numer karty ułożony w formacie XX XXXX XXXX XXXX XXXX
     */
    String newCardNumber(String newCardNumber){

        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < newCardNumber.length(); i++){
            cardNumber.append(newCardNumber.charAt(i));

            if (i == 1 || (i > 1 && (i -1) % 4 == 0)) {
                cardNumber.append(" ");
            }
        }


        return cardNumber.toString();
    }
    /**
     * Metoda odpowiadająca za wygenerowanie losowego ciągu cyfr i ułóżenie go w formie numeru karty
     * XX XXXX XXXX XXXX XXXX
     * @return Zwraca String w powyższym formacie
     */
    public static String generateCardNumber(){

        StringBuilder cardNumber = new StringBuilder();

        for(int i = 1; i <= 18; i++){
            Random random = new Random();
            int randInt = random.nextInt(10);
            cardNumber.append(randInt);

            if (i == 2 || (i > 2 && (i - 2) % 4 == 0)) {
                cardNumber.append(" ");
            }
        }
        return cardNumber.toString();
    }
}
