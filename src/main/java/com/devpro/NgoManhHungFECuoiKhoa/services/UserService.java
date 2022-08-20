package com.devpro.NgoManhHungFECuoiKhoa.services;

import org.springframework.stereotype.Service;

import com.devpro.NgoManhHungFECuoiKhoa.model.User;


@Service
public class UserService extends BaseService<User> {
	
	@Override
	protected Class<User> clazz() {
		return User.class;
	}

	public User loadUserByUsername(String userName) {
		String sql = "select * from tbl_users u where u.username = '" + userName + "' and status = 1";
		return this.getEntityByNativeSQL(sql);
	}
	
}
