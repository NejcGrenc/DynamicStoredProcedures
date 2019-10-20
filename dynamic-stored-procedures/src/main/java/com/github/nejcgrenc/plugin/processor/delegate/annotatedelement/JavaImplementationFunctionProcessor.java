package com.github.nejcgrenc.plugin.processor.delegate.annotatedelement;

import com.github.nejcgrenc.plugin.annotation.DynamicProcedure;
import com.github.nejcgrenc.plugin.annotation.DynamicProcedureArgument;
import com.github.nejcgrenc.plugin.model.DynamicProceduresGeneratorException;
import com.github.nejcgrenc.plugin.model.template.ArgumentData;
import com.github.nejcgrenc.plugin.model.template.ClassData;
import com.github.nejcgrenc.plugin.model.template.FunctionData;
import com.github.nejcgrenc.plugin.processor.delegate.JavaImplementationImportHandler;
import com.github.nejcgrenc.plugin.processor.delegate.JavaImplementationNameHandler;
import lombok.AllArgsConstructor;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class JavaImplementationFunctionProcessor {

	private final JavaImplementationImportHandler importHandler = new JavaImplementationImportHandler();

	private ClassData classData;

	public FunctionData prepareSingleProcedure(ExecutableElement methodElement) {
		String accessModifier = methodElement.getModifiers().iterator().next().toString();
		String returnType = methodElement.getReturnType().toString();
		String methodName = methodElement.getSimpleName().toString();

		DynamicProcedure dynamicProcedureAnnotation = methodElement.getAnnotation(DynamicProcedure.class);
		if (dynamicProcedureAnnotation == null) {
			throw new DynamicProceduresGeneratorException(
					"Attempting to process method without 'DynamicProcedure' annotation [functionName = "+methodName+"]. Annotation is required!");
		}
		String procedureName = dynamicProcedureAnnotation.name();

		boolean successful = importHandler.addImport(classData, returnType);
		if (successful) {
			returnType = JavaImplementationNameHandler.simpleNameFromFullName(returnType);
		}


		List<ArgumentData> argumentDataList = new ArrayList<>();
		for (VariableElement argumentElement : methodElement.getParameters()) {

			String argumentName = null;
			String argumentType = null;
			DynamicProcedureArgument argumentAnnotation = argumentElement.getAnnotation(DynamicProcedureArgument.class);
			if (argumentAnnotation != null && argumentAnnotation.name() != null && ! argumentAnnotation.name().isEmpty()) {
				argumentName = argumentAnnotation.name();
			}
			if (argumentAnnotation != null && argumentAnnotationType(argumentAnnotation) != null
					&& ! argumentAnnotationType(argumentAnnotation).equals(Void.class.getTypeName())) {
				argumentType = argumentAnnotationType(argumentAnnotation);
				boolean argumentTypeAdded = importHandler.addImport(classData, argumentType);
				if (argumentTypeAdded) {
					argumentType = JavaImplementationNameHandler.simpleNameFromFullName(argumentType);
				}
			}

			String fieldReturnType = argumentElement.asType().toString();
			boolean fieldTypeAdded = importHandler.addImport(classData, fieldReturnType);
			if (fieldTypeAdded) {
				fieldReturnType = JavaImplementationNameHandler.simpleNameFromFullName(fieldReturnType);
			}

			String fieldName = argumentElement.getSimpleName().toString();

			ArgumentData argumentData = new ArgumentData();
			argumentData.setArgumentType((argumentType != null) ? argumentType : fieldReturnType);
			argumentData.setArgumentName((argumentName != null) ? argumentName : fieldName);
			argumentData.setFieldType(fieldReturnType);
			argumentData.setFieldName(fieldName);
			argumentDataList.add(argumentData);
		}

		FunctionData functionData = new FunctionData();
		functionData.setProcedureName((procedureName == null || procedureName.isEmpty()) ? methodName : procedureName);
		functionData.setFunctionAccess(accessModifier);
		functionData.setFunctionReturnType(returnType);
		functionData.setFunctionName(methodName);
		functionData.setFunctionArguments(argumentDataList);
		return functionData;
	}

	private String argumentAnnotationType(DynamicProcedureArgument argumentAnnotation) {
		if (argumentAnnotation != null) {
			try {
				argumentAnnotation.type(); // this should throw
			} catch (MirroredTypeException mte) {
				return mte.getTypeMirror().toString();
			}
		}
		return null;
	}

}
