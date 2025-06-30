package com.example.authenticator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticatorServiceApplication {

	public static void main(String[] args) {
		int[] arr=new int[100];
		Long start=System.currentTimeMillis();
		for(int i=0;i<arr.length;i++){
			System.out.print("PROCESSING..."+arr[i]);
		}
		System.out.println("end time: "+(System.currentTimeMillis()-start));

		start=System.currentTimeMillis();
		for(int a:arr){
			System.out.print("PROCESSING..."+a);
		}
		System.out.println("end time: "+(System.currentTimeMillis()-start));
		SpringApplication.run(AuthenticatorServiceApplication.class, args);

	}

}
