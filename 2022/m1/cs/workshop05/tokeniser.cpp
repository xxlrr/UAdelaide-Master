    // tokeniser implementation for the workshop example language

#include "iobuffer.h"
#include "tokeniser-extras.h"

// to shorten the code
using namespace std ;
using namespace CS_IO_Buffers ;

// we are extending the Workshop_Tokeniser namespace

namespace Workshop_Tokeniser
{
    // identifier ::= letter alnum*
    static void parse_identifier()
    {
        next_char_mustbe(cg_identifier);
        while (next_char_isa(cg_alnum))read_next_char();
    }

    // label ::= '(' identifier ')'
    static void parse_label()
    {
        next_char_mustbe('(');
        parse_identifier();
        next_char_mustbe(')');
    }

    // integer ::= '0' | (digit19 digit*)
    static void parse_integer()
    {
    //    next_char_mustbe(cg_integer);
    if(next_char_isa('0')) read_next_char(); else
    while (next_char_isa(cg_integer))read_next_char();
    }

    // these 4 functions are commented out to avoid unused function warnings
    // if you choose not to use them, delete them

    // lt_le := '<' | '<='
   // static void parse_lt_le()
    //{
   // 	next_char_mustbe('<');
    //    if(next_char_isa('=')) read_next_char(); 
    //}

    // assign_eq ::= '=' | '=='
    //static void parse_assign_eq()
    //{
    //	next_char_mustbe('=');
    //    if(next_char_isa('=')) read_next_char(); 
    //}

    // not_ne ::= '!' | '!='
    //static void parse_not_ne()
    //{
//	next_char_mustbe('!');
  //      if(next_char_isa('=')) read_next_char();
    //}

    // gt_ge ::= '>' | '>='
    //static void parse_gt_ge()
    //{
//	next_char_mustbe('>');
  //      if(next_char_isa('=')) read_next_char(); 
    //}

    // varop ::= lt_le | assign_eq | not_ne | gt_ge
    // ch is '<', '=', '!', or '>'
    static void parse_varop()
    {
        read_next_char();
        if ( next_char_isa('=')) read_next_char(); 
       // if ( next_char_isa('=')) read_next_char(); else
       // if ( next_char_isa('<') ) parse_lt_le(); else 
       // if ( next_char_isa('=') ) parse_assign_eq() ;else
       // if ( next_char_isa('!') ) parse_not_ne() ;else
       // if ( next_char_isa('>') ) parse_gt_ge() ;
        //did_not_find(cg_varop);
    }

    // token ::= wspace | identifier | label | integer | op | varop | symbol
    static void parse_token()
    {
        if ( next_char_isa(cg_wspace) ) read_next_char() ; else
        if ( next_char_isa(cg_identifier) ) parse_identifier() ; else
        if ( next_char_isa('(') ) parse_label() ; else
        if ( next_char_isa(cg_integer) ) parse_integer() ; else
        if ( next_char_isa(cg_op) ) read_next_char() ; else
        if ( next_char_isa(cg_varop) ) parse_varop() ; else
        if ( next_char_isa(cg_symbol) ) read_next_char() ; else
        if ( next_char_isa(EOF) ) ; else
        did_not_find_start_of_token() ;
    }

    // parse the next token in the input and return a new
    // Token object that describes its kind and spelling
    // Note: you must not call new_token() anywhere else in your program
    //
    Token read_next_token()
    {
        parse_token() ;

        return new_token() ;
    }
}


