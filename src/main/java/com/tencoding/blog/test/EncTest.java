package com.tencoding.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void hashEncode() {
		String encPassword = new BCryptPasswordEncoder().encode("123");
		System.out.println("해시 값 확인 :" + encPassword);
		
		//$2a$10$fPowEkCXgX8/up5jpoZN9uFj1iHGBRF40Ox9t0cb.SlMgL5JlEX.e
		//$2a$10$g91xJB3z65vCV5FN/ecCYeGbJzJxbLyNyATAGDFYoSV.kA5BNoAa.


	}
	
}
