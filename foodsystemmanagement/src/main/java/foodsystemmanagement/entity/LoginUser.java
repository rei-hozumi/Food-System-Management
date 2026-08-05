package foodsystemmanagement.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails {

    private final User user;

    public LoginUser(User user) {
        this.user = user;
    }

    //権限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }
     //パスワード
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    //ログインID(User.javaのloginIdに変換)
    @Override
    public String getUsername() {
        return user.getLoginId();
    }
     //アカウント有効
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //ロックされていない
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
     //資格情報有効
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //有効ユーザー
    @Override
    public boolean isEnabled() {
        return true;
    }
    //Userエンティティ取得
    public User getUser() {
        return user;
    }
}