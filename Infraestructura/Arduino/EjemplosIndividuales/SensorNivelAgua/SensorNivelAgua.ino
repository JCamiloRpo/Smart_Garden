long sensorNivelAgua = 0;

// the setup routine runs once when you press reset:
void setup() {
  Serial.begin(9600);
}

// the loop routine runs over and over again forever:
void loop() {
  // read the input on analog pin 0:
  sensorNivelAgua = analogRead(A0);
  sensorNivelAgua=(sensorNivelAgua*100)/1024;
  
  Serial.print("Sensor cubierto = " ); 
  Serial.print(sensorNivelAgua);
  Serial.println("%");
  
  delay(500);
}
