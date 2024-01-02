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
    @ApiModelProperty("")
    private Long id;
    /**
    * 权限名
    */
    @ApiModelProperty("权限名")
    private String permissionName;
}
