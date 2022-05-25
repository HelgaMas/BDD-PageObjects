package ru.netology.bdd.data;

import lombok.Value;

public class IncorrectData {

    @Value
    public static class IncorrectCard {
        public String firstIncorrectNumber;
        public int Balance;
    }

    public static IncorrectCard getFirstIncorrectNumber() {
        return new IncorrectCard("5559 0000 0000 0005", 10_000);
    }
}