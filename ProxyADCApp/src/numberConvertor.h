#include <Arduino.h>

void convertNumber(double value, byte payload[])
{
    int intpart = (int)value;
    double decimal_part = modf(value, NULL);

    if (value < 0)
    {
        payload[3] = 1;
        payload[4] = abs(intpart);
    }
    else
    {
        payload[3] = 0;
    }

    int firstTwo = abs(int(decimal_part * 100));
    decimal_part = modf(decimal_part * 100, NULL);
    int secondTwo = abs(int(decimal_part * 100));

    payload[5] = firstTwo;
    payload[6] = secondTwo;
}