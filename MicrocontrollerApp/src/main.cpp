#include <Arduino.h>
#include <math.h>
/**
 * TestSerialUSB.ino - Test for Cordova Android SerialUSB communication with Arduino board
 * Author: Milos Petrasinovic <mpetrasinovic@pr-dc.com>
 * PR-DC, Republic of Serbia
 * info@pr-dc.com
 *
 * --------------------
 * Copyright (C) 2021 PR-DC <info@pr-dc.com>
 *
 * This file is part of TestSerialUSB.
 *
 * TestSerialUSB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * TestSerialUSB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TestSerialUSB.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

#define SERIAL_BAUDRATE 115200

uint32_t dt_PRINT = 3000; // [ms] time difference for PRINT
uint32_t t_PRINT;         // [ms] PRINT time
uint32_t send_idx = 0;    // [-] output packet index

uint32_t dt_LED = 1000; // [ms] time difference for LED state change
uint32_t t_LED;         // [ms] LED time

void blinkLED()
{
    static bool s = 0;
    if ((millis() - t_LED) > dt_LED)
    {
        t_LED = millis();
        s = !s;
        digitalWrite(LED_BUILTIN, s);
    }
}

// setup function
// --------------------
void setup()
{
    pinMode(LED_BUILTIN, OUTPUT);
    Serial.begin(SERIAL_BAUDRATE);
    t_PRINT = millis();
    t_LED = millis();
}

// loop function
// --------------------
void loop()
{
    // Read input and pass it back
    if (Serial.available())
    {
        Serial.print("Returning: ");
        while (Serial.available())
        {
            Serial.print((char)Serial.read());
        }
    }

    // Send packet
    if ((millis() - t_PRINT) > dt_PRINT)
    {
        t_PRINT = millis();
        send_idx++;
        Serial.print("Arduino packet ");
        Serial.print(send_idx);
    }

    blinkLED();
}

// blinkLED() function
// Blink LED at right time
// --------------------
