package foodsystemmanagement.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth

        	    //ログイン不要
        	    .requestMatchers(
        	            "/login",
        	            "/users/register",
        	            "/css/**"
        	    ).permitAll()

        	    //マスタ管理（管理者のみ）
        	    .requestMatchers(
        	            "/users/**"
        	    ).hasRole("ADMIN")
        	    
        	    //受注、原材料、商品管理
        	    .requestMatchers(
        	            "/orders/**",
        	            "/products/**",
        	            "/materials/**"
        	    ).hasAnyRole("ADMIN","PLANNER")

        	    //製造計画一覧
        	    .requestMatchers("/productionPlans/list")
        	    .hasAnyRole("ADMIN","PLANNER","WORKER")

        	    //製造計画登録
        	    .requestMatchers("/productionPlans/register")
        	    .hasAnyRole("ADMIN","PLANNER")

        	    //製造計画編集
        	    .requestMatchers("/productionPlans/edit/**")
        	    .hasAnyRole("ADMIN","PLANNER","WORKER")

        	    //製造計画削除
        	    .requestMatchers("/productionPlans/delete/**")
        	    .hasAnyRole("ADMIN","PLANNER")

        	    // その他
        	    .anyRequest().authenticated()
        	)

                    .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")//これでpostmappig不要
                        .defaultSuccessUrl("/top", true)
                        .failureUrl("/login?error")
                        .permitAll()
                    )

                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .invalidateHttpSession(true)//セッション削除
                            .deleteCookies("JSESSIONID")//Cookie削除
                            .permitAll()
                    );
/*            // CSRFを一旦無効
            .csrf(csrf -> csrf.disable())
            // デフォルトログイン画面を無効
            .formLogin(Customizer.withDefaults());*/
        return http.build();
    }

}