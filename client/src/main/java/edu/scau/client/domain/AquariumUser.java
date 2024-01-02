package edu.scau.client.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

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
import org.springframework.format.annotation.DateTimeFormat;

/**
* 用户信息表
* @TableName aqs_user
*/
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户信息表")
public class AquariumUser implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long id;
    /**
    * 用户名
    */
    @ApiModelProperty("用户名")
    private String username;
    /**
    * 密码
    */
    @ApiModelProperty("密码")
    private String password;
    /**
    * 昵称
    */
    @ApiModelProperty("昵称")
    private String nickname;
    /**
    * 电话号码
    */
    @ApiModelProperty("电话号码")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
    * 地址
    */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer sex;
    /**
    * 角色ID
    */
    @ApiModelProperty("角色ID")
    private Long roleId;
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
}
