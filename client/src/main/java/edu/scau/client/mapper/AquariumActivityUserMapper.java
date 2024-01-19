package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumActivityUser;

import java.util.List;


public interface AquariumActivityUserMapper{

    List<AquariumActivityUser> selectByActivityId(Long id, Integer startIndex, Integer pageSize);

    int insert(AquariumActivityUser aquariumActivityUser);

    int deleteByPrimaryKey(Long id);

    AquariumActivityUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AquariumActivityUser aquariumActivityUser);

    AquariumActivityUser selectByActivityIdAndUserId(Long id, Long userId);

    void deleteByActivityIdAndUserId(Long id, Long userId);

    List<AquariumActivityUser> selectByUserId(Long userId, Integer startIndex, Integer pageSize);

    void insertSelective(AquariumActivityUser aquariumActivityUser);
}




