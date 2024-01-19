package edu.scau.client.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "活动用户关联表")
public class AquariumActivityUser implements Serializable {
    /**
     *
     */
    @ApiModelProperty("")
    private Long id;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Long activityId;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 是否支付,0表示未支付，1表示已支付
     */
    @ApiModelProperty("是否支付,0表示未支付，1表示已支付")
    private Integer isPay;

    /**
     * 是否已检票,0表示未检票，1表示已检票
     */
    @ApiModelProperty("是否已检票,0表示未检票，1表示已检票")
    private Integer isCheck;

    /**
     * 用户评价
     */
    @ApiModelProperty("用户评价")
    private String comment;

    private static final long serialVersionUID = 1L;
}