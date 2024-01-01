package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumUser;


public interface AquariumUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AquariumUser record);

    int insertSelective(AquariumUser record);

    AquariumUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AquariumUser record);

    int updateByPrimaryKey(AquariumUser record);

    AquariumUser selectByUsername(String username);

    AquariumUser selectByUsernameAndPassword(AquariumUser aquariumUser);
}




