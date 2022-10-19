
// import java.util.Arrays;
// import Assembly.AsmFn;
// import Backend.*;
// import org.antlr.v4.gui.TreeViewer;
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import Util.globalScope;
import Util.error.error;

import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import AST.RootNode;
import Frontend.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        // String name = "test.mx";
        // InputStream input = new FileInputStream(name);
        String s = "";
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
            s += in.nextLine();
        in.close();

        try {
            RootNode ASTRoot;
            globalScope gScope = new globalScope(null);

            MxLexer lexer = new MxLexer(CharStreams.fromString(s));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            // show AST in console
            // System.out.println(parseTreeRoot.toStringTree(parser));

            // show AST in GUI
            // TreeViewer viewr = new TreeViewer(Arrays.asList(
            // parser.getRuleNames()), parseTreeRoot);
            // viewr.open();

            ASTBuilder astBuilder = new ASTBuilder(gScope);
            ASTRoot = (RootNode) astBuilder.visit(parseTreeRoot);
            // System.out.println("test");
            new SymbolCollector(gScope).visit(ASTRoot);
            new SemanticChecker(gScope).visit(ASTRoot);

            // mainFn f = new mainFn();
            // new IRBuilder(f, gScope).visit(ASTRoot);
            // new IRPrinter(System.out).visitFn(f);

            // AsmFn asmF = new AsmFn();
            // new InstSelector(asmF).visitFn(f);
            // new RegAlloc(asmF).work();
            // new AsmPrinter(asmF, System.out).print();
        } catch (error er) {
            System.out.println(er.toString());
            throw new RuntimeException();
        }
    }
}