package grenc.plugins.persistence.processor.delegate;

import grenc.plugins.persistence.model.template.ClassData;
import grenc.plugins.persistence.processor.delegate.annotatedelement.JavaImplementationClassProcessor;
import org.junit.Test;

import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class JavaImplementationClassProcessorTest {

	private JavaImplementationClassProcessor processor = new JavaImplementationClassProcessor();

	@Test
	public void shouldProcessStandardClassData() {
		TypeElement classElement = mockTypeElement("com.best.code.Sampler");

		ClassData classData = processor.process(classElement);

		assertEquals("com.best.code", classData.getPackageName());
		assertEquals("com.best.code.Sampler", classData.getFullClassName());
		assertEquals("Sampler", classData.getSimpleClassName());

		assertEquals("com.best.code.SamplerImpl", classData.getImplementedFullClassName());
		assertEquals("SamplerImpl", classData.getImplementedSimpleClassName());
	}

	@Test
	public void shouldProcessDefaultPackageClassData() {
		TypeElement classElement = mockTypeElement("Sampler");

		ClassData classData = processor.process(classElement);

		assertEquals(null, classData.getPackageName());
		assertEquals("Sampler", classData.getFullClassName());
		assertEquals("Sampler", classData.getSimpleClassName());

		assertEquals("SamplerImpl", classData.getImplementedFullClassName());
		assertEquals("SamplerImpl", classData.getImplementedSimpleClassName());
	}


	private TypeElement mockTypeElement(String fullClassName) {
		Name mockName = mock(Name.class);
		doReturn(fullClassName).when(mockName).toString();

		TypeElement typeElementMock = mock(TypeElement.class);
		doReturn(mockName).when(typeElementMock).getQualifiedName();
		return typeElementMock;
	}

}