package grenc.plugins.persistence;

import grenc.plugins.persistence.annotation.DynamicProcedure;
import grenc.plugins.persistence.annotation.DynamicProcedureArgument;

public interface AnnotationSpecifiedProcedure {

	@DynamicProcedure
	Integer sum_numbers(Integer a, Integer b);

	@DynamicProcedure(name = "sum_numbers")
	Integer sum(Integer a, Integer b);

	@DynamicProcedure(name = "sum_numbers")
	Integer sum_arguments(@DynamicProcedureArgument(name = "a") Integer param1, Integer b);

	@DynamicProcedure(name = "sum_numbers")
	Integer sum_arguments_withClass(@DynamicProcedureArgument(name = "a", type = Integer.class) int param1, Integer b);
}
