package foodsystemmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.User;
import foodsystemmanagement.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //ログイン
    public User login(String loginId,String password) {
    	//ログイン情報が見つかった→User、見つからない→NULL
    	User user = userRepository.findByLoginId(loginId).orElse(null);
    	if(user != null && user.getPassword().equals(password)) {
    		return user;
    	}
    	return null;
    }

    // ユーザー一覧取得
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // ユーザー登録
    public void save(User user) {
        userRepository.save(user);
    }

    // ユーザー取得（ID検索）
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // ユーザー更新
    public void update(User user) {
        userRepository.save(user);
    }

    // ユーザー削除
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
