package com.example.webappmanga.service.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.example.webappmanga.model.Address;

public interface IAddress {
	 Address createAddress(Address AddressDTO,String id);
	 Address updateAddress(Integer id, Address AddressDTO);
	 boolean deleteAddress(Integer id);

	 Address getByAddressId(Integer id);
	 List<Address> getAllAddress();
	 List<Address> getAllbyUser(Integer id);
}
