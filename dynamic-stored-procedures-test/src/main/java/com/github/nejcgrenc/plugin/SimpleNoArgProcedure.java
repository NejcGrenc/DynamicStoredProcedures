package com.github.nejcgrenc.plugin;

import com.github.nejcgrenc.plugin.annotation.DynamicProcedure;

public interface SimpleNoArgProcedure {

	@DynamicProcedure
	String hello();

	@DynamicProcedure
	int number_three();
}
