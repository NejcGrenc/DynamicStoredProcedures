package com.github.nejcgrenc.plugin.processor.delegate;

import com.github.nejcgrenc.plugin.model.template.ClassData;

public class JavaImplementationImportHandler {

	public boolean addImport(ClassData classData, String importFullName) {

		boolean fullNameAlreadyExists = classData.getImports().parallelStream()
				.anyMatch(existingFullName -> importFullName.equals(existingFullName));

		if (fullNameAlreadyExists) {
			return true;
		}

		if (isPrimitiveType(importFullName)) {
			return false;
		}

		String importSimpleName = JavaImplementationNameHandler.simpleNameFromFullName(importFullName);
		boolean simpleNameAlreadyExists = classData.getImports().parallelStream()
				.anyMatch(existingSimpleName -> importSimpleName.equals(JavaImplementationNameHandler.simpleNameFromFullName(existingSimpleName)));

		if (simpleNameAlreadyExists) {
			return false;
		}

		classData.addImport(importFullName);
		return true;
	}

	private boolean isPrimitiveType(String importName) {
		return equalsIgnoreCaseAny(importName, "void", "boolean", "byte", "short", "int", "long", "float", "double", "char");
	}

	private boolean equalsIgnoreCaseAny(String original, String... primitives) {
		for (String primitive : primitives) {
			if (primitive.equalsIgnoreCase(original)) {
				return true;
			}
		}
		return false;
	}
}
