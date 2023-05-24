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
        return key.back();
    }

    int findSlot(const std::string& key) const {
        char hashValue = hashFunction(key);
        int slotIndex = hashValue - 'a';

        while (true) {
            if (table[slotIndex].status == Slot::NEVER_USED || table[slotIndex].status == Slot::TOMBSTONE || table[slotIndex].key == key)
                break;

            slotIndex = (slotIndex + 1) % 26;
        }

        return slotIndex;
    }

    void insert(const std::string& key) {
        int slotIndex = findSlot(key);

        if (table[slotIndex].status != Slot::OCCUPIED) {
            table[slotIndex].key = key;
            table[slotIndex].status = Slot::OCCUPIED;
        }
    }

    void remove(const std::string& key) {
        int slotIndex = findSlot(key);

        if (table[slotIndex].status == Slot::OCCUPIED)
            table[slotIndex].status = Slot::TOMBSTONE;
    }

    void printKeys() const {
        for (int i = 0; i < 26; i++) {
            if (table[i].status == Slot::OCCUPIED)
                std::cout << table[i].key << " ";
        }
        std::cout << std::endl;
    }
};

int main() {
    HashTable hashTable;

    std::string moves;
    std::getline(std::cin, moves);

    size_t pos = 0;
    std::string move;
    while ((pos = moves.find(' ')) != std::string::npos) {
        move = moves.substr(0, pos);

        if (move[0] == 'A') {
            std::string key = move.substr(1);
            hashTable.insert(key);
        } else if (move[0] == 'D') {
            std::string key = move.substr(1);
            hashTable.remove(key);
        }

        moves.erase(0, pos + 1);
    }

    if (!moves.empty()) {
        if (moves[0] == 'A') {
            std::string key = moves.substr(1);
            hashTable.insert(key);
        } else if (moves[0] == 'D') {
            std::string key = moves.substr(1);
            hashTable.remove(key);
        }
    }

    hashTable.printKeys();

    return 0;
}
