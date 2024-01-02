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
* 角色信息表
* @TableName aqs_role
*/
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色信息表")
public class AquariumRole implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long id;
    /**
    * 角色名
    */
    @ApiModelProperty("角色名")
    private String roleName;
}
