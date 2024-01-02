package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumPermission;

public interface AquariumPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AquariumPermission record);

    int insertSelective(AquariumPermission record);

    AquariumPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AquariumPermission record);

    int updateByPrimaryKey(AquariumPermission record);
}




