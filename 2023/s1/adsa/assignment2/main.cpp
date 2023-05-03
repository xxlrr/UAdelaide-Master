#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <iterator>
using namespace std;

// Definition of a node in the AVL tree
struct Node
{
    int val;     // Value stored in the node
    int height;  // Height of the subtree rooted at this node
    Node* left;  // Pointer to the left child node
    Node* right; // Pointer to the right child node
    Node(int val) : val(val), height(1), left(nullptr), right(nullptr) {}
};

// Utility function to get the height of a node (returns 0 for nullptr)
int height(Node* node)
{
    if (node == nullptr)
        return 0;
    return node->height;
}

// Utility function to get the balance factor of a node (returns 0 for nullptr)
int getBalance(Node* node)
{
    if (node == nullptr)
        return 0;
    return height(node->left) - height(node->right);
}

// Utility function to update the height of a node based on its children's heights
void updateHeight(Node* node)
{
    node->height = max(height(node->left), height(node->right)) + 1;
}

// Utility function to perform a right rotation around a node
Node* rotateRight(Node* node)
{
    Node* newRoot = node->left;
    node->left = newRoot->right;
    newRoot->right = node;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
}

// Utility function to perform a left rotation around a node
Node* rotateLeft(Node* node)
{
    Node* newRoot = node->right;
    node->right = newRoot->left;
    newRoot->left = node;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
}

// Utility function to insert a value into the AVL tree
Node* insert(Node* root, int val)
{
    // 0. If the root is nullptr, create a new node with the given value
    if (root == nullptr)
        return new Node(val);

    // 1. insert the val to the tree
    if (val < root->val)
        root->left = insert(root->left, val);
    else if (val > root->val)
        root->right = insert(root->right, val);
    // else if the value is already in the tree, do nothing
    else
        return root;

    // 2. update height
    updateHeight(root);

    // 3. balance if this tree becomes unbalanced
    //    there are 4 cases: left-left, left-right, right-right, right-left
    int balance = getBalance(root);

    // left-left case
    if (balance > 1 && val < root->left->val)
    {
        return rotateRight(root);
    }
    // left-right case
    if (balance > 1 && val > root->left->val)
    {
        root->left = rotateLeft(root->left);
        return rotateRight(root);
    }
    // right-right case
    if (balance < -1 && val > root->right->val)
    {
        return rotateLeft(root);
    }
    // right-left case
    if (balance < -1 && val < root->right->val)
    {
        root->right = rotateRight(root->right);
        return rotateLeft(root);
    }
    
    // return if the tree don't need balance
    return root;
}

// Utility function to find the node with the smallest value in a subtree
Node* findMin(Node* root)
{
    if (root->left == nullptr)
        return root;
    return findMin(root->left);
}

// Utility function to delete a node with a given value from the AVL tree
Node* remove(Node* root, int val)
{
    // return directly nullptr if the root == nullptr
    if (root == nullptr)
        return root;

    // If the value is less than the root's value, delete it from the left subtree
    if (val < root->val)
    {
        root->left = remove(root->left, val);
    }
    // If the value is greater than the root's value, delete it from the right subtree
    else if (val > root->val)
    {
        root->right = remove(root->right, val);
    }
    // If the value is equal to the root's value, delete the root
    else
    {
        // If the root has at most one child, replace it with that child (or nullptr if it has none)
        if (root->left == nullptr || root->right == nullptr)
        {
            Node* temp = root->left ? root->left : root->right;
            if (temp == nullptr)
            {
                temp = root;
                root = nullptr;
            }
            else
            {
                *root = *temp;
            }
            delete temp;

        }
        // If the root has two children, replace it with the node with the smallest value in its right subtree
        else
        {
            Node* minNode = findMin(root->right);
            root->val = minNode->val;
            root->right = remove(root->right, minNode->val);
        }
    }
    // If the root is nullptr, return it
    if (root == nullptr)
        return root;
    // Otherwise, update the height of the root and return it
    updateHeight(root);

    // rebalance it if this node became unbalanced)
    int balance = getBalance(root);
 
    // then there are 4 cases: left-left, left-right, rigit-right, right-left
 
    // Left Left Case
    if (balance > 1 && getBalance(root->left) >= 0)
        return rotateRight(root);
 
    // Left Right Case
    if (balance > 1 &&
        getBalance(root->left) < 0)
    {
        root->left = rotateLeft(root->left);
        return rotateRight(root);
    }
 
    // Right Right Case
    if (balance < -1 &&
        getBalance(root->right) <= 0)
        return rotateLeft(root);
 
    // Right Left Case
    if (balance < -1 &&
        getBalance(root->right) > 0)
    {
        root->right = rotateRight(root->right);
        return rotateLeft(root);
    }

    return root;
}

// Utility function to print the values of the nodes in pre-order
void printPreOrder(Node* root)
{
    if (root != nullptr)
    {
        cout << root->val << " ";
        printPreOrder(root->left);
        printPreOrder(root->right);
    }
}

// Utility function to print the values of the nodes in post-order
void printPostOrder(Node* root)
{
    if (root != nullptr)
    {
        printPostOrder(root->left);
        printPostOrder(root->right);
        cout << root->val << " ";
    }
}

// Utility function to print the values of the nodes in in-order
void printInOrder(Node* root)
{
    if (root != nullptr)
    {
        printInOrder(root->left);
        cout << root->val << " ";
        printInOrder(root->right);
    }
}

// Print the AVL tree in the specified order
void printTree(Node* root, string order)
{
    // print EMPTY if the tree is empty
    if (root == nullptr) 
    {
        cout << "EMPTY";
        return;
    }

    // print the tree in pre-order
    if (order == "PRE")
    {
        printPreOrder(root);
    }
    // print the tree in post-order
    else if (order == "POST")
    {
        printPostOrder(root);
    }
    // print the tree in in-order
    else if (order == "IN")
    {
        printInOrder(root);
    }
}

int main()
{
    // Initialize an empty AVL tree
    Node* root = nullptr;
    // Read input line from the user
    string input;
    getline(cin, input);

    // Split input line into moves later
    istringstream iss(input);
    
    // Perform moves on the AVL tree
    string move;
    while (iss >> move)
    {
        // Add a node
        if (move[0] == 'A')
        {
            int val = stoi(move.substr(1));
            root = insert(root, val);
        }
        // Del a node
        else if (move[0] == 'D')
        {         
            int val = stoi(move.substr(1));
            root = remove(root, val);
        }
        // Print the AVL tree in the specified order
        else
        {
            printTree(root, move);
        }
        int a=0;
    }
    
    return 0;
}