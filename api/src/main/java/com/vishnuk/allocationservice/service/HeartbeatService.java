package com.vishnuk.allocationservice.service;


public interface HeartbeatService {

    Boolean refresh(String macAddress, String allocatedIPAddr);
}
