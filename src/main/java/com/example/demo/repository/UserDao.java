package com.example.demo.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.mapper.UserMapper;

import vo.UserVO;
@Component
public class UserDao {
	@Autowired
	private SqlSessionTemplate session;
	
	public int insert(UserVO user) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.insert(user);
	}
	
	public int selectUserNum(String userId, String userPw) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.selectUserNum(userId, userPw);
	}
	
	public UserVO select(String userId) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.selectUser(userId);
	}
	
	public int selectCheckId(String userId) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.selectCheckId(userId);
	}
	
	public UserVO userselect(int userNum) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.userselect(userNum);
	}
	
	public int userdelete(int userNum) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.userdelete(userNum);
	}
	
	public UserVO select(int userNum) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.select(userNum);
	}

	public int userupdate(UserVO user) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.userupdate(user);
	}
}
