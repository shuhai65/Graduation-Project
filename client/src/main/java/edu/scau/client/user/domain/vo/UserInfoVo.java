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
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("角色")
    private String role;
    @ApiModelProperty("性别")
    private String sex;

    public UserInfoVo(AquariumUser aquariumUser) {
        this.id = aquariumUser.getId();
        this.username = aquariumUser.getUsername();
        this.avatarId = aquariumUser.getAvatarId();
        this.nickname = aquariumUser.getNickname();
        this.phone = aquariumUser.getPhone();
        this.email = aquariumUser.getEmail();
        this.address = aquariumUser.getAddress();
        this.role = aquariumUser.getRoleId() == 1 ? "普通用户" : "管理员";
        this.sex = aquariumUser.getSex() == 0 ? "未知" : aquariumUser.getSex() == 1 ? "男" : "女";
    }
}
