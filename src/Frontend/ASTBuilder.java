package Frontend;

import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type;
import Util.globalScope;
import Util.position;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import AST.*;
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
        Type type = new Type();
        if (ctx.type().basicType() != null) {
            type.typeName = ctx.type().basicType().toString();
        } else {
            type.typeName = ctx.type().array().toString();
            type.array = true;
        }
        int varNum = ctx.Identifier().size();
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
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitVardefStmt(MxParser.VardefStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitPureExprStmt(MxParser.PureExprStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
        return visitChildren(ctx);
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
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        return visitChildren(ctx);
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
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBasicType(MxParser.BasicTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitArray(MxParser.ArrayContext ctx) {
        return visitChildren(ctx);
    }

}