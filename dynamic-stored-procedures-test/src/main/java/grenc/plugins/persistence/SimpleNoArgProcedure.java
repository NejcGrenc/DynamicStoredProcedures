package grenc.plugins.persistence;

import grenc.plugins.persistence.annotation.DynamicProcedure;

public interface SimpleNoArgProcedure {

	@DynamicProcedure
	String hello();

	@DynamicProcedure
	int number_three();
}
