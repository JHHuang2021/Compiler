package Backend.IR.IRType;

public class IRVoid extends IRType {

    public IRVoid() {
        super("void", 0);
    }

    @Override
    public IRType FatherType() {
        return this;
    }

}
