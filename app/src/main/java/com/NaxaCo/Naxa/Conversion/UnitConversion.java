package com.NaxaCo.Naxa.Conversion;

import android.widget.Toast;

/**
 * Created by Shri on 17/01/2018.
 */



public class UnitConversion {
    UnitDto unitDto;
    private double bigha;
    private double katha;
    private double dhur;
    private double ropani;
    private double khetmuri;
    private double aana;
    private double paisa;
    private double daam;
    private double meterSquare;
    private double squareFeet;

    public void convertUnit(double vInputUnit, int cCaseUnit) {
        unitDto = new UnitDto();
        switch (cCaseUnit) {

            case 1:
                //for bigha
                katha = vInputUnit * 20;
                meterSquare = vInputUnit * 6772.63;
                squareFeet = vInputUnit * 72900;
                ropani = vInputUnit * vInputUnit;//Unchecked
                bigha = vInputUnit;
                unitDto.setKatha(katha);
                //unitDto.getKatha();
                unitDto.setMeterSquare(meterSquare);
                unitDto.setSquareFeet(squareFeet);
                unitDto.setRopani(ropani);
                unitDto.setBigha(bigha);
                break;

            case 2:
                //for katha
                dhur = vInputUnit * 20;
                squareFeet = vInputUnit * 3645;
                meterSquare = vInputUnit * 338.63;
                katha = vInputUnit;
                unitDto.setDhur(dhur);
                unitDto.setMeterSquare(meterSquare);
                unitDto.setSquareFeet(squareFeet);
                unitDto.setKatha(katha);
                break;

            case 3:
                //for dhur
                meterSquare = vInputUnit * 16.93;
                squareFeet = vInputUnit * 182.25;
                dhur=vInputUnit;
                unitDto.setMeterSquare(meterSquare);
                unitDto.setSquareFeet(squareFeet);
                unitDto.setDhur(dhur);
                break;

            case 4:
                //for ropani
                aana = vInputUnit * vInputUnit; //Unchecked;
                paisa = vInputUnit * 64;
                meterSquare = vInputUnit * 508.72;
                squareFeet = vInputUnit * 5476;
                daam = vInputUnit * 256;
                ropani=vInputUnit;
                unitDto.setAana(aana);
                unitDto.setPaisa(paisa);
                unitDto.setMeterSquare(meterSquare);
                unitDto.setSquareFeet(squareFeet);
                unitDto.setDaam(daam);
                unitDto.setRopani(ropani);
                break;

            case 5:
                //for khetmuri
                ropani = vInputUnit * 25;
                khetmuri=vInputUnit;
                unitDto.setRopani(ropani);
                unitDto.setKhetmuri(khetmuri);
                break;

            case 6:
                //for aana
                paisa = vInputUnit * 4;
                meterSquare = vInputUnit * 31.80;
                squareFeet = vInputUnit * 342.25;
                daam = vInputUnit * 16;
                aana=vInputUnit;
                unitDto.setPaisa(paisa);
                unitDto.setMeterSquare(meterSquare);
                unitDto.setSquareFeet(squareFeet);
                unitDto.setDaam(daam);
                unitDto.setAana(aana);
                break;

            case 7:
                //for paisa
                daam = vInputUnit * 4;
                meterSquare = vInputUnit * 7.95;
                squareFeet = vInputUnit * 85.56;
                paisa=vInputUnit;
                unitDto.setDaam(daam);
                unitDto.setMeterSquare(meterSquare);
                unitDto.setSquareFeet(squareFeet);
                unitDto.setPaisa(paisa);
                break;

            case 8:
                //for daam
                meterSquare = vInputUnit * 1.99;
                daam = vInputUnit;
                unitDto.setMeterSquare(meterSquare);
                unitDto.setDaam(daam);
                break;
        }
    }
}
