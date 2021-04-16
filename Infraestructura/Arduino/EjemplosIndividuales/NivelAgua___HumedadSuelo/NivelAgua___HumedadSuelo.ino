long humedad=0;

// the setup routine runs once when you press reset:
void setup() {
  Serial.begin(9600);
}

// the loop routine runs over and over again forever:
void loop() {
  humedad  = analogRead(A1);
  Serial.print("    " ); 
  Serial.println(humedad);
  
  delay(500);
}
