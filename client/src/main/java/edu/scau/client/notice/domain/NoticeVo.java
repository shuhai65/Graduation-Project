package edu.scau.client.notice.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "通知详情")
public class NoticeVo {
    @ApiModelProperty("")
    private Long id;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("通知标题")
    private String title;
    @ApiModelProperty("通知内容")
    private String content;
}
