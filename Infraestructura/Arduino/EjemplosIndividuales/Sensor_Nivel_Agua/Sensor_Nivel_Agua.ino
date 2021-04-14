int Sensor;
void setup() {
  Serial.begin(9600);

}

void loop() {
  Sensor=analogRead(A0);
  Serial.println(Sensor);
  delay(1000);

}
