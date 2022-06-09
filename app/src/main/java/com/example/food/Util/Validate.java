package com.example.food.Util;

public class Validate {
    public static boolean checkPhone(String phone) {
        if (phone.length() == 10)
            return true;
        return false;
    }
}
