package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumActivityImage;

import java.util.List;

public interface AquariumActivityImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AquariumActivityImage aquariumActivityImage);

    void insertList(List<AquariumActivityImage> aquariumActivityImageList);

    AquariumActivityImage selectByPrimaryKey(Long id);

    int updateByPrimaryKey(AquariumActivityImage aquariumActivityImage);

    void deleteById(Long id, Long imageId);

    boolean selectByActivityIdAndImageId(Long activityId, Long imageId);

    List<Long> selectByActivityId(Long id);
}




