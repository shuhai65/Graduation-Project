package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumImage;


public interface AquariumImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AquariumImage aquariumImage);

    AquariumImage selectByPrimaryKey(Long id);

    int updateByPrimaryKey(AquariumImage aquariumImage);
}




