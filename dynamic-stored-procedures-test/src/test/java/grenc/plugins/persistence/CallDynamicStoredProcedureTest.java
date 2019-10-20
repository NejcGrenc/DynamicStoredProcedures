package grenc.plugins.persistence;

import grenc.plugins.persistence.testconfig.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class CallDynamicStoredProcedureTest {

	@Autowired
	private SimpleProcedure simpleProcedure;

	@Autowired
	private SimpleVoidProcedure simpleVoidProcedure;

	@Autowired
	private SimpleNoArgProcedure simpleNoArgProcedure;

	@Test
	public void shouldCallProcedure() {
		assertEquals(new Integer(5), simpleProcedure.next_prime(3));
		assertEquals(new Integer(7), simpleProcedure.sum_numbers(3, 4));
	}

	@Test
	public void shouldCallVoidProcedureWithoutException() {
		simpleVoidProcedure.say_loudly("HI");
	}

	@Test
	public void shouldCallNoArgProcedures() {
		assertEquals(3, simpleNoArgProcedure.number_three());
		assertEquals("Hello World", simpleNoArgProcedure.hello());
	}
}