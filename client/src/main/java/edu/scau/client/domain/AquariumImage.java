package edu.scau.client.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 图片信息表
 * @TableName aquarium_image
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "图片信息表")
public class AquariumImage implements Serializable {
    /**
     * 
     */
    @ApiModelProperty("")
    private Long id;

    /**
     * 图片内容
     */
    @ApiModelProperty("图片内容")
    private String data;

    private static final long serialVersionUID = 1L;
}