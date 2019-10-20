package grenc.plugins.persistence.processor.delegate;

public class JavaImplementationNameHandler {

	public static String packageNameFromFullName(String fullName) {
		int lastDot = fullName.lastIndexOf('.');
		return (lastDot > 0) ? fullName.substring(0, lastDot) : null;
	}

	public static String simpleNameFromFullName(String fullName) {
		int lastDot = fullName.lastIndexOf('.');
		return fullName.substring(lastDot + 1);
	}
}
