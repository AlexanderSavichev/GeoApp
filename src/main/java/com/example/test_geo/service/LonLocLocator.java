package com.example.test_geo.service;

public class LonLocLocator {
    public String LonLocString (String sentence, String FirstWord, String SecondWord){
        return sentence.substring(sentence.indexOf(FirstWord) + FirstWord.length(),
                sentence.indexOf(SecondWord));
    }
}
