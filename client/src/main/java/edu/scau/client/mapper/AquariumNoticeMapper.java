package edu.scau.client.mapper;

import edu.scau.client.domain.AquariumNotice;
import edu.scau.client.notice.domain.NoticeVo;

import java.util.List;


public interface AquariumNoticeMapper {

    List<NoticeVo> selectByUserId(Long userId, Integer startIndex, Integer pageSize);

    void deleteByPrimaryKey(Long id);
}




