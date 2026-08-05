package foodsystemmanagement.service;

import java.util.List;

import jakarta.transaction.Transactional;

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
    //取得（ID検索）
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    //更新
    // loginIdは変更しない
    // passwordは変更しない
    @Transactional
    public void update(User user) {
    	User updateUser = userRepository.findById(user.getId()).orElseThrow(() 
    											  -> new IllegalArgumentException("ユーザーが存在しません"));
    	updateUser.setUserName(user.getUserName());
    	updateUser.setRole(user.getRole());
        userRepository.save(updateUser);
    }
    //削除
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    //一覧検索
    public List<User>search(String loginId){
    	if(loginId == null || loginId.isBlank()) {
    		return userRepository.findAll();
    	}
    	return userRepository.findByLoginIdContaining(loginId);
    }

}
