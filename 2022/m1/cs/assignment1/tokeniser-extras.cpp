/**
 * @file tokeniser-extras.cpp
 * @author Hongxing Hao (a1845302@adelaide.edu.au)
 * @brief mainly provided support functions: char_isa(), classify_spelling(), correct_spelling()
 * @version 0.1
 * @date 2022-05-05
 * 
 * @copyright Copyright (c) 2022 
 */

#include <string>
#include <cmath>
#include "iobuffer.h"
#include "tokeniser-extras.h"

// to shorten the code
using namespace std ;
using namespace CS_IO_Buffers ;

namespace Assignment_Tokeniser
{
    // check if a char matches another char or is a member of a character group
    // eg char_isa('3',cg_digit) returns true
    bool char_isa(int ch, int cg)
    {

        // single character groups use their character as their value
        // do not add them to the switch statement
        if (ch == cg)
            return true;

        switch (cg)
        {
        // characters that start rule wspace
        case cg_wspace:
            switch (ch)
            {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
                return true;
            default:
                return false;
            }

        // characters that start rule identifier
        // identifier ::= ('a'-'z' | 'A'-'Z' | '$') letter*
        case cg_identifier:
            switch (ch)
            {
            case 'a' ... 'z':
            case 'A' ... 'Z':
            case '$':
                return true;
            default:
                return false;
            }

        // characters that start rule letter
        // letter ::= 'a'-'z' | 'A'-'Z' | '0'-'9' | '-' | '$' | '.'
        case cg_letter:
            switch (ch)
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

        // characters that start rule number
        // number ::= integer (fraction exponent?)?
        case cg_number:
            return char_isa(ch, cg_integer);

        // characters that start rule integer
        // integer   ::= '0' | (digit19 digit*)
        case cg_integer:
            return ch == '0' ? true : char_isa(ch, cg_digit19);

        // characters that start rule scientific
        // scientific ::= integer fraction exponent?
        case cg_scientific:
            return char_isa(ch, cg_integer);

        // characters that start rule digit
        // digit   ::= '0'-'9'.
        case cg_digit:
            switch (ch)
            {
            case '0' ... '9':
                return true;
            default:
                return false;
            }

        // characters that start rule digit19
        // digit19 ::= '1'-'9'
        case cg_digit19:
            switch (ch)
            {
            case '1' ... '9':
                return true;
            default:
                return false;
            }

        // characters that start rule exponent
        // exponent ::= eee sign? integer?
        case cg_exponent:
            return char_isa(ch, cg_eee);

        // characters that start rule eee
        // eee      ::= 'e' | 'E'
        case cg_eee:
            switch (ch)
            {
            case 'e':
            case 'E':
                return true;
            default:
                return false;
            }

        // characters that start rule eee
        // sign     ::= '+' | '-'
        case cg_sign:
            switch (ch)
            {
            case '+':
            case '-':
                return true;
            default:
                return false;
            }

        // characters that start rule instring
        // instring ::= ' ' | '!' | '#'-'~' | telegu
        case cg_instring:
            switch (ch)
            {
            case ' ':
            case '!':
            case '#' ... '~':
                return true;
            default:
                return char_isa(ch, cg_telegu);
            }

        // characters that start rule telegu
        // telegu ::= all unicode code-points from 0x0C00 to 0x0C7F
        case cg_telegu:
            switch (ch)
            {
            case L'\u0C00' ... L'\u0C7F':
                return true;
            default:
                return false;
            }

        // characters that start rule symbol
        // * symbol ::= '@' | '-' | '+' | '/' | '{' | '}' | '(' | ')' | '[' | ']' | '.' |
        //              ('~' '='?) | ('=' '='?) | ('*' '=') | ('<' '<' '<'?) | ('>' '>' '>'?)
        case cg_symbol:
            switch (ch)
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

        // characters that start rule comment_char
        // comment_char ::= '\t' | '\r' | ' '-'~' | armenian
        case cg_comment_char:
            switch (ch)
            {
            case '\t':
            case '\r':
            case ' ' ... '~':
                return true;
            default:
                return char_isa(ch, cg_armenian);
            }

        // characters that start rule armenian
        // armenian     ::= all unicode code-points from 0x0530 to 0x058F and 0xFB13 to 0xFB17
        case cg_armenian:
            switch (ch)
            {
            case L'\u0530' ... L'\u058F':
            case L'\uFB13' ... L'\uFB17':
                return true;
            default:
                return false;
            }
        default:
            return false;
        }
    }

    // work out the kind of a parsed token, this may call keyword_or_identifier()
    // the spelling is a valid token or "" if at end of input
    TokenKind classify_spelling(string spelling)
    {

        // I guess you might think it would be more efficient to use switch (spelling[0]) in this function.
        // But I prefer if (like Python). It's a lot clearer and more accurate, isn't it?

        if (spelling == "") return tk_eoi;

        // MILESTONE TESTS Tokens to be recognised:
        if (spelling == "\t") return tk_tab;
        if (spelling == "\n") return tk_newline;
        if (spelling == "\r") return tk_carriage_return;
        if (spelling == " " ) return tk_space;

        // identifier or key
        if (char_isa(spelling[0], cg_identifier))
            return keyword_or_identifier(spelling);

        // integer or scientific
        if (char_isa(spelling[0], cg_integer))
            return (spelling.find('.') == spelling.npos) ? tk_integer : tk_scientific;

        // the single character symbol tokens
        if (spelling == "@") return tk_at;
        if (spelling == "~") return tk_not;
        if (spelling == "=") return tk_assign;
        if (spelling == "-") return tk_sub;
        if (spelling == "+") return tk_add;
        if (spelling == "/") return tk_div;
        if (spelling == "{") return tk_lcb;
        if (spelling == "}") return tk_rcb;
        if (spelling == "(") return tk_lrb;
        if (spelling == ")") return tk_rrb;
        if (spelling == "[") return tk_lsb;
        if (spelling == "]") return tk_rsb;
        if (spelling == ".") return tk_dot;

        // tk_hash_comment,    // '#' comment_char* '\n', '#' and '\n' are not part of the spelling
        if (spelling.find("#") == 0)  return tk_hash_comment;

        // tk_eol_comment,     // '//' comment_char* '\n', '//' and '\n' are not part of the spelling
        if (spelling.find("//") == 0) return tk_eol_comment;

        // tk_string,          // '"' instring* '"', '"' and '"' are not part of the spelling
        if (spelling.find("\"") == 0) return tk_string;

        if (spelling == "*=")  return tk_mult;
        if (spelling == "~=")  return tk_ne;
        if (spelling == "==")  return tk_eq;
        if (spelling == "<<<") return tk_ll_shift;
        if (spelling == "<<")  return tk_la_shift;
        if (spelling == ">>>") return tk_rl_shift;
        if (spelling == ">>")  return tk_ra_shift;

        return tk_oops;            // for error handling
    }

    // the fastest way to process a scientific notation into a standard format
    static string format_scientific(string s)
    {
        int len = s.length();
        char n[len + 8]; // save the processed string. +8 is very enough!

        int s_i = 0, n_i = 0, n_valid = -1, dot_from = -1, dot_to = -1;
        // process the decimal part
        for (; s_i < len; s_i++)
        {
            if (s[s_i] == 'e' || s[s_i] == 'E')
                break;

            if (n_i == 1)
            {
                n[n_i] = '.';
                dot_to = s_i;
                n_i++;
            }

            switch (s[s_i])
            {
            case '1' ... '9':
                n[n_i] = s[s_i];
                n_valid = n_i;
                n_i++;
                break;
            case '0':
                if (n_i > 1)
                {
                    n[n_i] = s[s_i];
                    n_i++;
                }
                break;
            case '.':
                dot_from = s_i;
                break;
            default:
                // error
                return "";
            }
        }

        // this means that the decimal part is equal to 0
        if (n_valid == -1)
            return "0";

        // correct the dot position
        if (dot_to == -1)
            dot_to = s_i;
        if (dot_from == -1)
            dot_from = s_i;
        if (dot_to > dot_from)
            dot_to -= 1;

        // process the exponent part
        string s_exponent;
        int i_exponent;

        // adjust the value of i_exponent
        s_exponent = (s_i < len) ? s.substr(s_i + 1) : "";
        i_exponent = (s_exponent == "+" || s_exponent == "-" || s_exponent == "") ? 0 : stoi(s_exponent);
        i_exponent += dot_from - dot_to;

        n[++n_valid] = 'e';
        n[++n_valid] = i_exponent >= 0 ? '+' : '-';

        // convert the value of abs(i_exponent) to a string and put it to n
        int temp = abs(i_exponent);
        int width = (temp == 0 ? 0 : floor(log10(temp))) + 1;
        for (int i = 0; i < width; i++)
        {
            n[n_valid + width - i] = temp % 10 + '0';
            temp = temp / 10;
        }
        n_valid += width;

        n[++n_valid] = '\0';
        return string(n);   // return n;  is not safe?
    }

    // work out the correct spelling to use in the Token object being created by new_token()
    // the spelling is a valid token and kind is its kind
    string correct_spelling(TokenKind kind,string spelling)
    {
        if (spelling == "") return "";        

        // fixing hash_comment
        if (kind == tk_hash_comment){
            // delete '#' and '\n'
            return spelling.substr(1, spelling.length() - 2);
        }

        // fixing eol_comment
        if (kind == tk_eol_comment){
            // delete "//" and '\n'
            return spelling.substr(2, spelling.length() - 3);
        }

        // fixing string
        if (kind == tk_string){
            // delete '"' at at the beginning and end
            return spelling.substr(1, spelling.length() - 2);
        }

        // finxing scientific
        if (kind == tk_scientific){ 
            return format_scientific(spelling);
        }

        return spelling ;
    }

}