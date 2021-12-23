package com.mydic.user;

import java.io.File;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	public static String SHA256(String pwd) {
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}


	@RequestMapping("/")
	public String main() throws Exception {
		return "main";
	}
	@RequestMapping("/join.do")
	public String join() {
		System.out.println("join.jsp");
		return "join";
	}
	@RequestMapping("/login.do")
	public String login() {
		System.out.println("login.jsp");
		return "login";
	}
	
	@RequestMapping("/user_check.do")
	public @ResponseBody void user_check(UserVo vo,HttpServletResponse response) throws Exception {
		String id = userService.overlap(vo);
		int flag = 0;
		if(id!=null) {
			flag = 0;
		}
		else {
			flag = 100;
		}
		PrintWriter out = response.getWriter();
		out.print(flag);
	}	
	@RequestMapping("/joinok.do")
	public String join(UserVo vo,HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		vo.setPwd_hash(SHA256(vo.getPwd()));
		MultipartFile uploadfile = vo.getUploadfile();
		String path = request.getSession().getServletContext().getRealPath("/img/profile/");
		System.out.println(path);
		if(!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename();
			File file = new File(path + fileName);
			if(file.exists()) {	
				String onlyFileName = fileName.substring(0, fileName.indexOf("."));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String time = sdf.format(new Date());
				String extension = fileName.substring(fileName.indexOf("."));
				fileName = onlyFileName+time+extension;
			}
			vo.setFilename(fileName);
			uploadfile.transferTo(new File(path + fileName));
		}
		else {
			vo.setFilename("default.png");
		}
		userService.join(vo);
		String id = userService.login(vo);
		if(id==null)
		{
			session.setAttribute("id", null);
			return "main";
		}
		else
		{
			session.setAttribute("id", id);
			return "redirect:board.do";
		}
	}
	
	@RequestMapping("/loginok.do")
	public String login(UserVo vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setPwd_hash(SHA256(vo.getPwd()));
		String id = userService.login(vo);
		System.out.println(id);
		if(id==null)
		{
			session.setAttribute("id", "failed");
			System.out.println("아이디, 패스워드 틀림");
			return "loginfailed";
		}
		else
		{
			session.setAttribute("id", id);
			System.out.println("로그인 성공");
			return "redirect:board.do";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(UserVo vo,HttpSession session) {
		session.invalidate();
		return "main";
	}
	@RequestMapping("/myinfo.do")
	public String myinfo(UserVo vo,HttpSession session,Model model) throws Exception {
		vo.setId((String)session.getAttribute("id"));
		model.addAttribute("m", userService.myinfo(vo));
		return "myinfo";
	}
	@RequestMapping("/myinfoupdate.do")
	public String myinfoupdate(UserVo vo,HttpServletRequest request) throws Exception {
		MultipartFile uploadfile = vo.getUploadfile();
		String path = request.getSession().getServletContext().getRealPath("/img/profile/");
		if(!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename();
			File file = new File(path + fileName);
			if(file.exists()) {
				String onlyFileName = fileName.substring(0, fileName.indexOf("."));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String time = sdf.format(new Date());
				String extension = fileName.substring(fileName.indexOf("."));
				fileName = onlyFileName+time+extension;
			}
			vo.setFilename(fileName);
			if(!userService.getFilename(vo).equals("default.png")) {
				File delfile = new File(path + userService.getFilename(vo));
				delfile.delete();
			}
			uploadfile.transferTo(new File(path + fileName));
		}
		else {
			vo.setFilename(userService.getFilename(vo));
		}
		
		if(!vo.getPwd().equals(""))	{
			vo.setPwd_hash(SHA256(vo.getPwd()));
			userService.myinfoupdatepwd(vo);
		}
		else {
			userService.myinfoupdate(vo);
		}
		
		return "redirect:board.do";
	}
	@RequestMapping("/myinfodelete.do")
	public String myinfodelete(UserVo vo,HttpServletRequest request) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/img/profile/");
		if(!userService.getFilename(vo).equals("default.png")) {
			File delfile = new File(path + userService.getFilename(vo));
			delfile.delete();
		}
		userService.myBoarddelete(vo);
		userService.myComudelete(vo);
		userService.myinfodelete(vo);
		return "redirect:logout.do";
	}
}
