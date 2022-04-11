package com.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByAddress(String address);

}
