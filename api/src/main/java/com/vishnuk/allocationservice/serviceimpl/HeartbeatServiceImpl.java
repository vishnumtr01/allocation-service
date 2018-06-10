package com.vishnuk.allocationservice.serviceimpl;



import com.vishnuk.allocationservice.repository.RedisRepository;
import com.vishnuk.allocationservice.service.HeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HeartbeatServiceImpl implements HeartbeatService {

    @Autowired
    private RedisRepository redisRepository;

    @Value("${ip.expiry}")
    private  Long expiry;

    @Override
    public Boolean refresh (String macAddress, String allocatedIPAddr) {

       String storedIPAddr =redisRepository.find(macAddress);

      if (allocatedIPAddr.equals(storedIPAddr)) {
          redisRepository.saveAndExpiry(macAddress,allocatedIPAddr,expiry);
          redisRepository.saveAndExpiry(allocatedIPAddr,macAddress,expiry);
          return true;
      }
      else
      {
          return false;
      }
    }


}
