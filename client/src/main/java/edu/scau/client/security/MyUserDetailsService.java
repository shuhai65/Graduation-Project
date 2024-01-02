package edu.scau.client.security;

import edu.scau.client.domain.AquariumPermission;
import edu.scau.client.domain.AquariumRole;
import edu.scau.client.domain.AquariumUser;
import edu.scau.client.mapper.AquariumRoleMapper;
import edu.scau.client.mapper.AquariumUserMapper;
import edu.scau.common.domain.UserRedisBO;
import edu.scau.common.utils.AuthUserContext;
import edu.scau.common.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AquariumUserMapper aquariumUserMapper;
    @Autowired
    private AquariumRoleMapper aquariumRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AquariumUser aquariumUser = aquariumUserMapper.selectByUsername(username);
        if(aquariumUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<AquariumPermission> permissions = aquariumRoleMapper.findPermissionsByRoleId(aquariumUser.getRoleId());
        String[] permissionArray = new String[permissions.size()];
        for(int i = 0; i < permissions.size(); i++) {
            permissionArray[i] = permissions.get(i).getPermissionName();
        }
        AuthUserContext.set(new UserRedisBO(aquariumUser.getId(), aquariumUser.getUsername()));
        return User.withUsername(aquariumUser.getUsername()).password(aquariumUser.getPassword()).authorities(permissionArray).build();
    }
}
