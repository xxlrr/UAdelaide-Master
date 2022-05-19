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
#include <map>

// to make out programs a bit neater
using namespace std;

using namespace CS_IO_Buffers;
using namespace CS_Symbol_Tables;
using namespace Hack_Computer;

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

// disassemble an instruction - convert binary to symbolic form
string disassemble_instruction(uint16_t instruction)
{
    // A instruction
    if ((instruction & 0x8000) == 0) // 0x8000 == 0B 1000 0000 0000 0000
    {
        return "@" + to_string(instruction);
    }

    // C instruction
    if ((instruction & 0xE000) == 0xE000) // 0xE000 == 0B 1110 0000 0000 0000
    {
        uint16_t iComp = (instruction & 0x1FC0) >> 6; // 0x1FC0 == 0B 0001 1111 1100 0000
        uint16_t iDest = (instruction & 0x0038) >> 3; // 0x0038 == 0B 0000 0000 0011 1000
        uint16_t iJump = (instruction & 0x0007);      // 0x0007 == 0B 0000 0000 0000 0111

        return destination(iDest) + aluop(iComp) + jump(iJump);
    }

    return "** illegal instruction **";
}

// emulate the COMP part of a cpu instruction.
static bool emulate_comp(uint16_t instruction, int16_t* result)
{
    uint16_t iComp = (instruction & 0x1FC0) >> 6; // 0x1FC0 == 0B 0001 1111 1100 0000

    switch (iComp)
    {
    case 0B0101010: //   "0"
        *result = 0;
        break;
    case 0B0111111: //   "1"
        *result = 1;
        break;
    case 0B0111010: //  "-1"
        *result = -1;
        break;

    case 0B0110000: //   "A"
        *result = read_A();
        break;
    case 0B0110011: //  "-A"
        *result = -read_A();
        break;
    case 0B0110001: //  "!A"
        *result = ~read_A();
        break;
    case 0B0110111: // "A+1"
        *result = read_A() + 1;
        break;
    case 0B0110010: // "A-1"
        *result = read_A() - 1;
        break;

    case 0B0001100: //   "D"
        *result = read_D();
        break;
    case 0B0001111: //  "-D"
        *result = -read_D();
        break;
    case 0B0001101: //  "!D"
        *result = ~read_D();
        break;
    case 0B0011111: // "D+1"
        *result = read_D() + 1;
        break;
    case 0B0001110: // "D-1"
        *result = read_D() - 1;
        break;

    case 0B1110000: //   "M"
        *result = read_RAM(read_A());
        break;
    case 0B1110011: //  "-M"
        *result = -read_RAM(read_A());
        break;
    case 0B1110001: //  "!M"
        *result = ~read_RAM(read_A());
        break;
    case 0B1110111: // "M+1"
        *result = read_RAM(read_A()) + 1;
        break;
    case 0B1110010: // "M-1"
        *result = read_RAM(read_A()) - 1;
        break;

    case 0B0000010: // "D+A"
        *result = read_D() + read_A();
        break;
    case 0B0010011: // "D-A"
        *result = read_D() - read_A();
        break;
    case 0B0000111: // "A-D"
        *result = read_A() - read_D();
        break;
    case 0B0000000: // "D&A"
        *result = read_D() & read_A();
        break;
    case 0B0010101: // "D|A"
        *result = read_D() | read_A();
        break;

    case 0B1000010: // "D+M"
        *result = read_D() + read_RAM(read_A());
        break;
    case 0B1010011: // "D-M"
        *result = read_D() - read_RAM(read_A());
        break;
    case 0B1000111: // "M-D"
        *result = read_RAM(read_A()) - read_D();
        break;
    case 0B1000000: // "D&M"
        *result = read_D() & read_RAM(read_A());
        break;
    case 0B1010101: // "D|M"
        *result = read_D() | read_RAM(read_A());
        break;

    case 0B0001010: // .D. ???? illegel instruction? I don't why do this?
        *result = read_D();
        break;
    case 0B1111111: // .1. ???? illegel instruction? I don't why do this?
        *result = 1;
        break;

    default:
        return false; // illegel instruction, do nothing.
    }

    return true;
}

// emulate the DEST part of a cpu instruction.
// other parts are ignored.
static void emulate_dest(uint16_t instruction, int16_t operands)
{
    uint16_t iDest = (instruction & 0x0038) >> 3; // 0x0038 == 0B 0000 0000 0011 1000

    switch (iDest)
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
static void emulate_jump(uint16_t instruction, int16_t operands)
{
    uint16_t iJump = (instruction & 0x0007); // 0x0007 == 0B 0000 0000 0000 0111
    uint16_t pc = read_PC() + 1;

    switch (iJump)
    {
    case 0B000: // ""
        break;
    case 0B001: // "JGT"
        if (operands > 0)
            pc = read_A();
        break;
    case 0B010: // "JEQ"
        if (operands == 0)
            pc = read_A();
        break;
    case 0B011: // "JGE"
        if (operands >= 0)
            pc = read_A();
        break;
    case 0B100: // "JLT"
        if (operands < 0)
            pc = read_A();
        break;
    case 0B101: // "JNE"
        if (operands != 0)
            pc = read_A();
        break;
    case 0B110: // "JLE"
        if (operands <= 0)
            pc = read_A();
        break;
    case 0B111: // "JMP"
        pc = read_A();
        break;
    }

    write_PC(pc);
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
        int16_t value;
        if (emulate_comp(instruction, &value))
            emulate_dest(instruction, value);
        emulate_jump(instruction, value);
    }
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
