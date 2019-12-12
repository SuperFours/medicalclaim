package com.medicalclaim;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicalclaimApplicationTests {

	@Test
	public void applicationTest() {
		MedicalclaimApplication.main(new String[] {});
	    assertTrue(true);
	}

}
