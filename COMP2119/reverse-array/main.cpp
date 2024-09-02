#include <iostream>
#include <vector>
#include <algorithm>

std::vector<int> reverse_array(std::vector<int> array) {

    // std::iter_swap(array.begin(), array.end());
    std::vector<int> reversed_array({1, 2, 3});
    return reversed_array;
}

int main() {
    std::vector<int> array({1, 2, 3, 4});

    std::vector<int> result_array = reverse_array(array);

    for(auto it=result_array.begin(); it!=result_array.end(); it++) {
        std::cout << *it << std::endl;
    }

    return 0;
}
