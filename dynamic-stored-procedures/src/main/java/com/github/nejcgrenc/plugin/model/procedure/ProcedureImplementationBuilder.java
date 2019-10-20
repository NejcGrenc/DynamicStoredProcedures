package com.github.nejcgrenc.plugin.model.procedure;

import com.github.nejcgrenc.plugin.model.template.ClassData;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class ProcedureImplementationBuilder {

	protected static String templateName = "template/procedure.vm";

	private Template template;

	public ProcedureImplementationBuilder(VelocityEngine velocityEngine) {
		this.template = velocityEngine.getTemplate(templateName);
	}

	public ProcedureImplementationBuilder() {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("resource.loader", "class");
		velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngine.init();

		this.template = velocityEngine.getTemplate(templateName);
	}


	public String implement(ClassData classData) {

		System.out.println("[ProcedureImplementationBuilder] Implementing: " + classData.getSimpleClassName());
		VelocityContext context = new VelocityContext();

		if(classData.getPackageName() != null) {
			context.put("packagename", classData.getPackageName());
		}
		context.put("imports", classData.getImports());
		context.put("className", classData.getImplementedSimpleClassName());
		context.put("interfaceName", classData.getSimpleClassName());

		context.put("functions", classData.getFunctionDataSet());

		StringWriter writer = new StringWriter();
		template.merge( context, writer );

		return writer.toString();
	}
}
