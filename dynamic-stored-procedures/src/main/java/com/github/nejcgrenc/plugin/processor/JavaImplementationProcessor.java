package com.github.nejcgrenc.plugin.processor;

import com.github.nejcgrenc.plugin.model.DynamicProceduresGeneratorException;
import com.github.nejcgrenc.plugin.model.template.ClassData;
import com.github.nejcgrenc.plugin.model.template.FunctionData;
import com.github.nejcgrenc.plugin.processor.delegate.annotatedelement.JavaImplementationClassProcessor;
import com.github.nejcgrenc.plugin.processor.delegate.annotatedelement.JavaImplementationFunctionProcessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class JavaImplementationProcessor {

	public ClassData processClassAndMethods(TypeElement classElement, Set<ExecutableElement> methodElements) {
		verify(classElement, methodElements);

		JavaImplementationClassProcessor classProcessor = new JavaImplementationClassProcessor();
		ClassData classData = classProcessor.process(classElement);
		for (ExecutableElement methodElement : methodElements) {
			JavaImplementationFunctionProcessor functionProcessor = new JavaImplementationFunctionProcessor(classData);
			FunctionData functionData = functionProcessor.prepareSingleProcedure(methodElement);
			classData.addFunctionData(functionData);
		}
		return classData;
	}

	private void verify(TypeElement classElement, Set<ExecutableElement> methodElements) {
		for (Element classSubelement : classElement.getEnclosedElements()) {
			if (classSubelement.getKind() != ElementKind.METHOD) {
				throw new DynamicProceduresGeneratorException(
						"An unknown element found [" + classSubelement.getKind() + "] " + classSubelement.getSimpleName().toString());
			}
			if (! methodElements.contains(classSubelement)) {
				throw new DynamicProceduresGeneratorException(
						"Every method in the interface has to be annotated or default. " +
								"Un-annotated method found: " + classSubelement.getSimpleName().toString());
			}
		}
	}

}
