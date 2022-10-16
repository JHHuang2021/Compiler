package Frontend;

import java.util.HashMap;

import AST.*;
import AST.varDefStmtNode.Var;
import Util.Func;
import Util.Scope;
import Util.Type;
import Util.error.semanticError;
import Util.globalScope;
import Util.Scope.ScopeType;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private globalScope gScope;
    private Type currentClass = null;
    private ScopeType nxtScopeType = null;

    public SemanticChecker(globalScope gScope) {
        currentScope = this.gScope = gScope;
    }

    @Override
    public void visit(RootNode it) {
        it.classDefs.forEach(cd -> cd.accept(this));
        it.varDefs.forEach(vd -> vd.accept(this));
        it.funcDefs.forEach(fd -> fd.accept(this));
        // we SHOULD check struct definitions first
    }

    @Override
    public void visit(classDefNode it) {
        // gScope.addType(it.name, new Type(it.name), it.pos);
        currentClass = gScope.getTypeFromName(it.name, it.pos);
        it.varDefs.forEach(vd -> vd.accept(this));
        it.funcDefs.forEach(fd -> fd.accept(this));
        currentClass = null;
    }

    @Override
    public void visit(FnNode it) {
        if (currentScope != gScope)
            throw new semanticError("wrong position of function definition", it.pos);
        if (currentScope.contains(it.funcName, true))
            throw new semanticError("redefinition of function " + it.funcName, it.pos);
        gScope.defineFunc(it.funcName, it.retType.GetType(), it.argsDef, it.pos);
        nxtScopeType = ScopeType.FUNC;
        currentScope = new Scope(currentScope, ScopeType.FUNC);
        currentScope.returnType = it.retType.GetType();
        for (varDefStmtNode node : it.argsDef)
            node.accept(this);
        for (StmtNode stmt : it.stmts)
            stmt.accept(this);
        nxtScopeType = null;
        currentScope = gScope;
    }

    @Override
    public void visit(varDefStmtNode it) {
        if (currentClass != null) {
            if (currentClass.members == null)
                currentClass.members = new HashMap<>();
            for (Var var : it.var) {
                if (currentClass.members.containsKey(var.name))
                    throw new semanticError("redefinition of member " + var.name, it.pos);
                currentClass.members.put(var.name, gScope.getTypeFromName(var.type.ToString(),
                        it.pos));
                // if (var.init != null)
                // throw new semanticError("Yx does not support default init of members",
                // var.init.pos);
            }
            return;
        }

        // if (it.init != null) {
        for (Var var : it.var) {
            if (var.init != null)
                var.init.accept(this);
            if (var.init != null && !var.init.type.Equal(var.type))
                throw new semanticError("Semantic Error: type not match.",
                        var.init.pos);
            // }
            currentScope.define(var.name, gScope.getTypeFromName(var.type.ToString(),
                    it.pos), it.pos);
        }
    }

    @Override
    public void visit(returnStmtNode it) {
        if (currentScope == null || !currentScope.containScopeType(ScopeType.FUNC, true))
            throw new semanticError("Semantic Error: return doesn't exist in function.", it.pos);
        if (it.value != null) {
            it.value.accept(this);
            if (it.value.type.Equal(currentScope.returnType))
                throw new semanticError("Semantic Error: type not match.",
                        it.value.pos);
        } else if (currentScope.returnType.typeName != "void")
            throw new semanticError("Semantic Error: type not match.",
                    it.value.pos);
    }

    @Override
    public void visit(suiteStmtNode it) {
        currentScope.scopeType = nxtScopeType;
        if (!it.stmts.isEmpty()) {
            currentScope = new Scope(currentScope, nxtScopeType);
            for (StmtNode stmt : it.stmts)
                stmt.accept(this);
            currentScope = currentScope.parentScope();
        }
    }

    @Override
    public void visit(exprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(ifStmtNode it) {
        nxtScopeType = ScopeType.IF;
        it.condition.accept(this);
        if (it.condition.type.ToString() != "bool")
            throw new semanticError("Semantic Error: type not match. It should be bool",
                    it.condition.pos);
        it.thenSuite.accept(this);
        if (it.elseSuite != null)
            it.elseSuite.accept(this);
        nxtScopeType = null;
    }

    @Override
    public void visit(assignExprNode it) {
        it.rhs.accept(this);
        it.lhs.accept(this);
        if (!it.rhs.type.Equal(it.lhs.type))
            throw new semanticError("Semantic Error: type not match. ", it.pos);
        if (!it.lhs.isAssignable())
            throw new semanticError("Semantic Error: not assignable", it.lhs.pos);
        it.type = it.rhs.type;
    }

    @Override
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.rhs.type.Equal(it.lhs.type))
            throw new semanticError("Semantic error: type not match. It should be int",
                    it.lhs.pos);
    }

    @Override
    public <T> void visit(constExprNode<T> it) {
        String typeName = null;
        switch (it.value.getClass().getName()) {
            case "java.lang.String":
                typeName = "string";
                break;
            case "java.lang.Integer":
                typeName = "int";
                break;
            case "java.lang.Boolean":
                typeName = "bool";
                break;
        }
        it.type = new TypeNode(it.pos, new Type(typeName));
    }

    // @Override
    // public void visit(cmpExprNode it) {
    // it.lhs.accept(this);
    // it.rhs.accept(this);
    // if (it.rhs.type != it.lhs.type)
    // throw new semanticError("Semantic Error: type not match. ", it.pos);
    // }

    @Override
    public void visit(varExprNode it) {
        if (!currentScope.contains(it.var.get(0).name, true))
            throw new semanticError("Semantic Error: variable not defined. ", it.pos);
        currentClass = currentScope.getType(it.var.get(0).name, true);
        for (int i = 1; i < it.var.size(); i++) {
            currentClass = currentClass.containsVarible(it.var.get(i).name);
            if (currentClass == null)
                throw new semanticError("Semantic Error: variable not defined", it.pos);
        }
        it.type = new TypeNode(it.pos, currentClass);
        currentClass = null;
    }

    @Override
    public void visit(forStmtNode it) {
        nxtScopeType = ScopeType.FOR;
        it.var.accept(this);
        it.step.accept(this);
        it.condition.accept(this);
        if (it.condition.type.ToString() != "bool")
            throw new semanticError("Semantic Error: type not match. It should be bool",
                    it.condition.pos);
        it.forSuite.accept(this);
        nxtScopeType = null;
    }

    @Override
    public void visit(whileStmtNode it) {
        nxtScopeType = ScopeType.WHILE;
        it.condition.accept(this);
        if (it.condition.type.ToString() != "bool")
            throw new semanticError("Semantic Error: type not match. It should be bool",
                    it.condition.pos);
        it.whileSuite.accept(this);
        nxtScopeType = null;
    }

    @Override
    public void visit(breakStmtNode it) {
        if (currentScope == null || (!currentScope.containScopeType(ScopeType.FOR, true)
                && !currentScope.containScopeType(ScopeType.WHILE, true)))
            throw new semanticError("Semantic Error: break doesn't exist in loop.", it.pos);
    }

    @Override
    public void visit(continueStmtNode it) {
        if (currentScope == null || (!currentScope.containScopeType(ScopeType.FOR, true)
                && !currentScope.containScopeType(ScopeType.WHILE, true)))
            throw new semanticError("Semantic Error: break doesn't exist in loop.", it.pos);
    }

    @Override
    public void visit(newExprNode it) {
    }

    @Override
    public void visit(unaryExprNode it) {
        if (it.type.ToString() != "int")
            throw new semanticError("Semantic Error: type not match. It should be int",
                    it.pos);
    }

    @Override
    public void visit(logicExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type.ToString() != "bool" || (it.rhs != null && it.rhs.type.ToString() != "bool"))
            throw new semanticError("Semantic Error: type not match. ", it.pos);
    }

    @Override
    public void visit(funcCallExprNode it) {
        Func calledFunc = gScope.containFunc(it.name, it.pos);
        if (it.args.size() != calledFunc.args.size())
            throw new semanticError("Semantic Error: function " + it.name + " requires " + calledFunc.args.size()
                    + " but has " + it.args.size(), it.pos);
        for (int i = 0; i < it.args.size(); i++) {
            it.args.get(i).accept(this);
            if (!it.args.get(i).type.Equal(calledFunc.args.get(i).var.get(0).type))
                throw new semanticError("Semantic Error: type not match", it.pos);
        }
        it.type = new TypeNode(it.pos, currentScope.getType(it.name, true));
    }

    @Override
    public void visit(thisExprNode it) {

    }

    @Override
    public void visit(TypeNode typeNode) {

    }
}
