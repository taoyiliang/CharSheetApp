#ifndef ROLLER_H
#define ROLLER_H

#include <string>
#include <random>
#include <cmath>
#include <chrono>
using namespace std;

class Roller
{
public:
    int seed;
    mt19937 gen;
    uniform_real_distribution<> dist;

    Roller();
    int roll(int);
    int parseRoll(string);
    vector<int> parseMod(string);

};

#endif // ROLLER_H
