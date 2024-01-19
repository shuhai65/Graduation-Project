package edu.scau.client.mapper;

import edu.scau.client.activity.domain.req.ActivityReq;
import edu.scau.client.domain.AquariumActivity;

import java.util.List;

public interface AquariumActivityMapper {

    Long insertSelective(AquariumActivity record);

    AquariumActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AquariumActivity record);

    int deleteByPrimaryKey(Long id);


    List<AquariumActivity> selectByPage(Integer startIndex, Integer pageSize);

    List<AquariumActivity> selectByTitle(String title, Integer startIndex, Integer pageSize);
}




