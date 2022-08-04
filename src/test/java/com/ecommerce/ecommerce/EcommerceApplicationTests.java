package com.ecommerce.ecommerce;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EcommerceApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateUser() {

		User user = new User();

		user.setId(12);
		user.setUsername("hanadiahPertiwi");
		user.setPhone("099989786799");
		user.setAddress("Jakarta");
		User res = userRepository.save(user);

		assertNotNull(res);
	}

	@Test
	public void testGetUser() {

		User res = userRepository.findByUsername("hanadiahpertiwi");

		assertEquals("hanadiahpertiwi", res.getUsername().trim());
	}


	@Test
	public void testUpdateUser() {

		User user = userRepository.findByUsername("hanadiahpertiwi");

		user.setId(13);
		user.setUsername("Anisa");
		user.setPhone("09090909090909");
		user.setAddress("Palang palang");

		User res = userRepository.save(user);

		assertEquals("Anisa", res.getUsername().trim());
	}
	@Test
	public void testDeleteUser() {

		User user = userRepository.findByUsername("hanadiahpertiwi");

		userRepository.delete(user);

		assertNull(userRepository.findByUsername("hanadaiahpertiwi"));
	}


}
