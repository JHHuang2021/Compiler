package Frontend;

import AST.*;
import AST.varDefStmtNode.Var;
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
        currentScope.define(it.funcName, it.retType.GetType(), it.pos);
        nxtScopeType = ScopeType.FUNC;
        currentScope = new Scope(currentScope);
        for (varDefStmtNode node : it.argsDef)
            node.accept(this);
        for (StmtNode stmt : it.stmts)
            stmt.accept(this);
        currentScope.returnType = it.retType.GetType();
        nxtScopeType = null;
    }

    @Override
    public void visit(varDefStmtNode it) {
        if (currentClass != null) {
            assert (currentClass.members != null);
            for (Var var : it.var) {
                if (currentClass.members.containsKey(var.name))
                    throw new semanticError("redefinition of member " + var.name, it.pos);
                currentClass.members.put(var.name, gScope.getTypeFromName(var.type.ToString(),
                        it.pos));
                // if (var.init != null)
                // throw new semanticError("Yx does not support default init of members",
                // var.init.pos);
            }
        }

        // if (it.init != null) {
        for (Var var : it.var) {
            var.init.accept(this);
            if (var.init != null && var.init.type != var.type)
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
            if (it.value.type.GetType() != currentScope.returnType)
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
            currentScope = new Scope(currentScope);
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
        if (it.rhs.type != it.lhs.type)
            throw new semanticError("Semantic Error: type not match. ", it.pos);
        if (!it.lhs.isAssignable())
            throw new semanticError("Semantic Error: not assignable", it.lhs.pos);
        it.type = it.rhs.type;
    }

    @Override
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type != it.rhs.type)
            throw new semanticError("Semantic error: type not match. It should be int",
                    it.lhs.pos);
    }

    @Override
    public <T> void visit(constExprNode<T> it) {
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
        if (!currentScope.contains(it.name, true))
            throw new semanticError("Semantic Error: variable not defined. ", it.pos);
        it.type = new TypeNode(it.pos, currentScope.getType(it.name, true));
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
        if (!currentScope.contains(it.name, true))
            throw new semanticError("Semantic Error: variable not defined. ", it.pos);
        it.type = new TypeNode(it.pos, currentScope.getType(it.name, true));
    }

    @Override
    public void visit(thisExprNode it) {

    }

    @Override
    public void visit(TypeNode typeNode) {

    }
}
