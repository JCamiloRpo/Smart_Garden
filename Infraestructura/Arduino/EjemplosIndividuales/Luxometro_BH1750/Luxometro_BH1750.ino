//Tutorial: https://www.youtube.com/watch?v=AoFsWAJ-pE0

#include <Wire.h>
#include <BH1750.h>

BH1750 Luxometro;


void setup(){
  Wire.begin();
  Serial.begin(9600);
  Serial.println("Inicializando sensor...");
  Luxometro.begin(); //inicializamos el sensor
}


void loop() {
  uint16_t lux = Luxometro.readLightLevel();//Realizamos una lectura del sensor
  Serial.print("Luz(iluminancia):  ");
  Serial.print(lux);
  Serial.println(" lx");
  delay(2000);
}
