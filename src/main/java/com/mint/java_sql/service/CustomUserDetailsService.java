package com.mint.java_sql.service;

import com.mint.java_sql.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CustomUserDetailsService {

    UserDetails loadUserByUsername(String username);

    Collection<? extends GrantedAuthority> getAuthority(Employee employee);
}
