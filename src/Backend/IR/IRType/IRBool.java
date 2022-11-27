package Backend.IR.IRType;

public class IRBool extends IRType {

    public IRBool() {
        super("bool", 1);
    }

    @Override
    public IRType FatherType() {
        return this;
    }

}
