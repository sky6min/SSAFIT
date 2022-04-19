package com.ssafy.common.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 부가 상세정보(활성화 여부, 만료, 롤 등) 정의.
 */
public class SsafitUserDetails implements UserDetails {
	@Autowired
	User user;
	boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
    List<GrantedAuthority> roles = new ArrayList<>();
    
    public SsafitUserDetails(User user) {
    		super();
    		this.user = user;
    }
    
    public User getUser() {
    		return this.user;
    }
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	@Override
	public String getUsername() {
		return this.user.getNickname();
	}
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getRoleList().forEach(r -> {
			authorities.add(()->{ return r;});
		});
		return authorities;
	}
	public void setAuthorities(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
}