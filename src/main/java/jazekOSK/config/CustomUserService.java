/*
 * Copyright 2001,2017 (c) Point Of Sale Solutions (POSS) of Sabre Inc. All
 * rights reserved.
 * 
 * This software and documentation is the confidential and proprietary
 * information of Sabre Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Sabre Inc.
 */
package jazekOSK.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import jazekOSK.entity.User;
import jazekOSK.repository.UserRepository;
import jazekOSK.type.Role;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service("customUserDetailsService")
@Transactional
public class CustomUserService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Autowired
    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                        true, true, true, true,
                        getGrantedAuthorities(user.getRole().getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}
