package edu.scau.api.controller;

import edu.scau.client.notice.domain.NoticeVo;
import edu.scau.client.notice.service.NoticeService;
import edu.scau.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
@Validated
@Slf4j
@Api(value = "NoticeController", tags = "通知管理接口")
public class NoticeController {
    /**
     * 获取用户通知列表
     * 删除用户通知
     */
    @Autowired
    NoticeService noticeService;

    @ApiOperation("获取用户通知列表")
    @GetMapping("/list")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "startIndex", value = "起始下标", required = true, paramType = "query", dataTypeClass = Integer.class),
                    @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query", dataTypeClass = Integer.class)
            }
    )
    public ResultData<List<NoticeVo>> getNoticeList(@RequestParam("startIndex") Integer startIndex,
                                                    @RequestParam("pageSize") Integer pageSize) {
        List<NoticeVo> noticeList = noticeService.getNoticeList(startIndex, pageSize);
        return ResultData.success(noticeList);
    }

    @ApiOperation("删除用户通知")
    @GetMapping("/delete")
    @ApiImplicitParam(name = "id", value = "通知id", required = true, paramType = "query", dataTypeClass = Long.class)
    public ResultData<Object> deleteNotice(@RequestParam("id") Long id) {
        noticeService.deleteNotice(id);
        return ResultData.success();
    }
}
