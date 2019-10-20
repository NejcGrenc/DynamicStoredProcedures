package grenc.plugins.persistence;

import com.google.auto.service.AutoService;
import grenc.plugins.persistence.processor.DynamicProceduresFileCreator;
import grenc.plugins.persistence.processor.JavaImplementationProcessor;
import grenc.plugins.persistence.model.DynamicProceduresGeneratorException;
import grenc.plugins.persistence.model.procedure.ProcedureImplementationBuilder;
import grenc.plugins.persistence.model.template.ArgumentData;
import grenc.plugins.persistence.model.template.ClassData;
import grenc.plugins.persistence.model.template.FunctionData;
import grenc.plugins.persistence.processor.dto.AnnotatedElementsDto;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@SupportedAnnotationTypes("grenc.plugins.persistence.annotation.DynamicProcedure")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class DynamicProcedureAnnotationProcessor extends AbstractProcessor {

	public JavaImplementationProcessor javaImplementationProcessor = new JavaImplementationProcessor();

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		DynamicProceduresFileCreator fileCreator = new DynamicProceduresFileCreator(processingEnv.getFiler());

		AnnotatedElementsDto annotatedElementsDto = new AnnotatedElementsDto();
		for (TypeElement annotation : annotations) {

			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
			for (Element annotatedElement : annotatedElements) {

				if (annotatedElement.getKind() != ElementKind.METHOD) {
					throw new DynamicProceduresGeneratorException("Only methods can be annotated! " +
							"Found rogue annotation on an unknown element: " + annotatedElement.getSimpleName());
				}

				TypeElement classElement = (TypeElement) annotatedElement.getEnclosingElement();
				ExecutableElement methodElement = (ExecutableElement) annotatedElement;
				annotatedElementsDto.addMethodOfClass(classElement, methodElement);
			}

		}

		for (TypeElement classElement : annotatedElementsDto.getAnnotatedClassElementsMap().keySet()) {
			Set<ExecutableElement> methodSet = annotatedElementsDto.getAnnotatedClassElementsMap().get(classElement);
			ClassData classData = javaImplementationProcessor.processClassAndMethods(classElement, methodSet);

			ProcedureImplementationBuilder procedureImplementationBuilder = new ProcedureImplementationBuilder();
			String implementedFileAsString = procedureImplementationBuilder.implement(classData);
			fileCreator.writeFile(classData.getImplementedFullClassName(), implementedFileAsString);
		}

		return true;
	}

}