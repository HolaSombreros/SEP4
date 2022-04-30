#include <UplinkMessageBuilder.h>

static uint16_t _humidity;
static int16_t _temperature;

lora_driver_payload_t uplinkMessageBuilder_buildUplinkMessage(uint8_t port) {
	lora_driver_payload_t payload;
	
	payload.portNo = port;
	payload.len = 20;
	
	payload.bytes[0] = _humidity >> 8;
	payload.bytes[1] = _humidity & 0xFF;
	payload.bytes[2] = _temperature >> 8;
	payload.bytes[3] = _temperature & 0xFF;
	
	return payload;
}

void uplinkMessageBuilder_setHumidityData(uint16_t data) {
	_humidity = data;
}

void uplinkMessageBuilder_setTemperatureData(uint16_t data) {
	_temperature = data;
}