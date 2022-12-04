// Generated from /mnt/d/Projects/Compiler/src/Frontend/Parser/Mx.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Break=3, Continue=4, Int=5, Bool=6, String=7, If=8, Else=9, 
		For=10, New=11, While=12, Return=13, Class=14, This=15, LeftParen=16, 
		RightParen=17, LeftBracket=18, RightBracket=19, LeftBrace=20, RightBrace=21, 
		Less=22, LessEqual=23, Greater=24, GreaterEqual=25, LeftShift=26, RightShift=27, 
		Plus=28, Minus=29, Star=30, Divide=31, MOD=32, PlusPlus=33, MinusMinus=34, 
		And=35, Or=36, AndAnd=37, OrOr=38, Caret=39, Not=40, Tilde=41, Colon=42, 
		Semi=43, Comma=44, Assign=45, Equal=46, NotEqual=47, BoolConstant=48, 
		NullConstant=49, Identifier=50, DecimalInteger=51, StringConstant=52, 
		Whitespace=53, Newline=54, BlockComment=55, LineComment=56;
	public static final int
		RULE_program = 0, RULE_varDef = 1, RULE_classDef = 2, RULE_suite = 3, 
		RULE_body = 4, RULE_forexpr1 = 5, RULE_statement = 6, RULE_expression = 7, 
		RULE_primary = 8, RULE_argDef = 9, RULE_arg = 10, RULE_lambdaExpression = 11, 
		RULE_funcCall = 12, RULE_createFuncCall = 13, RULE_funcDef = 14, RULE_literal = 15, 
		RULE_type = 16, RULE_typewitharg = 17, RULE_basicType = 18, RULE_array = 19, 
		RULE_arraywitharg = 20, RULE_wrongarraywitharg = 21, RULE_dot = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "varDef", "classDef", "suite", "body", "forexpr1", "statement", 
			"expression", "primary", "argDef", "arg", "lambdaExpression", "funcCall", 
			"createFuncCall", "funcDef", "literal", "type", "typewitharg", "basicType", 
			"array", "arraywitharg", "wrongarraywitharg", "dot"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'->'", "'break'", "'continue'", "'int'", "'bool'", "'string'", 
			"'if'", "'else'", "'for'", "'new'", "'while'", "'return'", "'class'", 
			"'this'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'<'", "'<='", "'>'", 
			"'>='", "'<<'", "'>>'", "'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", 
			"'&'", "'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", "':'", "';'", "','", 
			"'='", "'=='", "'!='", null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "Break", "Continue", "Int", "Bool", "String", "If", 
			"Else", "For", "New", "While", "Return", "Class", "This", "LeftParen", 
			"RightParen", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
			"Less", "LessEqual", "Greater", "GreaterEqual", "LeftShift", "RightShift", 
			"Plus", "Minus", "Star", "Divide", "MOD", "PlusPlus", "MinusMinus", "And", 
			"Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", "Colon", "Semi", "Comma", 
			"Assign", "Equal", "NotEqual", "BoolConstant", "NullConstant", "Identifier", 
			"DecimalInteger", "StringConstant", "Whitespace", "Newline", "BlockComment", 
			"LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(51);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(46);
					classDef();
					}
					break;
				case 2:
					{
					setState(47);
					funcDef();
					}
					break;
				case 3:
					{
					{
					setState(48);
					varDef();
					setState(49);
					match(Semi);
					}
					}
					break;
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> Assign() { return getTokens(MxParser.Assign); }
		public TerminalNode Assign(int i) {
			return getToken(MxParser.Assign, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			type();
			setState(57);
			match(Identifier);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(58);
				match(Assign);
				setState(59);
				expression(0);
				}
			}

			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(62);
				match(Comma);
				setState(63);
				match(Identifier);
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Assign) {
					{
					setState(64);
					match(Assign);
					setState(65);
					expression(0);
					}
				}

				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(Class);
			setState(74);
			match(Identifier);
			setState(75);
			match(LeftBrace);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(80);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					{
					setState(76);
					varDef();
					setState(77);
					match(Semi);
					}
					}
					break;
				case 2:
					{
					setState(79);
					funcDef();
					}
					break;
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
			match(RightBrace);
			setState(86);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(LeftBrace);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Break) | (1L << Continue) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << If) | (1L << For) | (1L << New) | (1L << While) | (1L << Return) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << Semi) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
				{
				{
				setState(89);
				statement();
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(97);
				suite();
				}
				break;
			case 2:
				{
				setState(98);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Forexpr1Context extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Forexpr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forexpr1; }
	}

	public final Forexpr1Context forexpr1() throws RecognitionException {
		Forexpr1Context _localctx = new Forexpr1Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_forexpr1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(101);
				varDef();
				}
				break;
			case 2:
				{
				setState(102);
				expression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext forexpr2;
		public ExpressionContext forexpr3;
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public Forexpr1Context forexpr1() {
			return getRuleContext(Forexpr1Context.class,0);
		}
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class IfStmtContext extends StatementContext {
		public BodyContext thensent;
		public BodyContext elsesent;
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public List<BodyContext> body() {
			return getRuleContexts(BodyContext.class);
		}
		public BodyContext body(int i) {
			return getRuleContext(BodyContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class BlockContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class VardefStmtContext extends StatementContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public VardefStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class PureExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public PureExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new VardefStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				varDef();
				setState(106);
				match(Semi);
				}
				break;
			case 2:
				_localctx = new PureExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				expression(0);
				setState(109);
				match(Semi);
				}
				break;
			case 3:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				suite();
				}
				break;
			case 4:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				match(If);
				setState(113);
				match(LeftParen);
				setState(114);
				expression(0);
				setState(115);
				match(RightParen);
				setState(116);
				((IfStmtContext)_localctx).thensent = body();
				setState(119);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(117);
					match(Else);
					setState(118);
					((IfStmtContext)_localctx).elsesent = body();
					}
					break;
				}
				}
				break;
			case 5:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(121);
				match(For);
				setState(122);
				match(LeftParen);
				setState(123);
				forexpr1();
				setState(124);
				match(Semi);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
					{
					setState(125);
					((ForStmtContext)_localctx).forexpr2 = expression(0);
					}
				}

				setState(128);
				match(Semi);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
					{
					setState(129);
					((ForStmtContext)_localctx).forexpr3 = expression(0);
					}
				}

				setState(132);
				match(RightParen);
				setState(135);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(133);
					statement();
					}
					break;
				case 2:
					{
					setState(134);
					suite();
					}
					break;
				}
				}
				break;
			case 6:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(137);
				match(While);
				setState(138);
				match(LeftParen);
				setState(139);
				expression(0);
				setState(140);
				match(RightParen);
				setState(143);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(141);
					statement();
					}
					break;
				case 2:
					{
					setState(142);
					suite();
					}
					break;
				}
				}
				break;
			case 7:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(145);
				match(Return);
				{
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
					{
					setState(146);
					expression(0);
					}
				}

				}
				setState(149);
				match(Semi);
				}
				break;
			case 8:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(150);
				match(Break);
				setState(151);
				match(Semi);
				}
				break;
			case 9:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(152);
				match(Continue);
				setState(153);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(154);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypewithargContext typewitharg() {
			return getRuleContext(TypewithargContext.class,0);
		}
		public CreateFuncCallContext createFuncCall() {
			return getRuleContext(CreateFuncCallContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class LambdaContext extends ExpressionContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public LambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class UnaryExprContext extends ExpressionContext {
		public Token beforeop;
		public Token afterop;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(MxParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(MxParser.MinusMinus, 0); }
		public TerminalNode Tilde() { return getToken(MxParser.Tilde, 0); }
		public TerminalNode Minus() { return getToken(MxParser.Minus, 0); }
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class VisitExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public VisitExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class LogicExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode Equal() { return getToken(MxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode Less() { return getToken(MxParser.Less, 0); }
		public TerminalNode Greater() { return getToken(MxParser.Greater, 0); }
		public TerminalNode LessEqual() { return getToken(MxParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxParser.GreaterEqual, 0); }
		public TerminalNode AndAnd() { return getToken(MxParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxParser.OrOr, 0); }
		public LogicExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class FunctionCallExprContext extends ExpressionContext {
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public FunctionCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class AtomExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class ExprArrayContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public ExprArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class AssignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class ArithExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Star() { return getToken(MxParser.Star, 0); }
		public TerminalNode Divide() { return getToken(MxParser.Divide, 0); }
		public TerminalNode MOD() { return getToken(MxParser.MOD, 0); }
		public TerminalNode Plus() { return getToken(MxParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Caret() { return getToken(MxParser.Caret, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public ArithExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(158);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(New);
				setState(162);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(160);
					typewitharg();
					}
					break;
				case 2:
					{
					setState(161);
					createFuncCall();
					}
					break;
				}
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164);
				funcCall();
				}
				break;
			case 4:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(165);
				((UnaryExprContext)_localctx).beforeop = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Tilde))) != 0)) ) {
					((UnaryExprContext)_localctx).beforeop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(166);
				expression(8);
				}
				break;
			case 5:
				{
				_localctx = new LogicExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				((LogicExprContext)_localctx).op = match(Not);
				setState(168);
				expression(7);
				}
				break;
			case 6:
				{
				_localctx = new LambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				lambdaExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(197);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new VisitExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(173);
						match(T__0);
						setState(174);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(176);
						((ArithExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftShift) | (1L << RightShift) | (1L << Plus) | (1L << Minus) | (1L << Star) | (1L << Divide) | (1L << MOD))) != 0)) ) {
							((ArithExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(177);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new LogicExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(179);
						((LogicExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual) | (1L << Equal) | (1L << NotEqual))) != 0)) ) {
							((LogicExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(180);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(182);
						((ArithExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << And) | (1L << Or) | (1L << Caret))) != 0)) ) {
							((ArithExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(183);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new LogicExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(185);
						((LogicExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AndAnd || _la==OrOr) ) {
							((LogicExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(186);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(188);
						match(Assign);
						setState(189);
						expression(3);
						}
						break;
					case 7:
						{
						_localctx = new ExprArrayContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						{
						setState(191);
						match(LeftBracket);
						setState(192);
						expression(0);
						setState(193);
						match(RightBracket);
						}
						}
						break;
					case 8:
						{
						_localctx = new UnaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(196);
						((UnaryExprContext)_localctx).afterop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PlusPlus || _la==MinusMinus) ) {
							((UnaryExprContext)_localctx).afterop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_primary);
		try {
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BoolConstant:
			case NullConstant:
			case DecimalInteger:
			case StringConstant:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				literal();
				}
				break;
			case This:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				match(This);
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				match(LeftParen);
				setState(205);
				expression(0);
				setState(206);
				match(RightParen);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 4);
				{
				setState(208);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgDefContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ArgDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argDef; }
	}

	public final ArgDefContext argDef() throws RecognitionException {
		ArgDefContext _localctx = new ArgDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(211);
				type();
				setState(212);
				match(Identifier);
				}
			}

			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(216);
				match(Comma);
				setState(217);
				type();
				setState(218);
				match(Identifier);
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
				{
				setState(225);
				expression(0);
				}
			}

			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(228);
				match(Comma);
				setState(229);
				expression(0);
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaExpressionContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<TerminalNode> LeftParen() { return getTokens(MxParser.LeftParen); }
		public TerminalNode LeftParen(int i) {
			return getToken(MxParser.LeftParen, i);
		}
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public List<TerminalNode> RightParen() { return getTokens(MxParser.RightParen); }
		public TerminalNode RightParen(int i) {
			return getToken(MxParser.RightParen, i);
		}
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public ArgDefContext argDef() {
			return getRuleContext(ArgDefContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lambdaExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(LeftBracket);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==And) {
				{
				setState(236);
				match(And);
				}
			}

			setState(239);
			match(RightBracket);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftParen) {
				{
				setState(240);
				match(LeftParen);
				setState(241);
				argDef();
				setState(242);
				match(RightParen);
				}
			}

			setState(246);
			match(T__1);
			setState(247);
			suite();
			setState(248);
			match(LeftParen);
			setState(249);
			arg();
			setState(250);
			match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncCallContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(Identifier);
			setState(253);
			match(LeftParen);
			setState(254);
			arg();
			setState(255);
			match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateFuncCallContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public CreateFuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createFuncCall; }
	}

	public final CreateFuncCallContext createFuncCall() throws RecognitionException {
		CreateFuncCallContext _localctx = new CreateFuncCallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_createFuncCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(Identifier);
			setState(258);
			match(LeftParen);
			setState(259);
			arg();
			setState(260);
			match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ArgDefContext argDef() {
			return getRuleContext(ArgDefContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(262);
				type();
				}
				break;
			}
			setState(265);
			match(Identifier);
			setState(266);
			match(LeftParen);
			setState(267);
			argDef();
			setState(268);
			match(RightParen);
			setState(269);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode DecimalInteger() { return getToken(MxParser.DecimalInteger, 0); }
		public TerminalNode BoolConstant() { return getToken(MxParser.BoolConstant, 0); }
		public TerminalNode StringConstant() { return getToken(MxParser.StringConstant, 0); }
		public TerminalNode NullConstant() { return getToken(MxParser.NullConstant, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolConstant) | (1L << NullConstant) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				basicType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypewithargContext extends ParserRuleContext {
		public WrongarraywithargContext wrongarraywitharg() {
			return getRuleContext(WrongarraywithargContext.class,0);
		}
		public ArraywithargContext arraywitharg() {
			return getRuleContext(ArraywithargContext.class,0);
		}
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public TypewithargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typewitharg; }
	}

	public final TypewithargContext typewitharg() throws RecognitionException {
		TypewithargContext _localctx = new TypewithargContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typewitharg);
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				wrongarraywitharg();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				arraywitharg();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(279);
				basicType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasicTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BasicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicType; }
	}

	public final BasicTypeContext basicType() throws RecognitionException {
		BasicTypeContext _localctx = new BasicTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_basicType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			basicType();
			setState(287); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(285);
				match(LeftBracket);
				setState(286);
				match(RightBracket);
				}
				}
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LeftBracket );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArraywithargContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArraywithargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraywitharg; }
	}

	public final ArraywithargContext arraywitharg() throws RecognitionException {
		ArraywithargContext _localctx = new ArraywithargContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arraywitharg);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			basicType();
			setState(299);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(292);
					match(LeftBracket);
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
						{
						setState(293);
						expression(0);
						}
					}

					setState(296);
					match(RightBracket);
					}
					} 
				}
				setState(301);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			setState(306);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(302);
					match(LeftBracket);
					setState(303);
					match(RightBracket);
					}
					} 
				}
				setState(308);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WrongarraywithargContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public WrongarraywithargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wrongarraywitharg; }
	}

	public final WrongarraywithargContext wrongarraywitharg() throws RecognitionException {
		WrongarraywithargContext _localctx = new WrongarraywithargContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_wrongarraywitharg);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			basicType();
			setState(317);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(310);
					match(LeftBracket);
					setState(312);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
						{
						setState(311);
						expression(0);
						}
					}

					setState(314);
					match(RightBracket);
					}
					} 
				}
				setState(319);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			setState(322); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(320);
					match(LeftBracket);
					setState(321);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(324); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(330); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(326);
					match(LeftBracket);
					setState(327);
					expression(0);
					setState(328);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(332); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(341);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(334);
					match(LeftBracket);
					setState(336);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << BoolConstant) | (1L << NullConstant) | (1L << Identifier) | (1L << DecimalInteger) | (1L << StringConstant))) != 0)) {
						{
						setState(335);
						expression(0);
						}
					}

					setState(338);
					match(RightBracket);
					}
					} 
				}
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DotContext extends ParserRuleContext {
		public DotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dot; }
	}

	public final DotContext dot() throws RecognitionException {
		DotContext _localctx = new DotContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dot);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 14);
		case 7:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u015d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\7\2\66\n\2\f\2\16\29\13\2\3\3\3\3\3\3\3\3\5\3?\n\3\3\3\3\3"+
		"\3\3\3\3\5\3E\n\3\7\3G\n\3\f\3\16\3J\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\7\4S\n\4\f\4\16\4V\13\4\3\4\3\4\3\4\3\5\3\5\7\5]\n\5\f\5\16\5`\13\5\3"+
		"\5\3\5\3\6\3\6\5\6f\n\6\3\7\3\7\5\7j\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bz\n\b\3\b\3\b\3\b\3\b\3\b\5\b\u0081\n\b"+
		"\3\b\3\b\5\b\u0085\n\b\3\b\3\b\3\b\5\b\u008a\n\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\5\b\u0092\n\b\3\b\3\b\5\b\u0096\n\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009e"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\5\t\u00a5\n\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ad"+
		"\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00c8\n\t\f\t\16\t\u00cb\13\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00d4\n\n\3\13\3\13\3\13\5\13\u00d9\n\13"+
		"\3\13\3\13\3\13\3\13\7\13\u00df\n\13\f\13\16\13\u00e2\13\13\3\f\5\f\u00e5"+
		"\n\f\3\f\3\f\7\f\u00e9\n\f\f\f\16\f\u00ec\13\f\3\r\3\r\5\r\u00f0\n\r\3"+
		"\r\3\r\3\r\3\r\3\r\5\r\u00f7\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\5\20\u010a\n\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\22\3\22\5\22\u0116\n\22\3\23\3\23\3\23\5\23"+
		"\u011b\n\23\3\24\3\24\3\25\3\25\3\25\6\25\u0122\n\25\r\25\16\25\u0123"+
		"\3\26\3\26\3\26\5\26\u0129\n\26\3\26\7\26\u012c\n\26\f\26\16\26\u012f"+
		"\13\26\3\26\3\26\7\26\u0133\n\26\f\26\16\26\u0136\13\26\3\27\3\27\3\27"+
		"\5\27\u013b\n\27\3\27\7\27\u013e\n\27\f\27\16\27\u0141\13\27\3\27\3\27"+
		"\6\27\u0145\n\27\r\27\16\27\u0146\3\27\3\27\3\27\3\27\6\27\u014d\n\27"+
		"\r\27\16\27\u014e\3\27\3\27\5\27\u0153\n\27\3\27\7\27\u0156\n\27\f\27"+
		"\16\27\u0159\13\27\3\30\3\30\3\30\2\3\20\31\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\2\n\5\2\37\37#$++\3\2\34\"\4\2\30\33\60\61\4\2%"+
		"&))\3\2\'(\3\2#$\4\2\62\63\65\66\4\2\7\t\64\64\2\u0185\2\67\3\2\2\2\4"+
		":\3\2\2\2\6K\3\2\2\2\bZ\3\2\2\2\ne\3\2\2\2\fi\3\2\2\2\16\u009d\3\2\2\2"+
		"\20\u00ac\3\2\2\2\22\u00d3\3\2\2\2\24\u00d8\3\2\2\2\26\u00e4\3\2\2\2\30"+
		"\u00ed\3\2\2\2\32\u00fe\3\2\2\2\34\u0103\3\2\2\2\36\u0109\3\2\2\2 \u0111"+
		"\3\2\2\2\"\u0115\3\2\2\2$\u011a\3\2\2\2&\u011c\3\2\2\2(\u011e\3\2\2\2"+
		"*\u0125\3\2\2\2,\u0137\3\2\2\2.\u015a\3\2\2\2\60\66\5\6\4\2\61\66\5\36"+
		"\20\2\62\63\5\4\3\2\63\64\7-\2\2\64\66\3\2\2\2\65\60\3\2\2\2\65\61\3\2"+
		"\2\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\3\3\2\2\29\67"+
		"\3\2\2\2:;\5\"\22\2;>\7\64\2\2<=\7/\2\2=?\5\20\t\2><\3\2\2\2>?\3\2\2\2"+
		"?H\3\2\2\2@A\7.\2\2AD\7\64\2\2BC\7/\2\2CE\5\20\t\2DB\3\2\2\2DE\3\2\2\2"+
		"EG\3\2\2\2F@\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\5\3\2\2\2JH\3\2\2"+
		"\2KL\7\20\2\2LM\7\64\2\2MT\7\26\2\2NO\5\4\3\2OP\7-\2\2PS\3\2\2\2QS\5\36"+
		"\20\2RN\3\2\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3"+
		"\2\2\2WX\7\27\2\2XY\7-\2\2Y\7\3\2\2\2Z^\7\26\2\2[]\5\16\b\2\\[\3\2\2\2"+
		"]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ab\7\27\2\2b\t\3\2"+
		"\2\2cf\5\b\5\2df\5\16\b\2ec\3\2\2\2ed\3\2\2\2f\13\3\2\2\2gj\5\4\3\2hj"+
		"\5\20\t\2ig\3\2\2\2ih\3\2\2\2ij\3\2\2\2j\r\3\2\2\2kl\5\4\3\2lm\7-\2\2"+
		"m\u009e\3\2\2\2no\5\20\t\2op\7-\2\2p\u009e\3\2\2\2q\u009e\5\b\5\2rs\7"+
		"\n\2\2st\7\22\2\2tu\5\20\t\2uv\7\23\2\2vy\5\n\6\2wx\7\13\2\2xz\5\n\6\2"+
		"yw\3\2\2\2yz\3\2\2\2z\u009e\3\2\2\2{|\7\f\2\2|}\7\22\2\2}~\5\f\7\2~\u0080"+
		"\7-\2\2\177\u0081\5\20\t\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0084\7-\2\2\u0083\u0085\5\20\t\2\u0084\u0083\3\2"+
		"\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0089\7\23\2\2\u0087"+
		"\u008a\5\16\b\2\u0088\u008a\5\b\5\2\u0089\u0087\3\2\2\2\u0089\u0088\3"+
		"\2\2\2\u008a\u009e\3\2\2\2\u008b\u008c\7\16\2\2\u008c\u008d\7\22\2\2\u008d"+
		"\u008e\5\20\t\2\u008e\u0091\7\23\2\2\u008f\u0092\5\16\b\2\u0090\u0092"+
		"\5\b\5\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u009e\3\2\2\2\u0093"+
		"\u0095\7\17\2\2\u0094\u0096\5\20\t\2\u0095\u0094\3\2\2\2\u0095\u0096\3"+
		"\2\2\2\u0096\u0097\3\2\2\2\u0097\u009e\7-\2\2\u0098\u0099\7\5\2\2\u0099"+
		"\u009e\7-\2\2\u009a\u009b\7\6\2\2\u009b\u009e\7-\2\2\u009c\u009e\7-\2"+
		"\2\u009dk\3\2\2\2\u009dn\3\2\2\2\u009dq\3\2\2\2\u009dr\3\2\2\2\u009d{"+
		"\3\2\2\2\u009d\u008b\3\2\2\2\u009d\u0093\3\2\2\2\u009d\u0098\3\2\2\2\u009d"+
		"\u009a\3\2\2\2\u009d\u009c\3\2\2\2\u009e\17\3\2\2\2\u009f\u00a0\b\t\1"+
		"\2\u00a0\u00ad\5\22\n\2\u00a1\u00a4\7\r\2\2\u00a2\u00a5\5$\23\2\u00a3"+
		"\u00a5\5\34\17\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00ad\3"+
		"\2\2\2\u00a6\u00ad\5\32\16\2\u00a7\u00a8\t\2\2\2\u00a8\u00ad\5\20\t\n"+
		"\u00a9\u00aa\7*\2\2\u00aa\u00ad\5\20\t\t\u00ab\u00ad\5\30\r\2\u00ac\u009f"+
		"\3\2\2\2\u00ac\u00a1\3\2\2\2\u00ac\u00a6\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ac"+
		"\u00a9\3\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00c9\3\2\2\2\u00ae\u00af\f\16"+
		"\2\2\u00af\u00b0\7\3\2\2\u00b0\u00c8\5\20\t\17\u00b1\u00b2\f\b\2\2\u00b2"+
		"\u00b3\t\3\2\2\u00b3\u00c8\5\20\t\t\u00b4\u00b5\f\7\2\2\u00b5\u00b6\t"+
		"\4\2\2\u00b6\u00c8\5\20\t\b\u00b7\u00b8\f\6\2\2\u00b8\u00b9\t\5\2\2\u00b9"+
		"\u00c8\5\20\t\7\u00ba\u00bb\f\5\2\2\u00bb\u00bc\t\6\2\2\u00bc\u00c8\5"+
		"\20\t\6\u00bd\u00be\f\4\2\2\u00be\u00bf\7/\2\2\u00bf\u00c8\5\20\t\5\u00c0"+
		"\u00c1\f\20\2\2\u00c1\u00c2\7\24\2\2\u00c2\u00c3\5\20\t\2\u00c3\u00c4"+
		"\7\25\2\2\u00c4\u00c8\3\2\2\2\u00c5\u00c6\f\13\2\2\u00c6\u00c8\t\7\2\2"+
		"\u00c7\u00ae\3\2\2\2\u00c7\u00b1\3\2\2\2\u00c7\u00b4\3\2\2\2\u00c7\u00b7"+
		"\3\2\2\2\u00c7\u00ba\3\2\2\2\u00c7\u00bd\3\2\2\2\u00c7\u00c0\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\21\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00d4\5 \21\2\u00cd\u00d4"+
		"\7\21\2\2\u00ce\u00cf\7\22\2\2\u00cf\u00d0\5\20\t\2\u00d0\u00d1\7\23\2"+
		"\2\u00d1\u00d4\3\2\2\2\u00d2\u00d4\7\64\2\2\u00d3\u00cc\3\2\2\2\u00d3"+
		"\u00cd\3\2\2\2\u00d3\u00ce\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\23\3\2\2"+
		"\2\u00d5\u00d6\5\"\22\2\u00d6\u00d7\7\64\2\2\u00d7\u00d9\3\2\2\2\u00d8"+
		"\u00d5\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00e0\3\2\2\2\u00da\u00db\7."+
		"\2\2\u00db\u00dc\5\"\22\2\u00dc\u00dd\7\64\2\2\u00dd\u00df\3\2\2\2\u00de"+
		"\u00da\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2"+
		"\2\2\u00e1\25\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e5\5\20\t\2\u00e4\u00e3"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00ea\3\2\2\2\u00e6\u00e7\7.\2\2\u00e7"+
		"\u00e9\5\20\t\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3"+
		"\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\27\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00ef\7\24\2\2\u00ee\u00f0\7%\2\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f6\7\25\2\2\u00f2\u00f3\7\22\2\2\u00f3"+
		"\u00f4\5\24\13\2\u00f4\u00f5\7\23\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f2"+
		"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\4\2\2\u00f9"+
		"\u00fa\5\b\5\2\u00fa\u00fb\7\22\2\2\u00fb\u00fc\5\26\f\2\u00fc\u00fd\7"+
		"\23\2\2\u00fd\31\3\2\2\2\u00fe\u00ff\7\64\2\2\u00ff\u0100\7\22\2\2\u0100"+
		"\u0101\5\26\f\2\u0101\u0102\7\23\2\2\u0102\33\3\2\2\2\u0103\u0104\7\64"+
		"\2\2\u0104\u0105\7\22\2\2\u0105\u0106\5\26\f\2\u0106\u0107\7\23\2\2\u0107"+
		"\35\3\2\2\2\u0108\u010a\5\"\22\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2"+
		"\2\u010a\u010b\3\2\2\2\u010b\u010c\7\64\2\2\u010c\u010d\7\22\2\2\u010d"+
		"\u010e\5\24\13\2\u010e\u010f\7\23\2\2\u010f\u0110\5\b\5\2\u0110\37\3\2"+
		"\2\2\u0111\u0112\t\b\2\2\u0112!\3\2\2\2\u0113\u0116\5(\25\2\u0114\u0116"+
		"\5&\24\2\u0115\u0113\3\2\2\2\u0115\u0114\3\2\2\2\u0116#\3\2\2\2\u0117"+
		"\u011b\5,\27\2\u0118\u011b\5*\26\2\u0119\u011b\5&\24\2\u011a\u0117\3\2"+
		"\2\2\u011a\u0118\3\2\2\2\u011a\u0119\3\2\2\2\u011b%\3\2\2\2\u011c\u011d"+
		"\t\t\2\2\u011d\'\3\2\2\2\u011e\u0121\5&\24\2\u011f\u0120\7\24\2\2\u0120"+
		"\u0122\7\25\2\2\u0121\u011f\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3"+
		"\2\2\2\u0123\u0124\3\2\2\2\u0124)\3\2\2\2\u0125\u012d\5&\24\2\u0126\u0128"+
		"\7\24\2\2\u0127\u0129\5\20\t\2\u0128\u0127\3\2\2\2\u0128\u0129\3\2\2\2"+
		"\u0129\u012a\3\2\2\2\u012a\u012c\7\25\2\2\u012b\u0126\3\2\2\2\u012c\u012f"+
		"\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0134\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u0130\u0131\7\24\2\2\u0131\u0133\7\25\2\2\u0132\u0130\3"+
		"\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135"+
		"+\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u013f\5&\24\2\u0138\u013a\7\24\2\2"+
		"\u0139\u013b\5\20\t\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c"+
		"\3\2\2\2\u013c\u013e\7\25\2\2\u013d\u0138\3\2\2\2\u013e\u0141\3\2\2\2"+
		"\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0144\3\2\2\2\u0141\u013f"+
		"\3\2\2\2\u0142\u0143\7\24\2\2\u0143\u0145\7\25\2\2\u0144\u0142\3\2\2\2"+
		"\u0145\u0146\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u014c"+
		"\3\2\2\2\u0148\u0149\7\24\2\2\u0149\u014a\5\20\t\2\u014a\u014b\7\25\2"+
		"\2\u014b\u014d\3\2\2\2\u014c\u0148\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014c"+
		"\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0157\3\2\2\2\u0150\u0152\7\24\2\2"+
		"\u0151\u0153\5\20\t\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154"+
		"\3\2\2\2\u0154\u0156\7\25\2\2\u0155\u0150\3\2\2\2\u0156\u0159\3\2\2\2"+
		"\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158-\3\2\2\2\u0159\u0157\3"+
		"\2\2\2\u015a\u015b\7\3\2\2\u015b/\3\2\2\2+\65\67>DHRT^eiy\u0080\u0084"+
		"\u0089\u0091\u0095\u009d\u00a4\u00ac\u00c7\u00c9\u00d3\u00d8\u00e0\u00e4"+
		"\u00ea\u00ef\u00f6\u0109\u0115\u011a\u0123\u0128\u012d\u0134\u013a\u013f"+
		"\u0146\u014e\u0152\u0157";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}