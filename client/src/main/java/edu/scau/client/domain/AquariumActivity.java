package edu.scau.client.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "活动信息表")
public class AquariumActivity implements Serializable {

    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 活动标题
     */
    @ApiModelProperty("活动标题")
    private String title;
    /**
     * 活动内容
     */
    @ApiModelProperty("活动内容")
    private String content;
    /**
     * 活动开始时间
     */
    @ApiModelProperty("活动开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startTime;
    /**
     * 活动结束时间
     */
    @ApiModelProperty("活动结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endTime;
    /**
     * 活动地址
     */
    @ApiModelProperty("活动地址")
    private String address;
    /**
     * 活动费用(小数点后两位)
     */
    @ApiModelProperty("活动费用(小数点后两位)")
    private BigDecimal fee;
    /**
     * 活动状态,0表示未开始，1表示进行中，2表示已结束
     */
    @ApiModelProperty("活动状态,0表示未开始，1表示进行中，2表示已结束")
    private Integer status;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;
    /**
     * 删除标志
     */
    @ApiModelProperty("删除标志")
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}