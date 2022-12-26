package com.durgesh.service;

import java.util.List;
import com.durgesh.payloads.UserDto;

public interface UserService {
	
	UserDto save(UserDto dto);
	UserDto updateUser(UserDto dto,Long id);
	UserDto findById(Long id);
	List<UserDto> findAll();
	void deleteById(Long id);
	void deleteAll();
}
