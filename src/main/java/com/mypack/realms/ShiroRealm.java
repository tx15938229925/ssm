package com.mypack.realms;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.mypack.entity.User;
import com.mypack.service.UserServiceIF;


public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserServiceIF userService;
    

    public UserServiceIF getUserService() {
		return userService;
	}


	public void setUserService(UserServiceIF userService) {
		this.userService = userService;
	}


	//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	System.out.println("doGetAuthenticationToken："+token.hashCode());
    	
    	//1. 把AuthenticationToken 转换为 UsernamePasswordToken
    	UsernamePasswordToken uptoken=(UsernamePasswordToken) token;
    	
    	//2. 从UsernamePasswordToken 中获取 usernaem
    	String username = uptoken.getUsername();
    	
    	//3. 调用数据库方法 ，冲数据库查询username对应用户记录
    	User user = userService.queryOneUser(username);
    	
    	//4. 若用户不存在 则抛出UnknownAccount
    	if(user==null){
    		System.out.println("用户名不存在");
    		throw new UnknownAccountException("用户不存在！");
    	}
    	
    	//参数1.用户认证的对象(subject.getPrincipal();返回的对象), 
        //参数2.从数据库根据用户名查询到的用户的密码
        //参数3.把当前自定义的realm对象传给SimpleAuthenticationInfo,在配置文件需要注入
    	SimpleAuthenticationInfo Info = new SimpleAuthenticationInfo(username, user.getPassword(),this.getName());
        return Info;
    }
    

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection Principals) {
    	System.out.println("doGetAuthorizationInfo--->");
    	
    	//1. 从PrincipalCollection中获取登录用户信息 （用户名）

    	Object Principal=Principals.getPrimaryPrincipal();
    	
    	String name=(String)Principal;
    	System.out.println(Principal+"-->");
    	//2. 利用登陆的用户信息来获取当前用户角色权限(可能需要查询数据库)
    	//2.1 查询数据库查询出改登陆用户的权限级别 然后赋值给roles
    	User user = userService.queryOneUser(name);
    	int id = user.getRole().getId();
    	Set<String> roles=new LinkedHashSet<>();
    	if(id==1){
    		roles.add("用户管理");
    		roles.add("权限管理");
    	}else{
    		roles.add("用户管理");
    	}

    	//3. 创建SimpleAuthorizationInfo 并设置roles属性
    	SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
    	
    	System.out.println("info："+info.getRoles()+"-->");
    	//4. 返回SimpleAuthorizationInfo对象
        return info;
    }

}