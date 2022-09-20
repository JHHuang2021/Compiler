grammar Mx;

program: (classDef | funcDef | varDef)*;

varDef:
	type Identifier ('=' expression)? (
		',' Identifier ('=' expression)?
	)* ';';
classDef: Class Identifier '{' varDef* '}' ';';

suite: '{' statement* '}';

statement:
	suite		# block
	| varDef	# vardefStmt
	| If '(' expression ')' (statement | suite) (
		Else statement
		| suite
	)? # ifStmt
	| For '(' (varDef | expression)? ';' expression? ';' expression? ')' (
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
	primary																# atomExpr
	| New type															# newExpr
	| expression op = ('++' | '--')										# unaryExpr
	| op = ('++' | '--' | '!' | '~' | '-') expression					# unaryExpr
	| expression op = ('+' | '-' | '*' | '/' | '<<' | '>>') expression	# binaryExpr
	| expression op = (
		'=='
		| '!='
		| '<'
		| '>'
		| '<='
		| '>='
		| '&&'
		| '||'
	) expression				# binaryExpr
	| expression '=' expression	# assignExpr
	| funcCall					# functionCallExpr
	| This						# this;

primary:
	'(' expression ')'
	| Identifier ('[' expression ']')*
	| literal;

funcCall:
	((Identifier ('[' expression ']')*) '.')* Identifier '(' expression? (
		',' expression
	)* ')'
	| Identifier '(' expression? (',' expression)* ')';
funcDef:
	type? Identifier '(' (type Identifier)? (',' type Identifier)* ')' suite;

literal:
	DecimalInteger
	| BoolConstant
	| StringConstant
	| NullConstant;

type:
	Int
	| Bool
	| String
	| Identifier
	| type '[' expression? ']';

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
StringConstant: '"' .*? '"';
NullConstant: 'null';

Whitespace: [ \t]+ -> skip;

Newline: ( '\r' '\n'? | '\n') -> skip;

BlockComment: '/*' .*? '*/' -> skip;

LineComment: '//' ~[\r\n]* -> skip;