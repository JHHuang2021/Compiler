
// import java.util.Arrays;
// import Assembly.AsmFn;
// import Backend.*;
// import org.antlr.v4.gui.TreeViewer;
import Frontend.Parser.MxLexer;
import Frontend.Parser.MxParser;
import Util.MxErrorListener;
import Util.GlobalScope;
import Util.error.error;

import java.io.FileInputStream;
import java.io.InputStream;
// import java.util.Scanner;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import Backend.IR.IRBuilder;
import Backend.IR.IRPrinter;
import Frontend.AST.RootNode;
import Frontend.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        String name = "test.mx";
        InputStream input = new FileInputStream(name);

        // PrintStream errprint = new PrintStream("err.txt");
        // System.setErr(errprint);

        // InputStream input = System.in;

        try {
            RootNode ASTRoot;
            GlobalScope global_scope = new GlobalScope(null);

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parse_tree_root = parser.program();
            // show AST in console
            // System.out.println(parseTreeRoot.toStringTree(parser));

            // show AST in GUI
            // TreeViewer viewr = new TreeViewer(Arrays.asList(
            // parser.getRuleNames()), parseTreeRoot);
            // viewr.open();

            ASTBuilder ast_builder = new ASTBuilder(global_scope);
            ASTRoot = (RootNode) ast_builder.visit(parse_tree_root);
            // System.out.println("test");
            new SymbolCollector(global_scope).visit(ASTRoot);
            new SemanticChecker(global_scope).visit(ASTRoot);

            // mainFn f = new mainFn();
            IRBuilder ir_builder = new IRBuilder(global_scope);
            ir_builder.visit(ASTRoot);
            new IRPrinter("test.ll", ir_builder.global);

            // AsmFn asmF = new AsmFn();
            // new InstSelector(asmF).visitFn(f);
            // new RegAlloc(asmF).work();
            // new AsmPrinter(asmF, System.out).print();
        } catch (error er) {
            System.out.println(er.ToString());
            throw new RuntimeException();
        }
    }
}