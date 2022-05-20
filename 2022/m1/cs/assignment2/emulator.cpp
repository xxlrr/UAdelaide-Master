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

    switch (iComp)
    {
    case 0B0000000:    return read_D() & read_A();
    case 0B0000001:    return ~read_D() | ~read_A();
    case 0B0000010:    return read_D() + read_A();
    case 0B0000011:    return -read_D() - read_A() - 1;
    case 0B0000100:    return read_D() & ~read_A();
    case 0B0000101:    return ~read_D() | read_A();
    case 0B0000110:    return read_D() - read_A() - 1;
    case 0B0000111:    return read_A() - read_D();

    case 0B0001000:    return false;
    case 0B0001001:    return true;
    case 0B0001010:    return read_D();
    case 0B0001011:    return -read_D() - 1;
    case 0B0001100:    return read_D();
    case 0B0001101:    return ~read_D();
    case 0B0001110:    return read_D() - 1;
    case 0B0001111:    return -read_D();

    case 0B0010000:    return ~read_D() & read_A();
    case 0B0010001:    return read_D() | ~read_A();
    case 0B0010010:    return read_A() - read_D() - 1;
    case 0B0010011:    return read_D() - read_A();
    case 0B0010100:    return ~read_D() & ~read_A();
    case 0B0010101:    return read_D() | read_A();
    case 0B0010110:    return -read_D() - read_A() - 2;
    case 0B0010111:    return read_D() + read_A() + 1;

    case 0B0011000:    return false;
    case 0B0011001:    return true;
    case 0B0011010:    return -read_D() - 1;
    case 0B0011011:    return read_D();
    case 0B0011100:    return ~read_D();
    case 0B0011101:    return read_D();
    case 0B0011110:    return -read_D() - 2;
    case 0B0011111:    return read_D() + 1;

    case 0B0100000:    return false;
    case 0B0100001:    return true;
    case 0B0100010:    return read_A();
    case 0B0100011:    return -read_A() - 1;
    case 0B0100100:    return false;
    case 0B0100101:    return true;
    case 0B0100110:    return -read_A() - 1;
    case 0B0100111:    return read_A();

    case 0B0101000:    return false;
    case 0B0101001:    return true;
    case 0B0101010:    return 0;
    case 0B0101011:    return -1;
    case 0B0101100:    return false;
    case 0B0101101:    return true;
    case 0B0101110:    return -1;
    case 0B0101111:    return 0;

    case 0B0110000:    return read_A();
    case 0B0110001:    return ~read_A();
    case 0B0110010:    return read_A() - 1;
    case 0B0110011:    return -read_A();
    case 0B0110100:    return ~read_A();
    case 0B0110101:    return read_A();
    case 0B0110110:    return -read_A() - 2;
    case 0B0110111:    return read_A() + 1;

    case 0B0111000:    return false;
    case 0B0111001:    return true;
    case 0B0111010:    return -1;
    case 0B0111011:    return 0;
    case 0B0111100:    return true;
    case 0B0111101:    return false;
    case 0B0111110:    return -2;
    case 0B0111111:    return 1;

    case 0B1000000:    return read_D() & read_RAM(read_A());
    case 0B1000001:    return ~read_D() | ~read_RAM(read_A());
    case 0B1000010:    return read_D() + read_RAM(read_A());
    case 0B1000011:    return -read_D() - read_RAM(read_A()) - 1;
    case 0B1000100:    return read_D() & ~read_RAM(read_A());
    case 0B1000101:    return ~read_D() | read_RAM(read_A());
    case 0B1000110:    return read_D() - read_RAM(read_A()) - 1;
    case 0B1000111:    return read_RAM(read_A()) - read_D();

    case 0B1001000:    return false;
    case 0B1001001:    return true;
    case 0B1001010:    return read_D();
    case 0B1001011:    return -read_D() - 1;
    case 0B1001100:    return read_D();
    case 0B1001101:    return ~read_D();
    case 0B1001110:    return read_D() - 1;
    case 0B1001111:    return -read_D();

    case 0B1010000:    return ~read_D() & read_RAM(read_A());
    case 0B1010001:    return read_D() | ~read_RAM(read_A());
    case 0B1010010:    return read_RAM(read_A()) - read_D() - 1;
    case 0B1010011:    return read_D() - read_RAM(read_A());
    case 0B1010100:    return ~read_D() & ~read_RAM(read_A());
    case 0B1010101:    return read_D() | read_RAM(read_A());
    case 0B1010110:    return -read_D() - read_RAM(read_A()) - 2;
    case 0B1010111:    return read_D() + read_RAM(read_A()) + 1;

    case 0B1011000:    return false;
    case 0B1011001:    return true;
    case 0B1011010:    return -read_D() - 1;
    case 0B1011011:    return read_D();
    case 0B1011100:    return ~read_D();
    case 0B1011101:    return read_D();
    case 0B1011110:    return -read_D() - 2;
    case 0B1011111:    return read_D() + 1;

    case 0B1100000:    return false;
    case 0B1100001:    return true;
    case 0B1100010:    return read_RAM(read_A());
    case 0B1100011:    return -read_RAM(read_A()) - 1;
    case 0B1100100:    return false;
    case 0B1100101:    return true;
    case 0B1100110:    return -read_RAM(read_A()) - 1;
    case 0B1100111:    return read_RAM(read_A());

    case 0B1101000:    return false;
    case 0B1101001:    return true;
    case 0B1101010:    return 0;
    case 0B1101011:    return -1;
    case 0B1101100:    return false;
    case 0B1101101:    return true;
    case 0B1101110:    return -1;
    case 0B1101111:    return 0;

    case 0B1110000:    return read_RAM(read_A());
    case 0B1110001:    return ~read_RAM(read_A());
    case 0B1110010:    return read_RAM(read_A()) - 1;
    case 0B1110011:    return -read_RAM(read_A());
    case 0B1110100:    return ~read_RAM(read_A());
    case 0B1110101:    return read_RAM(read_A());
    case 0B1110110:    return -read_RAM(read_A()) - 2;
    case 0B1110111:    return read_RAM(read_A()) + 1;

    case 0B1111000:    return false;
    case 0B1111001:    return true;
    case 0B1111010:    return -1;
    case 0B1111011:    return 0;
    case 0B1111100:    return true;
    case 0B1111101:    return false;
    case 0B1111110:    return -2;
    case 0B1111111:    return 1;
    }
    
    // error do nothing.
    return instruction;
}

// emulate the DEST part of a cpu instruction.
// other parts are ignored.
static void emulate_dest(uint16_t instruction, int16_t operands)
{
    uint16_t iDest = (instruction & 0x0038) >> 3;        // 0x0038 == 0B 0000 0000 0011 1000

    switch (iDest)
    {
    case 0B000:        // ""
        break;
    case 0B001:        // "M"
        write_RAM(read_A(), operands);
        break;
    case 0B010:        // "D"
        write_D(operands);
        break;
    case 0B011:        // "MD"
        write_RAM(read_A(), operands);
        write_D(operands);
        break;
    case 0B100:        // "A"
        write_A(operands);
        break;
    case 0B101:        // "AM"
        write_A(operands);
        write_RAM(read_A(), operands);
        break;
    case 0B110:        // "AD"
        write_A(operands);
        write_D(operands);
        break;
    case 0B111:        // "AMD"
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
    uint16_t iJump = (instruction & 0x0007);        // 0x0007 == 0B 0000 0000 0000 0111
    uint16_t pc = read_PC() + 1;

    switch (iJump)
    {
    case 0B000:        // ""
        break;
    case 0B001:        // "JGT"
        if (operands > 0)
            pc = read_A();
        break;
    case 0B010:        // "JEQ"
        if (operands == 0)
            pc = read_A();
        break;
    case 0B011:        // "JGE"
        if (operands >= 0)
            pc = read_A();
        break;
    case 0B100:        // "JLT"
        if (operands < 0)
            pc = read_A();
        break;
    case 0B101:        // "JNE"
        if (operands != 0)
            pc = read_A();
        break;
    case 0B110:        // "JLE"
        if (operands <= 0)
            pc = read_A();
        break;
    case 0B111:        // "JMP"
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
