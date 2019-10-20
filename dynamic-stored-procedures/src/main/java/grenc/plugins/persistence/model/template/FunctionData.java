package grenc.plugins.persistence.model.template;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FunctionData {

	private String procedureName;

	private String functionAccess;
	private String functionReturnType;
	private String functionName;
	private List<ArgumentData> functionArguments = new ArrayList<>();

	public String combinedArgumentsString() {
		return functionArguments.stream().map(ArgumentData::argumentDetails).collect(Collectors.joining(", "));
	}
}
