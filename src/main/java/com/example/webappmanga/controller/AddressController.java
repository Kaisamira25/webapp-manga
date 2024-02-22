package com.example.webappmanga.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webappmanga.dto.request.AddressDTO;
import com.example.webappmanga.dto.response.Message;
import com.example.webappmanga.model.Address;
import com.example.webappmanga.model.User;
import com.example.webappmanga.service.AddressService;
import com.example.webappmanga.service.serviceInterface.IAddress;
import com.example.webappmanga.service.serviceInterface.UserServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AddressController {
	private final IAddress addSer;

	private final ModelMapper modelMapper;

	@Operation(summary = "Create a Genre", responses = { @ApiResponse(description = "success", responseCode = "200"), })
	@PostMapping("/add/{id}")
	public ResponseEntity<Message> CreateAddress(@RequestBody AddressDTO address, @PathVariable("id") String Id) {
		Address addr = modelMapper.map(address, Address.class);
		addr = (Address) addSer.createAddress(addr, Id);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "deleted fail, Genre dose not exist"));
	}

	@Operation(summary = "Update address", description = "When the Genre is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
			@ApiResponse(description = "success", responseCode = "200"),
			@ApiResponse(description = "Address not found", responseCode = "400") })
	@PutMapping("/update/{id}")
	public ResponseEntity<Message> updateAddress(@PathVariable("id") Integer id, @RequestBody AddressDTO address) {
		Address addr = modelMapper.map(address, Address.class);
		addr = addSer.updateAddress(id, addr);
		if (addr != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "updated successfully", addr));

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Message(0, "update fail, address dose not exist"));

	}

	@Operation(summary = "Delete a Genre", description = "When the Genre is successfully updated, the response status code is 200; otherwise, it is 400, accompanied by a corresponding message.", responses = {
			@ApiResponse(description = "success", responseCode = "200"),
			@ApiResponse(description = "Genre not found", responseCode = "400") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Message> deleteAddress(@PathVariable("id") Integer id) {
		System.out.println(id);
		boolean status = addSer.deleteAddress(id);
		if (status == true) {
			return ResponseEntity.status(HttpStatus.OK).body(new Message(1, "deleted successfully"));

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(0, "deleted fail, Genre dose not exist"));

	}

}
