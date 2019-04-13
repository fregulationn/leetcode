//
// Created by junjie on 19-4-13.
//

#include <iostream>
#include <map>

using namespace std;

int main() {
    string s = "xxbbxy";
    map<char, int> mapLetter;

    for (int i = 0; i < s.length(); i++) {
        char temp_str = s[i];
        if (mapLetter.find(temp_str) != mapLetter.end()) {
            mapLetter[temp_str] += 1;
        } else {
            mapLetter[temp_str] = 1;
        }
    }

    char min = 'z';
    for (int j = 0; j < s.length(); ++j) {
        if (mapLetter[s[j]] == 1) {
            cout << s[j];
            return 0;
        }


        bool findMoreMin = false;
        for (int k = j + 1; k < s.length(); ++k) {
            if (s[j] > s[k] && mapLetter[s[j]] > 1) {
                findMoreMin = true;
                mapLetter[s[j]] -= 1;
                break;
            }
        }
        if (!findMoreMin) {
            cout << s[j];
            return 0;
        }
    }
    std::cout << min << std::endl;
    return 0;
}