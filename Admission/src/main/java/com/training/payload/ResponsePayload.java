package com.training.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsePayload {
	public ResponsePayload() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	private String email;
	private String fname;
	private String lname;
	private int computer;
	private int biology;
	private String address;
	private String course;

}
