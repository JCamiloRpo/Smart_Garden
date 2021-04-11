//usando https://theorycircuit.com/veml6070-uv-sensor-arduino-interface/
// informacion http://arduinolearning.com/code/veml6070-ultraviolet-light-sensor-and-arduino-example.php
#include <Wire.h>
#include "Adafruit_VEML6070.h"

Adafruit_VEML6070 uv = Adafruit_VEML6070();

void setup() {
  Serial.begin(9600);
  Serial.println("VEML6070 Test");
  uv.begin(VEML6070_1_T);  // pass in the integration time constant
}


void loop() {
  Serial.print("UV light level: "); 
  Serial.println(uv.readUV());
  
  delay(1000);
}
