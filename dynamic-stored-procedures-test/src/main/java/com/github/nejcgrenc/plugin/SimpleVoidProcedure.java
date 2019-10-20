package com.github.nejcgrenc.plugin;

import com.github.nejcgrenc.plugin.annotation.DynamicProcedure;

public interface SimpleVoidProcedure {

	@DynamicProcedure
	void say_loudly(String value);

}
