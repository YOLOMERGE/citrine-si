grammar Unit;

// Parser Rules

expr                : left=expr op=('*'|'/') right=expr #operation
                    | '(' expr ')'                      #paren
                    | WORD                              #unit;
input               : expr EOF;


// Lexer Rules
WORD : [a-zA-Z'°"]+ ;
WHITESPACE : [ \t\r\n]+ -> skip;