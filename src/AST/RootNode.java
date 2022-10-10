package AST;

import Util.position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
    public ArrayList<FnNode> funcDefs;
    public ArrayList<classDefNode> classDefs;

    public RootNode(position pos) {
        super(pos);
        this.funcDefs = new ArrayList<>();
        this.classDefs = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
