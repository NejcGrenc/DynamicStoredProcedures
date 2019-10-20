package com.github.nejcgrenc.plugin;

import com.github.nejcgrenc.plugin.testconfig.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class PremadeStoredProcedureTest {

	@PersistenceContext
	private EntityManager manager;

	@Test
	public void shouldCallProcedure() {
		StoredProcedureQuery storedProcedureNextPrime = manager.createStoredProcedureQuery("NEXT_PRIME")
				                                .registerStoredProcedureParameter("value",   Integer.class, ParameterMode.IN)
												.setParameter("value", 3);

		assertEquals(5, storedProcedureNextPrime.getSingleResult());

		StoredProcedureQuery storedProcedureThree = manager.createStoredProcedureQuery("NUMBER_THREE");
		assertEquals(3, storedProcedureThree.getSingleResult());
	}

}