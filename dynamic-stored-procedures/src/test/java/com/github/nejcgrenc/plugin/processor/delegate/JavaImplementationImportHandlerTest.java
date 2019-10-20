package com.github.nejcgrenc.plugin.processor.delegate;

import com.github.nejcgrenc.plugin.model.template.ClassData;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaImplementationImportHandlerTest {

	private JavaImplementationImportHandler importHandler = new JavaImplementationImportHandler();

	@Test
	public void shouldAddNewImport() {
		ClassData classData = new ClassData();
		String existingImport = "com.best.code.OtherImport";
		classData.addImport(existingImport);

		String newImport = "com.best.code.Sampler";

		boolean success = importHandler.addImport(classData, newImport);

		// Import is added
		assertTrue(success);
		assertTrue(classData.getImports().contains(existingImport));
		assertTrue(classData.getImports().contains(newImport));
	}

	@Test
	public void shouldIncludeAlreadyExistingImport() {
		ClassData classData = new ClassData();
		String existingImport = "com.best.code.Sampler";
		classData.addImport(existingImport);

		String newImport = "com.best.code.Sampler";

		boolean success = importHandler.addImport(classData, newImport);

		// Import is added
		assertTrue(success);
		assertTrue(classData.getImports().contains(newImport));
	}

	@Test
	public void shouldNotIncludeImportWithSimilarName() {
		ClassData classData = new ClassData();
		String existingImport = "com.worst.code.Sampler";
		classData.addImport(existingImport);

		String newImport = "com.best.code.Sampler";

		boolean success = importHandler.addImport(classData, newImport);

		// Import is not added
		assertFalse(success);
		assertTrue(classData.getImports().contains(existingImport));
		assertFalse(classData.getImports().contains(newImport));
	}

	@Test
	public void shouldNotIncludePrimitiveTypes() {
		ClassData classData = new ClassData();

		String newVoidImport = "void";
		String newIntImport  = "int";
		String newLongImport = "long";

		boolean successVoid = importHandler.addImport(classData, newVoidImport);
		boolean successInt  = importHandler.addImport(classData, newIntImport);
		boolean successLong = importHandler.addImport(classData, newLongImport);

		// Imports are not added
		assertFalse(successVoid);
		assertFalse(classData.getImports().contains(newVoidImport));
		assertFalse(successInt);
		assertFalse(classData.getImports().contains(newIntImport));
		assertFalse(successLong);
		assertFalse(classData.getImports().contains(newLongImport));
	}

}