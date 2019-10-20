package com.github.nejcgrenc.plugin;

import com.google.auto.service.AutoService;
import com.github.nejcgrenc.plugin.model.DynamicProceduresGeneratorException;
import com.github.nejcgrenc.plugin.model.procedure.ProcedureImplementationBuilder;
import com.github.nejcgrenc.plugin.model.template.ClassData;
import com.github.nejcgrenc.plugin.processor.DynamicProceduresFileCreator;
import com.github.nejcgrenc.plugin.processor.JavaImplementationProcessor;
import com.github.nejcgrenc.plugin.processor.dto.AnnotatedElementsDto;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;


@SupportedAnnotationTypes("com.github.nejcgrenc.plugin.annotation.DynamicProcedure")
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