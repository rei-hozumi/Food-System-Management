package foodsystemmanagement.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.User;
import foodsystemmanagement.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //一覧取得
    public List<User> findAll() {
        return userRepository.findAll();
    }
    //登録,BCrypt変換
    public void save(User user) {

    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    //重複チェック
	public boolean existsByLoginId(String loginId) {
		return userRepository.existsByLoginId(loginId);
	}
    //取得（ID検索）
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    //更新
    // loginIdは変更しない
    // passwordは変更しない
    @Transactional
    public void update(User user) {

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow();

        existingUser.setUserName(user.getUserName());
        existingUser.setRole(user.getRole());

        // パスワード入力がある場合のみ変更
        if(user.getPassword() != null 
                && !user.getPassword().isEmpty()) {

            existingUser.setPassword(
                passwordEncoder.encode(user.getPassword())
            );
        }

        userRepository.save(existingUser);
    }
    //削除
    public boolean delete(Long id){

        User user = userRepository.findById(id)
                .orElseThrow();

        //自分は無効化禁止
        String loginUserId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if(user.getLoginId().equals(loginUserId)){
            return false;
        }

        user.setEnabled(false);
        userRepository.save(user);

        return true;
    }
    
    //一覧検索
    public List<User>search(String loginId){
    	if(loginId == null || loginId.isBlank()) {
    		return userRepository.findAll();
    	}
    	return userRepository.findByLoginIdContaining(loginId);
    }

}
