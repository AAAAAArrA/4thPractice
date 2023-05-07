package aruuke;


import aruuke.service.FrequencyService;


import java.io.*;
import java.util.HashMap;


public class App {
    public static void main(String[] args) throws IOException {
        var frequency = new FrequencyService();
        frequency.findPercentageRatio();


    }
}
