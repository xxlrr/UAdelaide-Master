// tokeniser implementation for the workshop example language

#include "iobuffer.h"
#include "tokeniser-extras.h"

// to shorten the code
using namespace std ;
using namespace CS_IO_Buffers ;

// we are extending the Assignment_Tokeniser namespace

namespace Assignment_Tokeniser
{
    // wspace ::= '\t' | '\n' | '\r' | ' '
    static void parse_wspace()
    {
        next_char_mustbe(cg_wspace);
        // Nothing else to do 
    }

    // identifier ::= ('a'-'z' | 'A'-'Z' | '$') letter*
    static void parse_identifier()
    {
        next_char_mustbe(cg_identifier);
        while(next_char_isa(cg_letter)) read_next_char();
    }

    // * integer   ::= '0' | (digit19 digit*)
    static void parse_integer()
    {
        // '0'
        if(next_char_isa('0'))
        {
            read_next_char();
        }
        // (digit19 digit*)
        else
        {
            // digit19
            next_char_mustbe(cg_digit19);
            // digit*
            while(next_char_isa(cg_digit)) read_next_char();
        }
    }

    // number ::= integer (fraction exponent?)?
    static void parse_number()
    {
        parse_integer();
        // parsing the part: (fraction exponent?)?
        if(next_char_isa('.'))
        {
            // read '.'
            read_next_char();

            // digit*
            while (next_char_isa(cg_digit)) read_next_char();
            
            // eee sign? integer?
            if(next_char_isa(cg_eee))
            {
                read_next_char();
                // sign?
                if(next_char_isa(cg_sign))
                    read_next_char();
                // integer?
                if(next_char_isa(cg_integer))
                    parse_integer();
            } 

        }
    }

    // string ::= '"' instring* '"'
    static void parse_string()
    {
        next_char_mustbe('"');
        while(next_char_isa(cg_instring)) read_next_char();
        next_char_mustbe('"');
    }

    // 'constructor' | 'function' | 'if-goto' | 'procedure' | 'this'
    // static void parse_keyword(){}

    //   - eol_suffix   ::= '/' comment_char* '\n'
    static void parse_eol_suffix()
    {
        next_char_mustbe('/');
        while(next_char_isa(cg_comment_char)) read_next_char();
        next_char_mustbe('\n');
    }

    static void parse_eol_comment_or_symbol()
    {
        next_char_mustbe('/');
        if(next_char_isa('/')) return parse_eol_suffix();
        // else do nothing 
    }

    // symbol ::= '@' | '-' | '+' | '/' | '{' | '}' | '(' | ')' | '[' | ']' | '.' | 
    //      ('~' '='?) | ('=' '='?) | ('*' '=') | ('<' '<' '<'?) | ('>' '>' '>'?)
    static void parse_symbol()
    {
        // ('~' '='?) | ('=' '='?)
        if(next_char_isa('~') || next_char_isa('='))
        {
            read_next_char();
            if(next_char_isa('=')) read_next_char();
        }
        else if(next_char_isa('*'))
        {
            read_next_char();
            next_char_mustbe('=');
        }
        // ('<' '<' '<'?)
        else if(next_char_isa('<'))
        {
            read_next_char();
            next_char_mustbe('<');
            if(next_char_isa('<')) read_next_char();
        }
        // ('<' '<' '<'?)
        else if(next_char_isa('>'))
        {
            read_next_char();
            next_char_mustbe('>');
            if(next_char_isa('>')) read_next_char();
        }
        // other symbols
        else
        {
            next_char_mustbe(cg_symbol);
        }
    }

    // 'hash_comment   ::= '#' comment_char* '\n'
    static void parse_hash_comment()
    {
        next_char_mustbe('#');
        while(next_char_isa(cg_comment_char)) read_next_char();
        next_char_mustbe('\n');
    }

    // token ::= wspace | identifier | number | string | keyword | symbol | eol_comment | hash_comment
    static void parse_token()
    {
        if ( next_char_isa(cg_wspace) ) parse_wspace(); else
        if ( next_char_isa(cg_identifier) ) parse_identifier(); else
        if ( next_char_isa(cg_number) ) parse_number(); else
        if ( next_char_isa('"') ) parse_string(); else
        // if ( next_char_isa(cg_keyword) ) parse_keyword(); else
        if ( next_char_isa('#') ) parse_hash_comment(); else
        if ( next_char_isa('/') ) parse_eol_comment_or_symbol(); else
        if ( next_char_isa(cg_symbol) ) parse_symbol(); else
        if ( next_char_isa(EOF) ) ; else
        did_not_find_start_of_token() ;
    }

    // parse the next token in the input and return a new
    // Token object that describes its kind and spelling
    // Note: you must not call new_token() anywhere else in your program
    // Note: you should not modify this function
    //
    Token read_next_token()
    {
        parse_token() ;

        return new_token() ;
    }
}

