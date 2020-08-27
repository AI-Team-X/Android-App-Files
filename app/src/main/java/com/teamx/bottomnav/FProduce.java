package com.teamx.bottomnav;

import android.graphics.Bitmap;

public class FProduce {
    public FProduce() {
    }

    public FProduce(String imageUri) {
        ImageUri = imageUri;
    }

    public FProduce(String cropsAvaliable, String salePerBasket, String noOfBaskets, String sizeOfFarm, String numberOfHarvestPerYear, String plantingStarts, String plantingStops, String harvestStarts, String harvestStops) {
        CropsAvaliable = cropsAvaliable;
        SalePerBasket = salePerBasket;
        NoOfBaskets = noOfBaskets;
        SizeOfFarm = sizeOfFarm;
        NumberOfHarvestPerYear = numberOfHarvestPerYear;
        PlantingStarts = plantingStarts;
        PlantingStops = plantingStops;
        HarvestStarts = harvestStarts;
        HarvestStops = harvestStops;
    }

    public String getCropsAvaliable() {
        return CropsAvaliable;
    }

    public void setCropsAvaliable(String cropsAvaliable) {
        CropsAvaliable = cropsAvaliable;
    }

    public String getSalePerBasket() {
        return SalePerBasket;
    }

    public void setSalePerBasket(String salePerBasket) {
        SalePerBasket = salePerBasket;
    }

    public String getNoOfBaskets() {
        return NoOfBaskets;
    }

    public void setNoOfBaskets(String noOfBaskets) {
        NoOfBaskets = noOfBaskets;
    }

    public String getSizeOfFarm() {
        return SizeOfFarm;
    }

    public void setSizeOfFarm(String sizeOfFarm) {
        SizeOfFarm = sizeOfFarm;
    }

    public String getNumberOfHarvestPerYear() {
        return NumberOfHarvestPerYear;
    }

    public void setNumberOfHarvestPerYear(String numberOfHarvestPerYear) {
        NumberOfHarvestPerYear = numberOfHarvestPerYear;
    }

    public String getPlantingStarts() {
        return PlantingStarts;
    }

    public void setPlantingStarts(String plantingStarts) {
        PlantingStarts = plantingStarts;
    }

    public String getPlantingStops() {
        return PlantingStops;
    }

    public void setPlantingStops(String plantingStops) {
        PlantingStops = plantingStops;
    }

    public String getHarvestStarts() {
        return HarvestStarts;
    }

    public void setHarvestStarts(String harvestStarts) {
        HarvestStarts = harvestStarts;
    }

    public String getHarvestStops() {
        return HarvestStops;
    }

    public void setHarvestStops(String harvestStops) {
        HarvestStops = harvestStops;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    String CropsAvaliable;
    String SalePerBasket;
    String NoOfBaskets, SizeOfFarm, NumberOfHarvestPerYear,PlantingStarts, PlantingStops;
    String HarvestStarts, HarvestStops;
    String ImageUri;
}
