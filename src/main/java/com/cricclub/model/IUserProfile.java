package com.cricclub.model;

public interface IUserProfile {

    Long getfollowersCount();
    Long getfollowingsCount();
    Long getShalomCount();
    String getUserCity();
    String getUserState();
    String getUserCountry();
    String getImageUrl();
}
