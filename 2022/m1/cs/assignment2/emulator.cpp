// emulate executing Hack CPU instructions
#include "iobuffer.h"
#include "symbols.h"
#include "hack_computer.h"
#include <bitset>
#include <vector>
#include <iostream>
#include <map>

// to make out programs a bit neater
using namespace std ;

using namespace CS_IO_Buffers ;
using namespace CS_Symbol_Tables ;
using namespace Hack_Computer ;

// This program must emulate the execution of Hack CPU instructions
//
// To read from registers A, D or PC, use read_A(), read_D() or read_PC()
// To write to registers A, D or PC, use write_A(value), write_D(value) or write_PC(value)
//
// To read from ROM use read_ROM(address)
// To read from RAM use read_RAM(address)
// To write to RAM use write_RAM(address,value)
//
// All values returned by a read_*() function are 16-bit unsigned integers
// All parameters passed to a write_*() function are 16-bit unsigned integers
//


/*****************   REPLACE THE FOLLOWING CODE  ******************/

// the C instruction table
struct CInstructionTable {

    //  The COMP part codes
    map<uint16_t, string> comp = {
        {0B0101010,   "0"},                                         {0B0111111,   "1"}, {0B0111010,  "-1"},    
        {0B0110000,   "A"}, {0B0110011,  "-A"}, {0B0110001,  "!A"}, {0B0110111, "A+1"}, {0B0110010, "A-1"},
        {0B0001100,   "D"}, {0B0001111,  "-D"}, {0B0001101,  "!D"}, {0B0011111, "D+1"}, {0B0001110, "D-1"},
        {0B1110000,   "M"}, {0B1110011,  "-M"}, {0B1110001,  "!M"}, {0B1110111, "M+1"}, {0B1110010, "M-1"},
        {0B0000010, "D+A"}, {0B0010011, "D-A"}, {0B0000111, "A-D"}, {0B0000000, "D&A"}, {0B0010101, "D|A"}, 
        {0B1000010, "D+M"}, {0B1010011, "D-M"}, {0B1000111, "M-D"}, {0B1000000, "D&M"}, {0B1010101, "D|M"},
    };
    
    // The DEST part codes
    string dest[8] = { "", "M", "D", "MD", "A", "AM", "AD", "AMD" };
    
    // The COMP part codes
    string jump[8] = { "", "JGT", "JEQ", "JGE", "JLT", "JNE", "JLE", "JMP" };
    
} CIT;  // the C instruction table

// disassemble an instruction - convert binary to symbolic form
// A instructions should be "@" followed by the value in decimal
// C instructions should be dest=alu;jump
// omit dest= if all destination bits are 0
// omit ;jump if all jump bits are 0
string disassemble_instruction(uint16_t instruction)
{
    // A instruction
    if ((instruction & 0x8000) == 0)                    // 0x8000 == 0B 1000 0000 0000 0000
    {
        return "@" + to_string(instruction);
    }

    // C instruction
    if ((instruction & 0xE000) == 0xE000)               // 0xE000 == 0B 1110 0000 0000 0000
    {
        uint16_t iComp = (instruction & 0x1FC0) >> 6;   // 0x1FC0 == 0B 0001 1111 1100 0000
        uint16_t iDest = (instruction & 0x0038) >> 3;   // 0x0038 == 0B 0000 0000 0011 1000
        uint16_t iJump = (instruction & 0x0007);        // 0x0007 == 0B 0000 0000 0000 0111
        
        return CIT.dest[iDest] + (iDest != 0 ? "=" : "") + CIT.comp[iComp] + (iJump !=0 ? ";" : "") + CIT.jump[iJump];
    }

    // If you do not have an A-instruction or a C-instruction return "** illegal instruction **".
    return "** illegal instruction **";
}

// emulate the COMP part of a cpu instruction.
// other parts are ignored.
static uint16_t emulate_comp(uint16_t instruction)
{
    uint16_t iComp = (instruction & 0x1FC0) >> 6;   // 0x1FC0 == 0B 0001 1111 1100 0000

    switch(iComp)
    {
        case 0B0101010: //   "0"
            return 0;
        case 0B0111111: //   "1"
            return 1;
        case 0B0111010: //  "-1"
            return -1;

        case 0B0110000: //   "A"
            return read_A();
        case 0B0110011: //  "-A"
            return -read_A();
        case 0B0110001: //  "!A"
            return !read_A();
        case 0B0110111: // "A+1"
            return read_A() + 1;
        case 0B0110010: // "A-1"
            return read_A() - 1;

        case 0B0001100: //   "D"
            return read_D();
        case 0B0001111: //  "-D"
            return -read_D();
        case 0B0001101: //  "!D"
            return !read_D();
        case 0B0011111: // "D+1"
            return read_D() + 1;
        case 0B0001110: // "D-1"
            return read_D() - 1;

        case 0B1110000: //   "M"
            return read_RAM(read_A());
        case 0B1110011: //  "-M"
            return -read_RAM(read_A());
        case 0B1110001: //  "!M"
            return !read_RAM(read_A());
        case 0B1110111: // "M+1"
            return read_RAM(read_A()) + 1;
        case 0B1110010: // "M-1"
            return read_RAM(read_A()) - 1;

        case 0B0000010: // "D+A"
            return read_D() + read_A();
        case 0B0010011: // "D-A"
            return read_D() - read_A();
        case 0B0000111: // "A-D"
            return read_A() - read_D();
        case 0B0000000: // "D&A"
            return read_D() & read_A();
        case 0B0010101: // "D|A"
            return read_D() | read_A();

        case 0B1000010: // "D+M"
            return read_D() + read_RAM(read_A());
        case 0B1010011: // "D-M"
            return read_D() - read_RAM(read_A());
        case 0B1000111: // "M-D"
            return read_RAM(read_A()) - read_D();
        case 0B1000000: // "D&M"
            return read_D() & read_RAM(read_A());
        case 0B1010101: // "D|M"
            return read_D() | read_RAM(read_A());
    }

    return 0;
}

// emulate the DEST part of a cpu instruction.
// other parts are ignored.
static void emulate_dest(uint16_t instruction, uint16_t operands)
{
    uint16_t iDest = (instruction & 0x0038) >> 3;   // 0x0038 == 0B 0000 0000 0011 1000

    switch(iDest)
    {
        case 0B000: // ""
            break;
        case 0B001: // "M"
            write_RAM(read_A(), operands);
            break;
        case 0B010: // "D"
            write_D(operands);
            break;
        case 0B011: // "MD"
            write_RAM(read_A(), operands);
            write_D(operands);
            break;
        case 0B100: // "A"
            write_A(operands);
            break;
        case 0B101: // "AM"
            write_A(operands);
            write_RAM(read_A(), operands);
            break;
        case 0B110: // "AD"
            write_A(operands);
            write_D(operands);
            break;
        case 0B111: // "AMD"
            write_A(operands);
            write_RAM(read_A(), operands);
            write_D(operands);
            break;
    }
}

// emulate the JUMP part of a cpu instruction.
// other parts are ignored.
static void emulate_jump(uint16_t instruction, uint16_t operands)
{
    uint16_t iJump = (instruction & 0x0007);        // 0x0007 == 0B 0000 0000 0000 0111

    switch(iJump)
    {
        case 0B000: // ""
            break;
        case 0B001: // "JGT"
            if (operands > 0)
                write_PC(read_A());
            break;
        case 0B010: // "JEQ"
            if (operands == 0)
                write_PC(read_A());
            break;
        case 0B011: // "JGE"
            if (operands >= 0)
                write_PC(read_A());
            break;
        case 0B100: // "JLT"
            if (operands < 0)
                write_PC(read_A());
            break;
        case 0B101: // "JNE"
            if (operands <= 0)
                write_PC(read_A());
            break;
        case 0B110: // "JLE"
            if (operands != 0)
                write_PC(read_A());
            break;
        case 0B111: // "JMP"
            write_PC(read_A());
            break;
    }
}

// emulate a cpu instruction - the Hack Computer has been initialised
// the PC contains the address in ROM of the instruction to execute
// if you want to produce additional output, use write_to_traces()
static void emulate_instruction()
{
    uint16_t pc = read_PC();
    uint16_t instruction = read_ROM(pc);
    
    // A instruction
    if ((instruction & 0x8000) == 0)
    {
        write_A(instruction);
    }

    write_PC(pc+1);
}


/*****************        DOWN TO HERE         ******************/

// if the first command line argument is "D" the test output must display disassembled instructions
bool display_disassembled_instructions = false ;

// this function only calls disassemble_instruction if required
// it is called from more_cpu_tests() and is passed the instruction being emulated
string disassemble(uint16_t instruction)
{
    if ( display_disassembled_instructions ) return disassemble_instruction(instruction) ;

    // default is to display instruction in binary
    return "\"" + std::bitset<16>(instruction).to_string() + "\"" ;
}


// main program
int main(int argc,char **argv)
{
    // force all output to be immediate - the test scripts will override this
    config_output(iob_immediate) ;
    config_errors(iob_immediate) ;

    // is the first command line argument "D", ie does the test output include disassembly of the instruction being emulated
    display_disassembled_instructions = argc > 1 && string(argv[1]) == "D" ;

    // more_cpu_tests() will initialise the next test and return true
    // if there are no more tests to be run, it returns false
    // more_cpu_tests() also outputs the results of the previous test
    while ( more_cpu_tests(disassemble) )
    {
        emulate_instruction() ;
    }

    // flush the output and any errors
    print_output() ;
    print_errors() ;
}

