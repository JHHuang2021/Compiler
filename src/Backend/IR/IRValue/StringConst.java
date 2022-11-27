package Backend.IR.IRValue;

import Backend.IR.IRType.IRType;

public class StringConst extends IRValue {
    public String s;
    public int ind;

    public StringConst(IRType type, String s, int ind) {
        super(type);
        this.s = s;
        this.ind = ind;
    }

    @Override
    public String ToString() {
        return "@str_" + ind;
    }

    public String Declare() {
        String str = "@str_" + ind + " = private unnamed_addr constant ";
        str += "[" + (s.length() + 1) + " x " + "i8" + ']' + " c\"" + s + "\\00\"";
        return str;
    }
}
