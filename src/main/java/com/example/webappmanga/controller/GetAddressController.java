package com.example.webappmanga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.Address;
import com.example.webappmanga.model.User;
import com.example.webappmanga.service.AddressService;
import com.example.webappmanga.service.serviceInterface.IAddress;
import com.example.webappmanga.service.serviceInterface.UserServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class GetAddressController {
	private final IAddress addSer;


	@GetMapping("/all/{id}")
	@Operation(summary = "Find All Address", responses = {
			@ApiResponse(description = "success", responseCode = "200") })
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Message> GetAllList(@PathVariable("id") Integer id) {
		List<Address> list = addSer.getAllbyUser(id);
		System.out.println(list.size());
		return ResponseEntity.status(HttpStatus.CREATED).body(new Message(1, "successful", list));
	}

	
	@PostMapping("/new")
	public String getAAA(@RequestBody String aaa) {
		System.out.println(aaa);
		return aaa;
	}
//
//	    
//
//	    // Get Address by ID
//	    @GetMapping("/{id}")
//	    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
//	        Address address = addressService.getAddressById(id);
//	        if (address != null) {
//	            return new ResponseEntity<>(address, HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	    }
//
//	    // Update an existing Address
//	    @PutMapping("/{id}")
//	    public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
//	        Address updatedAddress = addressService.updateAddress(id, address);
//	        if (updatedAddress != null) {
//	            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	    }
//
//	    // Delete an Address
//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
//	        addressService.deleteAddress(id);
//	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    }
}
