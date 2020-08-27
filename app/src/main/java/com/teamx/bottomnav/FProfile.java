package com.teamx.bottomnav;

public class FProfile {
    String Name, PhoneNumber, OtherPhoneNumber, Email, HomeAddress, NIN, StateOfOrigin, FarmLocation, CityOfResidenc;
    String StateOfResidence, YearsOfFarming, ImageUri ;

    public FProfile() {
    }

    public FProfile(String imageUri) {
        ImageUri = imageUri;
    }

    public FProfile(String name, String phoneNumber, String otherPhoneNumber, String email, String homeAddress, String NIN, String stateOfOrigin, String farmLocation, String cityOfResidenc, String stateOfResidence, String yearsOfFarming) {
        Name = name;
        PhoneNumber = phoneNumber;
        OtherPhoneNumber = otherPhoneNumber;
        Email = email;
        HomeAddress = homeAddress;
        this.NIN = NIN;
        StateOfOrigin = stateOfOrigin;
        FarmLocation = farmLocation;
        CityOfResidenc = cityOfResidenc;
        StateOfResidence = stateOfResidence;
        YearsOfFarming = yearsOfFarming;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getOtherPhoneNumber() {
        return OtherPhoneNumber;
    }

    public void setOtherPhoneNumber(String otherPhoneNumber) {
        OtherPhoneNumber = otherPhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }

    public String getNIN() {
        return NIN;
    }

    public void setNIN(String NIN) {
        this.NIN = NIN;
    }

    public String getStateOfOrigin() {
        return StateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        StateOfOrigin = stateOfOrigin;
    }

    public String getFarmLocation() {
        return FarmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        FarmLocation = farmLocation;
    }

    public String getCityOfResidenc() {
        return CityOfResidenc;
    }

    public void setCityOfResidenc(String cityOfResidenc) {
        CityOfResidenc = cityOfResidenc;
    }

    public String getStateOfResidence() {
        return StateOfResidence;
    }

    public void setStateOfResidence(String stateOfResidence) {
        StateOfResidence = stateOfResidence;
    }

    public String getYearsOfFarming() {
        return YearsOfFarming;
    }

    public void setYearsOfFarming(String yearsOfFarming) {
        YearsOfFarming = yearsOfFarming;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }
}
