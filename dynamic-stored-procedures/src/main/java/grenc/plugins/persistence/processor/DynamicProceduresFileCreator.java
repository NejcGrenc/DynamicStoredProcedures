package grenc.plugins.persistence.processor;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;

public class DynamicProceduresFileCreator {

	private Filer processingEnvironmentFiler;

	public DynamicProceduresFileCreator(Filer processingEnvironmentFiler) {
		this.processingEnvironmentFiler = processingEnvironmentFiler;
	}

	public void writeFile(String fullFileName, String fileContent) {
		try {
			JavaFileObject implementedFile = processingEnvironmentFiler.createSourceFile(fullFileName);
			try (PrintWriter out = new PrintWriter(implementedFile.openWriter())) {
				out.println(fileContent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
