#include <Redis.h>
#include <ArduinoJson.h>
#include "DHT.h"

#define WIFI_SSID "UPC1354629"
#define WIFI_PASSWORD "BQfmc7dmsz4x"

#define REDIS_ADDR "54.38.55.55"
#define REDIS_PORT 6379
#define REDIS_PASSWORD "Sasanka0101"

#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
#define SENSOR_ID_01 "000000001"
#define SENSOR_ID_02 "000000002"
uint8_t DHTPin = D2; 
               
// Initialize DHT sensor.
DHT dht(DHTPin, DHTTYPE);                

float Temperature;
float Humidity;
int myTime=1000000000;

Redis redis(REDIS_ADDR, REDIS_PORT);

void setup() {
    Serial.begin(115200);
    Serial.println();
    delay(100);

    Serial.println("Activate sensor");
    pinMode(DHTPin, INPUT);
    dht.begin();    

    WiFi.mode(WIFI_STA);
    WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
    Serial.print("Connecting to the WiFi");
    while (WiFi.status() != WL_CONNECTED)
    {
        delay(250);
        Serial.print(".");
    }
    Serial.println();
    Serial.print("IP Address: ");
    Serial.println(WiFi.localIP());

      if (redis.begin(REDIS_PASSWORD)) {
          Serial.println("Connected to the Redis server!");
      } else {
         Serial.println("Failed to connect to the Redis server!");
      return; }

//
//    redis.close();
//    Serial.print("Connection closed!");
}

void loop(){
  pushToRedis(); 
}

void pushToRedis() {
  delay(60000);
  
  Temperature = dht.readTemperature(); // Gets the values of the temperature
  Humidity = dht.readHumidity(); // Gets the values of the humidity 
  
  if (fmod(Temperature,10) == 0.0) {
    Temperature = Temperature + 0.1;
  }
  if (fmod(Humidity,10) == 0.0) {
    Humidity = Humidity + 0.1;
  }

    DynamicJsonDocument doc(2048);
    doc["number"] = myTime++;
    JsonObject data  = doc.createNestedObject("data");
    data["temperature"] = (float) Temperature;
    data["humidity"] = (float) Humidity;
    
   String json;
   serializeJson(doc, json);
   json.replace("\"","'");
    Serial.println(json);
    char str[10];
    
    redis.set(SENSOR_ID_01, json.c_str());
//    redis.hset(SENSOR_ID_02, itoa(myTime, str, 10), json.c_str());
}
