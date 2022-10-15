package Parser;
// Generated from Mx.g4 by ANTLR 4.7.2
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
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Break=3, Continue=4, Int=5, Bool=6, String=7, If=8, Else=9, 
		For=10, New=11, While=12, Return=13, Class=14, This=15, LeftParen=16, 
		RightParen=17, LeftBracket=18, RightBracket=19, LeftBrace=20, RightBrace=21, 
		Dim=22, Less=23, LessEqual=24, Greater=25, GreaterEqual=26, LeftShift=27, 
		RightShift=28, Plus=29, Minus=30, Star=31, Divide=32, PlusPlus=33, MinusMinus=34, 
		And=35, Or=36, AndAnd=37, OrOr=38, Caret=39, Not=40, Tilde=41, Colon=42, 
		Semi=43, Comma=44, Assign=45, Equal=46, NotEqual=47, Identifier=48, DecimalInteger=49, 
		BoolConstant=50, StringConstant=51, NullConstant=52, Whitespace=53, Newline=54, 
		BlockComment=55, LineComment=56;
	public static final int
		RULE_program = 0, RULE_varDef = 1, RULE_classDef = 2, RULE_suite = 3, 
		RULE_body = 4, RULE_forexpr1 = 5, RULE_statement = 6, RULE_expression = 7, 
		RULE_primary = 8, RULE_argDef = 9, RULE_arg = 10, RULE_lambdaExpression = 11, 
		RULE_classPref = 12, RULE_funcCall = 13, RULE_funcDef = 14, RULE_literal = 15, 
		RULE_type = 16, RULE_typewitharg = 17, RULE_basicType = 18, RULE_array = 19, 
		RULE_arraywitharg = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "varDef", "classDef", "suite", "body", "forexpr1", "statement", 
			"expression", "primary", "argDef", "arg", "lambdaExpression", "classPref", 
			"funcCall", "funcDef", "literal", "type", "typewitharg", "basicType", 
			"array", "arraywitharg"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'.'", "'break'", "'continue'", "'int'", "'bool'", "'string'", 
			"'if'", "'else'", "'for'", "'new'", "'while'", "'return'", "'class'", 
			"'this'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'[]'", "'<'", "'<='", 
			"'>'", "'>='", "'<<'", "'>>'", "'+'", "'-'", "'*'", "'/'", "'++'", "'--'", 
			"'&'", "'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", "':'", "';'", "','", 
			"'='", "'=='", "'!='", null, null, null, null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "Break", "Continue", "Int", "Bool", "String", "If", 
			"Else", "For", "New", "While", "Return", "Class", "This", "LeftParen", 
			"RightParen", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
			"Dim", "Less", "LessEqual", "Greater", "GreaterEqual", "LeftShift", "RightShift", 
			"Plus", "Minus", "Star", "Divide", "PlusPlus", "MinusMinus", "And", "Or", 
			"AndAnd", "OrOr", "Caret", "Not", "Tilde", "Colon", "Semi", "Comma", 
			"Assign", "Equal", "NotEqual", "Identifier", "DecimalInteger", "BoolConstant", 
			"StringConstant", "NullConstant", "Whitespace", "Newline", "BlockComment", 
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
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(45);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(42);
					classDef();
					}
					break;
				case 2:
					{
					setState(43);
					funcDef();
					}
					break;
				case 3:
					{
					setState(44);
					varDef();
					}
					break;
				}
				}
				setState(49);
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
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			type();
			setState(51);
			match(Identifier);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(52);
				match(Assign);
				setState(53);
				expression(0);
				}
			}

			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(56);
				match(Comma);
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

				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
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

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(Class);
			setState(70);
			match(Identifier);
			setState(71);
			match(LeftBrace);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				{
				setState(72);
				varDef();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(RightBrace);
			setState(79);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(LeftBrace);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Break) | (1L << Continue) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << If) | (1L << For) | (1L << New) | (1L << While) | (1L << Return) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << Semi) | (1L << Identifier) | (1L << DecimalInteger) | (1L << BoolConstant) | (1L << StringConstant) | (1L << NullConstant))) != 0)) {
				{
				{
				setState(82);
				statement();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
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
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(90);
				statement();
				}
				break;
			case 2:
				{
				setState(91);
				suite();
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForexpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForexpr1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForexpr1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Forexpr1Context forexpr1() throws RecognitionException {
		Forexpr1Context _localctx = new Forexpr1Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_forexpr1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(94);
				varDef();
				}
				break;
			case 2:
				{
				setState(95);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VardefStmtContext extends StatementContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VardefStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVardefStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVardefStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVardefStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PureExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public PureExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPureExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPureExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPureExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				suite();
				}
				break;
			case 2:
				_localctx = new VardefStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				varDef();
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				match(If);
				setState(101);
				match(LeftParen);
				setState(102);
				expression(0);
				setState(103);
				match(RightParen);
				setState(104);
				((IfStmtContext)_localctx).thensent = body();
				setState(107);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(105);
					match(Else);
					setState(106);
					((IfStmtContext)_localctx).elsesent = body();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				match(For);
				setState(110);
				match(LeftParen);
				setState(111);
				forexpr1();
				setState(112);
				match(Semi);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger) | (1L << BoolConstant) | (1L << StringConstant) | (1L << NullConstant))) != 0)) {
					{
					setState(113);
					((ForStmtContext)_localctx).forexpr2 = expression(0);
					}
				}

				setState(116);
				match(Semi);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger) | (1L << BoolConstant) | (1L << StringConstant) | (1L << NullConstant))) != 0)) {
					{
					setState(117);
					((ForStmtContext)_localctx).forexpr3 = expression(0);
					}
				}

				setState(120);
				match(RightParen);
				setState(123);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(121);
					statement();
					}
					break;
				case 2:
					{
					setState(122);
					suite();
					}
					break;
				}
				}
				break;
			case 5:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				match(While);
				setState(126);
				match(LeftParen);
				setState(127);
				expression(0);
				setState(128);
				match(RightParen);
				setState(131);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(129);
					statement();
					}
					break;
				case 2:
					{
					setState(130);
					suite();
					}
					break;
				}
				}
				break;
			case 6:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(133);
				match(Return);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger) | (1L << BoolConstant) | (1L << StringConstant) | (1L << NullConstant))) != 0)) {
					{
					setState(134);
					expression(0);
					}
				}

				setState(137);
				match(Semi);
				}
				break;
			case 7:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(138);
				match(Break);
				setState(139);
				match(Semi);
				}
				break;
			case 8:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(140);
				match(Continue);
				setState(141);
				match(Semi);
				}
				break;
			case 9:
				_localctx = new PureExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(142);
				expression(0);
				setState(143);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(145);
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
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaContext extends ExpressionContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public LambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLogicExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLogicExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLogicExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallExprContext extends ExpressionContext {
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public FunctionCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
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
		public TerminalNode Plus() { return getToken(MxParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Caret() { return getToken(MxParser.Caret, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public ArithExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArithExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArithExpr(this);
			else return visitor.visitChildren(this);
		}
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
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(149);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(New);
				setState(151);
				typewitharg();
				}
				break;
			case 3:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
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
				setState(153);
				expression(9);
				}
				break;
			case 4:
				{
				_localctx = new LogicExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				((LogicExprContext)_localctx).op = match(Not);
				setState(155);
				expression(8);
				}
				break;
			case 5:
				{
				_localctx = new FunctionCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				funcCall();
				}
				break;
			case 6:
				{
				_localctx = new LambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157);
				lambdaExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(179);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(177);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(160);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(161);
						((ArithExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftShift) | (1L << RightShift) | (1L << Plus) | (1L << Minus) | (1L << Star) | (1L << Divide))) != 0)) ) {
							((ArithExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(162);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new LogicExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(163);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(164);
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
						setState(165);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(166);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(167);
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
						setState(168);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new LogicExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(169);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(170);
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
						setState(171);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(173);
						match(Assign);
						setState(174);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new UnaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(176);
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
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_primary);
		try {
			int _alt;
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DecimalInteger:
			case BoolConstant:
			case StringConstant:
			case NullConstant:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				literal();
				}
				break;
			case This:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(This);
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				match(LeftParen);
				setState(185);
				expression(0);
				setState(186);
				match(RightParen);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 4);
				{
				setState(188);
				match(Identifier);
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(189);
						match(LeftBracket);
						setState(190);
						expression(0);
						setState(191);
						match(RightBracket);
						}
						} 
					}
					setState(197);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArgDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArgDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArgDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgDefContext argDef() throws RecognitionException {
		ArgDefContext _localctx = new ArgDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(200);
				type();
				setState(201);
				match(Identifier);
				}
			}

			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(205);
				match(Comma);
				setState(206);
				type();
				setState(207);
				match(Identifier);
				}
				}
				setState(213);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger) | (1L << BoolConstant) | (1L << StringConstant) | (1L << NullConstant))) != 0)) {
				{
				setState(214);
				expression(0);
				}
			}

			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(217);
				match(Comma);
				setState(218);
				expression(0);
				}
				}
				setState(223);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lambdaExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(LeftBracket);
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==And) {
				{
				setState(225);
				match(And);
				}
			}

			setState(228);
			match(RightBracket);
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftParen) {
				{
				setState(229);
				match(LeftParen);
				setState(230);
				argDef();
				setState(231);
				match(RightParen);
				}
			}

			setState(235);
			match(T__0);
			setState(236);
			suite();
			setState(237);
			match(LeftParen);
			setState(238);
			arg();
			setState(239);
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

	public static class ClassPrefContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public ClassPrefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classPref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassPref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassPref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassPref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassPrefContext classPref() throws RecognitionException {
		ClassPrefContext _localctx = new ClassPrefContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_classPref);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(241);
					match(Identifier);
					setState(248);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==LeftBracket) {
						{
						{
						setState(242);
						match(LeftBracket);
						setState(243);
						expression(0);
						setState(244);
						match(RightBracket);
						}
						}
						setState(250);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					setState(251);
					match(T__1);
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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

	public static class FuncCallContext extends ParserRuleContext {
		public ClassPrefContext classPref() {
			return getRuleContext(ClassPrefContext.class,0);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			classPref();
			setState(258);
			match(Identifier);
			setState(259);
			match(LeftParen);
			setState(260);
			arg();
			setState(261);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(263);
				type();
				}
				break;
			}
			setState(266);
			match(Identifier);
			setState(267);
			match(LeftParen);
			setState(268);
			argDef();
			setState(269);
			match(RightParen);
			setState(270);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DecimalInteger) | (1L << BoolConstant) | (1L << StringConstant) | (1L << NullConstant))) != 0)) ) {
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
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		try {
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(274);
				basicType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(275);
				array();
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
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public ArraywithargContext arraywitharg() {
			return getRuleContext(ArraywithargContext.class,0);
		}
		public TypewithargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typewitharg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypewitharg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypewitharg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitTypewitharg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypewithargContext typewitharg() throws RecognitionException {
		TypewithargContext _localctx = new TypewithargContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typewitharg);
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				basicType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				arraywitharg();
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBasicType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBasicType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBasicType(this);
			else return visitor.visitChildren(this);
		}
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
		public List<TerminalNode> Dim() { return getTokens(MxParser.Dim); }
		public TerminalNode Dim(int i) {
			return getToken(MxParser.Dim, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
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
			setState(286); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(285);
				match(Dim);
				}
				}
				setState(288); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Dim );
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
		public List<TerminalNode> Int() { return getTokens(MxParser.Int); }
		public TerminalNode Int(int i) {
			return getToken(MxParser.Int, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public ArraywithargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraywitharg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArraywitharg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArraywitharg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArraywitharg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArraywithargContext arraywitharg() throws RecognitionException {
		ArraywithargContext _localctx = new ArraywithargContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arraywitharg);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			basicType();
			setState(294); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(291);
					match(LeftBracket);
					setState(292);
					match(Int);
					setState(293);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(296); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u012d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\7\2\60\n\2\f\2\16"+
		"\2\63\13\2\3\3\3\3\3\3\3\3\5\39\n\3\3\3\3\3\3\3\3\3\5\3?\n\3\7\3A\n\3"+
		"\f\3\16\3D\13\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\4\3"+
		"\4\3\4\3\5\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\5\3\5\3\6\3\6\5\6_\n\6\3\7\3"+
		"\7\5\7c\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bn\n\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\bu\n\b\3\b\3\b\5\by\n\b\3\b\3\b\3\b\5\b~\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u0086\n\b\3\b\3\b\5\b\u008a\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\5\b\u0095\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a1"+
		"\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\t\u00b4\n\t\f\t\16\t\u00b7\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\7\n\u00c4\n\n\f\n\16\n\u00c7\13\n\5\n\u00c9\n\n\3\13\3\13\3"+
		"\13\5\13\u00ce\n\13\3\13\3\13\3\13\3\13\7\13\u00d4\n\13\f\13\16\13\u00d7"+
		"\13\13\3\f\5\f\u00da\n\f\3\f\3\f\7\f\u00de\n\f\f\f\16\f\u00e1\13\f\3\r"+
		"\3\r\5\r\u00e5\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ec\n\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\7\16\u00f9\n\16\f\16\16\16\u00fc\13\16"+
		"\3\16\7\16\u00ff\n\16\f\16\16\16\u0102\13\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\5\20\u010b\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\5\22\u0117\n\22\3\23\3\23\5\23\u011b\n\23\3\24\3\24\3\25\3\25\6"+
		"\25\u0121\n\25\r\25\16\25\u0122\3\26\3\26\3\26\3\26\6\26\u0129\n\26\r"+
		"\26\16\26\u012a\3\26\2\3\20\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*\2\n\5\2  #$++\3\2\35\"\4\2\31\34\60\61\4\2%&))\3\2\'(\3\2#$\3"+
		"\2\63\66\4\2\7\t\62\62\2\u014d\2\61\3\2\2\2\4\64\3\2\2\2\6G\3\2\2\2\b"+
		"S\3\2\2\2\n^\3\2\2\2\fb\3\2\2\2\16\u0094\3\2\2\2\20\u00a0\3\2\2\2\22\u00c8"+
		"\3\2\2\2\24\u00cd\3\2\2\2\26\u00d9\3\2\2\2\30\u00e2\3\2\2\2\32\u0100\3"+
		"\2\2\2\34\u0103\3\2\2\2\36\u010a\3\2\2\2 \u0112\3\2\2\2\"\u0116\3\2\2"+
		"\2$\u011a\3\2\2\2&\u011c\3\2\2\2(\u011e\3\2\2\2*\u0124\3\2\2\2,\60\5\6"+
		"\4\2-\60\5\36\20\2.\60\5\4\3\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60\63\3\2"+
		"\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\3\3\2\2\2\63\61\3\2\2\2\64\65\5\"\22"+
		"\2\658\7\62\2\2\66\67\7/\2\2\679\5\20\t\28\66\3\2\2\289\3\2\2\29B\3\2"+
		"\2\2:;\7.\2\2;>\7\62\2\2<=\7/\2\2=?\5\20\t\2><\3\2\2\2>?\3\2\2\2?A\3\2"+
		"\2\2@:\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\7-"+
		"\2\2F\5\3\2\2\2GH\7\20\2\2HI\7\62\2\2IM\7\26\2\2JL\5\4\3\2KJ\3\2\2\2L"+
		"O\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\7\27\2\2QR\7-\2\2"+
		"R\7\3\2\2\2SW\7\26\2\2TV\5\16\b\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2"+
		"\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\27\2\2[\t\3\2\2\2\\_\5\16\b\2]_\5\b\5\2^"+
		"\\\3\2\2\2^]\3\2\2\2_\13\3\2\2\2`c\5\4\3\2ac\5\20\t\2b`\3\2\2\2ba\3\2"+
		"\2\2bc\3\2\2\2c\r\3\2\2\2d\u0095\5\b\5\2e\u0095\5\4\3\2fg\7\n\2\2gh\7"+
		"\22\2\2hi\5\20\t\2ij\7\23\2\2jm\5\n\6\2kl\7\13\2\2ln\5\n\6\2mk\3\2\2\2"+
		"mn\3\2\2\2n\u0095\3\2\2\2op\7\f\2\2pq\7\22\2\2qr\5\f\7\2rt\7-\2\2su\5"+
		"\20\t\2ts\3\2\2\2tu\3\2\2\2uv\3\2\2\2vx\7-\2\2wy\5\20\t\2xw\3\2\2\2xy"+
		"\3\2\2\2yz\3\2\2\2z}\7\23\2\2{~\5\16\b\2|~\5\b\5\2}{\3\2\2\2}|\3\2\2\2"+
		"~\u0095\3\2\2\2\177\u0080\7\16\2\2\u0080\u0081\7\22\2\2\u0081\u0082\5"+
		"\20\t\2\u0082\u0085\7\23\2\2\u0083\u0086\5\16\b\2\u0084\u0086\5\b\5\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086\u0095\3\2\2\2\u0087\u0089"+
		"\7\17\2\2\u0088\u008a\5\20\t\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2"+
		"\u008a\u008b\3\2\2\2\u008b\u0095\7-\2\2\u008c\u008d\7\5\2\2\u008d\u0095"+
		"\7-\2\2\u008e\u008f\7\6\2\2\u008f\u0095\7-\2\2\u0090\u0091\5\20\t\2\u0091"+
		"\u0092\7-\2\2\u0092\u0095\3\2\2\2\u0093\u0095\7-\2\2\u0094d\3\2\2\2\u0094"+
		"e\3\2\2\2\u0094f\3\2\2\2\u0094o\3\2\2\2\u0094\177\3\2\2\2\u0094\u0087"+
		"\3\2\2\2\u0094\u008c\3\2\2\2\u0094\u008e\3\2\2\2\u0094\u0090\3\2\2\2\u0094"+
		"\u0093\3\2\2\2\u0095\17\3\2\2\2\u0096\u0097\b\t\1\2\u0097\u00a1\5\22\n"+
		"\2\u0098\u0099\7\r\2\2\u0099\u00a1\5$\23\2\u009a\u009b\t\2\2\2\u009b\u00a1"+
		"\5\20\t\13\u009c\u009d\7*\2\2\u009d\u00a1\5\20\t\n\u009e\u00a1\5\34\17"+
		"\2\u009f\u00a1\5\30\r\2\u00a0\u0096\3\2\2\2\u00a0\u0098\3\2\2\2\u00a0"+
		"\u009a\3\2\2\2\u00a0\u009c\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2"+
		"\2\2\u00a1\u00b5\3\2\2\2\u00a2\u00a3\f\t\2\2\u00a3\u00a4\t\3\2\2\u00a4"+
		"\u00b4\5\20\t\n\u00a5\u00a6\f\b\2\2\u00a6\u00a7\t\4\2\2\u00a7\u00b4\5"+
		"\20\t\t\u00a8\u00a9\f\7\2\2\u00a9\u00aa\t\5\2\2\u00aa\u00b4\5\20\t\b\u00ab"+
		"\u00ac\f\6\2\2\u00ac\u00ad\t\6\2\2\u00ad\u00b4\5\20\t\7\u00ae\u00af\f"+
		"\5\2\2\u00af\u00b0\7/\2\2\u00b0\u00b4\5\20\t\6\u00b1\u00b2\f\f\2\2\u00b2"+
		"\u00b4\t\7\2\2\u00b3\u00a2\3\2\2\2\u00b3\u00a5\3\2\2\2\u00b3\u00a8\3\2"+
		"\2\2\u00b3\u00ab\3\2\2\2\u00b3\u00ae\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4"+
		"\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\21\3\2\2"+
		"\2\u00b7\u00b5\3\2\2\2\u00b8\u00c9\5 \21\2\u00b9\u00c9\7\21\2\2\u00ba"+
		"\u00bb\7\22\2\2\u00bb\u00bc\5\20\t\2\u00bc\u00bd\7\23\2\2\u00bd\u00c9"+
		"\3\2\2\2\u00be\u00c5\7\62\2\2\u00bf\u00c0\7\24\2\2\u00c0\u00c1\5\20\t"+
		"\2\u00c1\u00c2\7\25\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c4"+
		"\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c9\3\2"+
		"\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00b8\3\2\2\2\u00c8\u00b9\3\2\2\2\u00c8"+
		"\u00ba\3\2\2\2\u00c8\u00be\3\2\2\2\u00c9\23\3\2\2\2\u00ca\u00cb\5\"\22"+
		"\2\u00cb\u00cc\7\62\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00ca\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\u00d5\3\2\2\2\u00cf\u00d0\7.\2\2\u00d0\u00d1\5\""+
		"\22\2\u00d1\u00d2\7\62\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00cf\3\2\2\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\25\3\2\2"+
		"\2\u00d7\u00d5\3\2\2\2\u00d8\u00da\5\20\t\2\u00d9\u00d8\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00df\3\2\2\2\u00db\u00dc\7.\2\2\u00dc\u00de\5\20"+
		"\t\2\u00dd\u00db\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\27\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e4\7\24\2"+
		"\2\u00e3\u00e5\7%\2\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6"+
		"\3\2\2\2\u00e6\u00eb\7\25\2\2\u00e7\u00e8\7\22\2\2\u00e8\u00e9\5\24\13"+
		"\2\u00e9\u00ea\7\23\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e7\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\7\3\2\2\u00ee\u00ef\5\b"+
		"\5\2\u00ef\u00f0\7\22\2\2\u00f0\u00f1\5\26\f\2\u00f1\u00f2\7\23\2\2\u00f2"+
		"\31\3\2\2\2\u00f3\u00fa\7\62\2\2\u00f4\u00f5\7\24\2\2\u00f5\u00f6\5\20"+
		"\t\2\u00f6\u00f7\7\25\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00ff\7\4\2\2\u00fe\u00f3\3\2\2\2\u00ff"+
		"\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\33\3\2\2"+
		"\2\u0102\u0100\3\2\2\2\u0103\u0104\5\32\16\2\u0104\u0105\7\62\2\2\u0105"+
		"\u0106\7\22\2\2\u0106\u0107\5\26\f\2\u0107\u0108\7\23\2\2\u0108\35\3\2"+
		"\2\2\u0109\u010b\5\"\22\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\u010d\7\62\2\2\u010d\u010e\7\22\2\2\u010e\u010f\5"+
		"\24\13\2\u010f\u0110\7\23\2\2\u0110\u0111\5\b\5\2\u0111\37\3\2\2\2\u0112"+
		"\u0113\t\b\2\2\u0113!\3\2\2\2\u0114\u0117\5&\24\2\u0115\u0117\5(\25\2"+
		"\u0116\u0114\3\2\2\2\u0116\u0115\3\2\2\2\u0117#\3\2\2\2\u0118\u011b\5"+
		"&\24\2\u0119\u011b\5*\26\2\u011a\u0118\3\2\2\2\u011a\u0119\3\2\2\2\u011b"+
		"%\3\2\2\2\u011c\u011d\t\t\2\2\u011d\'\3\2\2\2\u011e\u0120\5&\24\2\u011f"+
		"\u0121\7\30\2\2\u0120\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0120\3"+
		"\2\2\2\u0122\u0123\3\2\2\2\u0123)\3\2\2\2\u0124\u0128\5&\24\2\u0125\u0126"+
		"\7\24\2\2\u0126\u0127\7\7\2\2\u0127\u0129\7\25\2\2\u0128\u0125\3\2\2\2"+
		"\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b+\3"+
		"\2\2\2$/\618>BMW^bmtx}\u0085\u0089\u0094\u00a0\u00b3\u00b5\u00c5\u00c8"+
		"\u00cd\u00d5\u00d9\u00df\u00e4\u00eb\u00fa\u0100\u010a\u0116\u011a\u0122"+
		"\u012a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}