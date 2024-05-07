#include <math.h>

double sinwave(double x)
{
    return sin(x) * 5;
}

double coswave(double x)
{
    return sin(x) * 5;
}

double squarewave(double x)
{
    if (((int)x % 5) <= 5)
    {
        return 5.0;
    }
    else
    {
        return 0.0;
    }
}