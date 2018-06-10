package com.vishnuk.allocationservice.utility;

import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
public class Logstash {

    public  String getTraceId(Tracer tracer){
        String traceId= tracer.getCurrentSpan().traceIdString();
        return traceId;
    }
}