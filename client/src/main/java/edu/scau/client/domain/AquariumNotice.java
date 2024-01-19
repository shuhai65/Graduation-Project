package edu.scau.client.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "通知信息表")
public class AquariumNotice implements Serializable {
    /**
     * 
     */
    @ApiModelProperty("")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 通知标题
     */
    @ApiModelProperty("通知标题")
    private String title;

    /**
     * 通知内容
     */
    @ApiModelProperty("通知内容")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}