package a2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

// Back-end that outputs java source.
class JBackend {
	PrintStream out;

	public static void generate(String fname, Node ast) throws IOException {
		JBackend b = new JBackend();
		File f = new File(fname + ".java");
		b.out = new PrintStream(new FileOutputStream(f));

		b.println("class", fname, "{");
		b.putNode(ast);
		b.println("}");
	}

	void println(String...x) {
		print(x);
		out.println();
	}

	void print(String...x) {
		for(String s:x) {
			out.print(s);
		}
	}

	void putNode(Node n) {
		if(n instanceof StmtList) {
			putStmtList((StmtList)(n));
		}
		System.err.println(n.pos() + " :unhandled node");
		System.exit(4);
	}

	void putStmtList(StmtList l) {
		for(Node c: l.children()) {
			putNode(c);
		}
	}
}
