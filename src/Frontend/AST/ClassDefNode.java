package Frontend.AST;

import Util.position;

import java.util.ArrayList;

public class ClassDefNode extends ASTNode {
    public ArrayList<VarDefStmtNode> var_defs = new ArrayList<>();
    public ArrayList<FunctionNode> func_defs = new ArrayList<>();
    public String name;

    public ClassDefNode(position pos, String name) {
        super(pos);
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}