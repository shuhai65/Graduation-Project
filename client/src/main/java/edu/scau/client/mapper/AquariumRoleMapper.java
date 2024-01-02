package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumPermission;
import edu.scau.client.domain.AquariumRole;

import java.util.List;


public interface AquariumRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AquariumRole record);

    int insertSelective(AquariumRole record);

    AquariumRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AquariumRole record);

    int updateByPrimaryKey(AquariumRole record);

    List<AquariumPermission> findPermissionsByRoleId(Long id);
}




