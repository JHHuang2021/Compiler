package Backend.IR.IRType;

public class IRString extends IRType {
    public int length;

    public IRString(int length) {
        super("string", 32);
        this.length = length;
    }

    public String ToString() {
        if (length == -1)
            return "i8*";
        else return "["+(length+1)+" x i8]";
    }

    @Override
    public IRType FatherType() {
        return this;
    }

}
