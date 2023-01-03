package com.example.demo.repository.mapper;

import org.apache.ibatis.annotations.Param;

import vo.UserVO;

public interface UserMapper {

	public int insert(UserVO user);

	public UserVO selectUser(String userId);

	public int selectUserNum(@Param("userId") String id, @Param("userPw") String userPw);

	public int selectCheckId(@Param("userId") String id);

	public UserVO userselect(int userNum);

	public int userdelete(int userNum);

	public UserVO select(int userNum);

	public int userupdate(UserVO user);
}
