package edu.scau.client.user.domain.vo;

import edu.scau.client.domain.AquariumUser;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户信息")
public class UserInfoVo {
    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String address;
    private String role;
    private String sex;

    public UserInfoVo(AquariumUser aquariumUser) {
        this.id = aquariumUser.getId();
        this.username = aquariumUser.getUsername();
        this.nickname = aquariumUser.getNickname();
        this.phone = aquariumUser.getPhone();
        this.email = aquariumUser.getEmail();
        this.address = aquariumUser.getAddress();
        this.role = aquariumUser.getRoleId() == 1 ? "普通用户" : "管理员";
        this.sex = aquariumUser.getSex() == 0 ? "未知" : aquariumUser.getSex() == 1 ? "男" : "女";
    }
}
