package edu.scau.client.activity.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "活动参与者参数")
public class ParticipantReq {
    @ApiModelProperty("活动id")
    private Long activityId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("是否支付")
    private Integer isPay;
    @ApiModelProperty("是否检票")
    private Integer isCheck;
    @ApiModelProperty("评价")
    private String comment;
}
