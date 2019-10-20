package grenc.plugins.persistence;

import grenc.plugins.persistence.annotation.DynamicProcedure;

public interface SimpleVoidProcedure {

	@DynamicProcedure
	void say_loudly(String value);

}
