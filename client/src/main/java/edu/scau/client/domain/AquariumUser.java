package edu.scau.client.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long id;
    /**
    * 用户名
    */
    @NotBlank(message="[用户名]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("用户名")
    @Length(max= 20,message="编码长度不能超过20")
    private String username;
    /**
    * 密码
    */
    @NotBlank(message="[密码]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("密码")
    @Length(max= 50,message="编码长度不能超过50")
    private String password;
    /**
    * 昵称
    */
    @NotBlank(message="[昵称]不能为空")
    @Size(max= 60,message="编码长度不能超过60")
    @ApiModelProperty("昵称")
    @Length(max= 60,message="编码长度不能超过60")
    private String nickname;
    /**
    * 电话号码
    */
    @NotBlank(message="[电话号码]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("电话号码")
    @Length(max= 20,message="编码长度不能超过20")
    private String phone;
    /**
    * 地址
    */
    @NotBlank(message="[地址]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("地址")
    @Length(max= 100,message="编码长度不能超过100")
    private String address;
    /**
    * 角色ID
    */
    @NotNull(message="[角色ID]不能为空")
    @ApiModelProperty("角色ID")
    private Long roleId;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
    /**
    * 删除标志
    */
    @NotNull(message="[删除标志]不能为空")
    @ApiModelProperty("删除标志")
    private Integer isDelete;
}
