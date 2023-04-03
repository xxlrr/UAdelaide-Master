#include <iostream>
#include <string>
#include <algorithm>

using namespace std;


// Function to make str1 and str2 be the same length by adding leading 0s
int makeEqualLength(string &str1, string &str2)
{
    int len1 = str1.length();
    int len2 = str2.length();
    if (len1 < len2)
    {
        str1.insert(0, len2 - len1, '0');
        return len2;
    }
    else if (len1 > len2)
    {
        str2.insert(0, len1 - len2, '0');
    }
    return len1;
}

// Function to compare both integers num1 and num2, and return 1 if num1 > num2, 0 if num1 == num2, and -1 if num1 < num2
int compare(string num1, string num2) {
    int n = max(num1.length(), num2.length());

    int i1 = num1.length() - n;
    int i2 = num2.length() - n;
    for (int i=0; i<n; i++, i1++, i2++) {
        int n1 = i1 >= 0 ? num1[i1] - '0' : 0;
        int n2 = i2 >= 0 ? num2[i2] - '0' : 0;
        if (n1 > n2)
            return 1;
        else if (n1 < n2)
            return -1;
        else
            continue;
    }

    return 0;
}

// Function to add two numbers using the school method
string add(string a, string b, int base) {
    string res;
    int carry = 0;
    int len = max(a.length(), b.length());
    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());
    for (int i = 0; i < len; i++) {
        int x = i < a.length() ? a[i] - '0' : 0;
        int y = i < b.length() ? b[i] - '0' : 0;
        int sum = x + y + carry;
        res.push_back(sum % base + '0');
        carry = sum / base;
    }
    if (carry > 0) res.push_back(carry + '0');
    while(res.back() == '0' && res.length() > 1) res.pop_back();
    reverse(res.begin(), res.end());
    return res;
}

// Function to sub two numbers using the school method
string sub(string a, string b, int base) {
    string res = "";
    int borrow = 0;
    int len = max(a.length(), b.length());
    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());
    for(int i = 0; i < len; i++) {
        int x = i < a.length() ? a[i] - '0' : 0;
        int y = i < b.length() ? b[i] - '0' : 0;
        int diff = x - y - borrow;
        borrow = 0;
        if(diff < 0) {
            diff += base;
            borrow = 1;
        }
        res += char(diff % base + '0');
    }
    while(res.back() == '0' && res.length() > 1) res.pop_back();
    reverse(res.begin(), res.end());
    return res;
}

// Function to multiply two numbers using the Karatsuba algorithm
string karatsuba(string num1, string num2, int base) {
    int n = makeEqualLength(num1, num2);
    if (n == 1) {
        int prod = (num1[0]-'0') * (num2[0]-'0');
        return to_string(prod);
    }
    int half = n / 2;
    string a0 = num1.substr(0, num1.length() - half);
    string a1 = num1.substr(num1.length() - half);
    string b0 = num2.substr(0, num2.length() - half);
    string b1 = num2.substr(num2.length() - half);
    string p0 = karatsuba(a0, b0, base);
    string p1 = karatsuba(a1, b1, base);
    string p2 = karatsuba(add(a0, a1, base), add(b0, b1, base), base);
    string p3 = sub(sub(p2, p1, base), p0, base);
    string prod = p0;
    for (int i = 0; i < 2 * half; i++) {
        prod += "0";
    }
    prod = add(prod, p1, base);
    for (int i = 0; i < half; i++) {
        p3 += "0";
    }
    prod = add(prod, p3, base);

    return prod;
}

// Function to divide two numbers
string div(string num1, string num2, int base)
{
    string quotient = "";

    string cur = "";
    for (int i = 0; i < num1.length(); i++) {
        cur += num1[i];
        string q = "0";
        while(compare(cur, num2) >= 0) {
            cur = sub(cur, num2, base);
            q = add(q, "1", base);
        }
        quotient += q;
    }

    while(quotient.front() == '0' && quotient.length() > 1) quotient.erase(0, 1);
    return quotient;
}

// Main function
int main() {
    string num1, num2;
    int base;
    cin >> num1 >> num2 >> base;
    string sum = add(num1, num2, base);
    string prod = karatsuba(num1, num2, base);
    string quotient = div(num1, num2, base);
    cout << sum << " " << prod << " " << quotient << endl;
    return 0;
}