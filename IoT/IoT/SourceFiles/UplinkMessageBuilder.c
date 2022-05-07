#include <UplinkMessageBuilder.h>

static uint16_t _humidity;
static int16_t _temperature;
static int8_t _validationBits;

lora_driver_payload_t uplinkMessageBuilder_buildUplinkMessage(uint8_t port) {
	lora_driver_payload_t payload;
	
	payload.portNo = port;
	payload.len = 9;
	
	payload.bytes[0] = _humidity >> 8;
	payload.bytes[1] = _humidity & 0xFF;
	payload.bytes[2] = _temperature >> 8;
	payload.bytes[3] = _temperature & 0xFF;
	
	payload.bytes[8] = _validationBits;
	
	return payload;
}

void uplinkMessageBuilder_setHumidityData(uint16_t data) {
	_humidity = data;
	
	if (data == 200) {
		_validationBits |= 0 << 3;
	} else {
		_validationBits |= 1 << 3;
	}
}

void uplinkMessageBuilder_setTemperatureData(int16_t data) {
	_temperature = data;
	
	if (data == -100) {
		_validationBits |= 0 << 2;
	} else {
		_validationBits |= 1 << 2;
	}
}