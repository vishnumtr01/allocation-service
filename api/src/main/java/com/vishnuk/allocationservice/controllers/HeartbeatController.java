package com.vishnuk.allocationservice.controllers;

/**
 *  <hi> Dynamic IP Allocation </h1>
 *  This class shows us implementation of dynamic IP allocation service
 *
 * @author Vishnu Kumar
 * @version 1.0
 * @since 2018-05-26
 *
 */

import com.vishnuk.allocationservice.service.HeartbeatService;
import com.vishnuk.allocationservice.utility.Logstash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class HeartbeatController {


    @Autowired
    private HeartbeatService heartbeatService;
    @Autowired
    private Tracer tracer;
    @Autowired
    private Logstash logstash;






}
