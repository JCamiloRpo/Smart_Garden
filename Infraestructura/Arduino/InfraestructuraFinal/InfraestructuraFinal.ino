//LIBRERIAS
#include <OneWire.h>
#include <DallasTemperature.h>
#include <DHT.h>    
#include <DHT_U.h>
#include <Wire.h>
#include <BH1750.h>
#include <RTClib.h>





//CONSTANTES
const short pinTemperaturaSuelo = 9;
const short pinTemperaturaHumedad = 2;     // pin DATA de DHT22 a pin digital 2

//VARIABLES GLOBALES AUXILIARES
DateTime fecha(2021,4,16,3,4,0);
String salida="";
OneWire oneWireObjeto(pinTemperaturaSuelo);
DHT dht22(pinTemperaturaHumedad, DHT22);
BH1750 luxometro;

//VARIABLES GLOBALES DE SENSADO
int humedadSuelo=0;
DallasTemperature temperaturaSuelo(&oneWireObjeto);
int temperaturaAire=0;
int humedadAire=0;
uint16_t intensidadLuminica;

void setup() {
  Serial.begin(9600);
  temperaturaSuelo.begin();
  dht22.begin();
  Wire.begin();
  luxometro.begin();
}

// the loop routine runs over and over again forever:
void loop() {
  fecha=fecha+TimeSpan(0,0,0,2);
  salida=salida+fecha.year()+"-"+fecha.month()+"-"+fecha.day()+" "+fecha.hour()+":"+fecha.minute()+":"+fecha.second()+", ";
  
  humedadSuelo  = analogRead(A1);
  salida=salida+humedadSuelo+", ";

  temperaturaSuelo.requestTemperatures();
  salida=salida+temperaturaSuelo.getTempCByIndex(0)+", ";
  
  humedadAire = dht22.readHumidity();   // obtencion de valor de humedad
  salida=salida+humedadAire +", ";
  
  temperaturaAire = dht22.readTemperature();  // obtencion de valor de temperatura
  salida=salida+temperaturaAire+", ";

  intensidadLuminica = luxometro.readLightLevel();
  salida=salida+intensidadLuminica;
  
  Serial.println(salida);
  salida="";
  delay(2000);
}
