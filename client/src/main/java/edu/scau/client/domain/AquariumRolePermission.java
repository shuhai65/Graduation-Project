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
* 角色权限关联表
* @TableName aqs_role_permission
*/
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色权限关联表")
public class AquariumRolePermission implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long id;
    /**
    * 角色ID
    */
    @ApiModelProperty("角色ID")
    private Long roleId;
    /**
    * 权限ID
    */
    @ApiModelProperty("权限ID")
    private Long permissionId;
}
