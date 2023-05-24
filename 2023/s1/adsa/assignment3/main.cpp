#include <iostream>
#include <string>
#include <vector>

class HashTable {
private:
    struct Slot {
        std::string key;
        enum Status { NEVER_USED, TOMBSTONE, OCCUPIED } status;
    };

    std::vector<Slot> table;

public:
    HashTable() {
        table.resize(26);
        for (auto& slot : table) {
            slot.status = Slot::NEVER_USED;
        }
    }

    char hashFunction(const std::string& key) const {
        // Hash function that returns the last character of the key
        return key.back();
    }

    int findSlot(const std::string& key) const {
        // Find the slot index for the given key using linear probing

        char hashValue = hashFunction(key);
        int slotIndex = hashValue - 'a';

        while (true) {
            if (table[slotIndex].status == Slot::NEVER_USED || table[slotIndex].status == Slot::TOMBSTONE || table[slotIndex].key == key)
                break;

            // Move to the next slot (wrapping around if needed)
            slotIndex = (slotIndex + 1) % 26;
        }

        return slotIndex;
    }

    void insert(const std::string& key) {
        // Insert a key into the hash table

        int slotIndex = findSlot(key);

        if (table[slotIndex].status != Slot::OCCUPIED) {
            // If the slot is not occupied, insert the key
            table[slotIndex].key = key;
            table[slotIndex].status = Slot::OCCUPIED;
        }
    }

    void remove(const std::string& key) {
        // Remove a key from the hash table
        int slotIndex = findSlot(key);

        if (table[slotIndex].status == Slot::OCCUPIED)
            table[slotIndex].status = Slot::TOMBSTONE;
    }

    void printKeys() const {
        // Print all the keys in the hash table

        for (const auto& slot : table) {
            if (slot.status == Slot::OCCUPIED)
                std::cout << slot.key << " ";
        }
        std::cout << std::endl;
    }
};

int main() {
    HashTable hashTable;

    // Read the input line containing modification moves
    std::string moves;
    std::getline(std::cin, moves);

    size_t pos = 0;
    std::string move;
    while ((pos = moves.find(' ')) != std::string::npos) {
        move = moves.substr(0, pos);

        if (move[0] == 'A') {
            // If the move is an insertion, extract the key and insert it
            std::string key = move.substr(1);
            hashTable.insert(key);
        } else if (move[0] == 'D') {
            // If the move is a deletion, extract the key and remove it
            std::string key = move.substr(1);
            hashTable.remove(key);
        }

        // Remove the processed move from the input string
        moves.erase(0, pos + 1);
    }

    if (!moves.empty()) {
        if (moves[0] == 'A') {
            // If there's a single move left after processing the loop, it must be an insertion
            std::string key = moves.substr(1);
            hashTable.insert(key);
        } else if (moves[0] == 'D') {
        // If there's a single move left after processing the loop, it must be a deletion
            std::string key = moves.substr(1);
            hashTable.remove(key);
        }
    }

    // Print all the keys in the hash table
    hashTable.printKeys();

    return 0;
}
