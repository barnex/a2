package a2;

public final class Compiler {

	static Node compile(Node n) throws Error {
		Scope s = new Scope();

		//s.declare("printi", new Func(new Type[]{Type.INT}, null));

		resolve(n, s);
		return n;
	}

	static void resolve(Node n, Scope s) throws Error {

		// TODO: local variables
		if (n instanceof Decl) {
			Decl d = (Decl)(n);
			resolve(n.children()[1], s); // resolve rhs before declaring lhs
			s.declare(d.ident().pos(), d.ident().name, new Var());
		}

		if (n instanceof BlockStmt) {
			Scope childScope = new Scope();
			childScope.parent = s;
			for(Node c: n.children()) {
				resolve(c, childScope);
			}
			return;
		}

		if (n instanceof Ident) {
			Ident ident = (Ident)(n);
			ident.sym = s.find(ident.name);
			if (ident.sym == null) {
				throw new Error(n.pos() + " undefined: " + ident.name);
			}
			return;
		}

		Node[] c = n.children();
		for(int i=0; i<c.length; i++) {
			resolve(c[i], s);
		}

	}


	// recursively simplify AST rooted at N
	static Node simplify(Node n) {
		Node[] c = n.children();
		for (int i=0; i<c.length; i++) {
			c[i] = simplify(c[i]);
		}
		return n.simplify();
	}
}
