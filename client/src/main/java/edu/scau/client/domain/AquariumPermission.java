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
* 权限信息表
* @TableName aqs_permission
*/
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "权限信息表")
public class AquariumPermission implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long id;
    /**
    * 权限名
    */
    @NotBlank(message="[权限名]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("权限名")
    @Length(max= 20,message="编码长度不能超过20")
    private String permissionName;
}
