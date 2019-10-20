package com.github.nejcgrenc.plugin;

import com.github.nejcgrenc.plugin.annotation.DynamicProcedure;

public interface SimpleProcedure {

	@DynamicProcedure
	Integer next_prime(Integer value);

	@DynamicProcedure
	Integer sum_numbers(Integer a, Integer b);

}
