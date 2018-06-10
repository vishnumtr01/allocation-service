package com.vishnuk.allocationservice.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@Component

public class GenerateDynamicIP {

    private static int counter =0;

    @Value("${ip.start}")
    private  static String start;

    @Value("${ip.end}")
    private static String end;

    public synchronized static String getDynamicIP(){

        //TODO pick from property file

        start="192.168.0.1";
        end="192.168.0.254";


        String[] startParts = start.split("(?<=\\.)(?!.*\\.)");
        String[] endParts = end.split("(?<=\\.)(?!.*\\.)");

        int first = Integer.parseInt(startParts[1]);
        int last = Integer.parseInt(endParts[1]);

        counter++;
        first=first+counter;

         return  startParts[0] + first;


    }
}
