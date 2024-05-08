#include <Arduino.h>
#include "payloadUtils.h"
#include "config.h"
#include "numberConvertor.h"

#if DEV
// variable for signal generators
double x = -1.0;
bool isNegative = false;
#include "signalGenerators.h"
#endif

#define SERIAL_BAUDRATE 9600
#define VERSION_BYTE 0

// Description of Payload
// 0 - frame version
// 1 - reserved
// 2 - reserver
// 3 - positive (1) or negative (0)
// 4 - integer part
// 5 - 1..3 of decimal part
// 6 - 4..6 of decimal part
// 7 - 7..8 of decimal part
byte ADCPayload[8] = {
    0,
    0,
    0,

    0, // -+
    0, // XXX
    // .
    0, // XX
    0, // XX
    0, // XX
};
double signalValue = 0.0;

void setup()
{
    pinMode(LED_BUILTIN, OUTPUT);
    Serial.begin(SERIAL_BAUDRATE);
    Serial.flush();
}

void loop()
{

#if DEV
    signalValue = sinwave(x);
    x += 0.1;
#endif
    // convertNumber(signalValue, ADCPayload);
    Serial.flush();
    Serial.write(0);
    Serial.write(0);
    Serial.write(0);
    if (signalValue < 0)
    {
        Serial.write(1);
    }
    else
    {
        Serial.write(0);
    }
    Serial.write(abs((int)signalValue));
    Serial.write(abs((int)((signalValue - (int)signalValue) * 100)));
    sendPayload(ADCPayload);
    delay(50);
}
