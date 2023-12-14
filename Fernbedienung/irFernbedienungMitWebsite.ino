#include <Elegoo_GFX.h>   
#include <Elegoo_TFTLCD.h> 
#include <TouchScreen.h>
#include <Arduino.h>
#include "PinDefinitionsAndMore.h" // Define macros for input and output pin etc.
#include <IRremote.hpp>
#include <SPI.h>
#include <SD.h>

#include <RCSwitch.h>

#define BUFFPIXEL 150

RCSwitch rfModul = RCSwitch();

#if !defined(RAW_BUFFER_LENGTH)
#  if RAMEND <= 0x4FF || (defined(RAMSIZE) && RAMSIZE < 0x4FF)
#define RAW_BUFFER_LENGTH  120
#  elif RAMEND <= 0xAFF || (defined(RAMSIZE) && RAMSIZE < 0xAFF) // 0xAFF for LEONARDO
#define RAW_BUFFER_LENGTH  400 // 600 is too much here, because we have additional uint8_t rawCode[RAW_BUFFER_LENGTH];
#  else
#define RAW_BUFFER_LENGTH  750
#  endif
#endif


File myFile;

int befehl = 0;
int adresse = 0;
int decodetypnummer = 0;


int SEND_BUTTON_PIN = APPLICATION_PIN;
int STATUS_PIN = LED_BUILTIN;

int DELAY_BETWEEN_REPEAT = 50;
int DEFAULT_NUMBER_OF_REPEATS_TO_SEND = 3;

// Storage for the recorded code
struct storedIRDataStruct {
    IRData receivedIRData;
    // extensions for sendRaw
    uint8_t rawCode[RAW_BUFFER_LENGTH]; // The durations if raw
    uint8_t rawCodeLength; // The length of the code
} sStoredIRData;

uint8_t aray[RAW_BUFFER_LENGTH];
uint8_t rawlaenge; 


int lastButtonState;
bool schleifebeenden = false;

void dekodieren(IRData *aIRReceivedData);
void zuordnen(storedIRDataStruct *aIRDataToSend);


#define PULSE_WIDTH
/*

#define DECODE_DENON        // Includes Sharp
#define DECODE_JVC
#define DECODE_KASEIKYO
#define DECODE_PANASONIC    // alias for DECODE_KASEIKYO
#define DECODE_LG
#define DECODE_NEC          // Includes Apple and Onkyo
#define DECODE_SAMSUNG
#define DECODE_SONY
#define DECODE_RC5
#define DECODE_RC6

#define DECODE_BOSEWAVE
#define DECODE_LEGO_PF
#define DECODE_MAGIQUEST
#define DECODE_WHYNTER


*/

#define LCD_CS A3 
#define LCD_CD A2
#define LCD_WR A1
#define LCD_RD A0 

#define LCD_RESET A4

#define	BLACK   0x0000
#define	BLUE    0x001F
#define	RED     0xF800
#define	GREEN   0x07E0
#define CYAN    0x07FF
#define MAGENTA 0xF81F
#define YELLOW  0xFFE0
#define WHITE   0xFFFF


#define ILI9341_BLACK       0x0000      /*   0,   0,   0 */
#define ILI9341_NAVY        0x000F      /*   0,   0, 128 */
#define ILI9341_DARKGREEN   0x03E0      /*   0, 128,   0 */
#define ILI9341_DARKCYAN    0x03EF      /*   0, 128, 128 */
#define ILI9341_MAROON      0x7800      /* 128,   0,   0 */
#define ILI9341_PURPLE      0x780F      /* 128,   0, 128 */
#define ILI9341_OLIVE       0x7BE0      /* 128, 128,   0 */
#define ILI9341_LIGHTGREY   0xC618      /* 192, 192, 192 */
#define ILI9341_DARKGREY    0x7BEF      /* 128, 128, 128 */
#define ILI9341_BLUE        0x001F      /*   0,   0, 255 */
#define ILI9341_GREEN       0x07E0      /*   0, 255,   0 */
#define ILI9341_CYAN        0x07FF      /*   0, 255, 255 */
#define ILI9341_RED         0xF800      /* 255,   0,   0 */
#define ILI9341_MAGENTA     0xF81F      /* 255,   0, 255 */
#define ILI9341_YELLOW      0xFFE0      /* 255, 255,   0 */
#define ILI9341_WHITE       0xFFFF      /* 255, 255, 255 */
#define ILI9341_ORANGE      0xFD20      /* 255, 165,   0 */
#define ILI9341_GREENYELLOW 0xAFE5      /* 173, 255,  47 */
#define ILI9341_PINK        0xF81F


#define BUTTON_X 40
#define BUTTON_Y 100
#define BUTTON_W 60
#define BUTTON_H 30
#define BUTTON_SPACING_X 20
#define BUTTON_SPACING_Y 20

#define BUTTON_TEXTSIZE 2

#define BUTTON_X1 65           //buttons reihe 1
#define BUTTON_Y1 100
#define BUTTON_W1 108
#define BUTTON_H1 30
#define BUTTON_SPACING_X1 5
#define BUTTON_SPACING_Y1 20

// text box where numbers go
#define TEXT_X 10
#define TEXT_Y 10
#define TEXT_W 220
#define TEXT_H 50
#define TEXT_TSIZE 3
#define TEXT_TCOLOR ILI9341_MAGENTA

#define TEXT_LEN 12


String textfield = "";

#define YP A3  
#define XM A2  
#define YM 9   
#define XP 8  


#define TS_MINX 120
#define TS_MAXX 900

#define TS_MINY 70
#define TS_MAXY 920

#define STATUS_X 10
#define STATUS_Y 65



Elegoo_TFTLCD tft(LCD_CS, LCD_CD, LCD_WR, LCD_RD, LCD_RESET);
TouchScreen ts = TouchScreen(XP, YP, XM, YM, 300);


String fernbedienungen[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

int decodertypnummer = 0;
int bestaetigung = 0;
int bestaetigung1 = 0;
int bestaetigung2 = 0;
bool sdlesenweiter = false;
bool rfSignal = false;
bool remoteweiter;
int welcherbutton = 0;
int welcherbuttonalt = 0;
char bluetoothwert = 0;
int btalsint = 25;
int anzahlbilder = 0;
int value = 0;

Elegoo_GFX_Button buttons[15];

char buttonlabels[15][15] = {"Senden", "Scannen", "PLATZHALTER", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
uint16_t buttoncolors[15] = {ILI9341_DARKGREEN, ILI9341_RED, ILI9341_DARKGREY, 
                             ILI9341_DARKCYAN, ILI9341_DARKCYAN, ILI9341_DARKCYAN, 
                             ILI9341_DARKCYAN, ILI9341_DARKCYAN, ILI9341_DARKCYAN, 
                             ILI9341_DARKCYAN, ILI9341_DARKCYAN, ILI9341_DARKCYAN, 
                             ILI9341_DARKCYAN, ILI9341_DARKCYAN, ILI9341_DARKCYAN};


            uint32_t speicher1;
            //int speicher2;
            int32_t speicher3[2];
            uint16_t speicher4;
            uint16_t speicher5;
            uint16_t speicher6;
            uint8_t speicher7; //
            uint16_t speicher8;
            decode_type_t speicher9;
            int rfSpeicher1;
            int rfSpeicher2;
            int rfSpeicher3;


                             
void setup(void) {

  Serial.begin(115200);
  Serial1.begin(9600);

  pinMode(49, OUTPUT);
  pinMode(23, OUTPUT);
  pinMode(27, OUTPUT);
  pinMode(28, OUTPUT);
  pinMode(29, OUTPUT);
  pinMode(30, OUTPUT);
  pinMode(24, OUTPUT);
  pinMode(25, OUTPUT);
  pinMode(26, OUTPUT);

  digitalWrite(23, HIGH);
  digitalWrite(24, HIGH);
  digitalWrite(25, HIGH);
  digitalWrite(26, HIGH);
  digitalWrite(27, LOW);
  digitalWrite(28, LOW);
  digitalWrite(29, LOW);
  digitalWrite(30, LOW);

  tft.reset();

  uint16_t identifier = tft.readID();
  if(identifier==0x0101){     
      identifier=0x9341;
  }else{
    identifier=0x9341;
  }

  tft.begin(identifier);
  tft.setRotation(2);
  tft.fillScreen(BLACK);

  delay(1000);
  tft.drawRect(TEXT_X, TEXT_Y, TEXT_W, TEXT_H, ILI9341_WHITE);
  bmpDraw("woof.bmp", 0, 0);
  delay(1000);

#if defined(__AVR_ATmega32U4__) || defined(SERIAL_PORT_USBVIRTUAL) || defined(SERIAL_USB) /*stm32duino*/|| defined(USBCON) /*STM32_stm32*/|| defined(SERIALUSB_PID) || defined(ARDUINO_attiny3217)
    delay(4000); // To be able to connect Serial monitor after reset or power up and before first print out. Do not wait for an attached Serial Monitor!
#endif
    // Just to know which program is running on my Arduino
    Serial.println(F("Verzeichnis: " __FILE__ " letztes Update: " __DATE__ "\r\nVersion der Ir-Libary: " VERSION_IRREMOTE));

    // Start the receiver and if not 3. parameter specified, take LED_BUILTIN pin from the internal boards definition as default feedback LED
    IrReceiver.begin(IR_RECEIVE_PIN, ENABLE_LED_FEEDBACK);

#if defined(IR_SEND_PIN)
    IrSender.begin(); // Start with IR_SEND_PIN as send pin and enable feedback LED at default feedback LED pin
#else
    IrSender.begin(46, ENABLE_LED_FEEDBACK, USE_DEFAULT_FEEDBACK_LED_PIN); // Specify send pin and enable feedback LED at default feedback LED pin
#endif


  buttonsauslesen();
  namenauslesen();
  
  //websiteauslesen();

  digitalWrite(23, HIGH);
  digitalWrite(49, LOW);

  pinMode(STATUS_PIN, OUTPUT);

  Serial.print(F("Erlaubte Infrarot-Protokolle: "));
  printActiveIRProtocols(&Serial);
  Serial.println(F("at pin " STR(IR_RECEIVE_PIN)));

  Serial.print(F("Sender auf Pin " STR(IR_SEND_PIN) " (unten)."));
  Serial.println(" ");


  delay(100);
  IrReceiver.begin(IR_RECEIVE_PIN, ENABLE_LED_FEEDBACK);
  printActiveIRProtocols(&Serial);
  
  
                                                                                                    //buttons erstellen
      
      for (uint8_t col=0; col<2; col++) {                                                           //1.reihe
      buttons[col + 0*3].initButton(&tft, BUTTON_X1+col*(BUTTON_W1+BUTTON_SPACING_X1), 
                 BUTTON_Y1+0*(BUTTON_H1+BUTTON_SPACING_Y1),    
                  BUTTON_W1, BUTTON_H1, ILI9341_WHITE, buttoncolors[col+0*3], ILI9341_WHITE,
                  buttonlabels[col + 0*3], BUTTON_TEXTSIZE); 
      buttons[col + 0*3].drawButton();
    }


  for (uint8_t row=1; row<5; row++) {                                                               //ab 2.reihe
    for (uint8_t col=0; col<3; col++) {
      buttons[col + row*3].initButton(&tft, BUTTON_X+col*(BUTTON_W+BUTTON_SPACING_X), 
                 BUTTON_Y+row*(BUTTON_H+BUTTON_SPACING_Y),    
                  BUTTON_W, BUTTON_H, ILI9341_WHITE, buttoncolors[col+row*3], ILI9341_WHITE,
                  buttonlabels[col + row*3], BUTTON_TEXTSIZE); 
      buttons[col + row*3].drawButton();
    }
  }
}
                                                                                                  //statusbar ausgabe
void status(const __FlashStringHelper *msg) {
  tft.fillRect(STATUS_X, STATUS_Y, 240, 8, ILI9341_BLACK);
  tft.setCursor(STATUS_X, STATUS_Y);
  tft.setTextColor(ILI9341_WHITE);
  tft.setTextSize(1);
  tft.print(msg);
}

void status(char *msg) {
  tft.fillRect(STATUS_X, STATUS_Y, 240, 8, ILI9341_BLACK);
  tft.setCursor(STATUS_X, STATUS_Y);
  tft.setTextColor(ILI9341_WHITE);
  tft.setTextSize(1);
  tft.print(msg);
  
}

#define MINPRESSURE 100                           //10
#define MAXPRESSURE 1000                          //1000

#define SD_CS 53

void buttonsauslesen(){
  String h;
  int arraystelle = 3;
  pinMode(53, OUTPUT);
  if(!SD.begin(53)) {
    Serial.println("SD fehlt!");
    return;
  }
  Serial.println("SD vorhanden");
  myFile = SD.open("buttons.txt", FILE_READ);
  delay(100);
  if (myFile) {
    while(myFile.available()){
      h = myFile.readStringUntil(',');
      for(int e = 0; e < h.length(); e++){
        buttonlabels[arraystelle][e] = h.charAt(e);
      }
      arraystelle++;
    }
  }
}

void namenauslesen(){
  String h;
  int arraystelle = 0;
  pinMode(53, OUTPUT);
  if(!SD.begin(53)) {
    return;
  }
  myFile = SD.open("namen.txt", FILE_READ);
  delay(100);
  if (myFile) {
    while(myFile.available()){
      h = myFile.readStringUntil(',');
      fernbedienungen[arraystelle] = h;
      arraystelle++;
    }
  }
}

void websiteauslesen(){
  Serial1.end();
  delay(500);
  Serial1.begin(9600);
  delay(200);
  bool schleife = true;
  while(schleife){
  while(Serial1.available() > 0){
    String abc = Serial1.readStringUntil(10);
    Serial.println(abc.charAt(0));
    if(abc.charAt(0) == 'A'){
      Serial.println("Ja");
      schleife = false;      
    }
  }
  }
  delay(180);
  String h;
  pinMode(53, OUTPUT);
  if(!SD.begin(53)) {
    return;
  }
  myFile = SD.open("website.txt", FILE_READ);
  delay(100);
  if (myFile) {
    while(myFile.available()){
      h = myFile.readStringUntil(((char)10));
      Serial.println(h);
      Serial1.println(h);
    }
    
  }
  
}


void loop(void) {
/*
  if(Serial1.available() > 0){
    String q = Serial1.readString();
    Serial.print(q);
    if(q.equals("Hallo")){
      Serial.println("JA");
      delay(25);
      while(true){
        Serial.println(Serial1.read());
      }      
    }
  }
*/


  
  if(Serial1.available() > 0){
    bluetoothwert = Serial1.read();
    if(bluetoothwert != '30'){
      btalsint = ((int) bluetoothwert) - 46;
      bluetoothwert = '21';
    }
  }
  

  digitalWrite(13, HIGH);
  TSPoint p = ts.getPoint();
  digitalWrite(13, LOW);
          

  pinMode(XM, OUTPUT);
  pinMode(YP, OUTPUT);

   if (p.z > MINPRESSURE && p.z < MAXPRESSURE) {
  
    p.x = map(p.x, TS_MINX, TS_MAXX, tft.width(), 0);
    p.y = (tft.height()-map(p.y, TS_MINY, TS_MAXY, tft.height(), 0));
   }
   
 
  for (uint8_t b=0; b<15; b++) {
    if (buttons[b].contains(p.x, p.y)) {
      
      buttons[b].press(true);  
    } else {
      buttons[b].press(false);
    }
  }

  
  for (uint8_t b=0; b<15; b++) {
    if (buttons[b].justReleased()) {
    
      buttons[b].drawButton();
    }
    
    if (buttons[b].justPressed() | (btalsint > 2 & btalsint < 20)) {
        buttons[b].drawButton(true);  
        
        Serial.println(btalsint);

        if((b > 2 & b < 15) | (btalsint > 2 & btalsint < 19)){
          if(btalsint > 2 & btalsint < 15){
            b = btalsint;
          }
          textfield = fernbedienungen[b - 3];
          welcherbutton = b - 1;
          welcherbuttonalt = welcherbutton;
          adresse = 0;
          if(btalsint > 2 & btalsint < 15){
            b = 0;
          }
        }else{
          textfield = " ";
        }



        if(b == 8){
          if(bestaetigung1 == 0){
            status(F("Noch 2 mal druecken zum Loeschen"));
            bestaetigung1++;
            break;
          }
          if(bestaetigung1 == 1){
            status(F("Noch 1 mal druecken zum Loeschen"));
            bestaetigung1++;
            break;          
          }
          if(bestaetigung1 == 2){
            status(F(" "));
            status(F("Dateien geloescht"));
            bestaetigung1 = 0;
            pinMode(53, OUTPUT);
            if (!SD.begin(53)) {
              Serial.println("SD fehlt!");
              return;
            }
            for(int b = 0; b < 12; b++){
              SD.remove((fernbedienungen[b] + ".txt"));
            }
          }
        }else{
          bestaetigung1 = 0;
        }
        
        Serial.println(textfield);
        tft.setCursor(TEXT_X + 2, TEXT_Y+10);
        tft.setTextColor(TEXT_TCOLOR, ILI9341_BLACK);
        tft.setTextSize(TEXT_TSIZE);
        tft.print("        ");        
        tft.setCursor(TEXT_X + 2, TEXT_Y+10);
        tft.setTextColor(TEXT_TCOLOR, ILI9341_BLACK);
        tft.setTextSize(TEXT_TSIZE);
        tft.print(textfield);

        Serial.print("welcherbuttonalt: ");
        Serial.println(welcherbuttonalt);

        if (b == 1) {
          if(bestaetigung == 0){
          status(F("Noch 2 mal druecken"));
          bestaetigung++;
          break;
          }
          if(bestaetigung == 1){
          status(F("Noch 1 mal druecken"));
          bestaetigung++;
          break;          
          }
          if(bestaetigung == 2){
            status(F(" "));
            status(F("Scanne..."));
            bestaetigung = 0;
                    
            IrReceiver.start();
            scannen();
            //sStoredIRData.receivedIRData.flags = IRDATA_FLAGS_IS_REPEAT;
       
            
            speicher1 = sStoredIRData.receivedIRData.decodedRawData;
            speicher3[0] = sStoredIRData.receivedIRData.decodedRawDataArray[0];
            speicher3[1] = sStoredIRData.receivedIRData.decodedRawDataArray[1];
            speicher4 = sStoredIRData.receivedIRData.address;
            speicher5 = sStoredIRData.receivedIRData.command;
            speicher6 = sStoredIRData.receivedIRData.extra;
            speicher7 = sStoredIRData.receivedIRData.flags;
            speicher8 = sStoredIRData.receivedIRData.numberOfBits;
            speicher9 = sStoredIRData.receivedIRData.protocol;

            if(rfSignal == true){
              rfSpeicher1 = value;
              rfSpeicher2 = rfModul.getReceivedBitlength();
              rfSpeicher3 = rfModul.getReceivedProtocol();
              
            }

            Serial.println(" ");
            Serial.println(" ");
            Serial.println(" ");
            Serial.println("    VOR dem Speichern --> Werte der Speicherdaten(1 & 3-9) :");
            Serial.println(" ");
            Serial.println(sStoredIRData.receivedIRData.decodedRawData);
            //Serial.println(speicher2);
            Serial.println(speicher3[0]);
            Serial.println(speicher3[1]);
            Serial.println(speicher4);
            Serial.println(speicher5);
            Serial.println(speicher6);
            Serial.println(speicher7);
            Serial.println(speicher8);
            Serial.println(speicher9);
            Serial.println(" ");
            Serial.println(" ");
            Serial.println(" ");


            sdschreiben(&sStoredIRData);

          }
          delay(200);
          
        }else{
          bestaetigung = 0;
        }

        if (b == 0) {
          b = 22;
          status(F("Sende..."));
          IrReceiver.stop();
          

        if(adresse == 0){
          adresse = 1;
          sdlesen();
        }
        zuordnen(&sStoredIRData);
        Serial.println(F("Sende..."));
        digitalWrite(STATUS_PIN, HIGH);
        zuordnen(&sStoredIRData);
        digitalWrite(STATUS_PIN, LOW);
        status(F("           "));
        rfSignal = false;
        }else{
        delay(100);                                                           //wichtig
        }
    }
    btalsint = 20;
  }
}

void scannen(){
  String bit = IrReceiver.decodedIRData.numberOfBits + " Bits";
  String adaptiv1 = "Unbekannt   " + bit;
  String adaptiv2 = "NEC   " + bit;
  String adaptiv3 = "SONY   " + bit;
  String adaptiv4 = "RC5   " + bit;
  String adaptiv5 = "RC6   " + bit;
  String adaptiv6 = "SHARP   " + bit;
  String adaptiv7 = "JVC   " + bit;
  String adaptiv8 = "SAMSUNG   " + bit;
  String adaptiv9 = "LG   " + bit;
  String adaptiv10 = "WHYNTER   " + bit;
  String adaptiv11 = "PANASONIC   " + bit;
  String adaptiv12 = "DENON   " + bit;
  rfModul.enableReceive(2);
  while(true){
    
    if(IrReceiver.available()) {
      dekodieren(IrReceiver.read());
      /*switch (IrReceiver.decodedIRData.protocol) {
        default:
        case UNKNOWN:      Serial.print("UNKNOWN"); status(F(adaptiv1));      break ;
        case NEC:          Serial.print("NEC"); status(F(adaptiv2));         break ;
        case SONY:         Serial.print("SONY"); status(F(adaptiv3));        break ;
        case RC5:          Serial.print("RC5");   status(F(adaptiv4));       break ;
        case RC6:          Serial.print("RC6");   status(F(adaptiv5));       break ;
        case SHARP:        Serial.print("SHARP"); status(F(adaptiv6));       break ;
        case JVC:          Serial.print("JVC");   status(F(adaptiv7));       break ;
        case SAMSUNG:      Serial.print("SAMSUNG");  status(F(adaptiv8));    break ;
        case LG:           Serial.print("LG");    status(F(adaptiv9));       break ;
        case WHYNTER:      Serial.print("WHYNTER");  status(F(adaptiv10));   break ;
        case PANASONIC:    Serial.print("PANASONIC"); status(F(adaptiv11));  break ;
        case DENON:        Serial.print("Denon");  status(F(adaptiv12));     break ;
      }       */
      IrReceiver.resume(); 
      return;
    }
    if (rfModul.available()){
      Serial.println("433Hz-Signal erhalten");
      value = rfModul.getReceivedValue();
      if (value == 0) {
        Serial.println("Unbekannter Code");
      } 
      else {
      Serial.print("Empfangen: ");
      Serial.println( value );
    }
    delay(100);
    rfSignal = true;
    rfModul.resetAvailable();
    return;
  }
  }
}

void dekodieren(IRData *aIRReceivedData){

     if (aIRReceivedData->flags & IRDATA_FLAGS_IS_REPEAT) {
        Serial.println(F("Ignore repeat"));
        return;
    }
    if (aIRReceivedData->flags & IRDATA_FLAGS_IS_AUTO_REPEAT) {
        Serial.println(F("Ignore autorepeat"));
        return;
    }
    if (aIRReceivedData->flags & IRDATA_FLAGS_PARITY_FAILED) {
        Serial.println(F("Ignore parity error"));
        return;
    }
    /*
     * Copy decoded data
     */
    sStoredIRData.receivedIRData = *aIRReceivedData;

    if (sStoredIRData.receivedIRData.protocol == UNKNOWN) {
        Serial.print(F("Received unknown code and store "));
        Serial.print(IrReceiver.decodedIRData.rawDataPtr->rawlen - 1);
        Serial.println(F(" timing entries as raw "));
        IrReceiver.printIRResultRawFormatted(&Serial, true); // Output the results in RAW format
        sStoredIRData.rawCodeLength = IrReceiver.decodedIRData.rawDataPtr->rawlen - 1;
        /*
         * Store the current raw data in a dedicated array for later usage
         */
        IrReceiver.compensateAndStoreIRResultInArray(sStoredIRData.rawCode);
    } else {
        IrReceiver.printIRResultShort(&Serial);
        IrReceiver.printIRSendUsage(&Serial);
        sStoredIRData.receivedIRData.flags = 0; // clear flags -esp. repeat- for later sending
        Serial.println();
    }
}


void zuordnen(storedIRDataStruct *aIRDataToSend){
  if(rfSignal == true){
    Serial.println("Sende Rf-Signal");
    rfModul.disableReceive();
    delay(20);
    rfModul.enableTransmit(45);
    delay(20);
    
    rfModul.setProtocol(rfSpeicher3);
    delay(20);
    rfModul.send(rfSpeicher1, rfSpeicher2);
    delay(20);
  }else{

         digitalWrite(STATUS_PIN, HIGH);   
         digitalWrite(STATUS_PIN, LOW);
         delay(50);   
                                                                  
    if (aIRDataToSend->receivedIRData.protocol == UNKNOWN ) {
        //38 KHz
         IrSender.sendRaw(aIRDataToSend->rawCode, aIRDataToSend->rawCodeLength, 38);

         Serial.print(F("Sende als Raw-Code "));
         Serial.print(aIRDataToSend->rawCodeLength);
         Serial.println(F(", weil das Protokoll Unbekannt ist."));
    } else {


         IrSender.write(&aIRDataToSend->receivedIRData, 3);

         Serial.print(F("Sende... "));
         printIRResultShort(&Serial, &aIRDataToSend->receivedIRData, false);
    }
  }
}


void sdschreiben(storedIRDataStruct *aIRDataToSend){
  String dateiname;
  
  switch(welcherbuttonalt - 1){
    case 1 : dateiname = fernbedienungen[0] + ".txt"; break;
    case 2 : dateiname = fernbedienungen[1] + ".txt"; break;
    case 3 : dateiname = fernbedienungen[2] + ".txt"; break;
    case 4 : dateiname = fernbedienungen[3] + ".txt"; break;
    case 5 : dateiname = fernbedienungen[4] + ".txt"; break;
    case 6 : dateiname = fernbedienungen[5] + ".txt"; break;
    case 7 : dateiname = fernbedienungen[6] + ".txt"; break;
    case 8 : dateiname = fernbedienungen[7] + ".txt"; break;
    case 9 : dateiname = fernbedienungen[8] + ".txt"; break;
    case 10 : dateiname = fernbedienungen[9] + ".txt"; break;
    case 11 : dateiname = fernbedienungen[10] + ".txt"; break;
    case 12 : dateiname = fernbedienungen[11] + ".txt"; break;
  }
  Serial.println("schreiben");
  rawlaenge = aIRDataToSend->rawCodeLength;
  for(int i = 0; i < rawlaenge; i++){
    aray[i] = aIRDataToSend->rawCode[i];
  }
  pinMode(53, OUTPUT);
  delay(50);
  if (!SD.begin(53)) {
    Serial.println("SD fehlt!");
    return;
  }
  Serial.println("SD vorhanden");

  SD.remove(dateiname);
  delay(100);

  myFile = SD.open(dateiname, FILE_WRITE);

  if (myFile) {
    Serial.print("Speichere Daten in ");
    Serial.println(dateiname);

  if(rfSignal == true){
    myFile.println("RF433");
    myFile.println(rfSpeicher1);
    myFile.println(rfSpeicher2);
    myFile.println(rfSpeicher3);

  }else if(aIRDataToSend->receivedIRData.protocol == UNKNOWN){
    rawlaenge = aIRDataToSend->rawCodeLength;
    myFile.println(rawlaenge);
    for(int i = 0; i < rawlaenge; i++){
      aray[i] = aIRDataToSend->rawCode[i];
      myFile.println(aray[i]);
      delay(20);
    }
  }else{
    myFile.println(aIRDataToSend->receivedIRData.decodedRawData);
    //myFile.println(speicher2);
    myFile.println(speicher3[0]);
    myFile.println(speicher3[1]);
    myFile.println(speicher4);
    myFile.println(speicher5);
    myFile.println(speicher6);
    myFile.println(speicher7);
    myFile.println(speicher8);
    myFile.println(speicher9);
  }
    myFile.close();
  } else {
 myFile.close();
  }
  
}


int switchen(int g, String h){
  int f1 = 1;
  int f2 = 2;
  int f3 = 3;
  int f4 = 4;
  int f5 = 5;
  int f6 = 6;
  int f7 = 7;
  int f8 = 8;
  int f9 = 9;
  Serial.println("switchen: " + g);
  Serial.println(f1);
        if(g == f9 - 1){
          speicher9 = (uint32_t)h.toInt(); g++;
        }
        if(g == f8 - 1){
          speicher8 = (uint16_t)h.toInt(); g++; 
        }
        if(g == f7 - 1){
          speicher7 = (uint8_t)h.toInt(); g++;
        }
        if(g == f6 - 1){
          speicher6 = (uint16_t)h.toInt(); g++;
        }
        if(g == f5 - 1){
          speicher5 = (uint16_t)h.toInt(); g++;
        }
        if(g == f4 - 1){
          speicher4 = (uint16_t)h.toInt(); g++;
        }
        if(g == f3 - 1){
          speicher3[1] = h.toInt(); g++; 
        }
        if(g == f2 - 1){
          speicher3[0] = h.toInt(); g++; 
        }
        if(g == f1 - 1){
          speicher1 = h.toFloat(); g++;
        }
        return g;
}

void sdlesen(){
  String dateiname;
  switch(welcherbuttonalt){
    case 2 : dateiname = fernbedienungen[0] + ".txt"; Serial.println("datei1"); break;
    case 3 : dateiname = fernbedienungen[1] + ".txt"; Serial.println("datei2"); break;
    case 4 : dateiname = fernbedienungen[2] + ".txt"; Serial.println("datei3"); break;
    case 5 : dateiname = fernbedienungen[3] + ".txt"; break;
    case 6 : dateiname = fernbedienungen[4] + ".txt"; break;
    case 7 : dateiname = fernbedienungen[5] + ".txt"; break;
    case 8 : dateiname = fernbedienungen[6] + ".txt"; break;
    case 9 : dateiname = fernbedienungen[7] + ".txt"; break;
    case 10 : dateiname = fernbedienungen[8] + ".txt"; break;
    case 11 : dateiname = fernbedienungen[9] + ".txt"; break;
    case 12 : dateiname = fernbedienungen[10] + ".txt"; break;
    case 13 : dateiname = fernbedienungen[11] + ".txt"; break;
  }
  pinMode(53, OUTPUT);
  if(!SD.begin(53)) {
    Serial.println("SD fehlt!");
    return;
  }
  Serial.println("SD vorhanden");
  myFile = SD.open(dateiname, FILE_READ);
  delay(100);
  String h;
  String hilfsH;
  int werte = 0;
  int uebernehmenabwann = 0;
  if (myFile) {
    Serial.println(dateiname);

    int g = 0;
    bool rf = false;
    int rfZaehler = 0;

      while (schleifebeenden == false) {             
        Serial.print("Wert g: ");
        Serial.println(g);
        
    	  h = myFile.readStringUntil('\n');
        hilfsH = h;
        hilfsH.trim();
        
        if(rfSignal == false){
          g = switchen(g, h);
        }
        if(rf == true){
          rfZaehler++;
          if(rfZaehler == 1){
            rfSpeicher1 = h.toInt();
          }
          if(rfZaehler == 2){
            rfSpeicher2 = h.toInt();
          }
          if(rfZaehler == 3){
            rfSpeicher3 = h.toInt();
            schleifebeenden = true;
          }
        }
        Serial.print("H: ");
        Serial.println(h);
        if(hilfsH.equals("RF433")){
          rfSignal = true;
          rf = true;
        }
         if(g == 9){
           Serial.println(g);
           g = 0;
           schleifebeenden = true;
         }
      }
  }
schleifebeenden = false;

    myFile.close();
            
            sStoredIRData.receivedIRData.decodedRawData = speicher1;
            sStoredIRData.receivedIRData.rawDataPtr = 0;
            sStoredIRData.receivedIRData.decodedRawDataArray[0] = speicher3[0];
            sStoredIRData.receivedIRData.decodedRawDataArray[1] = speicher3[1];
            sStoredIRData.receivedIRData.address = speicher4;
            sStoredIRData.receivedIRData.command = speicher5;
            sStoredIRData.receivedIRData.extra = speicher6;
            sStoredIRData.receivedIRData.flags = speicher7;
            sStoredIRData.receivedIRData.numberOfBits = speicher8;
            Serial.println(" ");
            Serial.println(" ");
            Serial.println(" ");
            Serial.println("    NACH dem Speichern --> Werte der Speicherdaten(1 & 3-9) :");
            Serial.println(" ");
            Serial.println(sStoredIRData.receivedIRData.decodedRawData);
            //Serial.println(speicher2);                                                                    //wird nicht benutzt, frag einfach nicht
            Serial.println(speicher3[0]);
            Serial.println(speicher3[1]);
            Serial.println(speicher4);
            Serial.println(speicher5);
            Serial.println(speicher6);
            Serial.println(speicher7);
            Serial.println(speicher8);


            switch(speicher9 - 1){
              Serial.println(speicher9);
              Serial.println(" ");
              Serial.println(" ");
              Serial.println(" ");
              case 0:
                sStoredIRData.receivedIRData.protocol = UNKNOWN;
                break;
              case 1:
                sStoredIRData.receivedIRData.protocol = PULSE_DISTANCE;
                break;
              case 2:
                sStoredIRData.receivedIRData.protocol = APPLE;
                break;
              case 3:
               sStoredIRData.receivedIRData.protocol = DENON;
                break;
              case 4:
                sStoredIRData.receivedIRData.protocol = JVC;
                break;
              case 5:
                sStoredIRData.receivedIRData.protocol = LG;
                break;
              case 6:
                sStoredIRData.receivedIRData.protocol = LG2;
                break;
              case 7:
                sStoredIRData.receivedIRData.protocol = NEC;
                break;
              case 8:
                sStoredIRData.receivedIRData.protocol = NEC2;
                break;
              case 9:
                sStoredIRData.receivedIRData.protocol = ONKYO;
                break;
              case 10:
                sStoredIRData.receivedIRData.protocol = PANASONIC;
                break;
              case 11:
                sStoredIRData.receivedIRData.protocol = KASEIKYO;
                break;
              case 12:
                sStoredIRData.receivedIRData.protocol = KASEIKYO_DENON;
                break;
              case 13:
                sStoredIRData.receivedIRData.protocol = KASEIKYO_SHARP;
                break;
              case 14:
                sStoredIRData.receivedIRData.protocol = KASEIKYO_JVC;
                break;
              case 15:
                sStoredIRData.receivedIRData.protocol = KASEIKYO_MITSUBISHI;
                break;
              case 16:
                sStoredIRData.receivedIRData.protocol = RC5;
                break;
              case 17:
                sStoredIRData.receivedIRData.protocol = RC6;
                break;
              case 18:
                sStoredIRData.receivedIRData.protocol = SAMSUNG;
                break;
              case 19:
                sStoredIRData.receivedIRData.protocol = SAMSUNG_LG;
                break;
              case 20:
                sStoredIRData.receivedIRData.protocol = SHARP;
                break;
              case 21:
                sStoredIRData.receivedIRData.protocol = SONY;
                break;
              case 22:
                sStoredIRData.receivedIRData.protocol = BANG_OLUFSEN;
                break;
              case 23:
                sStoredIRData.receivedIRData.protocol = BOSEWAVE;
                break;
              case 24:
                sStoredIRData.receivedIRData.protocol = LEGO_PF;
                break;
              case 25:
                sStoredIRData.receivedIRData.protocol = MAGIQUEST;
                break;
              case 26:
                sStoredIRData.receivedIRData.protocol = WHYNTER;
                break;
              case 27:
                //sStoredIRData.receivedIRData.protocol = FAST;                                           //weiss nicht warum
                break;
            }         
}

void bmpDraw(char *filename, int x, int y) {

  File     bmpFile;
  int      bmpWidth, bmpHeight;   // W+H in pixels
  uint8_t  bmpDepth;              // Bit depth (currently must be 24)
  uint32_t bmpImageoffset;        // Start of image data in file
  uint32_t rowSize;               // Not always = bmpWidth; may have padding
  uint8_t  sdbuffer[3*BUFFPIXEL]; // pixel in buffer (R+G+B per pixel)
  uint16_t lcdbuffer[BUFFPIXEL];  // pixel out buffer (16-bit per pixel)
  uint8_t  buffidx = sizeof(sdbuffer); // Current position in sdbuffer
  boolean  goodBmp = false;       // Set to true on valid header parse
  boolean  flip    = true;        // BMP is stored bottom-to-top
  int      w, h, row, col;
  uint8_t  r, g, b;
  uint32_t pos = 0, startTime = millis();
  uint8_t  lcdidx = 0;
  boolean  first = true;

  if((x >= tft.width()) || (y >= tft.height())) return;

  Serial.println();
  Serial.print(F("Loading image '"));
  Serial.print(filename);
  Serial.println('\'');
  // Open requested file on SD card
  if ((bmpFile = SD.open(filename)) == NULL) {
    Serial.println(F("File not found"));
    return;
  }

  // Parse BMP header
  if(read16(bmpFile) == 0x4D42) { // BMP signature
    Serial.println(F("File size: ")); Serial.println(read32(bmpFile));
    (void)read32(bmpFile); // Read & ignore creator bytes
    bmpImageoffset = read32(bmpFile); // Start of image data
    Serial.print(F("Image Offset: ")); Serial.println(bmpImageoffset, DEC);
    // Read DIB header
    Serial.print(F("Header size: ")); Serial.println(read32(bmpFile));
    bmpWidth  = read32(bmpFile);
    bmpHeight = read32(bmpFile);
    if(read16(bmpFile) == 1) { // # planes -- must be '1'
      bmpDepth = read16(bmpFile); // bits per pixel
      Serial.print(F("Bit Depth: ")); Serial.println(bmpDepth);
      if((bmpDepth == 24) && (read32(bmpFile) == 0)) { // 0 = uncompressed

        goodBmp = true; // Supported BMP format -- proceed!
        Serial.print(F("Image size: "));
        Serial.print(bmpWidth);
        Serial.print('x');
        Serial.println(bmpHeight);

        // BMP rows are padded (if needed) to 4-byte boundary
        rowSize = (bmpWidth * 3 + 3) & ~3;

        // If bmpHeight is negative, image is in top-down order.
        // This is not canon but has been observed in the wild.
        if(bmpHeight < 0) {
          bmpHeight = -bmpHeight;
          flip      = false;
        }

        // Crop area to be loaded
        w = bmpWidth;
        h = bmpHeight;
        if((x+w-1) >= tft.width())  w = tft.width()  - x;
        if((y+h-1) >= tft.height()) h = tft.height() - y;

        // Set TFT address window to clipped image bounds
        tft.setAddrWindow(x, y, x+w-1, y+h-1);

        for (row=0; row<h; row++) { // For each scanline...
          // Seek to start of scan line.  It might seem labor-
          // intensive to be doing this on every line, but this
          // method covers a lot of gritty details like cropping
          // and scanline padding.  Also, the seek only takes
          // place if the file position actually needs to change
          // (avoids a lot of cluster math in SD library).
          if(flip) // Bitmap is stored bottom-to-top order (normal BMP)
            pos = bmpImageoffset + (bmpHeight - 1 - row) * rowSize;
          else     // Bitmap is stored top-to-bottom
            pos = bmpImageoffset + row * rowSize;
          if(bmpFile.position() != pos) { // Need seek?
            bmpFile.seek(pos);
            buffidx = sizeof(sdbuffer); // Force buffer reload
          }

          for (col=0; col<w; col++) { // For each column...
            // Time to read more pixel data?
            if (buffidx >= sizeof(sdbuffer)) { // Indeed
              // Push LCD buffer to the display first
              if(lcdidx > 0) {
                tft.pushColors(lcdbuffer, lcdidx, first);
                lcdidx = 0;
                first  = false;
              }
              bmpFile.read(sdbuffer, sizeof(sdbuffer));
              buffidx = 0; // Set index to beginning
            }

            // Convert pixel from BMP to TFT format
            b = sdbuffer[buffidx++];
            g = sdbuffer[buffidx++];
            r = sdbuffer[buffidx++];
            lcdbuffer[lcdidx++] = tft.color565(r,g,b);
          } // end pixel
        } // end scanline
        // Write any remaining data to LCD
        if(lcdidx > 0) {
          tft.pushColors(lcdbuffer, lcdidx, first);
        } 
        Serial.print(F("Loaded in "));
        Serial.print(millis() - startTime);
        Serial.println(" ms");
      } // end goodBmp
    }
  }

  bmpFile.close();
  if(!goodBmp) Serial.println(F("BMP format not recognized."));
}

// These read 16- and 32-bit types from the SD card file.
// BMP data is stored little-endian, Arduino is little-endian too.
// May need to reverse subscript order if porting elsewhere.

uint16_t read16(File f) {
  uint16_t result;
  ((uint8_t *)&result)[0] = f.read(); // LSB
  ((uint8_t *)&result)[1] = f.read(); // MSB
  return result;
}

uint32_t read32(File f) {
  uint32_t result;
  ((uint8_t *)&result)[0] = f.read(); // LSB
  ((uint8_t *)&result)[1] = f.read();
  ((uint8_t *)&result)[2] = f.read();
  ((uint8_t *)&result)[3] = f.read(); // MSB
  return result;
}