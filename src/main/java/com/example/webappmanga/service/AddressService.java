package com.example.webappmanga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.webappmanga.model.Address;
import com.example.webappmanga.model.User;
import com.example.webappmanga.repository.AddressRepository;
import com.example.webappmanga.repository.UserRepository;
import com.example.webappmanga.service.serviceInterface.CreateAndUpdateI;
import com.example.webappmanga.service.serviceInterface.IAddress;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService implements IAddress{
	@Autowired
	private final AddressRepository addRepo;
	@Autowired
	private final UserRepository userRepo;

	@Override
	public Address createAddress(Address AddressDTO,String id) {
		Address addr=new Address();
		User user=userRepo.findById(Integer.parseInt(id)).get();
		addr.setDistrict( AddressDTO.getDistrict());
		addr.setCity( AddressDTO.getCity());
		addr.setPhone_number( AddressDTO.getPhone_number());
		addr.setWard( AddressDTO.getWard());
		addr.setAddress( AddressDTO.getAddress());
		addr.setUser(user);
		return addRepo.save(addr);
	}
	@Override
	public Address updateAddress(Integer id, Address AddressDTO) {
		Address addr = addRepo.findById(id).orElse(null);
		if(addr != null) {
			addr.setDistrict(AddressDTO.getDistrict());
			addr.setCity(AddressDTO.getCity());
			addr.setPhone_number(AddressDTO.getPhone_number());
			addr.setWard(AddressDTO.getWard());
			addr.setAddress(AddressDTO.getAddress());
			return addRepo.save(addr);
		}
		return null;
	}
	@Override
	public boolean deleteAddress(Integer id) {
		Address addr=getByAddressId(id);
		if(addr != null) {
			addRepo.delete(addr);
			return true;
		}
		return false;
	}
	@Override
	public Address getByAddressId(Integer id) {
		
		return addRepo.findById(id).orElse(null);
	}
	@Override
	public List<Address> getAllAddress() {
		return addRepo.findAll();
	}
	@Override
	public List<Address> getAllbyUser(Integer id) {
		
		return addRepo.findByUser(userRepo.findById(id).get());
	}
	
	

	

	
	
	
	
}
