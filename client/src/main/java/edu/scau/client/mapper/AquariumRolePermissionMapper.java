package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumPermission;
import edu.scau.client.domain.AquariumRolePermission;

import java.util.List;


public interface AquariumRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AquariumRolePermission record);

    int insertSelective(AquariumRolePermission record);

    AquariumRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AquariumRolePermission record);

    int updateByPrimaryKey(AquariumRolePermission record);

    List<AquariumPermission> selectPermissionByRoleId(Long id);
}




