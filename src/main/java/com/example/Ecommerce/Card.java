package com.example.Ecommerce;

import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class Card {
    private String cardHolderName;
    private String cardNo;
    private String CVV;
    private YearMonth expDate;

    public Card(String cardHolderName, String cardNo, String CVV, YearMonth expDate) {
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.CVV = CVV;
        this.expDate = expDate;
    }

    public Card() { }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public YearMonth getExpDate() {
        return expDate;
    }

    public void setExpDate(int month, int year) {
        this.expDate = YearMonth.of(year,month);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", CVV='" + CVV + '\'' +
                ", expDate=" + expDate +
                '}';
    }
}