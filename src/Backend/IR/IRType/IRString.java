package Backend.IR.IRType;

public class IRString extends IRType {
    public int length;

    public IRString(int length) {
        super("", 32);
        this.length = length;
    }

    public String ToString() {
        return "i8*";
    }

    @Override
    public IRType FatherType() {
        return this;
    }

}
