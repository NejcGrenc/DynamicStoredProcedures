package grenc.plugins.persistence;

import grenc.plugins.persistence.annotation.DynamicProcedure;

public interface SimpleProcedure {

	@DynamicProcedure
	Integer next_prime(Integer value);

	@DynamicProcedure
	Integer sum_numbers(Integer a, Integer b);

}
