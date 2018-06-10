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

import com.vishnuk.allocationservice.models.HearbeatResponse;
import com.vishnuk.allocationservice.service.AllocationService;
import com.vishnuk.allocationservice.service.HeartbeatService;
import com.vishnuk.allocationservice.utility.Logstash;

import com.vishnuk.allocationservice.models.AllocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class AllocationController {


    @Autowired
    private HeartbeatService heartbeatService;
    @Autowired
    private AllocationService allocationService;
    @Autowired
    private Tracer tracer;
    @Autowired
    private Logstash logstash;




    @RequestMapping(value = "/getDynamicIp/{macAddress}",method = RequestMethod.GET)
    public ResponseEntity getDynamicIp(@PathVariable(value = "macAddress") String macAddress){

        String allocatedIp= allocationService.allocate(macAddress);

        AllocationResponse genericResponse = new AllocationResponse();

        genericResponse.setDynamicIP(allocatedIp);

        return new ResponseEntity<>(genericResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/refresh/{macAddress}/{allocatedIPAddr}",method = RequestMethod.GET)

    public ResponseEntity refresh(@PathVariable(value = "macAddress") String macAddress,@PathVariable(value = "allocatedIPAddr") String allocatedIPAddr) throws Exception {
        Boolean status = heartbeatService.refresh(macAddress, allocatedIPAddr);

        HearbeatResponse hearbeatResponse = new HearbeatResponse();
        hearbeatResponse.setStatus(status);
        return new ResponseEntity<>(hearbeatResponse, HttpStatus.OK);

    }


}
