package com.hu.libraryManagement.dao;

import com.hu.libraryManagement.VO.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {
    public int insert(UserVO userVO); //添加
    public UserVO select(String userAccount);

}
