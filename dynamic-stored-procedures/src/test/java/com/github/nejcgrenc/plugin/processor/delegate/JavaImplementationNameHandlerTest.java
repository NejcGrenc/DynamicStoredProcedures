package com.github.nejcgrenc.plugin.processor.delegate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaImplementationNameHandlerTest {

	@Test
	public void shouldProcessStandardName() {
		assertEquals("com.best.code", JavaImplementationNameHandler.packageNameFromFullName("com.best.code.Sampler"));
		assertEquals("Sampler", JavaImplementationNameHandler.simpleNameFromFullName("com.best.code.Sampler"));
	}

	@Test
	public void shouldProcessDefaultPackageName() {
		assertEquals(null, JavaImplementationNameHandler.packageNameFromFullName("Sampler"));
		assertEquals("Sampler", JavaImplementationNameHandler.simpleNameFromFullName("Sampler"));
	}
}