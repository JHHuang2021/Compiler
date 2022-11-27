package Backend.IR.IRType;

public class IRInt extends IRType {

    public IRInt() {
        super("int", 32);
    }

    @Override
    public IRType FatherType() {
        return this;
    }

}
