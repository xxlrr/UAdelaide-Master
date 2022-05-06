#include <string>
#include <math.h>
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
        int dPos=-1, ePos=-1;
        for(int i=0; i<scientific.length(); i++)
        {
            switch (scientific[i])
            {
            case '.':
                dPos = i;
                break;
            case 'E':
            case 'e':
                ePos = i;
                break;
            }
        }

        string s_integer, s_fraction, s_exponent;
        s_integer = dPos >= 0 ? scientific.substr(0, dPos) : scientific.substr(0, ePos);
        s_fraction = dPos >= 0 ? scientific.substr(dPos+1, ePos-dPos-1) : "0";
        s_exponent = ePos >= 0 ? s_exponent = scientific.substr(ePos+1) : "0";
        if(s_fraction == "")
            s_fraction = "0";
        if(s_exponent=="+" || s_exponent=="-" || s_exponent=="")
            s_exponent = "0";

        // cout << scientific << " " <<s_integer << " " << s_fraction << " " << s_exponent << endl;

        int integer, fraction, exponent;
        integer = stoi(s_integer);
        fraction = stoi(s_fraction);
        exponent = stoi(s_exponent);

        if(integer == 0 && fraction==0)
            return "0";

        int shift, zoom, odd, leading0s;
        if(integer != 0) {
            leading0s = s_fraction.length() - floor(log10(fraction)) - 1;
            shift = floor(log10(integer));
            zoom = pow(10, shift);
            odd = zoom == 0 ? 0 : integer % zoom;
            integer = zoom == 0 ? integer : integer / zoom;
            exponent = exponent + shift;
        }
        else 
        {
            leading0s = 0;
            shift = floor(log10(fraction));
            zoom = pow(10, shift);
            odd = zoom == 0 ? 0 : fraction % zoom;
            integer = zoom == 0 ? fraction : fraction / zoom;
            shift -=  s_fraction.length();
            exponent = exponent + shift;
            fraction = 0;
        }
        while(fraction!=0 && fraction%10==0) fraction /= 10;

        // cout << shift << " " << zoom << " " << odd << " " << leading0s << endl;

        string new_scientific;
        new_scientific = std::to_string(integer);
        if(odd != 0 || fraction != 0)
        {
            new_scientific +=  ".";
            if(odd != 0) {
                new_scientific += string(shift-(log10(odd)+1), '0') + std::to_string(odd);
            }
            if(fraction != 0) {
                new_scientific += string(leading0s, '0') + std::to_string(fraction);
            }
        }
        new_scientific.append("e").append(exponent >=0 ? "+" : "").append(::to_string(exponent));

        return new_scientific;
        // return scientific;
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