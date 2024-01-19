package edu.scau.client.activity.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import edu.scau.client.domain.AquariumActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "活动详情")
public class ActivityVo {
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
     * 图片id列表
     */
    @ApiModelProperty("图片id列表")
    private List<Long> imageIds;

    public void setAquariumActivity(AquariumActivity aquariumActivity) {
        this.id = aquariumActivity.getId();
        this.title = aquariumActivity.getTitle();
        this.content = aquariumActivity.getContent();
        this.startTime = aquariumActivity.getStartTime();
        this.endTime = aquariumActivity.getEndTime();
        this.address = aquariumActivity.getAddress();
        this.fee = aquariumActivity.getFee();
    }

    public void setImageIdList(List<Long> longs) {
        this.imageIds = longs;
    }
}
