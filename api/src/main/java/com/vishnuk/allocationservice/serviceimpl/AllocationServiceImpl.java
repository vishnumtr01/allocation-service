package com.vishnuk.allocationservice.serviceimpl;



import com.vishnuk.allocationservice.exceptionhandler.RedisError;
import com.vishnuk.allocationservice.repository.RedisRepository;
import com.vishnuk.allocationservice.service.AllocationService;

import com.vishnuk.allocationservice.utility.GenerateDynamicIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Configuration

@Service
public class AllocationServiceImpl implements AllocationService {

    private final Logger logger = LogManager.getLogger("logstash");
    @Autowired
    private RedisRepository redisRepository;


    @Value("${ip.expiry}")
    private  Long expiry;


    @Override
    public String allocate(String macAddress) {




    try {

        String dynamicIP = null;
        String storedIPAddr = redisRepository.find(macAddress);

        if (storedIPAddr != null) {
            return storedIPAddr;
        }

        GenerateDynamicIP generateDynamicIP = new GenerateDynamicIP();


        boolean allocated = true;

        while (allocated){


            dynamicIP = generateDynamicIP.getDynamicIP();

            String isIPalreadyallocated = redisRepository.find(dynamicIP);


        if ((isIPalreadyallocated==null)) {

            //Save Mac and IP in Redis with auto expiry set
            redisRepository.saveAndExpiry(macAddress, dynamicIP, expiry);
            redisRepository.saveAndExpiry(dynamicIP, macAddress, expiry);

            allocated=false;
        }



    }
        return dynamicIP;
    }
    catch (Exception e) {

        logger.error(e.getMessage());

        throw new RedisError(e.getMessage());
    }


    }


}
