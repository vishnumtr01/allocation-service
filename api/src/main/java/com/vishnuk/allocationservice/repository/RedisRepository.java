package com.vishnuk.allocationservice.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface RedisRepository {
    public String save(String key, String object);
    public String saveHashEntries(String key, String hashKey, String hashValue);
    public String saveAndExpiry(String key, String object, long time);
    public String find(String key);
    public Map<String, String> findHashEntries(String redisKey);
    public String saveSetEntries(String key, String value);
    public Set<String> findSetEntries(String key);
    public String saveListEntries(String key, String value);
    public List<String> findListEntries(String key);
}
