//LIBRERIAS
#include <OneWire.h>
#include <DallasTemperature.h>
#include <DHT.h>    
#include <DHT_U.h>
#include <Wire.h>
#include <BH1750.h>
#include <RTClib.h>
#include <Separador.h>




//CONSTANTES
const short pinTemperaturaSuelo = 9;
const short pinTemperaturaHumedad = 2;     // pin DATA de DHT22 a pin digital 2
const short tiempoSensado=2;

//VARIABLES GLOBALES AUXILIARES
DateTime fecha(2021,4,16,3,4,0);
String salida="";
OneWire oneWireObjeto(pinTemperaturaSuelo);
DHT dht22(pinTemperaturaHumedad, DHT22);
BH1750 luxometro;
int inicializacion=1;
Separador s;

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
  delay(10000);
}
void configurarHora(){
  String data="";
  int anno=2021;
  int mes=4;
  int dia=17;
  int hora=0;
  int minuto=0;
  int segundo=0;
  char a=39;
  data=data+Serial.read();
  //data =  Serial.readStringUntil(' ');
  Serial.println(data);
  data=data+Serial.read();
  data=data+Serial.read();
  //data =  Serial.readStringUntil(' ');
  Serial.println(data);
  data = s.separa(data,a,1);
  
  anno = s.separa(data,' ',0).toInt();
  mes = s.separa(data,' ',1).toInt();
  dia = s.separa(data,' ',2).toInt();
  hora = s.separa(data,' ',3).toInt();
  minuto = s.separa(data,' ',4).toInt();
  segundo = s.separa(data,' ',5).toInt();
  Serial.println("Control 3");
  DateTime f1(anno,mes,dia,hora,minuto,segundo);
  fecha= f1;
}

// the loop routine runs over and over again forever:
void loop() {
  if( inicializacion==1){
    //Serial.println("Control 1");
    //configurarHora();
    //Serial.println("Control 4");
    inicializacion=0;
    
  }
  else if(inicializacion==0){
    fecha=fecha+TimeSpan(0,0,0,tiempoSensado);
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
    delay(tiempoSensado*1000);
  }
  /*else{
    Serial.println("Fallo");
    delay(tiempoSensado*1000);
  }*/
}
