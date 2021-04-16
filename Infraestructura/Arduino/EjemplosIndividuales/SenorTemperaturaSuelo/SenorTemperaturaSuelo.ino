 
#include <OneWire.h>
#include <DallasTemperature.h>

// Pin donde se conecta el bus 1-Wire
const int pinDatosDQ = 9;

// Instancia a las clases OneWire y DallasTemperature
OneWire oneWireObjeto(pinDatosDQ);
DallasTemperature sensorDS18B20(&oneWireObjeto);

void setup() {
    Serial.begin(9600);
    // Iniciamos el bus 1-Wire
    sensorDS18B20.begin(); 
}

void loop() {
    sensorDS18B20.requestTemperatures();
 
    // Leemos y mostramos los datos del sensor DS18B20
    Serial.print("Temperatura : ");
    Serial.print(sensorDS18B20.getTempCByIndex(0));
    Serial.println(" C");
    
    delay(1000);

}
