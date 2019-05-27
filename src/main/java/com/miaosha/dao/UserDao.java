package com.miaosha.dao;

import com.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(int id);

    @Insert("insert into user (id,name) values(#{id},#{name})")
    public void insert(User user);
}
