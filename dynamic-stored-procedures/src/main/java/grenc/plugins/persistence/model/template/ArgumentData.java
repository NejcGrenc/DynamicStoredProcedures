package grenc.plugins.persistence.model.template;

import lombok.Data;
import lombok.ToString;

@Data
public class ArgumentData {

	private String argumentName;
	private String argumentType;

	private String fieldName;
	private String fieldType;


	public String argumentDetails() {
		return fieldType + " " + fieldName;
	}
}
