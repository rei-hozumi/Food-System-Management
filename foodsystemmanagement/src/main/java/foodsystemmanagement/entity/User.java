package foodsystemmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "ログインIDを入力してください")
	@Column(name = "login_id", unique = true, nullable = false, length = 10)
	private String loginId;

	@NotBlank(message = "パスワードを入力してください")
	@Column(nullable = false, length = 255)
	private String password;

	@NotBlank(message = "ユーザー名を入力してください")
	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;

	@NotBlank(message = "権限を選択してください")
	@Column(nullable = false, length = 50)
	private String role;
	
	public User() {}
	
	public User(Long id,String loginId,String password,String userName,String role) {
		this.id=id;
		this.loginId=loginId;
		this.password=password;
		this.userName=userName;
		this.role=role;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId=loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public String getRoleName() {
		switch(role){
			case "ADMIN":return "管理者";
			case "PLANNER":return "計画担当";
			case "WORKER":return "作業者";
			default:return "";
		}
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	
	
	
}
