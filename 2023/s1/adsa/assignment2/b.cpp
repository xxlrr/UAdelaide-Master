#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <iterator>
using namespace std;


struct Node
{
    int val;     
    int height;  
    Node* left;  
    Node* right; 
    Node(int val) : val(val), height(1), left(nullptr), right(nullptr) {}
};


int height(Node* node)
{
    if (node == nullptr)
        return 0;
    return node->height;
}

int getBalance(Node* node)
{
    if (node == nullptr)
        return 0;
    return height(node->left) - height(node->right);
}

void updateHeight(Node* node)
{
    node->height = max(height(node->left), height(node->right)) + 1;
}

Node* rotateRight(Node* node)
{
    Node* newRoot = node->left;
    node->left = newRoot->right;
    newRoot->right = node;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
}


Node* rotateLeft(Node* node)
{
    Node* newRoot = node->right;
    node->right = newRoot->left;
    newRoot->left = node;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
}

Node* insert(Node* root, int val)
{
    if (root == nullptr)
        return new Node(val);

    if (val < root->val)
        root->left = insert(root->left, val);
    else if (val > root->val)
        root->right = insert(root->right, val);
    else //HERE
        return root; //HERE
        
    updateHeight(root);

    int balance = getBalance(root);

    if (balance > 1 && val < root->left->val)
    {
        return rotateRight(root);
    }
    
    if (balance > 1 && val > root->left->val)
    {
        root->left = rotateLeft(root->left);
        return rotateRight(root);
    }
    
    if (balance < -1 && val > root->right->val)
    {
        return rotateLeft(root);
    }
    
    if (balance < -1 && val < root->right->val)
    {
        root->right = rotateRight(root->right);
        return rotateLeft(root); //HERE
    }
    
    
    return root;
}

Node* findMin(Node* root)
{
    if (root->left == nullptr)
        return root;
    return findMin(root->left);
}


Node* remove(Node* root, int val)
{
    
    if (root == nullptr)
        return root;

    if (val < root->val)
    {
        root->left = remove(root->left, val);
    }
    
    else if (val > root->val)
    {
        root->right = remove(root->right, val);
    }
    
    else
    {
        
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
        else
        {
            Node* minNode = findMin(root->right);
            root->val = minNode->val;
            root->right = remove(root->right, minNode->val);
        }
    }
    
    if (root == nullptr)
        return root;
    
    updateHeight(root);

    int balance = getBalance(root);
 
    if (balance > 1 && getBalance(root->left) >= 0)
        return rotateRight(root);
 
    if (balance > 1 &&
        getBalance(root->left) < 0)
    {
        root->left = rotateLeft(root->left);
        return rotateRight(root);
    }
 
    
    if (balance < -1 &&
        getBalance(root->right) <= 0)
        return rotateLeft(root);
 
    
    if (balance < -1 &&
        getBalance(root->right) > 0)
    {
        root->right = rotateRight(root->right);
        return rotateLeft(root);
    }

    return root;
}


void printPreOrder(Node* root)
{
    if (root != nullptr)
    {
        cout << root->val << " ";
        printPreOrder(root->left);
        printPreOrder(root->right);
    }
}


void printPostOrder(Node* root)
{
    if (root != nullptr)
    {
        printPostOrder(root->left);
        printPostOrder(root->right);
        cout << root->val << " ";
    }
}


void printInOrder(Node* root)
{
    if (root != nullptr)
    {
        printInOrder(root->left);
        cout << root->val << " ";
        printInOrder(root->right);
    }
}


void printTree(Node* root, string order)
{
    
    if (root == nullptr) 
    {
        cout << "EMPTY";
        return;
    }

    
    if (order == "PRE")
    {
        printPreOrder(root);
    }
    
    else if (order == "POST")
    {
        printPostOrder(root);
    }
    
    else if (order == "IN")
    {
        printInOrder(root);
    }
}

int main()
{
    
    Node* root = nullptr;
    
    string input;
    getline(cin, input);

    
    istringstream iss(input);
    
    
    string move;
    while (iss >> move)
    {
        
        if (move[0] == 'A')
        {
            int val = stoi(move.substr(1));
            root = insert(root, val);
        }
        
        else if (move[0] == 'D')
        {         
            int val = stoi(move.substr(1));
            root = remove(root, val);
        }
        
        else
        {
            printTree(root, move);
        }
        int a=0;
    }
    
    return 0;
}