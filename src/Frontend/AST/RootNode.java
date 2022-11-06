package Frontend.AST;

import Util.position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
    public ArrayList<FunctionNode> func_defs;
    public ArrayList<ClassDefNode> class_defs;
    public ArrayList<VarDefStmtNode> var_defs;

    public RootNode(position pos) {
        super(pos);
        this.func_defs = new ArrayList<>();
        this.class_defs = new ArrayList<>();
        this.var_defs = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
