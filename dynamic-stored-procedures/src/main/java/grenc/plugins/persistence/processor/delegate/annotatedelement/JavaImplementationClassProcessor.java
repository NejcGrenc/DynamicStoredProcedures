package grenc.plugins.persistence.processor.delegate.annotatedelement;

import grenc.plugins.persistence.model.template.ClassData;
import grenc.plugins.persistence.processor.delegate.JavaImplementationNameHandler;

import javax.lang.model.element.TypeElement;

public class JavaImplementationClassProcessor {

	protected static String implementedClassSuffix = "Impl";

	public ClassData process(TypeElement classElement) {
		String fullClassName = classElement.getQualifiedName().toString();

		String packageName = JavaImplementationNameHandler.packageNameFromFullName(fullClassName);
		String simpleClassName = JavaImplementationNameHandler.simpleNameFromFullName(fullClassName);

		String implementedClassName = fullClassName + implementedClassSuffix;
		String implementedSimpleClassName = JavaImplementationNameHandler.simpleNameFromFullName(implementedClassName);

		ClassData processedData = new ClassData();
		processedData.setPackageName(packageName);
		processedData.setFullClassName(fullClassName);
		processedData.setSimpleClassName(simpleClassName);
		processedData.setImplementedFullClassName(implementedClassName);
		processedData.setImplementedSimpleClassName(implementedSimpleClassName);
		return processedData;
	}
}
