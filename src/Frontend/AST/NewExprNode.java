package Frontend.AST;

import java.util.ArrayList;

import Util.Type;
import Util.position;

public class NewExprNode extends ExprNode {
    public ArrayList<ExprNode> args = null;

    public NewExprNode(Type newType, position pos) {
        super(pos);
        this.type = newType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return true;
    }
}
