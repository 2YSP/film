package cn.sp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security 配置类
 * @author 2YSP
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)//开启方法安全注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 配置用户认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("2ysp")
		.password("1234")
		.roles("admin");
	}
	/**
	 * 请求授权
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().headers().disable()
		.authorizeRequests()
		.antMatchers("/","/static/**","/film/**","/webSite/**","/webSiteInfo/**","/aboutMe").permitAll() //配置不需要身份认证的请求地址
		.anyRequest().authenticated()// 其他所有路径都需要访问认证
		.and()
		.formLogin()
		.loginPage("/login") // 指定登录地址
		.defaultSuccessUrl("/admin") //登录成功后默认跳转的页面
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.permitAll();
	}

	
}
