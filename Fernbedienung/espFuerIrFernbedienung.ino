#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <SoftwareSerial.h>
SoftwareSerial SUART(D2, D1); //SRX = D2 = GPIO-4; STX = D1 = GPIO-5


const char* ssid = "Altherminator";          //wlan name
const char* password = "Dingenskirchen";     //wlan passwort ist "Dingenskirchen"
char websideChar[10000];                     //wenn error dann erhoehen oder senken
String eingehenderString = "";
int laengeString = 0;



IPAddress local_ip(192,168,1,1);
IPAddress gateway(192,168,1,1);
IPAddress subnet(255,255,255,0);

ESP8266WebServer server(80);



void webpage()
{
  server.send(200,"text/html", websideChar);
}

void lesenSerial(){                 //"void lesenSerial" ist ein versuch meinerseits
 Serial.println(" ");
 delay(7000);
  long zeit = millis();
  
  Serial.println(millis() - zeit);
  Serial.print("Erwarte eingehende Webside als String...");
  delay(200);
  SUART.print("A");
  delay(300);
  SUART.println("");
  bool vergleich = true;


  while(vergleich == true){

    eingehenderString = SUART.readStringUntil(10);
    if(eingehenderString != "^"){
      
      Serial.println(eingehenderString);
      for(int i = laengeString; i < (laengeString + eingehenderString.length()); i++){
        websideChar[i] = eingehenderString.charAt((i - laengeString));
      }
      //eingehenderString = "^";
      laengeString = eingehenderString.length() + laengeString;
      if((millis() - zeit) >= 14000){
        vergleich = false;
      }
    }
    
  }
  String test = websideChar;
  Serial.println(test);
}

void setup() {
  Serial.begin(115200);
  SUART.begin(9600);
  WiFi.softAP(ssid, password);
  WiFi.softAPConfig(local_ip, gateway, subnet);
  delay(100);
  lesenSerial();                                            //weil es nicht funktioniert hat wegen rx/tx
  server.on("/", webpage);
  server.begin();
  
}
void loop() {
  server.handleClient();                                      //sorgt dafür, dass man auf die webside zugreifen kann
}

void lesen(){
  if(SUART.available() > 0){
    String ergebnis = "";
    ergebnis = SUART.readString();
    if(ergebnis.charAt(0) > 30){
      Serial.println(ergebnis);
    }
  }
}