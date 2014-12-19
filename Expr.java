package a2;

// Expr is a Node that can be evaluated to a value of a certain type.
interface Expr extends Node {
	Type[] type();
}
