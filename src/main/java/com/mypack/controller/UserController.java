package com.mypack.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.github.pagehelper.PageInfo;
import com.mypack.entity.Menu;
import com.mypack.entity.User;
import com.mypack.service.UserServiceIF;

@Controller
public class UserController {

	@Autowired
	UserServiceIF userService;

	public UserServiceIF getUserService() {
		return userService;
	}

	public void setUserService(UserServiceIF userService) {
		this.userService = userService;
	}
/*
 	//不用shiro
	@RequestMapping("/loginVerify")
	public String login(User user, Model model, HttpSession session) {
		User u = userService.queryOneUser(user.getUsername());
		// System.out.println(u);
		// 查询该用户的权限级别
		// System.out.println("权限级别为"+u.getRole().getId());
		if (u != null && u.getPassword().equals(user.getPassword())) {
			List<Menu> menulst = userService.queryAllMenusByRoleId(u.getRole().getId());
			model.addAttribute("menulst", menulst);
			session.setAttribute("user", user.getUsername());
			return "index";
		} else {
			return "login.jsp";
		}
	}*/

	// 用shiro的登录
	@RequestMapping("/loginVerify")
	public String login(@RequestParam("username")String username,@RequestParam("password") String password,HttpSession session) {
		Subject currentUser = SecurityUtils.getSubject();// 获取当前用户对象
		System.out.println(username+password);
		if (!currentUser.isAuthenticated()) {
			// 把用户名封装为 UsernamePasswordToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			session.setAttribute("username", username);
			try {
				System.out.println("token："+token.hashCode());
				// 执行登录 在这里把 token 传到了ShiroRealm 的 doGetAuthenticationInfo 方法里
				currentUser.login(token);
			}
			// 所有认证时异常的父类
			catch (AuthenticationException ae) {
				// 用于抛出异常
				//ae.printStackTrace();
				System.out.println("登陆失败" + ae.getMessage());
				//捕获到登录失败的异常跳转到login.jsp
				return "redirect:/login.jsp";
			}

		}
		return "index";
	}

	@RequestMapping("list")
	public String list() {
		return "";
	}

	@RequestMapping("/biaoti")
	public String panduan() {
		return "biaoti";
	}

	@RequestMapping("/listAllUser")
	public String listAll(Integer currentPage, Model model,HttpServletRequest request) {
		// System.out.println("UserController.listAll()");
		
		if (currentPage == null) {
			currentPage = 1;
		}	
		
		PageInfo<User> pf = userService.queryAllUserByPage(currentPage, 3);

		// 如果在第一页和最后一页点击上一页或者下一页会跳转到 第一页和 最后一页
		if (pf.getPageNum() == pf.getFirstPage()) {
			pf.setPrePage(pf.getFirstPage());
		}

		if (pf.getPageNum() == pf.getLastPage()) {
			pf.setNextPage(pf.getLastPage());
		}
		model.addAttribute("pf", pf);
		return "list";
	}
	
	//用于增删改 的 查询所有用户
	@RequestMapping("/listAllUser2")
	public String listAll2(Integer currentPage, Model model,HttpServletRequest request) {
		// System.out.println("UserController.listAll()");
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request); 
		System.out.println("测试"+map.get("pageNum").toString()); 
		String pageNum1 = map.get("pageNum").toString();
		int pageNum=Integer.parseInt(pageNum1);
		if (currentPage == null) {
			currentPage =pageNum;
		}

		
		
		PageInfo<User> pf = userService.queryAllUserByPage(currentPage, 3);

		// 如果在第一页和最后一页点击上一页或者下一页会跳转到 第一页和 最后一页
		if (pf.getPageNum() == pf.getFirstPage()) {
			pf.setPrePage(pf.getFirstPage());
		}

		if (pf.getPageNum() == pf.getLastPage()) {
			pf.setNextPage(pf.getLastPage());
		}
		
		model.addAttribute("pf", pf);
		return "list";
	}

	// 跳转修改页面
	@RequestMapping("/updateuser")
	public String update(Integer id,Integer pageNum, Model model) {
		User user = userService.queryOneUserById(id);
		System.out.println(pageNum);
		model.addAttribute("user", user);
		model.addAttribute("pageNum", pageNum);
		return "updateuser";
	}

	// 开始修改
	@RequestMapping("/startUpdate")
	public String update(User user,Integer pageNum, int roleId,RedirectAttributes attributes) {

		System.out.println(user);
		System.out.println(roleId);
		
		attributes.addFlashAttribute("pageNum", pageNum); 
		
		boolean flag1 = userService.updateRole(roleId, user.getId());
		boolean flag2 = userService.updateUser(user);
		System.out.println(flag2);
		if (flag1 && flag2) {
			System.out.println("执行了if");
			return "redirect:/listAllUser2";
		} else {
			System.out.println("执行了else");
			return "redirect:/updateuser";
		}

	}

	@RequestMapping("/aaa")
	public String aa() {
		
		return "aaa";
	}

	// 跳转到添加用户
	@RequestMapping("/adduser")
	public String adduser(Model model) {
		int maxId = userService.queryMaxUserId();
		model.addAttribute("maxId",maxId+1);
		return "adduser";
	}

	// 开始新增
	@RequestMapping("/startAddUser")
	public String startAddUser(User user, int roleId,RedirectAttributes attributes) {

		boolean flag1 = userService.addUser(user);
		boolean flag2 = userService.addRole(roleId, user.getId());
		if (flag1 && flag2) {
			int id = user.getId();
			int pageNum=0;
			//判断新增用户的id应该仔哪一页
			if(id%3==0){
				pageNum=id/3;
			}
			else{
				pageNum=id/3+1;
			}
			attributes.addFlashAttribute("pageNum", pageNum); 
			System.out.println("执行了if");
			return "redirect:/listAllUser2";
		} else {
			System.out.println("执行了else");
			return "redirect:/adduser";
		}
	}

	// 删除用户
	@RequestMapping("/deleteuser")
	public String deleteUser(int id,RedirectAttributes attributes) {
		// id 和 userId 的值是相同的
		int userId = id;
		boolean flag1 = userService.deleteUserById(id);
		boolean flag2 = userService.deleteUserRole(userId);
		int pageNum=0;
		if(id%3==1||id%3==0){
			pageNum=id/3;
		}
		else{
			pageNum=id/3+1;
		}
		System.out.println("pageNum:"+pageNum);
		attributes.addFlashAttribute("pageNum", pageNum); 
		if (flag1 && flag2) {
			System.out.println("执行了if");
			return "redirect:/listAllUser2";
		} else {
			System.out.println("执行了else");
			return "redirect:/listAllUser";
		}
	}
}













