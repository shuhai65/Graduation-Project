package edu.scau.client.notice.service;

import edu.scau.client.mapper.AquariumNoticeMapper;
import edu.scau.client.mapper.AquariumUserMapper;
import edu.scau.client.notice.domain.NoticeVo;
import edu.scau.common.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    AquariumNoticeMapper noticeMapper;
    @Autowired
    AquariumUserMapper userMapper;


    public List<NoticeVo> getNoticeList( Integer startIndex, Integer pageSize) {
        Long userId = SecurityUtil.getUserInfo().getId();
        List<NoticeVo> noticeList = noticeMapper.selectByUserId(userId, startIndex, pageSize);
        return noticeList;
    }


    public void deleteNotice(Long id) {
        noticeMapper.deleteByPrimaryKey(id);
    }
}
