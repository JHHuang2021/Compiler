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
		T__0=1, Break=2, Continue=3, Int=4, Bool=5, String=6, If=7, Else=8, For=9, 
		New=10, While=11, Return=12, Class=13, This=14, LeftParen=15, RightParen=16, 
		LeftBracket=17, RightBracket=18, LeftBrace=19, RightBrace=20, Less=21, 
		LessEqual=22, Greater=23, GreaterEqual=24, LeftShift=25, RightShift=26, 
		Plus=27, Minus=28, Star=29, Divide=30, PlusPlus=31, MinusMinus=32, And=33, 
		Or=34, AndAnd=35, OrOr=36, Caret=37, Not=38, Tilde=39, Colon=40, Semi=41, 
		Comma=42, Assign=43, Equal=44, NotEqual=45, Identifier=46, DecimalInteger=47, 
		BoolConstant=48, StringConstant=49, NullConstant=50, Whitespace=51, Newline=52, 
		BlockComment=53, LineComment=54;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "Break", "Continue", "Int", "Bool", "String", "If", "Else", "For", 
			"New", "While", "Return", "Class", "This", "LeftParen", "RightParen", 
			"LeftBracket", "RightBracket", "LeftBrace", "RightBrace", "Less", "LessEqual", 
			"Greater", "GreaterEqual", "LeftShift", "RightShift", "Plus", "Minus", 
			"Star", "Divide", "PlusPlus", "MinusMinus", "And", "Or", "AndAnd", "OrOr", 
			"Caret", "Not", "Tilde", "Colon", "Semi", "Comma", "Assign", "Equal", 
			"NotEqual", "Identifier", "DecimalInteger", "BoolConstant", "StringConstant", 
			"NullConstant", "Whitespace", "Newline", "BlockComment", "LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'break'", "'continue'", "'int'", "'bool'", "'string'", 
			"'if'", "'else'", "'for'", "'new'", "'while'", "'return'", "'class'", 
			"'this'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'<'", "'<='", "'>'", 
			"'>='", "'<<'", "'>>'", "'+'", "'-'", "'*'", "'/'", "'++'", "'--'", "'&'", 
			"'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", "':'", "';'", "','", "'='", 
			"'=='", "'!='", null, null, null, null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Break", "Continue", "Int", "Bool", "String", "If", "Else", 
			"For", "New", "While", "Return", "Class", "This", "LeftParen", "RightParen", 
			"LeftBracket", "RightBracket", "LeftBrace", "RightBrace", "Less", "LessEqual", 
			"Greater", "GreaterEqual", "LeftShift", "RightShift", "Plus", "Minus", 
			"Star", "Divide", "PlusPlus", "MinusMinus", "And", "Or", "AndAnd", "OrOr", 
			"Caret", "Not", "Tilde", "Colon", "Semi", "Comma", "Assign", "Equal", 
			"NotEqual", "Identifier", "DecimalInteger", "BoolConstant", "StringConstant", 
			"NullConstant", "Whitespace", "Newline", "BlockComment", "LineComment"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\28\u0153\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3"+
		"$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3-\3.\3."+
		"\3.\3/\3/\7/\u0103\n/\f/\16/\u0106\13/\3\60\3\60\7\60\u010a\n\60\f\60"+
		"\16\60\u010d\13\60\3\60\5\60\u0110\n\60\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\5\61\u011b\n\61\3\62\3\62\7\62\u011f\n\62\f\62\16\62\u0122"+
		"\13\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\6\64\u012c\n\64\r\64\16"+
		"\64\u012d\3\64\3\64\3\65\3\65\5\65\u0134\n\65\3\65\5\65\u0137\n\65\3\65"+
		"\3\65\3\66\3\66\3\66\3\66\7\66\u013f\n\66\f\66\16\66\u0142\13\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\7\67\u014d\n\67\f\67\16\67\u0150"+
		"\13\67\3\67\3\67\4\u0120\u0140\28\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\63e\64g\65i\66k\67m8\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\63;\3"+
		"\2\62;\4\2\13\13\"\"\4\2\f\f\17\17\2\u015c\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2"+
		"\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\3o\3\2\2\2\5q\3\2\2\2\7w"+
		"\3\2\2\2\t\u0080\3\2\2\2\13\u0084\3\2\2\2\r\u0089\3\2\2\2\17\u0090\3\2"+
		"\2\2\21\u0093\3\2\2\2\23\u0098\3\2\2\2\25\u009c\3\2\2\2\27\u00a0\3\2\2"+
		"\2\31\u00a6\3\2\2\2\33\u00ad\3\2\2\2\35\u00b3\3\2\2\2\37\u00b8\3\2\2\2"+
		"!\u00ba\3\2\2\2#\u00bc\3\2\2\2%\u00be\3\2\2\2\'\u00c0\3\2\2\2)\u00c2\3"+
		"\2\2\2+\u00c4\3\2\2\2-\u00c6\3\2\2\2/\u00c9\3\2\2\2\61\u00cb\3\2\2\2\63"+
		"\u00ce\3\2\2\2\65\u00d1\3\2\2\2\67\u00d4\3\2\2\29\u00d6\3\2\2\2;\u00d8"+
		"\3\2\2\2=\u00da\3\2\2\2?\u00dc\3\2\2\2A\u00df\3\2\2\2C\u00e2\3\2\2\2E"+
		"\u00e4\3\2\2\2G\u00e6\3\2\2\2I\u00e9\3\2\2\2K\u00ec\3\2\2\2M\u00ee\3\2"+
		"\2\2O\u00f0\3\2\2\2Q\u00f2\3\2\2\2S\u00f4\3\2\2\2U\u00f6\3\2\2\2W\u00f8"+
		"\3\2\2\2Y\u00fa\3\2\2\2[\u00fd\3\2\2\2]\u0100\3\2\2\2_\u010f\3\2\2\2a"+
		"\u011a\3\2\2\2c\u011c\3\2\2\2e\u0125\3\2\2\2g\u012b\3\2\2\2i\u0136\3\2"+
		"\2\2k\u013a\3\2\2\2m\u0148\3\2\2\2op\7\60\2\2p\4\3\2\2\2qr\7d\2\2rs\7"+
		"t\2\2st\7g\2\2tu\7c\2\2uv\7m\2\2v\6\3\2\2\2wx\7e\2\2xy\7q\2\2yz\7p\2\2"+
		"z{\7v\2\2{|\7k\2\2|}\7p\2\2}~\7w\2\2~\177\7g\2\2\177\b\3\2\2\2\u0080\u0081"+
		"\7k\2\2\u0081\u0082\7p\2\2\u0082\u0083\7v\2\2\u0083\n\3\2\2\2\u0084\u0085"+
		"\7d\2\2\u0085\u0086\7q\2\2\u0086\u0087\7q\2\2\u0087\u0088\7n\2\2\u0088"+
		"\f\3\2\2\2\u0089\u008a\7u\2\2\u008a\u008b\7v\2\2\u008b\u008c\7t\2\2\u008c"+
		"\u008d\7k\2\2\u008d\u008e\7p\2\2\u008e\u008f\7i\2\2\u008f\16\3\2\2\2\u0090"+
		"\u0091\7k\2\2\u0091\u0092\7h\2\2\u0092\20\3\2\2\2\u0093\u0094\7g\2\2\u0094"+
		"\u0095\7n\2\2\u0095\u0096\7u\2\2\u0096\u0097\7g\2\2\u0097\22\3\2\2\2\u0098"+
		"\u0099\7h\2\2\u0099\u009a\7q\2\2\u009a\u009b\7t\2\2\u009b\24\3\2\2\2\u009c"+
		"\u009d\7p\2\2\u009d\u009e\7g\2\2\u009e\u009f\7y\2\2\u009f\26\3\2\2\2\u00a0"+
		"\u00a1\7y\2\2\u00a1\u00a2\7j\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7n\2\2"+
		"\u00a4\u00a5\7g\2\2\u00a5\30\3\2\2\2\u00a6\u00a7\7t\2\2\u00a7\u00a8\7"+
		"g\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7w\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac"+
		"\7p\2\2\u00ac\32\3\2\2\2\u00ad\u00ae\7e\2\2\u00ae\u00af\7n\2\2\u00af\u00b0"+
		"\7c\2\2\u00b0\u00b1\7u\2\2\u00b1\u00b2\7u\2\2\u00b2\34\3\2\2\2\u00b3\u00b4"+
		"\7v\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7u\2\2\u00b7"+
		"\36\3\2\2\2\u00b8\u00b9\7*\2\2\u00b9 \3\2\2\2\u00ba\u00bb\7+\2\2\u00bb"+
		"\"\3\2\2\2\u00bc\u00bd\7]\2\2\u00bd$\3\2\2\2\u00be\u00bf\7_\2\2\u00bf"+
		"&\3\2\2\2\u00c0\u00c1\7}\2\2\u00c1(\3\2\2\2\u00c2\u00c3\7\177\2\2\u00c3"+
		"*\3\2\2\2\u00c4\u00c5\7>\2\2\u00c5,\3\2\2\2\u00c6\u00c7\7>\2\2\u00c7\u00c8"+
		"\7?\2\2\u00c8.\3\2\2\2\u00c9\u00ca\7@\2\2\u00ca\60\3\2\2\2\u00cb\u00cc"+
		"\7@\2\2\u00cc\u00cd\7?\2\2\u00cd\62\3\2\2\2\u00ce\u00cf\7>\2\2\u00cf\u00d0"+
		"\7>\2\2\u00d0\64\3\2\2\2\u00d1\u00d2\7@\2\2\u00d2\u00d3\7@\2\2\u00d3\66"+
		"\3\2\2\2\u00d4\u00d5\7-\2\2\u00d58\3\2\2\2\u00d6\u00d7\7/\2\2\u00d7:\3"+
		"\2\2\2\u00d8\u00d9\7,\2\2\u00d9<\3\2\2\2\u00da\u00db\7\61\2\2\u00db>\3"+
		"\2\2\2\u00dc\u00dd\7-\2\2\u00dd\u00de\7-\2\2\u00de@\3\2\2\2\u00df\u00e0"+
		"\7/\2\2\u00e0\u00e1\7/\2\2\u00e1B\3\2\2\2\u00e2\u00e3\7(\2\2\u00e3D\3"+
		"\2\2\2\u00e4\u00e5\7~\2\2\u00e5F\3\2\2\2\u00e6\u00e7\7(\2\2\u00e7\u00e8"+
		"\7(\2\2\u00e8H\3\2\2\2\u00e9\u00ea\7~\2\2\u00ea\u00eb\7~\2\2\u00ebJ\3"+
		"\2\2\2\u00ec\u00ed\7`\2\2\u00edL\3\2\2\2\u00ee\u00ef\7#\2\2\u00efN\3\2"+
		"\2\2\u00f0\u00f1\7\u0080\2\2\u00f1P\3\2\2\2\u00f2\u00f3\7<\2\2\u00f3R"+
		"\3\2\2\2\u00f4\u00f5\7=\2\2\u00f5T\3\2\2\2\u00f6\u00f7\7.\2\2\u00f7V\3"+
		"\2\2\2\u00f8\u00f9\7?\2\2\u00f9X\3\2\2\2\u00fa\u00fb\7?\2\2\u00fb\u00fc"+
		"\7?\2\2\u00fcZ\3\2\2\2\u00fd\u00fe\7#\2\2\u00fe\u00ff\7?\2\2\u00ff\\\3"+
		"\2\2\2\u0100\u0104\t\2\2\2\u0101\u0103\t\3\2\2\u0102\u0101\3\2\2\2\u0103"+
		"\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105^\3\2\2\2"+
		"\u0106\u0104\3\2\2\2\u0107\u010b\t\4\2\2\u0108\u010a\t\5\2\2\u0109\u0108"+
		"\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u0110\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u0110\7\62\2\2\u010f\u0107\3"+
		"\2\2\2\u010f\u010e\3\2\2\2\u0110`\3\2\2\2\u0111\u0112\7v\2\2\u0112\u0113"+
		"\7t\2\2\u0113\u0114\7w\2\2\u0114\u011b\7g\2\2\u0115\u0116\7h\2\2\u0116"+
		"\u0117\7c\2\2\u0117\u0118\7n\2\2\u0118\u0119\7u\2\2\u0119\u011b\7g\2\2"+
		"\u011a\u0111\3\2\2\2\u011a\u0115\3\2\2\2\u011bb\3\2\2\2\u011c\u0120\7"+
		"$\2\2\u011d\u011f\13\2\2\2\u011e\u011d\3\2\2\2\u011f\u0122\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2"+
		"\2\2\u0123\u0124\7$\2\2\u0124d\3\2\2\2\u0125\u0126\7p\2\2\u0126\u0127"+
		"\7w\2\2\u0127\u0128\7n\2\2\u0128\u0129\7n\2\2\u0129f\3\2\2\2\u012a\u012c"+
		"\t\6\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\b\64\2\2\u0130h\3\2\2\2"+
		"\u0131\u0133\7\17\2\2\u0132\u0134\7\f\2\2\u0133\u0132\3\2\2\2\u0133\u0134"+
		"\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0137\7\f\2\2\u0136\u0131\3\2\2\2\u0136"+
		"\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\b\65\2\2\u0139j\3\2\2\2"+
		"\u013a\u013b\7\61\2\2\u013b\u013c\7,\2\2\u013c\u0140\3\2\2\2\u013d\u013f"+
		"\13\2\2\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u0141\3\2\2\2"+
		"\u0140\u013e\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144"+
		"\7,\2\2\u0144\u0145\7\61\2\2\u0145\u0146\3\2\2\2\u0146\u0147\b\66\2\2"+
		"\u0147l\3\2\2\2\u0148\u0149\7\61\2\2\u0149\u014a\7\61\2\2\u014a\u014e"+
		"\3\2\2\2\u014b\u014d\n\7\2\2\u014c\u014b\3\2\2\2\u014d\u0150\3\2\2\2\u014e"+
		"\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150\u014e\3\2"+
		"\2\2\u0151\u0152\b\67\2\2\u0152n\3\2\2\2\r\2\u0104\u010b\u010f\u011a\u0120"+
		"\u012d\u0133\u0136\u0140\u014e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}