#include <Arduino.h>
#include "notifyUtils.h"
// Frame Send
// All frames starts with x01 byte
// Then contents which splits by x00
// Ends with n symbol
int sendPayload(byte payload[])
{
    for (int i = 0; i < 8; i++)
    {
        Serial.write(payload[i]);
        Serial.write("\x00");
    }

    return 0;
}