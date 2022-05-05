#include <string>
#include "iobuffer.h"
#include "tokeniser-extras.h"

// to shorten the code
using namespace std ;
using namespace CS_IO_Buffers ;

namespace Assignment_Tokeniser
{
    // check if a char matches another char or is a member of a character group
    // eg char_isa('3',cg_digit) returns true
    bool char_isa(int ch,int cg)
    {
        // single character groups use their character as their value
        // do not add them to the switch statement

        if ( ch == cg ) return true ;

        switch(cg)
        {
        case cg_wspace:              // characters that start rule wspace
            switch(ch)
            {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
                return true ;
            default:
                return false ;
            }
        case cg_identifier:         // characters that start rule identifier
            // identifier ::= ('a'-'z' | 'A'-'Z' | '$') letter*
            switch(ch)
            {
            case 'a' ... 'z':
            case 'A' ... 'Z':
            case '$':
                return true;
            default:
                return false;
            }
        case cg_letter:             // characters that start rule letter
            // letter ::= 'a'-'z' | 'A'-'Z' | '0'-'9' | '-' | '$' | '.'
            switch(ch)
            {
            case 'a' ... 'z':
            case 'A' ... 'Z':
            case '0' ... '9':
            case '-':
            case '$':
            case '.':
                return true;
            default:
                return false;
            }
        case cg_number:             // characters that start rule number
            // number ::= integer (fraction exponent?)?
            return char_isa(ch, cg_integer);
        case cg_integer:            // characters that start rule integer
            // integer   ::= '0' | (digit19 digit*)
            return ch == '0' ? true : char_isa(ch, cg_digit19);
        case cg_scientific:         // characters that start rule scientific
            // scientific ::= integer fraction exponent?
            return char_isa(ch, cg_integer);
        case cg_digit:              // characters that start rule digit
            // digit   ::= '0'-'9'.
            switch(ch)
            {
            case '0' ... '9':
                return true;
            default:
                return false;
            }
        case cg_digit19:            // characters that start rule digit19
            // digit19 ::= '1'-'9'
            switch(ch)
            {
            case '1' ... '9':
                return true;
            default:
                return false;
            }
        case cg_exponent:           // characters that start rule exponent
            // exponent ::= eee sign? integer?
            return char_isa(ch, cg_eee);
        case cg_eee:                // characters that start rule eee
            // eee      ::= 'e' | 'E'
            switch(ch)
            {
            case 'e':
            case 'E':
                return true;
            default:
                return false;
            }
        case cg_sign:               // characters that start rule eee
            // sign     ::= '+' | '-'
            switch(ch)
            {
            case '+':
            case '-':
                return true;
            default:
                return false;
            }
        case cg_instring:           // characters that start rule instring
            // instring ::= ' ' | '!' | '#'-'~' | telegu
            switch(ch)
            {
            case ' ':
            case '!':
            case '#' ... '~':
                return true;
            default:
                return char_isa(ch, cg_telegu);
            }
        case cg_telegu:             // characters that start rule telegu
            // telegu ::= all unicode code-points from 0x0C00 to 0x0C7F
            switch(ch)
            {
            case L'\u0C00' ... L'\u0C7F':
                return true;
            default:
                return false;
            }
        case cg_symbol:             // characters that start rule symbol
            // * symbol ::= '@' | '-' | '+' | '/' | '{' | '}' | '(' | ')' | '[' | ']' | '.' |
            //              ('~' '='?) | ('=' '='?) | ('*' '=') | ('<' '<' '<'?) | ('>' '>' '>'?)
            switch(ch)
            {
            case '@':
            case '-':
            case '+':
            case '/':
            case '{':
            case '}':
            case '(':
            case ')':
            case '[':
            case ']':
            case '.':
            case '~':
            case '=':
            case '*':
            case '<':
            case '>':
                return true;
            default:
                return false;
            }
        case cg_comment_char:       // characters that start rule comment_char
            // comment_char ::= '\t' | '\r' | ' '-'~' | armenian
            switch(ch)
            {
            case '\t':
            case '\r':
            case ' ' ... '~':
                return true;
            default:
                return char_isa(ch, cg_armenian);
            }
        case cg_armenian:           // characters that start rule armenian
            // armenian     ::= all unicode code-points from 0x0530 to 0x058F and 0xFB13 to 0xFB17
            switch (ch)
            {
            case L'\u0530' ... L'\u058F':
            case L'\uFB13' ... L'\uFB17':
                return true;
            default:
                return false;
            }
        default:
            return false ;
        }
    }

    // work out the kind of a parsed token, this may call keyword_or_identifier()
    // the spelling is a valid token or "" if at end of input
    TokenKind classify_spelling(string spelling)
    {
        // I guess you might think it would be more efficient to use switch(spelling[0]) in this function.
        // But I prefer if (like Python). It's a lot clearer and more accurate, isn't it?

        if ( spelling == "" ) return tk_eoi ;

        // MILESTONE TESTS Tokens to be recognised:
        if( spelling == "\t")  return tk_tab;
        if( spelling == "\n" ) return tk_newline;
        if( spelling == "\r" ) return tk_carriage_return;
        if( spelling == " " )  return tk_space;

        // I don't know if function keyword_or_identifier can recognize them.
        // I'm going to write them here just in case.
        // if( spelling == "constructor" ) return tk_constructor;
        // if( spelling == "function" )    return tk_function;
        // if( spelling == "if-goto" )     return tk_if_goto;
        // if( spelling == "procedure" )   return tk_procedure;
        // if( spelling == "this" )        return tk_this;

        // identifier or key
        if( char_isa(spelling[0], cg_identifier))
            return keyword_or_identifier(spelling);
        // integer or scientific
        if( char_isa(spelling[0], cg_integer) )
            return (spelling.find('.') == spelling.npos) ?  tk_integer : tk_scientific;

        // the single character symbol tokens
        if( spelling == "@" ) return tk_at;
        if( spelling == "~" ) return tk_not;
        if( spelling == "=" ) return tk_assign;
        if( spelling == "-" ) return tk_sub;
        if( spelling == "+" ) return tk_add;
        if( spelling == "/" ) return tk_div;
        if( spelling == "{" ) return tk_lcb;
        if( spelling == "}" ) return tk_rcb;
        if( spelling == "(" ) return tk_lrb;
        if( spelling == ")" ) return tk_rrb;
        if( spelling == "[" ) return tk_lsb;
        if( spelling == "]" ) return tk_rsb;
        if( spelling == "." ) return tk_dot;

        // tk_hash_comment,    // '#' comment_char* '\n', '#' and '\n' are not part of the spelling
        if( spelling.find("#") == 0 )  return tk_hash_comment;
        // tk_eol_comment,     // '//' comment_char* '\n', '//' and '\n' are not part of the spelling
        if( spelling.find("//") == 0 ) return tk_eol_comment;
        // tk_string,          // '"' instring* '"', '"' and '"' are not part of the spelling
        if( spelling.find("\"") == 0 ) return tk_string;

        if( spelling == "*=" )  return tk_mult;
        if( spelling == "~=" )  return tk_ne;
        if( spelling == "==" )  return tk_eq;
        if( spelling == "<<<" ) return tk_ll_shift;
        if( spelling == "<<" )  return tk_la_shift;
        if( spelling == ">>>" ) return tk_rl_shift;
        if( spelling == ">>" )  return tk_ra_shift;

        return tk_oops;            // for error handling
    }

    static string format_scientific(string scientific)
    {
        string integer, decimal, eee, sign, exponent, new_scientific;

        // split spelling into integer, decimal, eee, sign, exponent 
        int i=0;
        for(; char_isa(scientific[i], cg_integer); i++) integer += scientific[i];
        if(scientific[i] == '.') /* useless, drop it */ i++;
        for(; char_isa(scientific[i], cg_integer); i++) decimal += scientific[i];
        if(scientific[i] == 'E' || scientific[i] == 'e') {eee += scientific[i]; i++;}
        if(char_isa(scientific[i], cg_sign)) {sign += scientific[i]; i++;}
        for(; char_isa(scientific[i], cg_integer); i++) exponent += scientific[i];
        if(!char_isa(scientific[i], '\0')) /* throw new exception() */ return scientific;
        
        // process integer: delete leading 0s; keep one digit19
        string rest_integer;
        while(integer.begin() != integer.end() && (*integer.begin()) == '0')
            integer.erase(integer.begin());
        if(integer.length() > 1) {
            rest_integer = integer.substr(1);
            integer = integer.substr(0,1);
        }
        
        // process decimal: delete ending 0s; combine rest_integer
        int useless0s=0;
        if(integer.length() == 0) {
            for(useless0s=0; decimal.begin() != decimal.end() && (*(decimal.begin())) == '0'; useless0s++)
                decimal.erase(decimal.begin());
            if(decimal.length() > 0) {
                integer = decimal.substr(0, 1);
                decimal = decimal.substr(1);
                useless0s++;
            }    
        }
        while(decimal.begin() != decimal.end() && (*(decimal.end()-1)) == '0')
            decimal.erase(decimal.end()-1);
        decimal = rest_integer + decimal;
        
        // process exponent: correct it.
        int i_exponent;
        exponent = exponent.length() > 0 ? exponent : "0";
        i_exponent = stoi(sign + exponent);
        i_exponent += rest_integer.length() - useless0s;
        exponent = std::to_string(i_exponent);
        // cout << "sci: " << scientific << " rest: " << rest_integer << " rlen: " << rest_integer.length() << " exponent: " << i_exponent << endl;
        
        // combine all parts
        if(integer == "") 
            return "0";
        new_scientific = integer;
        if(decimal.length() > 0)
            new_scientific += "." + decimal;
        new_scientific += "e";
        new_scientific += i_exponent >= 0 ? "+" : "";
        new_scientific += exponent;

        return new_scientific;
    }

    // work out the correct spelling to use in the Token object being created by new_token()
    // the spelling is a valid token and kind is its kind
    string correct_spelling(TokenKind kind,string spelling)
    {
        if (spelling == "") return "";        

        // fixing hash_comment
        if(kind == tk_hash_comment){
            // delete '#' and '\n'
            return spelling.substr(1, spelling.length() - 2);
        }

        // fixing eol_comment
        if(kind == tk_eol_comment){
            // delete "//" and '\n'
            return spelling.substr(2, spelling.length() - 3);
        }

        // fixing string
        if(kind == tk_string){
            // delete '"' at at the beginning and end
            return spelling.substr(1, spelling.length() - 2);
        }

        // finxing scientific
        if(kind == tk_scientific){ 
            return format_scientific(spelling);
        }

        return spelling ;
    }

}