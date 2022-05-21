/**
 * @file emulator.cpp
 * @author Hongxing Hao (a1845302@adelaide.edu.au)
 * @brief emulate executing Hack CPU instructions
 * @version 0.1
 * @date 2022-05-19
 *
 * @copyright Copyright (c) 2022
 */
#include "iobuffer.h"
#include "symbols.h"
#include "hack_computer.h"
#include <bitset>
#include <vector>
#include <iostream>

// to make out programs a bit neater
using namespace std;

using namespace CS_IO_Buffers;
using namespace CS_Symbol_Tables;
using namespace Hack_Computer;


// disassemble an instruction - convert binary to symbolic form
string disassemble_instruction(uint16_t instruction)
{
    // A instruction
    if ((instruction & 0x8000) == 0x0000)        // 0x8000 == 0B 1000 0000 0000 0000
    {
        return "@" + to_string(instruction);
    }

    // C instruction
    if ((instruction & 0xE000) == 0xE000)        // 0xE000 == 0B 1110 0000 0000 0000
    {
        uint16_t iComp = (instruction & 0x1FC0) >> 6;        // 0x1FC0 == 0B 0001 1111 1100 0000
        uint16_t iDest = (instruction & 0x0038) >> 3;        // 0x0038 == 0B 0000 0000 0011 1000
        uint16_t iJump = (instruction & 0x0007);             // 0x0007 == 0B 0000 0000 0000 0111

        return destination(iDest) + aluop(iComp) + jump(iJump);
    }

    return "** illegal instruction **";
}

// emulate the COMP part of a cpu instruction.
static int16_t emulate_comp(uint16_t instruction)
{
    uint16_t iComp = (instruction & 0x1FC0) >> 6;        // 0x1FC0 == 0B 0001 1111 1100 0000
    
    uint16_t x=read_D(), y=read_A(), o;
    y = (iComp & 0B1000000) ? read_RAM(y) : y;
    x = (iComp & 0B0100000) ?  0 : x;
    x = (iComp & 0B0010000) ? ~x : x;
    y = (iComp & 0B0001000) ?  0 : y;
    y = (iComp & 0B0000100) ? ~y : y;
    o = (iComp & 0B0000010) ? (x + y) : ( x & y );
    o = (iComp & 0B0000001) ? ~o : o;

    return o;
}

// emulate the DEST part of a cpu instruction.
// other parts are ignored.
static void emulate_dest(uint16_t instruction, int16_t operand)
{
    uint16_t iDest = (instruction & 0x0038) >> 3;        // 0x0038 == 0B 0000 0000 0011 1000

    if (iDest & 0B100) write_A(operand);
    if (iDest & 0B010) write_D(operand);
    if (iDest & 0B001) write_RAM(read_A(), operand);
}

// emulate the JUMP part of a cpu instruction.
// other parts are ignored.
static void emulate_jump(uint16_t instruction, int16_t operand)
{
    uint16_t iJump = (instruction & 0x0007);        // 0x0007 == 0B 0000 0000 0000 0111
    uint16_t next_pc = read_PC() + 1;

    switch (iJump)
    {
    case 0B000:        // ""
        break;
    case 0B001:        // "JGT"
        if (operand > 0)
            next_pc = read_A();
        break;
    case 0B010:        // "JEQ"
        if (operand == 0)
            next_pc = read_A();
        break;
    case 0B011:        // "JGE"
        if (operand >= 0)
            next_pc = read_A();
        break;
    case 0B100:        // "JLT"
        if (operand < 0)
            next_pc = read_A();
        break;
    case 0B101:        // "JNE"
        if (operand != 0)
            next_pc = read_A();
        break;
    case 0B110:        // "JLE"
        if (operand <= 0)
            next_pc = read_A();
        break;
    case 0B111:        // "JMP"
        next_pc = read_A();
        break;
    }

    write_PC(next_pc);
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
        write_PC(pc + 1);
    }

    // C instruction
    else if ((instruction & 0xE000) == 0xE000)
    {
        int16_t value = emulate_comp(instruction);
        emulate_dest(instruction, value);
        emulate_jump(instruction, value);
    }

    // else do nothing
}

/*****************        DOWN TO HERE         ******************/

// if the first command line argument is "D" the test output must display disassembled instructions
bool display_disassembled_instructions = false;

// this function only calls disassemble_instruction if required
// it is called from more_cpu_tests() and is passed the instruction being emulated
string disassemble(uint16_t instruction)
{
    if (display_disassembled_instructions) return disassemble_instruction(instruction);

    // default is to display instruction in binary
    return "\"" + std::bitset<16>(instruction).to_string() + "\"";
}

// main program
int main(int argc, char **argv)
{
    // force all output to be immediate - the test scripts will override this
    config_output(iob_immediate);
    config_errors(iob_immediate);

    // is the first command line argument "D", ie does the test output include disassembly of the instruction being emulated
    display_disassembled_instructions = argc > 1 && string(argv[1]) == "D";

    // more_cpu_tests() will initialise the next test and return true
    // if there are no more tests to be run, it returns false
    // more_cpu_tests() also outputs the results of the previous test
    while (more_cpu_tests(disassemble))
    {
        emulate_instruction();
    }

    // flush the output and any errors
    print_output();
    print_errors();
}
