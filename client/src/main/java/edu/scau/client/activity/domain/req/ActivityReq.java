package edu.scau.client.activity.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import edu.scau.client.domain.AquariumActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "活动参数")
public class ActivityReq {
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
     * 活动图片id list
     */
    @ApiModelProperty("活动图片id list")
    private List<Long> imageIdList;

    public AquariumActivity toAquariumActivity() {
        AquariumActivity aquariumActivity = new AquariumActivity();
        aquariumActivity.setId(id);
        aquariumActivity.setTitle(title);
        aquariumActivity.setContent(content);
        aquariumActivity.setStartTime(startTime);
        aquariumActivity.setEndTime(endTime);
        aquariumActivity.setAddress(address);
        aquariumActivity.setFee(fee);
        return aquariumActivity;
    }
}
