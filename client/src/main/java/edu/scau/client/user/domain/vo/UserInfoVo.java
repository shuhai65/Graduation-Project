package edu.scau.client.user.domain.vo;

import edu.scau.client.domain.AquariumUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户信息")
public class UserInfoVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("头像id")
    private Long avatarId;
    @ApiModelProperty("角色")
    private String role;

    public UserInfoVo(AquariumUser aquariumUser) {
        this.id = aquariumUser.getId();
        this.username = aquariumUser.getUsername();
        this.avatarId = aquariumUser.getAvatarId();
        this.role = aquariumUser.getRoleId() == 1 ? "普通用户" : "管理员";
    }
}
