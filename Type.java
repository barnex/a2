package a2;

final class Type {

	String name;

	static final Type INT = new Type("int");
	static final Type STRING = new Type("string");

	Type(String name) {
		this.name = name;
	}

}
