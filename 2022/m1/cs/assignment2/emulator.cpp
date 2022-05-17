// emulate executing Hack CPU instructions
#include "iobuffer.h"
#include "symbols.h"
#include "hack_computer.h"
#include <bitset>
#include <vector>
#include <iostream>

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

    // *********** reading / writing registers / RAM / ROM ***********
    //
    // read register A, D or PC and return an unsigned 16-bit integer
    // uint16_t read_A() {}
    // uint16_t read_D() {}

    // uint16_t PC;
    // uint16_t read_PC()
    // {
    //     return PC;
    // }

    // write an unsigned 16-bit integer to register A, D or PC
    // void write_A(uint16_t new_value) {}
    // void write_D(uint16_t new_value) {}
    // void write_PC(uint16_t new_value) {}

    // read an unsigned 16-bit integer from an address in ROM
    // attempts to read from addresses in the range 32768 to 65535 will return 0 as the result
    // uint16_t ROM[32768];
    // uint16_t read_ROM(uint16_t address)
    // {
    //     if (address > 32767)
    //         return 0;
    //     return ROM[address];
    // }

    // read an unsigned 16-bit integer from an address in RAM
    // attempts to read from addresses in the range 24577 to 65535 will return 0 as the result
    // uint16_t read_RAM(uint16_t address) {}

    // write an unsigned 16-bit integer to an address in RAM
    // attempts to write to addresses in the range 24576 to 65535 will not change the RAM
    // void write_RAM(uint16_t address,uint16_t new_value) {}

    // *********** disassembler support  ***********
    //
    // assembler equivalent of destination, aluop and jump components of a C-instruction
    // std::string destination(uint16_t destination_bits) {}
    // std::string aluop(uint16_t aluop_bits) {}
    // std::string jump(uint16_t jump_bits) {}

/*****************   REPLACE THE FOLLOWING CODE  ******************/


// disassemble an instruction - convert binary to symbolic form
// A instructions should be "@" followed by the value in decimal
// C instructions should be dest=alu;jump
// omit dest= if all destination bits are 0
// omit ;jump if all jump bits are 0
string disassemble_instruction(uint16_t instruction)
{
    return "A=D+1;JGT" ;
}

// emulate a cpu instruction - the Hack Computer has been initialised
// the PC contains the address in ROM of the instruction to execute
// if you want to produce additional output, use write_to_traces()
static void emulate_instruction()
{
    uint16_t pc = read_PC();
    uint16_t instruction = read_ROM(pc);
    // write_to_traces(to_string(pc));
    // write_to_traces(to_string(instruction));
    
    if ((instruction & 0x8000) == 0)
    {
        // A instruction
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

