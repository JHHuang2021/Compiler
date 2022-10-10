package AST;

import Util.position;
import java.util.ArrayList;

public class funcCallExprNode extends ExprNode {
    public String name;
    public ArrayList<ExprNode> args;

    public funcCallExprNode(position pos, String name) {
        super(pos);
        this.name = name;
        args = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
