package com.github.nejcgrenc.plugin.model.template;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ClassData {

	private String packageName;
	private Set<String> imports = new HashSet<>();

	private String fullClassName;
	private String simpleClassName;
	private String implementedFullClassName;
	private String implementedSimpleClassName;

	private Set<FunctionData> functionDataSet = new HashSet<>();

	public void addImport(String singleImport) {
		this.imports.add(singleImport);
	}

	public void addFunctionData(FunctionData functionData) {
		this.functionDataSet.add(functionData);
	}
}
