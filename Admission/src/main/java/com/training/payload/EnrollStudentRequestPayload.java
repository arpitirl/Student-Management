package com.training.payload;

import lombok.Data;

@Data
public class EnrollStudentRequestPayload {
	private String name;
	private String email;
	
	
	private int computer;
	private int biology;
	private String address;
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
