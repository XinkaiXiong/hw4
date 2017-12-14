/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id1212.hw4.controller;

import id1212.hw4.model.Currency;
import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 *
 */

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyController {

    public static double TOTAL_AMOUNT = 0;
    @PersistenceContext(unitName = "Currency_Converter")
    private EntityManager em;

    public String convert(String src, String desti, int amount) {
        String convertResult = null;
        String currSymbol = null;
        DecimalFormat dFormat = new DecimalFormat("#0.00");
        System.out.println("Source currency: " + src);
        System.out.println("Destiny Currency: " + desti);
        System.out.println("Currency Amount: " + amount);

        Currency newCurr = em.find(Currency.class, src);

        if (newCurr == null) {
            convertResult = "Your input is wrong! Please check!";
            System.out.println("newCurr is null not find");
        } else {
            System.out.println("Start conversion!");
            convertResult = newCurr.convert(desti, amount);
            if (!convertResult.equals("0.0")) {

                double tempAmount = Double.parseDouble(convertResult);
                convertResult = dFormat.format(tempAmount);

                switch (desti) {
                    case "EUR":
                        currSymbol = "€";
                        break;

                    case "eur":
                        currSymbol = "€";
                        break;

                    case "GBP":
                        currSymbol = "￡";
                        break;

                    case "gbp":
                        currSymbol = "￡";
                        break;

                    case "SEK":
                        currSymbol = "kr";
                        break;

                    case "sek":
                        currSymbol = "kr";
                        break;

                    case "USD":
                        currSymbol = "$";
                        break;

                    case "usd":
                        currSymbol = "$";
                        break;

                    case "RMB":
                        currSymbol = "¥";
                        break;

                    case "rmb":
                        currSymbol = "¥";
                        break;
                }
                convertResult = currSymbol + " " + convertResult;
                System.out.println(convertResult);
            } else {
                convertResult = "Your input is wrong! Please check!";
            }
        }
        return convertResult;
    }

    public String toatalConvert(String src, int amount) {
        String totalConvertResult = null;
        DecimalFormat dFormat = new DecimalFormat("#0.00");
        double tempAmount = 0.0;
        System.out.println("Source currency: " + src);
        System.out.println("Currency Amount: " + amount);

        Currency newCurr = em.find(Currency.class, src);

        if (newCurr != null) {

            System.out.println("Start conversion!");
            totalConvertResult = newCurr.convert("USD", amount);
            tempAmount = Double.parseDouble(totalConvertResult);
            System.out.println("New data amount in dollar: " + tempAmount);
        }

        TOTAL_AMOUNT = TOTAL_AMOUNT + tempAmount;
        totalConvertResult = dFormat.format(TOTAL_AMOUNT);
        totalConvertResult = "$ " + totalConvertResult;
        return totalConvertResult;

    }

}
