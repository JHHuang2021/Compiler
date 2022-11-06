package Frontend.AST;

import java.util.ArrayList;

import Util.position;

public class LambdaExprNode extends ExprNode {
    public ArrayList<VarDefStmtNode> args_def = new ArrayList<>();
    public ArrayList<ExprNode> args = new ArrayList<>();
    public ArrayList<StmtNode> stmts=new ArrayList<>();
    public boolean if_global_scope = false;

    public LambdaExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
