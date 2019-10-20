package com.github.nejcgrenc.plugin.processor.dto;

import lombok.Data;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class AnnotatedElementsDto {

	private Map<TypeElement, Set<ExecutableElement>> annotatedClassElementsMap = new HashMap<>();

	public void addMethodOfClass(TypeElement classElement, ExecutableElement methodElement) {
		if (! annotatedClassElementsMap. containsKey(classElement)) {
			annotatedClassElementsMap.put(classElement, new HashSet<>());
		}
		annotatedClassElementsMap.get(classElement).add(methodElement);
	}
}
