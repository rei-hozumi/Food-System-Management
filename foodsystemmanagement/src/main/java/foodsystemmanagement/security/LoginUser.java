package foodsystemmanagement.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import foodsystemmanagement.entity.User;

public class LoginUser implements UserDetails {
	public final User user;
	public LoginUser(User user) {
		this.user=user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	}
	@Override
	public @Nullable String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		return user.getLoginId();
	}
}
