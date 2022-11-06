package Frontend.AST;

import Util.position;
import java.util.ArrayList;

public class FuncCallExprNode extends ExprNode {
    public String name;
    public ArrayList<ExprNode> args;

    public FuncCallExprNode(position pos, String name) {
        super(pos);
        this.name = name;
        this.args = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
