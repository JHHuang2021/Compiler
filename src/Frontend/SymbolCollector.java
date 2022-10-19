package Frontend;

import AST.*;
import Util.Type;
import Util.globalScope;
import Util.error.semanticError;

import java.util.HashMap;

public class SymbolCollector implements ASTVisitor {
    private globalScope gScope;
    private Type currentClass = null;

    public SymbolCollector(globalScope gScope) {
        this.gScope = gScope;

    }

    @Override
    public void visit(RootNode it) {
        // it.strDefs.forEach(sd -> sd.accept(this));
        // it.classDefs.forEach(cd -> cd.accept(this));
        // for (int i = 0; i < it.varDefs.size(); i++)
        //     it.varDefs.get(i).accept(this);
        for (int i = 0; i < it.classDefs.size(); i++)
            it.classDefs.get(i).accept(this);
        for (int i = 0; i < it.funcDefs.size(); i++)
            it.funcDefs.get(i).accept(this);
    }

    @Override
    public void visit(classDefNode it) {
        Type newClass = new Type(it.name);
        newClass.members = new HashMap<>();
        currentClass = newClass;
        for (int i = 0; i < it.varDefs.size(); i++)
            it.varDefs.get(i).accept(this);
        for (int i = 0; i < it.funcDefs.size(); i++)
            it.funcDefs.get(i).accept(this);
        currentClass = null;
        gScope.addType(it.name, newClass, it.pos);
    }

    @Override
    public void visit(FnNode it) {
        if (currentClass != null) {
            if (currentClass.funcs == null)
                currentClass.funcs = new HashMap<>();
            if (currentClass.containFunc(it.funcName, it.pos) != null)
                throw new semanticError("redefinition of function " + it.funcName, it.pos);
            if (it.retType == null)
                it.retType = new TypeNode(it.pos, new Type("Create"));
            currentClass.defineFunc(it.funcName, it.retType.GetType(), it.argsDef, it.pos);
            return;
        }
        if (gScope.containFunc(it.funcName, it.pos) != null || gScope.containType(it.funcName, it.pos) != null)
            throw new semanticError("redefinition of function " + it.funcName, it.pos);
        gScope.defineFunc(it.funcName, it.retType.GetType(), it.argsDef, it.pos);
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

    @Override
    public void visit(visitExprNode it) {

    }
}
