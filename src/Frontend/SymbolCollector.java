package Frontend;

import AST.*;
import Util.Type;
import Util.globalScope;

import java.util.HashMap;

public class SymbolCollector implements ASTVisitor {
    private globalScope gScope;

    public SymbolCollector(globalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public void visit(RootNode it) {
        // it.strDefs.forEach(sd -> sd.accept(this));
        it.classDefs.forEach(cd -> cd.accept(this));
    }

    @Override
    public void visit(classDefNode it) {
        Type newClass = new Type();
        newClass.members = new HashMap<>();
        it.varDefs.forEach(vd -> vd.accept(this));
        gScope.addType(it.name, newClass, it.pos);
    }

    @Override
    public void visit(FnNode it) {
    }

    @Override
    public void visit(varDefStmtNode it) {
    }

    @Override
    public void visit(returnStmtNode it) {
    }

    @Override
    public void visit(suiteStmtNode it) {
    }

    @Override
    public void visit(exprStmtNode it) {
    }

    @Override
    public void visit(ifStmtNode it) {
    }

    @Override
    public void visit(assignExprNode it) {
    }

    @Override
    public void visit(binaryExprNode it) {
    }

    @Override
    public <T> void visit(constExprNode<T> it) {
    }

    // @Override public void visit(cmpExprNode it) {}
    @Override
    public void visit(varExprNode it) {
    }

    @Override
    public void visit(forStmtNode it) {

    }

    @Override
    public void visit(whileStmtNode it) {

    }

    @Override
    public void visit(breakStmtNode it) {

    }

    @Override
    public void visit(continueStmtNode it) {

    }

    @Override
    public void visit(newExprNode it) {

    }

    @Override
    public void visit(unaryExprNode it) {

    }

    @Override
    public void visit(logicExprNode it) {

    }

    @Override
    public void visit(funcCallExprNode it) {

    }

    @Override
    public void visit(thisExprNode it) {

    }

    @Override
    public void visit(TypeNode typeNode) {

    }
}
