grammar Mx;

program: (classDef | funcDef | varDef)*;

varDef:
	type Identifier ('=' expression)? (
		',' Identifier ('=' expression)?
	)* ';';
classDef: Class Identifier '{' varDef* '}' ';';

suite: '{' statement* '}';
body: (statement | suite);
forexpr1: (varDef | expression)?;
statement:
	suite		# block
	| varDef	# vardefStmt
	| If '(' expression ')' thensent = body (
		Else elsesent = body
	)? # ifStmt
	| For '(' forexpr1 ';' forexpr2 = expression? ';' forexpr3 = expression? ')' (
		statement
		| suite
	)												# forStmt
	| While '(' expression ')' (statement | suite)	# whileStmt
	| Return expression? ';'						# returnStmt
	| Break ';'										# breakStmt
	| Continue ';'									# continueStmt
	| expression ';'								# pureExprStmt
	| ';'											# emptyStmt;

expression:
	primary																	# atomExpr
	| New typewitharg														# newExpr
	| expression afterop = ('++' | '--')									# unaryExpr
	| beforeop = ('++' | '--' | '~' | '-') expression						# unaryExpr
	| op = '!' expression													# logicExpr
	| expression op = ('*' | '/' | '+' | '-' | '<<' | '>>') expression		# arithExpr
	| expression op = ('==' | '!=' | '<' | '>' | '<=' | '>=') expression	# logicExpr
	| expression op = ('&' | '^' | '|') expression							# bitExpr
	| expression op = ('&&' | '||') expression								# logicExpr
	| expression '=' expression												# assignExpr
	| funcCall																# functionCallExpr
	| lambdaExpression														# lambda;

primary:
	literal
	| This
	| '(' expression ')'
	| Identifier ('[' expression ']')*;

argDef: (type Identifier)? (',' type Identifier)*;
arg: expression? ( ',' expression)*;
lambdaExpression:
	'[' '&'? ']' ('(' argDef ')')? '->' suite '(' arg ')';

funcCall:
	((Identifier ('[' expression ']')*) '.')* Identifier '(' arg ')'
	| Identifier '(' arg ')';
funcDef: type? Identifier '(' argDef ')' suite;

literal:
	DecimalInteger
	| BoolConstant
	| StringConstant
	| NullConstant;

type: basicType | array;
typewitharg: basicType | arraywitharg;
basicType: Int | Bool | String | Identifier;
array: basicType '[]'+;
arraywitharg: basicType ('[' Int ']')+;

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
Dim: '[]';

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

Assign: '=';
Equal: '==';
NotEqual: '!=';

Identifier: [a-zA-Z] [a-zA-Z_0-9]*;

DecimalInteger: [1-9] [0-9]* | '0';
BoolConstant: 'true' | 'false';
StringConstant: '"' (ESC | .)*? '"';
fragment ESC: '\\"' | '\\\\';
NullConstant: 'null';

Whitespace: [ \t]+ -> skip;

Newline: ( '\r' '\n'? | '\n') -> skip;

BlockComment: '/*' .*? '*/' -> skip;

LineComment: '//' ~[\r\n]* -> skip;