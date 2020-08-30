package ejerhtml;
import java.util.ArrayList;
import java_cup.runtime.*;

%%
%class Lexer
%line
%column
%cup

%{
    public ArrayList<Token> ts = new ArrayList<Token>();
    public String errlex = "";
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
%}

//alfabetos
letra = [a-zA-Z]
numero = [1-9]
espacio = [\n\t\r ]
com = [\"]

num = {numero}({numero}|"0")*
texto = ">" ({letra} | {num} | "0" | " " | {com})* "<"  
coments = "!--" ({letra} | {num} | "0" | " " | {com})* "--"
id = {letra} ({letra}|{num}|"0"|"_")*


%%
"input" {ts.add(new Token("Input: ",yytext()));return symbol(sym.input);}
"type" {ts.add(new Token("Tipo Variable: ",yytext()));return symbol(sym.type);}
"text" {ts.add(new Token("Dato Tipo Texto: ",yytext()));return symbol(sym.text);}
"number" {ts.add(new Token("Dato Tipo Número: ",yytext()));return symbol(sym.number);}
"date" {ts.add(new Token("Dato Tipo Fecha: ",yytext()));return symbol(sym.date);}
"radio" {ts.add(new Token("Botón Opción: ",yytext()));return symbol(sym.radio);}
"checkbox" {ts.add(new Token("Boton chequeo: ",yytext()));return symbol(sym.checkbox);}
"select" {ts.add(new Token("Lista: ",yytext()));return symbol(sym.lista);}
"option" {ts.add(new Token("Opción: ",yytext()));return symbol(sym.option);}
">" {ts.add(new Token("mayor: ",yytext()));return symbol(sym.mayor);}
"<" {ts.add(new Token("menor: ",yytext()));return symbol(sym.menor);}
"/" {ts.add(new Token("Slash: ",yytext()));return symbol(sym.division);}
{com} {ts.add(new Token("Comilla: ",yytext()));return symbol(sym.comilla);}
"=" {ts.add(new Token("Igual: ",yytext()));return symbol(sym.igual);}
"name" {ts.add(new Token("Nombre: ",yytext()));return symbol(sym.name);}
"Label" {ts.add(new Token("Label: ",yytext()));return symbol(sym.label);}
"h1" {ts.add(new Token("Etiqueta propuesta H1: ",yytext()));return symbol(sym.h1);}
"min" {ts.add(new Token("Parametro ",yytext()));return symbol(sym.min);}
"max" {ts.add(new Token("Parametro ",yytext()));return symbol(sym.max);}
"maxlength" {ts.add(new Token("Parametro ",yytext()));return symbol(sym.maxlength);}


{num} {ts.add(new Token("Valor: ",yytext()));return symbol(sym.num);}
{coments} {ts.add(new Token("Comentario: ",yytext()));return symbol(sym.coments);}
{id} {ts.add(new Token("Identificador",yytext()));return symbol(sym.id);}
{texto} {ts.add(new Token("Texto",yytext()));return symbol(sym.texto);}
{espacio} {}
. {errlex+=("\nError: Invalid symbol. Line " + (yyline+1) + " Column " + (yycolumn+1));}