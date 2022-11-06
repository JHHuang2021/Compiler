// package Backend;

// import AST.*;
// import MIR.*;
// import Util.Scope;
// import Util.globalScope;

// // import static MIR.binary.opType.add;
// // import static MIR.binary.opType.sub;
// // import static MIR.binary.opType.eq;
// // import static MIR.binary.opType.ne;

// public class IRBuilder implements ASTVisitor {
//     private BasicBlock currentBlock = null;
//     private Function currentFunc = null;
//     private Scope currentScope;
//     private globalScope gScope;

//     public IRBuilder(globalScope gScope) {
//         currentScope = gScope;
//         this.gScope = gScope;
//     }

//     @Override
//     public void visit(RootNode it) {
//     }

//     // @Override public void visit(structDefNode it) {

//     // }

//     // @Override
//     // public void visit(FnRootNode it) {
//     // currentScope = new Scope(currentScope);
//     // it.stmts.forEach(s -> s.accept(this));
//     // currentScope = currentScope.parentScope();
//     // }

//     @Override
//     public void visit(varDefStmtNode it) {
//         // currentScope.entities.put(it.name, new register());
//     }

//     @Override
//     public void visit(returnStmtNode it) {
//         // entity value;
//         // if (it.value != null) {
//         // it.value.accept(this);
//         // value = it.value.val;
//         // } else value = null;
//         // currentBlock.push_back(new ret(value));
//     }

//     // @Override
//     // public void visit(blockStmtNode it) {
//     // currentScope = new Scope(currentScope);
//     // }

//     @Override
//     public void visit(exprStmtNode it) {
//         // it.expr.accept(this);
//     }

//     @Override
//     public void visit(ifStmtNode it) {
//         it.condition.accept(this);
//         BasicBlock trueBranch = new BasicBlock(), falseBranch = new BasicBlock();
//         currentBlock.push_back(new Branch(it.condition, trueBranch,
//                 falseBranch));

//         BasicBlock destination = new BasicBlock();
//         currentBlock = trueBranch;
//         it.thenSuite.accept(this);
//         currentBlock.push_back(new Jump(destination));
//         currentBlock = falseBranch;
//         it.elseSuite.accept(this);
//         currentBlock.push_back(new Jump(destination));
//         currentBlock = destination;

//         currentFunc.blocks.add(trueBranch);
//         currentFunc.blocks.add(falseBranch);
//         currentFunc.blocks.add(destination);
//     }

//     @Override
//     public void visit(assignExprNode it) {
//         it.lhs.accept(this);
//         if (it.rhs instanceof binaryExprNode ||
//                 it.rhs instanceof logicExprNode) {
//             it.rhs.val = it.lhs.val;
//             it.rhs.accept(this);
//         } else {
//             it.rhs.accept(this);
//             currentBlock.push_back(
//                     new binary((register) it.lhs.val, it.rhs.val, new constant(0), add));
//         }
//     }

//     @Override
//     public void visit(binaryExprNode it) {
//         it.lhs.accept(this);
//         it.rhs.accept(this);
//         Register value;
//         if (it.val != null)
//             value = (Register) it.val;
//         else {
//             value = new Register();
//             it.val = value;
//         }
//         Binary.opType op = it.opCode == binaryExprNode.binaryOpType.add ? add : sub;
//         currentBlock.push_back(new Binary(value, it.lhs.val, it.rhs.val, op));
//     }

//     @Override
//     public <T> void visit(constExprNode<T> it) {
//         // it.val = new constant(it.value);
//     }

//     // @Override
//     // public void visit(cmpExprNode it) {
//     // it.lhs.accept(this);
//     // it.rhs.accept(this);
//     // register value;
//     // if (it.val != null) value = (register)it.val;
//     // else {
//     // value = new register();
//     // it.val = value;
//     // }
//     // binary.opType op = it.opCode == cmpExprNode.cmpOpType.eq ? eq : ne;
//     // currentBlock.push_back(new binary(value, it.lhs.val, it.rhs.val, op));
//     // }

//     @Override
//     public void visit(varExprNode it) {
//         // it.val = currentScope.getEntity(it.name, true);
//     }

//     @Override
//     public void visit(classDefNode it) {

//     }

//     @Override
//     public void visit(FnNode it) {

//     }

//     @Override
//     public void visit(suiteStmtNode it) {

//     }

//     @Override
//     public void visit(forStmtNode it) {

//     }

//     @Override
//     public void visit(whileStmtNode it) {

//     }

//     @Override
//     public void visit(breakStmtNode it) {

//     }

//     @Override
//     public void visit(continueStmtNode it) {

//     }

//     @Override
//     public void visit(newExprNode it) {

//     }

//     @Override
//     public void visit(unaryExprNode it) {

//     }

//     @Override
//     public void visit(logicExprNode it) {

//     }

//     @Override
//     public void visit(funcCallExprNode it) {

//     }

//     @Override
//     public void visit(thisExprNode it) {

//     }

//     @Override
//     public void visit(TypeNode it) {

//     }

//     @Override
//     public void visit(visitExprNode it) {

//     }

//     @Override
//     public void visit(exprArrayNode it) {

//     }

//     @Override
//     public void visit(lambdaExprNode it) {

//     }
// }
