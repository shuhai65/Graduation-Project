package edu.scau.api.controller;

import edu.scau.client.activity.domain.req.ActivityReq;
import edu.scau.client.activity.domain.req.ParticipantReq;
import edu.scau.client.activity.domain.vo.ActivityVo;
import edu.scau.client.activity.domain.vo.ParticipantVo;
import edu.scau.client.activity.service.ActivityService;
import edu.scau.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@Validated
@Slf4j
@Api(value = "ActivityController", tags = "活动管理接口")
public class ActivityController {
    /**
     * 创建活动
     * 删除活动图片
     * 修改活动
     * 删除活动
     * 获取活动列表
     * 获取活动详情
     * 获取活动参与者列表
     * 用户检票
     * 用户退出活动
     * 获取用户参与的活动列表
     * 用户缴费
     * 用户评价反馈
     * 获取活动评价列表
     */
    @Autowired
    ActivityService activityService;

    @ApiOperation("创建活动")
    @PostMapping("/create")
    public ResultData<Long> createActivity(@RequestBody @Validated ActivityReq activityReq) {
        Long activityId = activityService.createActivity(activityReq);
        return ResultData.success(activityId);
    }

    @ApiOperation("删除活动图片")
    @PostMapping("/deleteImage")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "活动id", required = true, paramType = "query", dataTypeClass = Long.class),
                    @ApiImplicitParam(name = "imageId", value = "图片id", required = true, paramType = "query", dataTypeClass = Long.class)
            }
    )
    public ResultData<Object> deleteImage(@RequestParam("id") Long id, @RequestParam("imageId") Long imageId) {
        activityService.deleteImage(id, imageId);
        return ResultData.success();
    }

    @ApiOperation("修改活动")
    @PostMapping("/update")
    public ResultData<Object> updateActivity(@RequestBody @Validated ActivityReq activityReq) {
        activityService.updateActivity(activityReq);
        return ResultData.success();
    }

    @ApiOperation("删除活动")
    @PostMapping("/delete")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, paramType = "query", dataTypeClass = Long.class)
    public ResultData<Object> deleteActivity(@RequestParam("id") Long id) {
        activityService.deleteActivity(id);
        return ResultData.success();
    }

    @ApiOperation("获取活动列表")
    @GetMapping("/list")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "startIndex", value = "起始下标", required = true, paramType = "query", dataTypeClass = Integer.class),
                    @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query", dataTypeClass = Integer.class)
            }
    )
    public ResultData<List<ActivityVo>> getActivityList(@RequestParam("startIndex") Integer startIndex, @RequestParam("pageSize") Integer pageSize) {
        return ResultData.success(activityService.getActivityList(startIndex, pageSize));
    }

    @ApiOperation("获取活动详情")
    @GetMapping("/detail")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, paramType = "query", dataTypeClass = Long.class)
    public ResultData<ActivityVo> getActivityDetail(@RequestParam("id") Long id) {
        return ResultData.success(activityService.getActivityDetail(id));
    }

    @ApiOperation("获取活动参与者列表")
    @GetMapping("/participant/list")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "活动id", required = true, paramType = "query", dataTypeClass = Long.class),
                    @ApiImplicitParam(name = "startIndex", value = "起始下标", required = true, paramType = "query", dataTypeClass = Integer.class),
                    @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query", dataTypeClass = Integer.class)
            }
    )
    public ResultData<List<ParticipantVo>> getParticipantList(@RequestParam("id") Long id, @RequestParam("startIndex") Integer startIndex, @RequestParam("pageSize") Integer pageSize) {
        return ResultData.success(activityService.getParticipantList(id, startIndex, pageSize));
    }

    @ApiOperation("用户报名")
    @PostMapping("/user/apply")
    public ResultData<Object> apply(@RequestBody @Validated ParticipantReq participantReq) {
        activityService.apply(participantReq);
        return ResultData.success();
    }

    @ApiOperation("用户检票")
    @PostMapping("/user/check")
    public ResultData<Object> check(@RequestBody @Validated ParticipantReq participantReq) {
        activityService.check(participantReq);
        return ResultData.success();
    }

    @ApiOperation("用户退出活动")
    @PostMapping("/user/quit")
    public ResultData<Object> quit(@RequestBody @Validated ParticipantReq participantReq) {
        activityService.quit(participantReq);
        return ResultData.success();
    }

    @ApiOperation("获取用户参与的活动列表")
    @GetMapping("/user/list")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataTypeClass = Long.class),
                    @ApiImplicitParam(name = "startIndex", value = "起始下标", required = true, paramType = "query", dataTypeClass = Integer.class),
                    @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query", dataTypeClass = Integer.class)
            }
    )
    public ResultData<List<ActivityVo>> getUserActivityList(@RequestParam("userId") Long userId, @RequestParam("startIndex") Integer startIndex, @RequestParam("pageSize") Integer pageSize) {
        return ResultData.success(activityService.getUserActivityList(userId, startIndex, pageSize));
    }

    @ApiOperation("用户缴费")
    @PostMapping("/user/pay")
    public ResultData<Object> pay(@RequestBody @Validated ParticipantReq participantReq) {
        activityService.pay(participantReq);
        return ResultData.success();
    }

    @ApiOperation("用户评价反馈")
    @PostMapping("/feedback")
    public ResultData<Object> feedback(@RequestBody @Validated ParticipantReq participantReq) {
        activityService.feedback(participantReq);
        return ResultData.success();
    }

    @ApiOperation("根据标题搜索活动")
    @GetMapping("/search")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "title", value = "活动标题", required = true, paramType = "query", dataTypeClass = String.class),
                    @ApiImplicitParam(name = "startIndex", value = "起始下标", required = true, paramType = "query", dataTypeClass = Integer.class),
                    @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query", dataTypeClass = Integer.class)
            }
    )
    public ResultData<List<ActivityVo>> searchActivity(@RequestParam("title") String title, @RequestParam("startIndex") Integer startIndex, @RequestParam("pageSize") Integer pageSize) {
        return ResultData.success(activityService.searchActivity(title, startIndex, pageSize));
    }

}
