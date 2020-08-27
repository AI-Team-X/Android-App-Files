package com.teamx.bottomnav;

public class FSell {
    String CurrentPrice, PrevoiusPrice, NumberOfBasketsSOld, IncomePerBasket,TotalIncomeMade;

    public FSell() {
    }

    public FSell(String currentPrice, String prevoiusPrice, String numberOfBasketsSOld, String incomePerBasket, String totalIncomeMade) {
        CurrentPrice = currentPrice;
        PrevoiusPrice = prevoiusPrice;
        NumberOfBasketsSOld = numberOfBasketsSOld;
        IncomePerBasket = incomePerBasket;
        TotalIncomeMade = totalIncomeMade;
    }

    public String getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getPrevoiusPrice() {
        return PrevoiusPrice;
    }

    public void setPrevoiusPrice(String prevoiusPrice) {
        PrevoiusPrice = prevoiusPrice;
    }

    public String getNumberOfBasketsSOld() {
        return NumberOfBasketsSOld;
    }

    public void setNumberOfBasketsSOld(String numberOfBasketsSOld) {
        NumberOfBasketsSOld = numberOfBasketsSOld;
    }

    public String getIncomePerBasket() {
        return IncomePerBasket;
    }

    public void setIncomePerBasket(String incomePerBasket) {
        IncomePerBasket = incomePerBasket;
    }

    public String getTotalIncomeMade() {
        return TotalIncomeMade;
    }

    public void setTotalIncomeMade(String totalIncomeMade) {
        TotalIncomeMade = totalIncomeMade;
    }
}
