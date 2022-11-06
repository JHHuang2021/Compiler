package Frontend.AST;

import Util.Type;
import Util.position;

import java.util.ArrayList;

public class FunctionNode extends ASTNode {
    public ArrayList<StmtNode> stmts;
    public ArrayList<VarDefStmtNode> args_def;
    public Type ret_type;
    public String func_name;

    public FunctionNode(position pos, Type retType, String funcName) {
        super(pos);
        stmts = new ArrayList<>();
        args_def = new ArrayList<>();
        this.ret_type = retType;
        this.func_name = funcName;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
