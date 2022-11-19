package Frontend.AST;

import Backend.IR.IRValue.IRValue;
import Util.Type;
import Util.position;

public abstract class ExprNode extends ASTNode {
    public Type type;
    public IRValue val = null;
    // public entity val;

    public ExprNode(position pos) {
        super(pos);
    }

    public boolean isAssignable() {
        return false;
    }

    public boolean IfThis() {
        return false;
    }
}
