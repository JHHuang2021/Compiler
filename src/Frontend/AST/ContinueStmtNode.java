package Frontend.AST;
import Util.position;
public class ContinueStmtNode extends StmtNode{
    public ContinueStmtNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
