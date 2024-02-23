package com.example.webappmanga.service;

import com.example.webappmanga.model.User;
import com.example.webappmanga.repository.UserRepository;
import com.example.webappmanga.service.serviceInterface.CreateAndUpdateI;
import com.example.webappmanga.service.serviceInterface.UserServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceI, CreateAndUpdateI<Integer,User> {
    private final UserRepository userRepository;
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Object create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(Integer key, User entity) {
        return userRepository.save(entity);
    }

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
