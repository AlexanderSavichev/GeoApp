package com.example.test_geo.service;

public class LonLocLocator {
    public String LonLocString (String sentence, String firstWord, String secondWord){
        return sentence.substring(sentence.indexOf(firstWord) + firstWord.length(),
                sentence.indexOf(secondWord));
    }
}
