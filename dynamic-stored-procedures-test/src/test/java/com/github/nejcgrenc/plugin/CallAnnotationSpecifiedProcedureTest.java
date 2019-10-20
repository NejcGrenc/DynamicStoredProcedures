package com.github.nejcgrenc.plugin;

import com.github.nejcgrenc.plugin.testconfig.Application;
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
public class CallAnnotationSpecifiedProcedureTest {

	@Autowired
	private AnnotationSpecifiedProcedure procedure;

	@Test
	public void shouldCallProcedureWithDefaultValues() {
		assertEquals(new Integer(7), procedure.sum_numbers(3, 4));
	}

	@Test
	public void shouldCallProcedureWithDefinedName() {
		assertEquals(new Integer(7), procedure.sum(3, 4));
	}

	@Test
	public void shouldCallProcedureWithAnnotatedArgument() {
		assertEquals(new Integer(7), procedure.sum_arguments(3, 4));
	}

	@Test
	public void shouldCallProcedureWithFullyAnnotatedArgument() {
		assertEquals(new Integer(7), procedure.sum_arguments_withClass(3, 4));
	}

}