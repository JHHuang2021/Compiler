grammar Mx;

program: (classDef | funcDef | (varDef ';'))*;

varDef:
	type Identifier ('=' expression)? (
		',' Identifier ('=' expression)?
	)*;
classDef:
	Class Identifier '{' ((varDef ';') | funcDef)* '}' ';';

suite: '{' statement* '}';
body: (suite | statement);
forexpr1: (varDef | expression)?;
statement:
	varDef ';'			# vardefStmt
	| expression ';'	# pureExprStmt
	| suite				# block
	| If '(' expression ')' thensent = body (
		Else elsesent = body
	)? # ifStmt
	| For '(' forexpr1 ';' forexpr2 = expression? ';' forexpr3 = expression? ')' (
		statement
		| suite
	)												# forStmt
	| While '(' expression ')' (statement | suite)	# whileStmt
	| Return (expression?) ';'						# returnStmt
	| Break ';'										# breakStmt
	| Continue ';'									# continueStmt
	| ';'											# emptyStmt;

expression:
	expression ('[' expression ']')												# exprArray
	| primary																	# atomExpr
	| expression '.' expression													# visitExpr
	| New (typewitharg | createFuncCall)										# newExpr
	| funcCall																	# functionCallExpr
	| expression afterop = ('++' | '--')										# unaryExpr
	| beforeop = ('++' | '--' | '~' | '-') expression							# unaryExpr
	| op = '!' expression														# logicExpr
	| expression op = ('*' | '/' | '%' | '+' | '-' | '<<' | '>>') expression	# arithExpr
	| expression op = ('==' | '!=' | '<' | '>' | '<=' | '>=') expression		# logicExpr
	| expression op = ('&' | '^' | '|') expression								# arithExpr
	| expression op = ('&&' | '||') expression									# logicExpr
	| expression '=' expression													# assignExpr
	| lambdaExpression															# lambda;

primary: literal | This | '(' expression ')' | Identifier;

argDef: (type Identifier)? (',' type Identifier)*;
arg: expression? ( ',' expression)*;
lambdaExpression:
	'[' '&'? ']' ('(' argDef ')')? '->' suite '(' arg ')';
funcCall: Identifier '(' arg ')';
createFuncCall: Identifier '(' arg ')';
funcDef: type? Identifier '(' argDef ')' suite;

literal:
	DecimalInteger
	| BoolConstant
	| StringConstant
	| NullConstant;

type: array | basicType;
typewitharg: wrongarraywitharg | arraywitharg | basicType;
basicType: Int | Bool | String | Identifier;
array: basicType ('[' ']')+;
arraywitharg: basicType ('[' expression? ']')* ('[' ']')*;
wrongarraywitharg:
	basicType ('[' expression? ']')* ('[' ']')+ (
		'[' expression ']'
	)+ ('[' expression? ']')*;

Break: 'break';
Continue: 'continue';
Int: 'int';
Bool: 'bool';
String: 'string';
If: 'if';
Else: 'else';
For: 'for';
New: 'new';
While: 'while';
Return: 'return';
Class: 'class';
This: 'this';

LeftParen: '(';
RightParen: ')';
LeftBracket: '[';
RightBracket: ']';
LeftBrace: '{';
RightBrace: '}';

Less: '<';
LessEqual: '<=';
Greater: '>';
GreaterEqual: '>=';
LeftShift: '<<';
RightShift: '>>';

Plus: '+';
Minus: '-';
Star: '*';
Divide: '/';
MOD: '%';
PlusPlus: '++';
MinusMinus: '--';

And: '&';
Or: '|';
AndAnd: '&&';
OrOr: '||';
Caret: '^';
Not: '!';
Tilde: '~';

Colon: ':';
Semi: ';';
Comma: ',';
dot: '.';

Assign: '=';
Equal: '==';
NotEqual: '!=';

// True: 'true'; False: 'false'; Null: 'null';

BoolConstant: 'true' | 'false';
NullConstant: 'null';
Identifier: [a-zA-Z] [a-zA-Z_0-9]*;
DecimalInteger: [1-9] [0-9]* | '0';
StringConstant: '"' (ESC | .)*? '"';
fragment ESC: '\\"' | '\\\\';

Whitespace: [ \t]+ -> skip;

Newline: ( '\r' '\n'? | '\n') -> skip;

BlockComment: '/*' .*? '*/' -> skip;

LineComment: '//' ~[\r\n]* -> skip;