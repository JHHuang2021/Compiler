package Frontend;

import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type;
import Util.globalScope;
import Util.position;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import AST.*;
import AST.unaryExprNode.unaryOpType;
import AST.varDefStmtNode.Var;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private globalScope gScope;

    public ASTBuilder(globalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));
        ctx.funcDef().forEach(fd -> root.funcDefs.add((FnNode) visit(fd)));
        ctx.classDef().forEach(cd -> root.classDefs.add((classDefNode) visit(cd)));
        return root;
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        ArrayList<Var> varList = new ArrayList<>();

        int varNum = ctx.Identifier().size();
        TypeNode type = (TypeNode) visit(ctx.type());
        for (int i = 0; i < varNum; i++) {
            String name = ctx.Identifier(i).toString();
            ExprNode expr = null;
            if (ctx.expression() != null)
                expr = (ExprNode) visit(ctx.expression(i));
            varList.add(new Var(type, name, expr));
        }

        return new varDefStmtNode(new position(ctx), varList);
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        classDefNode classDef = new classDefNode(new position(ctx), ctx.Identifier().toString());
        ctx.varDef().forEach(vd -> classDef.varDefs.add((varDefStmtNode) visit(vd)));
        return classDef;
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        suiteStmtNode node = new suiteStmtNode(new position(ctx));

        if (!ctx.statement().isEmpty()) {
            for (ParserRuleContext stmt : ctx.statement()) {
                StmtNode tmp = (StmtNode) visit(stmt);
                if (tmp != null)
                    node.stmts.add(tmp);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitVardefStmt(MxParser.VardefStmtContext ctx) {
        return visit(ctx.varDef());
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        suiteStmtNode thenSuite = null, elseSuite = null;
        ExprNode condition = (ExprNode) visit(ctx.expression());
        if (ctx.thensent.statement() != null)
            thenSuite = new suiteStmtNode((StmtNode) visit(ctx.thensent.statement()), new position(ctx));
        else
            thenSuite = new suiteStmtNode((StmtNode) visit(ctx.thensent.suite()), new position(ctx));
        if (ctx.elsesent != null && ctx.elsesent.statement() != null)
            elseSuite = new suiteStmtNode((StmtNode) visit(ctx.elsesent.statement()), new position(ctx));
        else if (ctx.elsesent != null && ctx.elsesent.statement() == null)
            elseSuite = new suiteStmtNode((StmtNode) visit(ctx.elsesent.suite()), new position(ctx));

        return new ifStmtNode(condition, thenSuite, elseSuite, new position(ctx));
    }

    @Override

    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        suiteStmtNode forSuite = null;
        ExprNode forexpr1 = null, forexpr2 = null, forexpr3 = null;
        if (ctx.forexpr1().varDef() != null)
            forexpr1 = (ExprNode) visit(ctx.forexpr1().varDef());
        else
            forexpr1 = (ExprNode) visit(ctx.forexpr1().expression());

        if (ctx.forexpr2 != null)
            forexpr2 = (ExprNode) visit(ctx.forexpr2);

        if (ctx.forexpr3 != null)
            forexpr3 = (ExprNode) visit(ctx.forexpr3);

        if (ctx.statement() != null)
            forSuite = new suiteStmtNode((StmtNode) visit(ctx.statement()), new position(ctx));
        else
            forSuite = new suiteStmtNode((StmtNode) visit(ctx.suite()), new position(ctx));

        return new forStmtNode(forexpr1, forexpr2, forexpr3, forSuite, new position(ctx));
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        suiteStmtNode whileSuite = null;
        ExprNode condition = (ExprNode) visit(ctx.expression());

        if (ctx.statement() != null)
            whileSuite = new suiteStmtNode((StmtNode) visit(ctx.statement()), new position(ctx));
        else
            whileSuite = new suiteStmtNode((StmtNode) visit(ctx.suite()), new position(ctx));

        return new whileStmtNode(condition, whileSuite, new position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ExprNode ret = null;

        if (ctx.expression() != null)
            ret = (ExprNode) visit(ctx.expression());

        return new returnStmtNode(ret, new position(ctx));
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new breakStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new continueStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitPureExprStmt(MxParser.PureExprStmtContext ctx) {
        return new exprStmtNode((ExprNode) visit(ctx.expression()), new position(ctx));
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        return new newExprNode((TypeNode) visit(ctx.typewitharg()), new position(ctx));
    }

    @Override
    public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
        ExprNode expr = (ExprNode) visit(ctx.expression());
        if (ctx.beforeop != null) {
            if (ctx.PlusPlus() != null)
                return new unaryExprNode(expr, unaryOpType.laddadd, new position(ctx));
            if (ctx.MinusMinus() != null)
                return new unaryExprNode(expr, unaryOpType.lsubsub, new position(ctx));
            if (ctx.Tilde() != null)
                return new unaryExprNode(expr, unaryOpType.tilde, new position(ctx));
            if (ctx.Minus() != null)
                return new unaryExprNode(expr, unaryOpType.neg, new position(ctx));
        } else {
            if (ctx.PlusPlus() != null)
                return new unaryExprNode(expr, unaryOpType.raddadd, new position(ctx));
            if (ctx.MinusMinus() != null)
                return new unaryExprNode(expr, unaryOpType.rsubsub, new position(ctx));
        }
        return null;
    }

    @Override
    public ASTNode visitFunctionCallExpr(MxParser.FunctionCallExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        return new assignExprNode(lhs, rhs, new position(ctx));
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.Identifier() != null) {
            if (ctx.expression().isEmpty())
                return new varExprNode(ctx.Identifier().toString(), new position(ctx.Identifier()));
            else {
                int dim = ctx.expression().size();
                ArrayList<ExprNode> dimArgs = new ArrayList<>();
                for (int i = 0; i < dim; i++)
                    dimArgs.add((ExprNode) visit(ctx.expression(i)));
                return new varExprNode(ctx.Identifier().toString(), dim, dimArgs, new position(ctx.Identifier()));
            }
        }
        if (ctx.This() != null)
            return new varExprNode(new position(ctx));
        if (ctx.expression() != null)
            return visit(ctx.expression(0));
        if (ctx.literal() != null)
            return visit(ctx.literal());
        return null;
    }

    @Override
    public ASTNode visitArgDef(MxParser.ArgDefContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitArg(MxParser.ArgContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFuncCall(MxParser.FuncCallContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        Type type = new Type();
        if (ctx.basicType() != null) {
            type.typeName = ctx.basicType().toString();
        } else {
            type.typeName = ctx.array().toString();
            type.array = true;
            type.dim = ctx.array().Dim().size();
        }
        return new TypeNode(new position(ctx), type);
    }

    @Override
    public ASTNode visitTypewitharg(MxParser.TypewithargContext ctx) {
        Type type = new Type();
        if (ctx.basicType() != null) {
            type.typeName = ctx.basicType().toString();
        } else {
            type.typeName = ctx.arraywitharg().toString();
            type.array = true;
            type.dim = ctx.arraywitharg().Int().size();
            for (int i = 0; i < type.dim; i++)
                type.dimArgs.add((ExprNode) visit(ctx.arraywitharg().Int(i)));
        }
        return new TypeNode(new position(ctx), type);
    }

    @Override
    public ASTNode visitBasicType(MxParser.BasicTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitArray(MxParser.ArrayContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBitExpr(MxParser.BitExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitLogicExpr(MxParser.LogicExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitArithExpr(MxParser.ArithExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitArraywitharg(MxParser.ArraywithargContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitLambda(MxParser.LambdaContext ctx) {
        return visitChildren(ctx);
    }

}