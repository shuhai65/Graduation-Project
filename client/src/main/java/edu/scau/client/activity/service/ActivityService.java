package edu.scau.client.activity.service;

import edu.scau.client.activity.domain.req.ActivityReq;
import edu.scau.client.activity.domain.req.ParticipantReq;
import edu.scau.client.activity.domain.vo.ActivityVo;
import edu.scau.client.activity.domain.vo.ParticipantVo;
import edu.scau.client.domain.AquariumActivity;
import edu.scau.client.domain.AquariumActivityImage;
import edu.scau.client.domain.AquariumActivityUser;
import edu.scau.client.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    AquariumActivityImageMapper activityImageMapper;
    @Autowired
    AquariumActivityMapper activityMapper;
    @Autowired
    AquariumActivityUserMapper activityUserMapper;
    @Autowired
    AquariumNoticeMapper noticeMapper;
    @Autowired
    AquariumUserMapper userMapper;

    @Transactional
    public Long createActivity(ActivityReq activityReq) {
        AquariumActivity aquariumActivity = activityReq.toAquariumActivity();
        Long id = activityMapper.insertSelective(aquariumActivity);
        if(activityReq.getImageIdList()==null|| activityReq.getImageIdList().isEmpty()){
            return id;
        }
        List<AquariumActivityImage> aquariumActivityImageList = new ArrayList<>();
        activityReq.getImageIdList().forEach(image -> {
            AquariumActivityImage aquariumActivityImage = new AquariumActivityImage();
            aquariumActivityImage.setActivityId(id);
            aquariumActivityImage.setImageId(image);
            aquariumActivityImageList.add(aquariumActivityImage);
        });
        activityImageMapper.insertList(aquariumActivityImageList);
        return id;
    }

    @Transactional
    public void deleteActivity(Long id) {
        activityMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void updateActivity(ActivityReq activityReq) {
        AquariumActivity aquariumActivity = activityReq.toAquariumActivity();
        activityMapper.updateByPrimaryKeySelective(aquariumActivity);
        List<Long> imageList = activityImageMapper.selectByActivityId(aquariumActivity.getId());
        if(activityReq.getImageIdList()==null|| activityReq.getImageIdList().isEmpty()){
            return;
        }
        for (Long imageId : activityReq.getImageIdList()) {
            AquariumActivityImage aquariumActivityImage = new AquariumActivityImage();
            aquariumActivityImage.setActivityId(aquariumActivity.getId());
            aquariumActivityImage.setImageId(imageId);
            if (imageList.contains(imageId)) {
                imageList.remove(imageId);
            } else {
                activityImageMapper.insert(aquariumActivityImage);
            }
        }
    }

    @Transactional
    public void deleteImage(Long id, Long imageId) {
        activityImageMapper.deleteById(id, imageId);
    }

    public List<ActivityVo> getActivityList(Integer startIndex, Integer pageSize) {
        List<AquariumActivity> aquariumActivityList = activityMapper.selectByPage(startIndex, pageSize);
        if(aquariumActivityList==null|| aquariumActivityList.isEmpty()){
            return null;
        }
        List<ActivityVo> activityVoList = new ArrayList<>();
        aquariumActivityList.forEach(aquariumActivity -> {
            ActivityVo activityVo = new ActivityVo();
            activityVo.setAquariumActivity(aquariumActivity);
            activityVo.setImageIdList(activityImageMapper.selectByActivityId(aquariumActivity.getId()));
            activityVoList.add(activityVo);
        });
        return activityVoList;
    }

    public ActivityVo getActivityDetail(Long id) {
        AquariumActivity aquariumActivity = activityMapper.selectByPrimaryKey(id);
        ActivityVo activityVo = new ActivityVo();
        activityVo.setAquariumActivity(aquariumActivity);
        activityVo.setImageIdList(activityImageMapper.selectByActivityId(aquariumActivity.getId()));
        return activityVo;
    }

    public List<ParticipantVo> getParticipantList(Long id, Integer startIndex, Integer pageSize) {
        List<AquariumActivityUser> userIdList = activityUserMapper.selectByActivityId(id, startIndex, pageSize);
        if(userIdList==null|| userIdList.isEmpty()){
            return null;
        }
        List<ParticipantVo> participantVoList = new ArrayList<>();
        userIdList.forEach(aquariumActivityUser -> {
            ParticipantVo participantVo = new ParticipantVo();
            participantVo.setVo(aquariumActivityUser);
            participantVo.setUsername(userMapper.selectByPrimaryKey(aquariumActivityUser.getUserId()).getUsername());
            participantVoList.add(participantVo);
        });
        return participantVoList;
    }

    @Transactional
    public void check(ParticipantReq participantReq) {
        AquariumActivityUser aquariumActivityUser = activityUserMapper.selectByActivityIdAndUserId(participantReq.getActivityId(), participantReq.getUserId());
        aquariumActivityUser.setIsCheck(1);
        activityUserMapper.updateByPrimaryKeySelective(aquariumActivityUser);
    }

    @Transactional
    public void quit(ParticipantReq participantReq) {
        activityUserMapper.deleteByActivityIdAndUserId(participantReq.getActivityId(), participantReq.getUserId());
    }

    public List<ActivityVo> getUserActivityList(Long userId, Integer startIndex, Integer pageSize) {
        List<AquariumActivityUser> aquariumActivityUserList = activityUserMapper.selectByUserId(userId, startIndex, pageSize);
        if(aquariumActivityUserList==null|| aquariumActivityUserList.isEmpty()){
            return null;
        }
        List<ActivityVo> activityVoList = new ArrayList<>();
        aquariumActivityUserList.forEach(aquariumActivityUser -> {
            ActivityVo activityVo = new ActivityVo();
            activityVo.setAquariumActivity(activityMapper.selectByPrimaryKey(aquariumActivityUser.getActivityId()));
            activityVo.setImageIdList(activityImageMapper.selectByActivityId(aquariumActivityUser.getActivityId()));
            activityVoList.add(activityVo);
        });
        return activityVoList;
    }

    @Transactional
    public void pay(ParticipantReq participantReq) {
        AquariumActivityUser aquariumActivityUser = activityUserMapper.selectByActivityIdAndUserId(participantReq.getActivityId(), participantReq.getUserId());
        if (aquariumActivityUser.getIsPay() == 1) {
            return;
        }
        aquariumActivityUser.setIsPay(1);
        activityUserMapper.updateByPrimaryKeySelective(aquariumActivityUser);
    }

    @Transactional
    public void feedback(ParticipantReq participantReq) {
        AquariumActivityUser aquariumActivityUser = activityUserMapper.selectByActivityIdAndUserId(participantReq.getActivityId(), participantReq.getUserId());
        aquariumActivityUser.setComment(participantReq.getComment());
        activityUserMapper.updateByPrimaryKeySelective(aquariumActivityUser);
    }

    public List<ActivityVo> searchActivity(String title, Integer startIndex, Integer pageSize) {
        List<AquariumActivity> aquariumActivityList = activityMapper.selectByTitle(title, startIndex, pageSize);
        if(aquariumActivityList==null|| aquariumActivityList.isEmpty()){
            return null;
        }
        List<ActivityVo> activityVoList = new ArrayList<>();
        aquariumActivityList.forEach(aquariumActivity -> {
            ActivityVo activityVo = new ActivityVo();
            activityVo.setAquariumActivity(aquariumActivity);
            activityVo.setImageIdList(activityImageMapper.selectByActivityId(aquariumActivity.getId()));
            activityVoList.add(activityVo);
        });
        return activityVoList;
    }

    @Transactional
    public void apply(ParticipantReq participantReq) {
        AquariumActivityUser aquariumActivityUser = new AquariumActivityUser();
        aquariumActivityUser.setActivityId(participantReq.getActivityId());
        aquariumActivityUser.setUserId(participantReq.getUserId());
        activityUserMapper.insertSelective(aquariumActivityUser);
    }
}
