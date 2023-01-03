package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserDao;

import vo.UserVO;

@Component
public class userService {
	@Autowired
	private  UserDao dao;
	
	public boolean join(UserVO user) throws DuplicateKeyException {
		
		try {
			if(dao.insert(user) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		} finally {
			System.out.println("에러");
		}
		return false;
	}
	
	public boolean login(String userId, String userPw) {
		if(dao.selectUserNum(userId,userPw) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkId(String userId) {
		if(dao.selectCheckId(userId) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public UserVO userselect(int userNum) {
		return dao.userselect(userNum);
	}

	public UserVO getUserInfo(String loginId) {
		return dao.select(loginId);
	}

	public boolean userdelete(int userNum, String loginId) {
		UserVO user = dao.userselect(userNum);

		if (loginId != null && loginId.equals(user.getUserId())) {
			dao.userdelete(userNum);
			return true;
		} else {
			return false;
		}

	}

	public boolean userupdate(UserVO user, String loginId) {
		UserVO original = dao.select(user.getUserNum());

		if (loginId != null && loginId.equals(original.getUserId())) {
			dao.userupdate(user);
			return true;
		} else {
			return false;
		}
	}
}


