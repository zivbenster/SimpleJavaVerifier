/**
 * Created by rooty on 6/11/15.
 */
package dataStructures.vars;

import dataStructures.vars.exceptions.IllegalAssignmentException;

/**
 * Object that represents an object created in the sjava file given and represents its attributes.
 * A VariableObject may only be of one of the legal types in sjava.
 */
public class VariableObject {

	private final String name;
	private final String type;
	private String value;
	private boolean isFinal = false; // will change if given in constructor
	/**
	 * Constructor one - gets all params for creation of initialized variable.
	 *
	 * @param name
	 * @param type
	 * @param value
	 * @param isFinal
	 */
	public VariableObject(String name, String type, String value, Boolean isFinal)
			throws IllegalAssignmentException {
		this.name = name;
		this.type = type;
		this.value = value;
		this.isFinal = isFinal;

		if (!checkLegalValue()) {
			throw new IllegalAssignmentException(name);
		}
	}


	/**
	 * Constructor two - gets all but Value param - creates an uninitialized variable.
	 *
	 * @param name
	 * @param type
	 */
	public VariableObject(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Checks that the value string given to the constructor matches the legal patterns of the known types.
	 *
	 * @return true if the pattern of the given value matches a known types value pattern.
	 */
	private boolean checkLegalValue() {
		if (this.value == null) {
			return true;
		}
		for (VarTypeAndValue item : VarTypeAndValue.values()) {
			if (this.type.equals(item.getType())) { // validate that the type will match the pattern
				if (this.value.matches(item.getPattern())) {
					return true;
				} else return false;
			}
		}
		return false;
	}

	/**
	 * Gets value.
	 *
	 * @return Value of value.
	 */
	public String getValue() {
		return value;
	}


	//################ getters for all fields + setter for new value ###################

	/**
	 * Sets new value.
	 *
	 * @param value New value of value.
	 */
	public void setValue(String value) throws IllegalAssignmentException {

		this.value = value;


		if (!checkLegalValue()) {
			throw new IllegalAssignmentException(name);
		}
	}

	/**
	 * Gets type.
	 *
	 * @return Value of type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets name.
	 *
	 * @return Value of name.
	 */

	public String getName() {
		return name;
	}

	/**
	 * Get if variable is final
	 *
	 * @return true if final, else false.
	 */
	public boolean isFinal() {
		return isFinal;
	}

	public boolean equals(Object obj) {
		return (((VariableObject)obj).getName().equals(name));
	}


	//##################################################################################


	/**
	 * enumerator class nested in VariableObject class.
	 * Holds the legal types and patterns of values that are allowed to be made in VariableObject.
	 */
	public enum VarTypeAndValue {
		INT("int", "-?[0-9]+ *"),
		DOUBLE("double", "-?[0-9]+(\\.[0-9]+)? *"),
		STRING("String", "\".*\" *"),
		CHAR("char", "\'.\' *"),
		BOOLEAN("boolean", "((true|false)|(-?[0-9]+(\\.[0-9]+)?)) *");

		private String type;
		private String pattern;

		//Constructor
		VarTypeAndValue(String type, String pattern) {
			this.type = type;
			this.pattern = pattern;
		}

		/**
		 * Gets the type.
		 *
		 * @return the type of the value
		 */
		public String getType() {
			return type;
		}

		/**
		 * Gets the pattern.
		 *
		 * @return the pattern of the value
		 */
		public String getPattern() {
			return pattern;
		}
	}
}