package a2;

class Func implements Symbol{
	Type[] ins, outs;

	Func(Type[] ins, Type[] outs){
		if (ins == null){
			ins = new Type[0];
		}
		if (outs == null){
			outs = new Type[0];
		}
		this.ins = ins;
		this.outs = outs;
	}
}
