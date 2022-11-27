package Backend.IR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import Backend.IR.IRBlock.IRBlock;
import Backend.IR.IRBlock.IRFunc;
import Backend.IR.IRInstr.IRInstr;
import Backend.IR.IRType.IRClass;
import Backend.IR.IRValue.Register;
import Backend.IR.IRValue.StringConst;

public class IRPrinter {
    public OutputStream out;
    public PrintWriter printer;

    public IRPrinter(String file_name, IRGlobal global) {
        try {
            File file = new File(file_name);
            assert file.exists() || file.createNewFile();

            out = new FileOutputStream(file_name, false);
            printer = new PrintWriter(out);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        visit(global);

        try {
            printer.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void visit(IRGlobal global) {
        for (IRClass i : global.classes)
            printer.println(i.Declare());

        for (StringConst i : global.strs)
            printer.println(i.Declare());

        for (Register i : global.vars)
            printer.println(i.Declare());

        for (IRFunc i : global.funcs) {
            if (i.is_builtin)
                printer.println(i.Declare(true));
        }

        for (IRFunc i : global.funcs) {
            if (!i.is_builtin)
                visit(i);
        }
    }

    public void visit(IRFunc func) {

        String str = func.Declare(false);
        if (func.func_name == "__global_var_init") {
            if (func.blocks.size() == 1 && func.blocks.get(0).instr.isEmpty())
                return;
            str += " section \".text.startup\"";
        }
        printer.println(str);
        printer.println("{");
        for (IRBlock i : func.blocks) {
            if (i.instr.isEmpty())
                continue;
            visit(i);
        }
        printer.println("}");
    }

    public void visit(IRBlock block) {
        printer.println(block.ToString());
        for (IRInstr i : block.instr) {
            String str = i.ToString();
            if (!str.equals(""))
                printer.println("\t" + str);
        }
    }
}