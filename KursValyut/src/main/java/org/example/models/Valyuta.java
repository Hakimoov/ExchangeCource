package org.example.models;

public class Valyuta {
    int id;
    String Code;
    String Ccy;
    String CcyNm_RU;
    String CcyNm_UZ;
    String CcyNm_UZC;
    String CcyNm_EN;
    String Nominal;
    String Rate;
    String Diff;
    String Date;

    public Valyuta(int id, String code, String ccy, String ccyNm_RU,
           String ccyNm_UZ, String ccyNm_UZC, String ccyNm_EN,
           String nominal, String rate, String diff, String date) {

        this.id = id;
        Code = code;
        Ccy = ccy;
        CcyNm_RU = ccyNm_RU;
        CcyNm_UZ = ccyNm_UZ;
        CcyNm_UZC = ccyNm_UZC;
        CcyNm_EN = ccyNm_EN;
        Nominal = nominal;
        Rate = rate;
        Diff = diff;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getCcyNm_RU() {
        return CcyNm_RU;
    }

    public void setCcyNm_RU(String ccyNm_RU) {
        CcyNm_RU = ccyNm_RU;
    }

    public String getCcyNm_UZ() {
        return CcyNm_UZ;
    }

    public void setCcyNm_UZ(String ccyNm_UZ) {
        CcyNm_UZ = ccyNm_UZ;
    }

    public String getCcyNm_UZC() {
        return CcyNm_UZC;
    }

    public void setCcyNm_UZC(String ccyNm_UZC) {
        CcyNm_UZC = ccyNm_UZC;
    }

    public String getCcyNm_EN() {
        return CcyNm_EN;
    }

    public void setCcyNm_EN(String ccyNm_EN) {
        CcyNm_EN = ccyNm_EN;
    }

    public String getNominal() {
        return Nominal;
    }

    public void setNominal(String nominal) {
        Nominal = nominal;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getDiff() {
        return Diff;
    }

    public void setDiff(String diff) {
        Diff = diff;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    /*
"id": 69,
        "Code": "840",
        "Ccy": "USD",
        "CcyNm_RU": "Доллар США",
        "CcyNm_UZ": "AQSH dollari",
        "CcyNm_UZC": "АҚШ доллари",
        "CcyNm_EN": "US Dollar",
        "Nominal": "1",
        "Rate": "11570.09",
        "Diff": "17.03",
        "Date": "10.07.2023"'*/
}
