DROP ALIAS IF EXISTS NEXT_PRIME;
CREATE ALIAS NEXT_PRIME AS $$
	Integer nextPrime(Integer value) {
		return BigInteger.valueOf(value).nextProbablePrime().intValue();
	}
$$;

DROP ALIAS IF EXISTS SUM_NUMBERS;
CREATE ALIAS SUM_NUMBERS AS $$
	int sum(int a, int b) {
		return a + b;
	}
$$;

DROP ALIAS IF EXISTS SAY_LOUDLY;
CREATE ALIAS SAY_LOUDLY AS $$
	void loud(String text) {
		System.out.println(text + "!");
	}
$$;

DROP ALIAS IF EXISTS HELLO;
CREATE ALIAS HELLO AS $$
	String hello() {
		return "Hello World";
	}
$$;

DROP ALIAS IF EXISTS NUMBER_THREE;
CREATE ALIAS NUMBER_THREE AS $$
	int numberThree() {
		return 3;
	}
$$;
