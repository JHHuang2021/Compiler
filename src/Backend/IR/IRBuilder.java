package Backend.IR;

import java.util.HashMap;
import java.util.Stack;

import javax.lang.model.type.NullType;

import org.antlr.v4.runtime.misc.Pair;

import Backend.IR.IRBlock.*;
import Backend.IR.IRInstr.*;
import Backend.IR.IRType.*;
import Backend.IR.IRValue.*;
import Frontend.AST.ASTVisitor;
import Frontend.AST.AssignExprNode;
import Frontend.AST.BinaryExprNode;
import Frontend.AST.BreakStmtNode;
import Frontend.AST.ClassDefNode;
import Frontend.AST.ConstExprNode;
import Frontend.AST.ContinueStmtNode;
import Frontend.AST.ExprArrayNode;
import Frontend.AST.ExprNode;
import Frontend.AST.ExprStmtNode;
import Frontend.AST.ForStmtNode;
import Frontend.AST.FuncCallExprNode;
import Frontend.AST.FunctionNode;
import Frontend.AST.IfStmtNode;
import Frontend.AST.LambdaExprNode;
import Frontend.AST.LogicExprNode;
import Frontend.AST.NewExprNode;
import Frontend.AST.ReturnStmtNode;
import Frontend.AST.RootNode;
import Frontend.AST.StmtNode;
import Frontend.AST.SuiteStmtNode;
import Frontend.AST.ThisExprNode;
import Frontend.AST.TypeNode;
import Frontend.AST.UnaryExprNode;
import Frontend.AST.VarDefStmtNode;
import Frontend.AST.VarExprNode;
import Frontend.AST.VisitExprNode;
import Frontend.AST.WhileStmtNode;
import Frontend.AST.BinaryExprNode.BinaryOpType;
import Frontend.AST.LogicExprNode.LogicOpType;
import Frontend.AST.UnaryExprNode.UnaryOpType;
import Frontend.AST.VarDefStmtNode.Var;
import Util.GlobalScope;
import Util.Scope;
import Util.Type;
import Util.Scope.ScopeType;

public class IRBuilder implements ASTVisitor {
    public IRBlock cur_block = null;
    public IRClass cur_class = null;
    public Register cur_visit_class = null;
    public IRFunc cur_func = null;
    public HashMap<String, IRType> types = new HashMap<>();
    public HashMap<String, IRFunc> funcs = new HashMap<>();
    public IRGlobal global = null;
    public Stack<IRBlock> break_to = new Stack<>();
    public Stack<IRBlock> continue_to = new Stack<>();
    public HashMap<String, IRValue> val_recoder = new HashMap<>();
    public int str_cnt = 0;
    public int reg_cnt = 0;
    public boolean args_define = false;

    private IRType int_type = null;
    private IRType bool_type = null;
    private IRType string_type = null;
    private IRType void_type = null;

    private Scope current_scope;
    private GlobalScope global_scope;

    IRValue GetValue(IRValue input, IRType match) {
        Register dest = null;
        if (input instanceof Constant || input.type.ToString().equals(match.ToString()))
            return input;
        else if (input instanceof StringConst) {
            dest = new Register(match, "load_reg", false, reg_cnt++);
            cur_block.instr.add(new IRGetElementPtr(dest, input.type, input, 0));
            return dest;
        } else {
            dest = new Register(match, "load_reg", false, reg_cnt++);
            cur_block.instr.add(new IRLoad(dest, (Register) input));
            return dest;
        }
    }

    void GenerateNewLoop(Register reg, Type type, int dim) {
        // reg = alloca
        if (dim == type.dim - type.dim_args.size()) {
            return;
        }
        IRClass ir_type = (IRClass) types.get("__built_in_array_" + type.type_name + dim);

        IRType int_ptr_type = new IRPointer(1, int_type);
        Register r = new Register(int_ptr_type, "class_load", false, reg_cnt++);
        Register r_array = new Register(new IRPointer(1, ir_type.var_type.get(1)), "class_load", false, reg_cnt++);
        cur_block.instr.add(new IRGetElementPtr(r, ir_type, reg, 0));
        cur_block.instr.add(new IRGetElementPtr(r_array, ir_type, reg, 1));

        IRValue store_addr = type.dim_args.get(type.dim - dim).val;
        cur_block.instr.add(
                new IRStore(GetValue(store_addr, r.type.FatherType()), r));

        Register size = new Register(int_type, "malloc_size", false, reg_cnt++);
        cur_block.instr.add(
                new IRBin(size, BinaryOpType.mul, GetValue(r, int_type),
                        new Constant(int_type, int_ptr_type.size / 8)));
        Register malloc_reg = new Register(int_ptr_type, "malloc_reg", false, reg_cnt++);
        // IRFunc malloc = funcs.get("__builtin_malloc");
        IRCall call_malloc = new IRCall("__builtin_malloc", malloc_reg);
        call_malloc.args.add(size);
        cur_block.instr.add(call_malloc);
        if (!r_array.type.ToString().equals("i32**")) {
            Register bit_cast = new Register(r_array.type.FatherType(), "bit_cast", false, reg_cnt++);
            cur_block.instr.add(new IRBitCast(malloc_reg, bit_cast));
            cur_block.instr.add(new IRStore(bit_cast, r_array));
        } else
            cur_block.instr.add(new IRStore(malloc_reg, r_array));

        if (dim - 1 != type.dim - type.dim_args.size()) {
            Register it = new Register(int_type, "iter_reg", false, reg_cnt++);
            cur_block.instr.add(new IRAlloc(int_type, it));
            it.type = new IRPointer(1, it.type);
            cur_block.instr.add(new IRStore(new Constant(int_type, 0), it));

            IRBlock cond_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
            cur_func.blocks.add(cond_block);

            IRBlock body_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
            cur_func.blocks.add(body_block);

            IRBlock next_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
            cur_func.blocks.add(next_block);

            IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
            cur_func.blocks.add(new_block);

            Register cond = new Register(bool_type, "cond_reg", false, reg_cnt++);

            cur_block.instr.add(new IRBr(body_block));
            cur_block = cond_block;

            IRValue r_val = GetValue(r, int_type);
            cur_block.instr.add(new IRCmp(cond, LogicOpType.slt, GetValue(it, int_type), r_val));
            cur_block.instr.add(new IRBr(cond, body_block, new_block));
            cur_block = next_block;

            Register binary_tmp = new Register(int_type, "binary_tmp", false, reg_cnt++);
            cur_block.instr
                    .add(new IRBin(binary_tmp, BinaryOpType.add, GetValue(it, int_type), new Constant(int_type, 1)));
            cur_block.instr.add(new IRStore(binary_tmp, it));
            cur_block.instr.add(new IRBr(cond_block));

            cur_block = body_block;
            Register load_array = new Register(r_array.type.FatherType(), "load_array", false, reg_cnt++);
            cur_block.instr.add(new IRLoad(load_array, r_array));
            Register get_array_elem = new Register(load_array.type, "get_array_elem", false, reg_cnt++);
            cur_block.instr.add(new IRGetElementPtr(get_array_elem, load_array.type.FatherType(), load_array,
                    GetValue(it, int_type)));
            GenerateNewLoop(get_array_elem, type, dim - 1);
            cur_block.instr.add(new IRBr(next_block));

            cur_block = new_block;
        }
    }

    IRType FromArrayTypeToIRClass(Type type, int dim) {
        if (dim == 0)
            return types.get(type.type_name);
        String type_name = "__built_in_array_" + type.type_name + dim;
        IRType array_type = types.get(type_name);
        if (array_type == null) {
            IRClass new_array_type = new IRClass(type_name);
            IRType basic_type = FromArrayTypeToIRClass(type, dim - 1);
            basic_type = new IRPointer(1, basic_type);
            new_array_type.var_type.add(int_type);
            new_array_type.var_type.add(basic_type);
            new_array_type.idt_to_rank.put("size", 0);
            new_array_type.idt_to_rank.put("value", 1);
            types.put(type_name, new_array_type);
            global.classes.add(new_array_type);
            return new_array_type;
        } else
            return array_type;
    }

    void LoadBuiltinFunc() {
        IRFunc print = new IRFunc("__builtin_print", void_type);
        print.args.add(new Register(types.get("string"), "s", false, 0));
        print.is_builtin = true;
        funcs.put("print", print);
        global.funcs.add(print);

        IRFunc println = new IRFunc("__builtin_println", void_type);
        println.args.add(new Register(types.get("string"), "s", false, 0));
        println.is_builtin = true;
        funcs.put("println", println);
        global.funcs.add(println);

        IRFunc printInt = new IRFunc("__builtin_printInt", void_type);
        printInt.args.add(new Register(int_type, "int", false, 0));
        printInt.is_builtin = true;
        funcs.put("printInt", printInt);
        global.funcs.add(printInt);

        IRFunc printlnInt = new IRFunc("__builtin_printlnInt", void_type);
        printlnInt.args.add(new Register(int_type, "int", false, 0));
        printlnInt.is_builtin = true;
        funcs.put("printlnInt", printlnInt);
        global.funcs.add(printlnInt);

        IRFunc getInt = new IRFunc("__builtin_getInt", int_type);
        getInt.is_builtin = true;
        funcs.put("getInt", getInt);
        global.funcs.add(getInt);

        IRFunc getString = new IRFunc("__builtin_getString", types.get("string"));
        getString.is_builtin = true;
        funcs.put("getString", getString);
        global.funcs.add(getString);

        IRFunc toString = new IRFunc("__builtin_toString", types.get("string"));
        toString.args.add(new Register(int_type, "int", false, 0));
        toString.is_builtin = true;
        funcs.put("toString", toString);
        global.funcs.add(toString);

        IRFunc malloc = new IRFunc("__builtin_malloc", new IRPointer(1, int_type));
        malloc.args.add(new Register(int_type, "size", false, 0));
        malloc.is_builtin = true;
        funcs.put(malloc.func_name, malloc);
        global.funcs.add(malloc);

        IRFunc str_add = new IRFunc("__builtin_str_add", types.get("string"));
        str_add.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_add.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_add.is_builtin = true;
        funcs.put(str_add.func_name, str_add);
        global.funcs.add(str_add);

        IRFunc str_eq = new IRFunc("__builtin_str_eq", bool_type);
        str_eq.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_eq.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_eq.is_builtin = true;
        funcs.put(str_eq.func_name, str_eq);
        global.funcs.add(str_eq);

        IRFunc str_ne = new IRFunc("__builtin_str_ne", bool_type);
        str_ne.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_ne.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_ne.is_builtin = true;
        funcs.put(str_ne.func_name, str_ne);
        global.funcs.add(str_ne);

        IRFunc str_gt = new IRFunc("__builtin_str_sgt", bool_type);
        str_gt.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_gt.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_gt.is_builtin = true;
        funcs.put(str_gt.func_name, str_gt);
        global.funcs.add(str_gt);

        IRFunc str_ge = new IRFunc("__builtin_str_sge", bool_type);
        str_ge.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_ge.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_ge.is_builtin = true;
        funcs.put(str_ge.func_name, str_ge);
        global.funcs.add(str_ge);

        IRFunc str_lt = new IRFunc("__builtin_str_slt", bool_type);
        str_lt.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_lt.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_lt.is_builtin = true;
        funcs.put(str_lt.func_name, str_lt);
        global.funcs.add(str_lt);

        IRFunc str_le = new IRFunc("__builtin_str_sle", bool_type);
        str_le.args.add(new Register(types.get("string"), "lhs", false, 0));
        str_le.args.add(new Register(types.get("string"), "rhs", false, 1));
        str_le.is_builtin = true;
        funcs.put(str_le.func_name, str_le);
        global.funcs.add(str_le);

        IRFunc str_len = new IRFunc("__builtin_length", int_type);
        str_len.args.add(new Register(types.get("string"), "this", false, 0));
        str_len.is_builtin = true;
        funcs.put(str_len.func_name, str_len);
        global.funcs.add(str_len);

        IRFunc str_substring = new IRFunc("__builtin_substring", types.get("string"));
        str_substring.args.add(new Register(types.get("string"), "this", false, 0));
        str_substring.args.add(new Register(int_type, "left", false, 1));
        str_substring.args.add(new Register(int_type, "right", false, 2));
        str_substring.is_builtin = true;
        funcs.put(str_substring.func_name, str_substring);
        global.funcs.add(str_substring);

        IRFunc str_parseInt = new IRFunc("__builtin_parseInt", int_type);
        str_parseInt.args.add(new Register(types.get("string"), "this", false, 0));
        str_parseInt.is_builtin = true;
        funcs.put(str_parseInt.func_name, str_parseInt);
        global.funcs.add(str_parseInt);

        IRFunc str_ord = new IRFunc("__builtin_ord", int_type);
        str_ord.args.add(new Register(types.get("string"), "this", false, 0));
        str_ord.args.add(new Register(int_type, "pos", false, 1));
        str_ord.is_builtin = true;
        funcs.put(str_ord.func_name, str_ord);
        global.funcs.add(str_ord);
    }

    String GetFuncName(FunctionNode it) {
        String func_name = "";
        if (cur_class != null) {
            if (!cur_class.name.equals("string"))
                func_name = cur_class.name + ".";
            else
                func_name = "__builtin_";
        }
        return func_name + it.func_name;
    }

    public IRBuilder(GlobalScope global_scope) {
        this.global = new IRGlobal();
        current_scope = this.global_scope = global_scope;
    }

    @Override
    public void visit(AssignExprNode it) {
        if (it.rhs instanceof NewExprNode) {
            it.lhs.accept(this);
            it.rhs.val = it.lhs.val;
            it.rhs.accept(this);
            NewExprNode new_expr = (NewExprNode) it.rhs;
            String type_name = new_expr.type.type_name;
            if (new_expr.type.dim == 0) {
                IRCall func_call = new IRCall(type_name + '.' + type_name, null);
                func_call.args.add(it.lhs.val);
                if (new_expr.args != null)
                    for (ExprNode j : new_expr.args)
                        func_call.args.add(j.val);
                cur_block.instr.add(func_call);
            } else {
                // TODO
            }
            it.val = it.lhs.val;
        } else {
            it.rhs.accept(this);
            it.lhs.accept(this);
            cur_block.instr.add(new IRStore(GetValue(it.rhs.val, it.lhs.val.type.FatherType()), it.lhs.val));
        }

    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        it.val = new Register(it.lhs.val.type.FatherType(), "binary_expr", false, reg_cnt++);
        IRValue vlhs = it.lhs.val, vrhs = it.rhs.val;

        if (it.val.type instanceof IRString) {
            IRFunc called_func = funcs.get("__builtin_str_add");
            IRCall func_call = new IRCall("__builtin_str_add", it.val);
            func_call.args.add(GetValue(vlhs, string_type));
            func_call.args.add(GetValue(vrhs, string_type));
            cur_block.instr.add(func_call);
        } else
            cur_block.instr
                    .add(new IRBin(it.val, it.opCode, GetValue(vlhs, int_type), GetValue(vrhs, int_type)));
    }

    @Override
    public void visit(BreakStmtNode it) {
        cur_block.instr.add(new IRBr(break_to.peek()));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(ClassDefNode it) {
        current_scope = new Scope(current_scope);
        cur_class = (IRClass) types.get(it.name);
        for (VarDefStmtNode i : it.var_defs)
            i.accept(this);
        for (FunctionNode i : it.func_defs)
            i.accept(this);
        cur_class = null;
        current_scope = current_scope.ParentScope();
    }

    @Override
    public <T> void visit(ConstExprNode<T> it) {
        if (it.value instanceof Long)
            it.val = new Constant(int_type, ((Long) it.value).intValue());
        else if (it.value instanceof Boolean)
            it.val = new Constant(bool_type, (Boolean) it.value ? 1 : 0);
        else if (it.value instanceof NullType)
            it.val = new Constant(types.get("null"), 0);
        else if (it.value instanceof String) {
            StringConst str;
            if (val_recoder.containsKey(it.value))
                str = (StringConst) val_recoder.get(it.value);
            else {
                str = new StringConst(new IRString(((String) it.value).length() - 2),
                        (String) it.value, str_cnt++);
                global.strs.add(str);
                val_recoder.put((String) it.value, str);
            }
            str.s = str.s.substring(1, str.s.length() - 1);
            it.val = str;
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        cur_block.instr.add(new IRBr(continue_to.peek()));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
    }

    @Override
    public void visit(ExprArrayNode it) {
        it.expr.accept(this);
        it.offset.accept(this);
        IRClass type = (IRClass) it.expr.val.type.FatherType();

        IRType get_element_type = type.var_type.get(1);
        Register reg = new Register(new IRPointer(1, get_element_type), "get_element", false, reg_cnt++);
        cur_block.instr.add(new IRGetElementPtr(reg, type, it.expr.val, 1));

        it.val = new Register(get_element_type, "expr_array", false, reg_cnt++);
        IRGetElementPtr inst = new IRGetElementPtr((Register) it.val, get_element_type.FatherType(),
                GetValue(reg, get_element_type),
                GetValue(it.offset.val, int_type));
        cur_block.instr.add(inst);
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(ForStmtNode it) {
        IRBlock cond_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cond_block);

        IRBlock body_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(body_block);

        IRBlock next_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(next_block);

        IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(new_block);

        break_to.push(new_block);
        continue_to.push(next_block);

        current_scope = new Scope(current_scope);

        if (it.var != null)
            it.var.accept(this);
        cur_block.instr.add(new IRBr(body_block));
        cur_block = cond_block;

        if (it.condition != null) {
            it.condition.accept(this);
            cur_block.instr.add(new IRBr(it.condition.val, body_block, new_block));
        } else
            cur_block.instr.add(new IRBr(body_block));
        cur_block = next_block;

        if (it.step != null)
            it.step.accept(this);
        cur_block.instr.add(new IRBr(cond_block));

        cur_block = body_block;
        it.for_suite.accept(this);
        cur_block.instr.add(new IRBr(next_block));

        cur_block = new_block;
        current_scope = current_scope.ParentScope();

        break_to.pop();
        continue_to.pop();
    }

    @Override
    public void visit(FuncCallExprNode it) {
        String func_name = "";
        if (cur_visit_class != null && !(cur_visit_class.type.FatherType() instanceof IRString))
            func_name = cur_visit_class.type.name + ".";

        func_name += it.name;
        IRFunc called_func = null;
        if (cur_class != null && cur_visit_class == null) {
            called_func = funcs.get(cur_class.name + '.' + func_name);
            if (called_func != null)
                cur_visit_class = cur_func.this_ptr;
        }
        if (cur_visit_class != null && cur_visit_class.type.name.contains("__built_in_array")
                && it.name.equals("size")) {
            it.val = new Register(new IRPointer(1, int_type), "size_reg", false, reg_cnt++);
            cur_block.instr
                    .add(new IRGetElementPtr((Register) it.val, cur_visit_class.type.FatherType(), cur_visit_class, 0));
            return;
        }
        if (called_func == null)
            called_func = funcs.get(func_name);
        if (called_func == null)
            called_func = funcs.get("__builtin_" + func_name);
        if (!(called_func.ret_type instanceof IRVoid))
            it.val = new Register(called_func.ret_type, func_name, false, reg_cnt++);
        IRCall func_call = new IRCall(called_func.func_name, it.val);
        int i = 0;
        if (cur_visit_class != null)
            func_call.args.add(GetValue(cur_visit_class, called_func.args.get(i++).type));
        cur_visit_class = null;

        for (int j = 0; j < it.args.size(); j++) {
            it.args.get(j).accept(this);
            func_call.args.add(GetValue(it.args.get(j).val, called_func.args.get(i++).type));
        }

        cur_block.instr.add(func_call);
    }

    @Override
    public void visit(FunctionNode it) {
        current_scope = new Scope(current_scope, ScopeType.FUNC);
        cur_func = funcs.get(GetFuncName(it));
        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);

        if (it.func_name.equals("main")) {
            IRFunc init_func = funcs.get("__global_var_init");
            if (!init_func.IfEmpty())
                cur_block.instr.add(new IRCall("__global_var_init", null));
        }

        if (cur_class != null)
            cur_func.args.add(cur_func.this_ptr);

        args_define = true;
        for (VarDefStmtNode i : it.args_def) {
            i.accept(this);
            if (!(i.var.get(0).val.type.FatherType() instanceof IRClass))
                i.var.get(0).val.type = i.var.get(0).val.type.FatherType();
            cur_func.args.add((Register) i.var.get(0).val);
        }
        args_define = false;

        for (int i = 0; i < it.stmts.size(); i++) {
            it.stmts.get(i).accept(this);
            if (i != it.stmts.size() - 1 && cur_func.ret_type.name.equals("void"))
                cur_func.has_return = false;
        }

        if (!cur_func.has_return) {
            if (cur_func.func_name.equals("main"))
                cur_block.instr.add(new IRRet(new Constant(int_type, 0)));
            else
                cur_block.instr.add(new IRRet(new Constant(void_type, 0)));
        }

        cur_block = null;
        cur_func = null;
        current_scope = current_scope.ParentScope();
    }

    @Override
    public void visit(IfStmtNode it) {
        IRBlock true_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(true_block);
        IRBlock false_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(false_block);
        IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(new_block);

        it.condition.accept(this);
        cur_block.instr.add(new IRBr(it.condition.val, true_block, false_block));
        cur_block = true_block;

        it.then_suite.accept(this);
        cur_block.instr.add(new IRBr(new_block));
        cur_block = false_block;

        if (it.else_suite != null)
            it.else_suite.accept(this);

        cur_block.instr.add(new IRBr(new_block));
        cur_block = new_block;
    }

    @Override
    public void visit(LambdaExprNode it) {

    }

    @Override
    public void visit(LogicExprNode it) {
        it.lhs.accept(this);
        IRBlock next_block = new IRBlock(cur_func.func_name + "_" + (cur_func.blocks.size() + 1));
        IRValue lval = it.lhs.val;

        if (it.op_code == LogicOpType.andand) {
            if (lval instanceof Constant) {
                if (((Constant) lval).val == 1) {
                    it.rhs.accept(this);
                    it.val = it.rhs.val;
                } else
                    it.val = lval;
                cur_block.instr.add(new IRBr(next_block));
                cur_block = next_block;
            } else {
                IRBlock block1 = cur_block;
                IRBlock block2 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block2);

                IRValue cond_val = lval;
                if (cond_val.type instanceof IRPointer) {
                    Register reg = new Register(bool_type, "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) cond_val));
                    cond_val = reg;
                }

                cur_block.instr.add(new IRBr(cond_val, block2, next_block));

                cur_block = block2;
                it.rhs.accept(this);
                IRValue vrhs = it.rhs.val;
                if (vrhs.type instanceof IRPointer) {
                    Register reg = new Register(bool_type, "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) vrhs));
                    vrhs = reg;
                }

                cur_block.instr.add(new IRBr(next_block));

                it.val = new Register(bool_type, "and_and", false, reg_cnt++);
                cur_block = next_block;
                cur_block.instr.add(
                        new IRPhi((Register) it.val, new Constant(bool_type, 0), vrhs, block1, block2));
            }
            cur_func.blocks.add(next_block);
        } else if (it.op_code == LogicOpType.oror) {
            if (lval instanceof Constant) {
                if (((Constant) lval).val == 0) {
                    it.rhs.accept(this);
                    it.val = it.rhs.val;
                } else
                    it.val = lval;
                cur_block.instr.add(new IRBr(next_block));
                cur_block = next_block;
            } else {
                IRBlock block1 = cur_block;
                IRBlock block2 = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
                cur_func.blocks.add(block2);

                IRValue cond_val = lval;
                if (cond_val.type instanceof IRPointer) {
                    Register reg = new Register(bool_type, "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) cond_val));
                    cond_val = reg;
                }

                cur_block.instr.add(new IRBr(cond_val, next_block, block2));

                cur_block = block2;
                it.rhs.accept(this);
                IRValue vrhs = it.rhs.val;
                if (vrhs.type instanceof IRPointer) {
                    Register reg = new Register(bool_type, "load", false, reg_cnt++);
                    cur_block.instr.add(new IRLoad(reg, (Register) vrhs));
                    vrhs = reg;
                }

                cur_block.instr.add(new IRBr(next_block));

                it.val = new Register(bool_type, "or_or", false, reg_cnt++);
                cur_block = next_block;
                cur_block.instr.add(
                        new IRPhi((Register) it.val, new Constant(bool_type, 1), vrhs, block1, block2));
            }
            cur_func.blocks.add(next_block);
        } else if (it.op_code == LogicOpType.not) {
            IRType type = bool_type;
            if (lval instanceof Constant)
                it.val = new Constant(type, ((Constant) lval).val);
            else {
                it.val = new Register(type, "not", false, reg_cnt++);
                cur_block.instr
                        .add(new IRBin(it.val, BinaryOpType.xor, new Constant(type, 1), GetValue(lval, bool_type)));
            }
        } else {
            it.val = new Register(bool_type, "cmp", false, reg_cnt++);
            it.rhs.accept(this);
            if (it.lhs.val.type.FatherType() instanceof IRString) {
                IRFunc called_func = funcs.get("__builtin_str_" + it.op_code.toString());
                IRCall func_call = new IRCall(called_func.func_name, it.val);
                func_call.args.add(GetValue(it.lhs.val, string_type));
                func_call.args.add(GetValue(it.rhs.val, string_type));
                cur_block.instr.add(func_call);
            } else
                cur_block.instr
                        .add(new IRCmp(it.val, it.op_code, GetValue(it.lhs.val, int_type),
                                GetValue(it.rhs.val, int_type)));
        }
    }

    @Override
    public void visit(NewExprNode it) {
        if (it.type.dim_args != null)
            for (ExprNode i : it.type.dim_args)
                i.accept(this);

        if (it.val == null)
            it.val = new Register(FromArrayTypeToIRClass(it.type, it.type.dim), "new_reg", false, reg_cnt++);
        if (it.type.dim != 0)
            GenerateNewLoop((Register) it.val, it.type, it.type.dim);
    }

    @Override
    public void visit(RootNode it) {
        int_type = new IRInt();
        bool_type = new IRBool();
        string_type = new IRString(-1);
        void_type = new IRVoid();
        types.put("void", void_type);
        types.put("null", void_type);
        types.put("bool", bool_type);
        types.put("int", int_type);
        types.put("string", string_type);

        for (ClassDefNode i : it.class_defs) {
            IRClass new_class = new IRClass(i.name);
            types.put(i.name, new_class);
            global.classes.add(new_class);
        }

        for (ClassDefNode i : it.class_defs) {
            IRClass curr_class = (IRClass) types.get(i.name);
            for (int m = 0; m < i.var_defs.size(); m++) {
                VarDefStmtNode j = i.var_defs.get(m);
                IRType type = FromArrayTypeToIRClass(j.var.get(0).type, j.var.get(0).type.dim);
                for (Var k : j.var)
                    curr_class.idt_to_rank.put(k.name, m);

                curr_class.var_type.add(type);
            }
        }

        LoadBuiltinFunc();

        for (ClassDefNode i : it.class_defs) {
            cur_class = (IRClass) types.get(i.name);
            for (FunctionNode j : i.func_defs) {
                IRType func_type = null;
                if (j.ret_type.type_name.equals("Create"))
                    func_type = void_type;
                else
                    func_type = FromArrayTypeToIRClass(j.ret_type, j.ret_type.dim);
                if (func_type instanceof IRClass)
                    func_type = new IRPointer(1, func_type);
                String func_name = GetFuncName(j);
                IRFunc new_func = new IRFunc(func_name, func_type);
                new_func.this_ptr = new Register(new IRPointer(1, cur_class), "this_ptr", false, reg_cnt++);
                funcs.put(func_name, new_func);
                global.funcs.add(new_func);
            }
            cur_class = null;
        }

        for (FunctionNode i : it.func_defs) {
            IRType func_type = FromArrayTypeToIRClass(i.ret_type, i.ret_type.dim);
            if (func_type instanceof IRClass)
                func_type = new IRPointer(1, func_type);
            String func_name = GetFuncName(i);
            IRFunc new_func = new IRFunc(func_name, func_type);
            funcs.put(func_name, new_func);
            global.funcs.add(new_func);
        }

        IRFunc global_init_func = new IRFunc("__global_var_init", void_type);
        funcs.put(global_init_func.func_name, global_init_func);
        global.funcs.add(global_init_func);
        cur_func = global_init_func;
        cur_block = new IRBlock("__global_var_init_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
        for (VarDefStmtNode i : it.var_defs) {
            i.accept(this);
            global.vars.add((Register) i.var.get(0).val);
        }
        cur_block.instr.add(new IRRet(new Constant(void_type, 0)));
        cur_block = null;
        cur_func = null;

        for (ClassDefNode i : it.class_defs)
            i.accept(this);

        for (FunctionNode i : it.func_defs)
            i.accept(this);
    }

    @Override
    public void visit(ReturnStmtNode it) {
        cur_block.is_returned = true;
        if (it.value != null) {
            it.value.accept(this);
            cur_block.instr.add(new IRRet(GetValue(it.value.val, cur_func.ret_type)));
        } else
            cur_block.instr.add(new IRRet(new Constant(void_type, 0)));

        cur_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cur_block);
        cur_func.has_return = true;
    }

    @Override
    public void visit(SuiteStmtNode it) {
        current_scope = new Scope(current_scope);
        for (StmtNode i : it.stmts)
            i.accept(this);
        current_scope = current_scope.ParentScope();
    }

    @Override
    public void visit(ThisExprNode it) {
        it.val = cur_func.this_ptr;
    }

    @Override
    public void visit(TypeNode it) {

    }

    @Override
    public void visit(UnaryExprNode it) {
        it.expr.accept(this);
        it.val = new Register(int_type, "unary", false, reg_cnt++);
        if (it.opCode == UnaryOpType.raddadd || it.opCode == UnaryOpType.rsubsub) {
            cur_block.instr.add(new IRLoad((Register) it.val, (Register) it.expr.val));
            Register reg = new Register(int_type, "tmp", false, reg_cnt++);
            BinaryOpType op = it.opCode == UnaryOpType.raddadd ? BinaryOpType.add : BinaryOpType.sub;
            cur_block.instr.add(
                    new IRBin(reg, op, it.val, (IRValue) new Constant(int_type, 1)));
            cur_block.instr.add(new IRStore(reg, it.expr.val));
        } else if (it.opCode == UnaryOpType.laddadd || it.opCode == UnaryOpType.lsubsub) {
            Register reg = new Register(int_type, "tmp", false, reg_cnt++);
            BinaryOpType op = it.opCode == UnaryOpType.laddadd ? BinaryOpType.add : BinaryOpType.sub;
            cur_block.instr.add(new IRLoad((Register) reg, (Register) it.expr.val));
            cur_block.instr.add(new IRBin(it.val, op, reg, (IRValue) new Constant(int_type, 1)));
            cur_block.instr.add(new IRStore(it.val, it.expr.val));
        } else if (it.opCode == UnaryOpType.neg)
            cur_block.instr
                    .add(new IRBin(it.expr.val, BinaryOpType.sub, (IRValue) new Constant(int_type, 0), it.expr.val));
        else if (it.opCode == UnaryOpType.tilde)
            cur_block.instr
                    .add(new IRBin(it.expr.val, BinaryOpType.xor, (IRValue) new Constant(int_type, -1), it.expr.val));

    }

    @Override
    public void visit(VarDefStmtNode it) {
        if ((cur_class != null && !current_scope.IfInFunc()))
            return;
        boolean is_global = false;
        is_global = cur_func.func_name == "__global_var_init" ? true : false;
        for (Var i : it.var) {
            IRType type = FromArrayTypeToIRClass(i.type, i.type.dim);

            if (!args_define) {
                i.val = new Register(type, "var_def", is_global, reg_cnt++);
                if (type instanceof IRString) {
                    i.init.accept(this);
                    Register reg = new Register(type, "get_element", false, reg_cnt++);
                    cur_block.instr.add(new IRGetElementPtr((Register) reg, i.init.val.type, i.init.val,
                            0));
                    cur_block.instr.add(new IRStore(reg, i.val));
                    i.val.type = new IRPointer(1, i.val.type);
                    current_scope.DefineVarible(i.name, (Register) i.val);
                    continue;
                } else {
                    cur_block.instr.add(new IRAlloc(i.val.type, i.val));
                    i.val.type = new IRPointer(1, i.val.type);
                }
                current_scope.DefineVarible(i.name, (Register) i.val);
            } else {
                i.val = new Register(new IRPointer(1, type), "arg_define", false, reg_cnt++);
                current_scope.DefineVarible(i.name, (Register) i.val);
            }

            if (i.init != null) {
                if (i.init instanceof NewExprNode) {
                    i.init.val = i.val;
                    i.init.accept(this);
                    NewExprNode new_expr = (NewExprNode) i.init;
                    String type_name = new_expr.type.type_name;
                    if (new_expr.type.dim == 0) {
                        IRFunc called_func = funcs.get(type_name + '.' + type_name);
                        if (called_func == null)
                            continue;
                        IRCall func_call = new IRCall(type_name + '.' + type_name, null);
                        func_call.args.add(i.val);
                        if (new_expr.args != null)
                            for (ExprNode j : new_expr.args)
                                func_call.args.add(j.val);
                        cur_block.instr.add(func_call);
                    } else {
                        // TODO
                    }
                } else {
                    i.init.accept(this);
                    cur_block.instr.add(new IRStore(GetValue(i.init.val, i.val.type.FatherType()), i.val));
                }
            }
        }
    }

    @Override
    public void visit(VarExprNode it) {
        IRType type = FromArrayTypeToIRClass(it.type, it.type.dim);

        it.val = new Register(type, "var_expr", false, reg_cnt++);
        if (cur_visit_class != null) {
            IRClass clas = (IRClass) types.get(cur_visit_class.type.name);
            int ind = clas.idt_to_rank.get(it.name);
            Register get_ptr = new Register(new IRPointer(1, type), "get_ptr", false, reg_cnt++);
            it.val = get_ptr;
            cur_block.instr.add(new IRGetElementPtr(get_ptr, cur_visit_class.type.FatherType(), cur_visit_class, ind));
            return;
        }

        if (cur_class != null) {
            Pair<Register, Boolean> ret = current_scope.GetVaribleVal(it.name, false);
            if (ret.b) {
                it.val = ret.a;
                return;
            }
            if (cur_class.idt_to_rank.containsKey(it.name)) {
                it.val.type = new IRPointer(1, it.val.type);
                cur_block.instr.add(new IRGetElementPtr((Register) it.val,
                        ((IRPointer) cur_func.this_ptr.type).father_type, cur_func.this_ptr,
                        cur_class.idt_to_rank.get(it.name)));
                return;
            }
        }

        Pair<Register, Boolean> ret = current_scope.GetVaribleVal(it.name, true);
        it.val = ret.a;
    }

    @Override
    public void visit(VisitExprNode it) {
        it.visitor.accept(this);
        cur_visit_class = (Register) it.visitor.val;
        it.visitee.accept(this);
        cur_visit_class = null;
        it.val = it.visitee.val;
    }

    @Override
    public void visit(WhileStmtNode it) {
        IRBlock cond_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(cond_block);

        IRBlock body_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(body_block);

        IRBlock new_block = new IRBlock(cur_func.func_name + "_" + cur_func.blocks.size());
        cur_func.blocks.add(new_block);

        break_to.push(new_block);
        continue_to.push(body_block);

        cur_block = cond_block;
        it.condition.accept(this);
        cur_block.instr.add(new IRBr(it.condition.val, body_block, new_block));

        cur_block = body_block;
        it.while_suite.accept(this);
        cur_block.instr.add(new IRBr(cond_block));

        cur_block = new_block;

        break_to.pop();
        continue_to.pop();
    }

}