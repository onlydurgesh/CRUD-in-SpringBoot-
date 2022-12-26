package com.durgesh.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.durgesh.entity.User;
import com.durgesh.payloads.UserDto;
import com.durgesh.repository.UserRepo;
import com.durgesh.service.UserService;
import com.durgeshexceptions.ResourceNotFoundException;

@Service
public class UserserviceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper mapper;

	User dtoToUser(UserDto dto) {
		User user = mapper.map(dto, User.class);
		return user;
	}
	UserDto userToUserDto(User user) {
		UserDto dto = mapper.map(user, UserDto.class);
		return dto;
	}
	@Override
	public UserDto save(UserDto dto) {
		User user = this.dtoToUser(dto);
		User save = repo.save(user);
		return userToUserDto(save);
	}
	@Override
	public UserDto updateUser(UserDto dto, Long id) {
		User user = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		User save = repo.save(user);
		return userToUserDto(save);
	}
	@Override
	public UserDto findById(Long id) {
		User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		return userToUserDto(user);
	}
	@Override
	public List<UserDto> findAll() {
		List<User> users = repo.findAll();
		List<UserDto> collect = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return collect;
	}
	@Override
	public void deleteById(Long id) {
		this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
	}
	@Override
	public void deleteAll() {
		repo.deleteAll();

	}

}
