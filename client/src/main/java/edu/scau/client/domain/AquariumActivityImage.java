package edu.scau.client.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "活动图片关联表")
public class AquariumActivityImage implements Serializable {
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
     * 图片ID
     */
    @ApiModelProperty("图片ID")
    private Long imageId;

    private static final long serialVersionUID = 1L;
}