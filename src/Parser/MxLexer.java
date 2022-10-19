package Parser;
// Generated from Mx.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "Break", "Continue", "Int", "Bool", "String", "If", "Else", 
			"For", "New", "While", "Return", "Class", "This", "LeftParen", "RightParen", 
			"LeftBracket", "RightBracket", "LeftBrace", "RightBrace", "Less", "LessEqual", 
			"Greater", "GreaterEqual", "LeftShift", "RightShift", "Plus", "Minus", 
			"Star", "Divide", "MOD", "PlusPlus", "MinusMinus", "And", "Or", "AndAnd", 
			"OrOr", "Caret", "Not", "Tilde", "Colon", "Semi", "Comma", "Assign", 
			"Equal", "NotEqual", "BoolConstant", "NullConstant", "Identifier", "DecimalInteger", 
			"StringConstant", "ESC", "Whitespace", "Newline", "BlockComment", "LineComment"
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


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0165\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3"+
		"!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3"+
		"*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\61\3\61\5\61\u0115\n\61\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\7\63\u011e\n\63\f\63\16\63\u0121\13\63\3\64\3\64\7\64\u0125"+
		"\n\64\f\64\16\64\u0128\13\64\3\64\5\64\u012b\n\64\3\65\3\65\3\65\7\65"+
		"\u0130\n\65\f\65\16\65\u0133\13\65\3\65\3\65\3\66\3\66\3\66\3\66\5\66"+
		"\u013b\n\66\3\67\6\67\u013e\n\67\r\67\16\67\u013f\3\67\3\67\38\38\58\u0146"+
		"\n8\38\58\u0149\n8\38\38\39\39\39\39\79\u0151\n9\f9\169\u0154\139\39\3"+
		"9\39\39\39\3:\3:\3:\3:\7:\u015f\n:\f:\16:\u0162\13:\3:\3:\4\u0131\u0152"+
		"\2;\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\2"+
		"m\67o8q9s:\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\63;\3\2\62;\4\2\13\13\"\""+
		"\4\2\f\f\17\17\2\u016f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5w\3\2\2\2\7"+
		"z\3\2\2\2\t\u0080\3\2\2\2\13\u0089\3\2\2\2\r\u008d\3\2\2\2\17\u0092\3"+
		"\2\2\2\21\u0099\3\2\2\2\23\u009c\3\2\2\2\25\u00a1\3\2\2\2\27\u00a5\3\2"+
		"\2\2\31\u00a9\3\2\2\2\33\u00af\3\2\2\2\35\u00b6\3\2\2\2\37\u00bc\3\2\2"+
		"\2!\u00c1\3\2\2\2#\u00c3\3\2\2\2%\u00c5\3\2\2\2\'\u00c7\3\2\2\2)\u00c9"+
		"\3\2\2\2+\u00cb\3\2\2\2-\u00cd\3\2\2\2/\u00cf\3\2\2\2\61\u00d2\3\2\2\2"+
		"\63\u00d4\3\2\2\2\65\u00d7\3\2\2\2\67\u00da\3\2\2\29\u00dd\3\2\2\2;\u00df"+
		"\3\2\2\2=\u00e1\3\2\2\2?\u00e3\3\2\2\2A\u00e5\3\2\2\2C\u00e7\3\2\2\2E"+
		"\u00ea\3\2\2\2G\u00ed\3\2\2\2I\u00ef\3\2\2\2K\u00f1\3\2\2\2M\u00f4\3\2"+
		"\2\2O\u00f7\3\2\2\2Q\u00f9\3\2\2\2S\u00fb\3\2\2\2U\u00fd\3\2\2\2W\u00ff"+
		"\3\2\2\2Y\u0101\3\2\2\2[\u0103\3\2\2\2]\u0105\3\2\2\2_\u0108\3\2\2\2a"+
		"\u0114\3\2\2\2c\u0116\3\2\2\2e\u011b\3\2\2\2g\u012a\3\2\2\2i\u012c\3\2"+
		"\2\2k\u013a\3\2\2\2m\u013d\3\2\2\2o\u0148\3\2\2\2q\u014c\3\2\2\2s\u015a"+
		"\3\2\2\2uv\7\60\2\2v\4\3\2\2\2wx\7/\2\2xy\7@\2\2y\6\3\2\2\2z{\7d\2\2{"+
		"|\7t\2\2|}\7g\2\2}~\7c\2\2~\177\7m\2\2\177\b\3\2\2\2\u0080\u0081\7e\2"+
		"\2\u0081\u0082\7q\2\2\u0082\u0083\7p\2\2\u0083\u0084\7v\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7p\2\2\u0086\u0087\7w\2\2\u0087\u0088\7g\2\2\u0088"+
		"\n\3\2\2\2\u0089\u008a\7k\2\2\u008a\u008b\7p\2\2\u008b\u008c\7v\2\2\u008c"+
		"\f\3\2\2\2\u008d\u008e\7d\2\2\u008e\u008f\7q\2\2\u008f\u0090\7q\2\2\u0090"+
		"\u0091\7n\2\2\u0091\16\3\2\2\2\u0092\u0093\7u\2\2\u0093\u0094\7v\2\2\u0094"+
		"\u0095\7t\2\2\u0095\u0096\7k\2\2\u0096\u0097\7p\2\2\u0097\u0098\7i\2\2"+
		"\u0098\20\3\2\2\2\u0099\u009a\7k\2\2\u009a\u009b\7h\2\2\u009b\22\3\2\2"+
		"\2\u009c\u009d\7g\2\2\u009d\u009e\7n\2\2\u009e\u009f\7u\2\2\u009f\u00a0"+
		"\7g\2\2\u00a0\24\3\2\2\2\u00a1\u00a2\7h\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4"+
		"\7t\2\2\u00a4\26\3\2\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8"+
		"\7y\2\2\u00a8\30\3\2\2\2\u00a9\u00aa\7y\2\2\u00aa\u00ab\7j\2\2\u00ab\u00ac"+
		"\7k\2\2\u00ac\u00ad\7n\2\2\u00ad\u00ae\7g\2\2\u00ae\32\3\2\2\2\u00af\u00b0"+
		"\7t\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7w\2\2\u00b3"+
		"\u00b4\7t\2\2\u00b4\u00b5\7p\2\2\u00b5\34\3\2\2\2\u00b6\u00b7\7e\2\2\u00b7"+
		"\u00b8\7n\2\2\u00b8\u00b9\7c\2\2\u00b9\u00ba\7u\2\2\u00ba\u00bb\7u\2\2"+
		"\u00bb\36\3\2\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7j\2\2\u00be\u00bf\7"+
		"k\2\2\u00bf\u00c0\7u\2\2\u00c0 \3\2\2\2\u00c1\u00c2\7*\2\2\u00c2\"\3\2"+
		"\2\2\u00c3\u00c4\7+\2\2\u00c4$\3\2\2\2\u00c5\u00c6\7]\2\2\u00c6&\3\2\2"+
		"\2\u00c7\u00c8\7_\2\2\u00c8(\3\2\2\2\u00c9\u00ca\7}\2\2\u00ca*\3\2\2\2"+
		"\u00cb\u00cc\7\177\2\2\u00cc,\3\2\2\2\u00cd\u00ce\7>\2\2\u00ce.\3\2\2"+
		"\2\u00cf\u00d0\7>\2\2\u00d0\u00d1\7?\2\2\u00d1\60\3\2\2\2\u00d2\u00d3"+
		"\7@\2\2\u00d3\62\3\2\2\2\u00d4\u00d5\7@\2\2\u00d5\u00d6\7?\2\2\u00d6\64"+
		"\3\2\2\2\u00d7\u00d8\7>\2\2\u00d8\u00d9\7>\2\2\u00d9\66\3\2\2\2\u00da"+
		"\u00db\7@\2\2\u00db\u00dc\7@\2\2\u00dc8\3\2\2\2\u00dd\u00de\7-\2\2\u00de"+
		":\3\2\2\2\u00df\u00e0\7/\2\2\u00e0<\3\2\2\2\u00e1\u00e2\7,\2\2\u00e2>"+
		"\3\2\2\2\u00e3\u00e4\7\61\2\2\u00e4@\3\2\2\2\u00e5\u00e6\7\'\2\2\u00e6"+
		"B\3\2\2\2\u00e7\u00e8\7-\2\2\u00e8\u00e9\7-\2\2\u00e9D\3\2\2\2\u00ea\u00eb"+
		"\7/\2\2\u00eb\u00ec\7/\2\2\u00ecF\3\2\2\2\u00ed\u00ee\7(\2\2\u00eeH\3"+
		"\2\2\2\u00ef\u00f0\7~\2\2\u00f0J\3\2\2\2\u00f1\u00f2\7(\2\2\u00f2\u00f3"+
		"\7(\2\2\u00f3L\3\2\2\2\u00f4\u00f5\7~\2\2\u00f5\u00f6\7~\2\2\u00f6N\3"+
		"\2\2\2\u00f7\u00f8\7`\2\2\u00f8P\3\2\2\2\u00f9\u00fa\7#\2\2\u00faR\3\2"+
		"\2\2\u00fb\u00fc\7\u0080\2\2\u00fcT\3\2\2\2\u00fd\u00fe\7<\2\2\u00feV"+
		"\3\2\2\2\u00ff\u0100\7=\2\2\u0100X\3\2\2\2\u0101\u0102\7.\2\2\u0102Z\3"+
		"\2\2\2\u0103\u0104\7?\2\2\u0104\\\3\2\2\2\u0105\u0106\7?\2\2\u0106\u0107"+
		"\7?\2\2\u0107^\3\2\2\2\u0108\u0109\7#\2\2\u0109\u010a\7?\2\2\u010a`\3"+
		"\2\2\2\u010b\u010c\7v\2\2\u010c\u010d\7t\2\2\u010d\u010e\7w\2\2\u010e"+
		"\u0115\7g\2\2\u010f\u0110\7h\2\2\u0110\u0111\7c\2\2\u0111\u0112\7n\2\2"+
		"\u0112\u0113\7u\2\2\u0113\u0115\7g\2\2\u0114\u010b\3\2\2\2\u0114\u010f"+
		"\3\2\2\2\u0115b\3\2\2\2\u0116\u0117\7p\2\2\u0117\u0118\7w\2\2\u0118\u0119"+
		"\7n\2\2\u0119\u011a\7n\2\2\u011ad\3\2\2\2\u011b\u011f\t\2\2\2\u011c\u011e"+
		"\t\3\2\2\u011d\u011c\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120f\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0126\t\4\2\2"+
		"\u0123\u0125\t\5\2\2\u0124\u0123\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124"+
		"\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012b\3\2\2\2\u0128\u0126\3\2\2\2\u0129"+
		"\u012b\7\62\2\2\u012a\u0122\3\2\2\2\u012a\u0129\3\2\2\2\u012bh\3\2\2\2"+
		"\u012c\u0131\7$\2\2\u012d\u0130\5k\66\2\u012e\u0130\13\2\2\2\u012f\u012d"+
		"\3\2\2\2\u012f\u012e\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u0132\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0135\7$"+
		"\2\2\u0135j\3\2\2\2\u0136\u0137\7^\2\2\u0137\u013b\7$\2\2\u0138\u0139"+
		"\7^\2\2\u0139\u013b\7^\2\2\u013a\u0136\3\2\2\2\u013a\u0138\3\2\2\2\u013b"+
		"l\3\2\2\2\u013c\u013e\t\6\2\2\u013d\u013c\3\2\2\2\u013e\u013f\3\2\2\2"+
		"\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142"+
		"\b\67\2\2\u0142n\3\2\2\2\u0143\u0145\7\17\2\2\u0144\u0146\7\f\2\2\u0145"+
		"\u0144\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0149\7\f"+
		"\2\2\u0148\u0143\3\2\2\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014b\b8\2\2\u014bp\3\2\2\2\u014c\u014d\7\61\2\2\u014d\u014e\7,\2\2\u014e"+
		"\u0152\3\2\2\2\u014f\u0151\13\2\2\2\u0150\u014f\3\2\2\2\u0151\u0154\3"+
		"\2\2\2\u0152\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0155\3\2\2\2\u0154"+
		"\u0152\3\2\2\2\u0155\u0156\7,\2\2\u0156\u0157\7\61\2\2\u0157\u0158\3\2"+
		"\2\2\u0158\u0159\b9\2\2\u0159r\3\2\2\2\u015a\u015b\7\61\2\2\u015b\u015c"+
		"\7\61\2\2\u015c\u0160\3\2\2\2\u015d\u015f\n\7\2\2\u015e\u015d\3\2\2\2"+
		"\u015f\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0163"+
		"\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u0164\b:\2\2\u0164t\3\2\2\2\17\2\u0114"+
		"\u011f\u0126\u012a\u012f\u0131\u013a\u013f\u0145\u0148\u0152\u0160\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}