#include <Arduino.h>

void blinkLED()
{
    digitalWrite(LED_BUILTIN, 1);
    digitalWrite(LED_BUILTIN, 0);
}