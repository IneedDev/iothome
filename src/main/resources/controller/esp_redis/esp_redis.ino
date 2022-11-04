#include <Redis.h>
#include <ArduinoJson.h>
#include <NTPClient.h>
#include <WiFiUdp.h>
#include "DHT.h"

#define WIFI_SSID "*******"
#define WIFI_PASSWORD "*******"

#define REDIS_ADDR "*******"
#define REDIS_PORT 6379
#define REDIS_PASSWORD "*******"

#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
#define DHTTYPE11 DHT11

#define SENSOR_ID_01 "000000001"
#define SENSOR_ID_02 "000000002"
uint8_t DHTPin = D2;
uint8_t DHTPin11 = D3;

// Initialize DHT sensor.
DHT dht(DHTPin, DHTTYPE);

DHT dht11(DHTPin11, DHTTYPE11);

float Temperature;
float Temperature11;
float Humidity;
float Humidity11;
int myTime=1000000000;

Redis redis(REDIS_ADDR, REDIS_PORT);

WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org");

//Week Days
String weekDays[7]={"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

//Month names
String months[12]={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


void setup() {
  setupNtpClient();


    Serial.begin(115200);
    Serial.println();
    delay(100);

    Serial.println("Activate sensor");
    pinMode(DHTPin, INPUT);
    pinMode(DHTPin11, INPUT);
    dht.begin();
    dht11.begin();

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
}

void loop(){
  timeClient.update();
  getCurrnetTimeFormated();
  pushToRedis();
}

void setupNtpClient() {
  timeClient.begin();
  timeClient.setTimeOffset(0);
}

String getCurrnetTimeFormated() {
  String formattedTime = timeClient.getFormattedTime();
  unsigned long epochTime = timeClient.getEpochTime();
  struct tm *ptm = gmtime ((time_t *)&epochTime);
  int monthDay = ptm->tm_mday;
  int currentMonth = ptm->tm_mon+1;
  int currentYear = ptm->tm_year+1900;
  String currentDate = String(currentYear) + "-" + getCorrectValue(currentMonth) + "-" + getCorrectValue(monthDay) + " " + String(formattedTime);
  return currentDate;
}

String getCorrectValue(int value) {
    if (value < 10) {
    return "0" + String(value);
  } else {
    return String(value);
  }
}

float prepareSensorFloatData(float data) {
  if (fmod(data,10) == 0.0) {
    data = data + 0.1;
    return data;
  };
  return data;
}
void pushToRedis() {
  delay(600);
  Temperature = prepareSensorFloatData(dht.readTemperature()); // Gets the values of the temperature
  Humidity = prepareSensorFloatData(dht.readHumidity()); // Gets the values of the humidity
  Temperature11 = prepareSensorFloatData(dht11.readTemperature()); // Gets the values of the temperature

  redis.set(SENSOR_ID_01, prepareRedisQuery(Temperature, Humidity).c_str());
  redis.set(SENSOR_ID_02, prepareRedisQuery(Temperature11, Humidity11).c_str());

}

String prepareRedisQuery(float temperature, float humidity) {
  static float a = 0.0;
  DynamicJsonDocument doc(2048);
  doc["number"] = myTime++;
  doc["time"] = getCurrnetTimeFormated();
  JsonObject data  = doc.createNestedObject("data");
  data["temperature"] = isnan((float) temperature) || temperature == 0 ? 0 : (float) temperature;
  data["humidity"] = isnan((float) humidity) || (float) humidity == 0 ? 0 : (float) humidity;
  String json;
  serializeJson(doc, json);
  json.replace("\"","'");
  return json;}