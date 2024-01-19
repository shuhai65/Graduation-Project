package edu.scau.client.activity.domain.vo;

import edu.scau.client.domain.AquariumActivityUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "活动参与者详情")
public class ParticipantVo {
    @ApiModelProperty("参与者id")
    private Long id;
    @ApiModelProperty("参与者姓名")
    private String username;
    @ApiModelProperty("是否支付")
    private Integer isPay;
    @ApiModelProperty("是否检票")
    private Integer isCheck;
    @ApiModelProperty("评价")
    private String comment;
    public void setVo(AquariumActivityUser aquariumActivityUser){
        this.id = aquariumActivityUser.getId();
        this.isPay = aquariumActivityUser.getIsPay();
        this.isCheck = aquariumActivityUser.getIsCheck();
        this.comment = aquariumActivityUser.getComment();
    }
}
