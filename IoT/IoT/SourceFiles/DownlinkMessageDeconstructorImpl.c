#include <DownlinkMessageDeconstructor.h>
#include <semphr.h>

#define CHECK_BIT(variable, position) variable & (1 << position)

static SemaphoreHandle_t _mutex;

// TODO - define default values.
static uint16_t _humidityLOW;
static int16_t _temperatureLOW;
static uint16_t _ppmLOW;
static uint16_t _humidityHIGH;
static int16_t _temperatureHIGH;
static uint16_t _ppmHIGH;
static uint16_t _soundHIGH;

void downlinkMessageDeconstructor_create(SemaphoreHandle_t mutex){
	_mutex = mutex;
}

void downlinkMessageDeconstructor_deconstructDownlinkMessage(lora_driver_payload_t payload){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		if(CHECK_BIT(payload.bytes[14], 0)){
			_soundHIGH = (payload.bytes[12] << 8) + payload.bytes[13];
		}
	
		if(CHECK_BIT(payload.bytes[14], 1)){
			_ppmLOW = (payload.bytes[10] << 8) + payload.bytes[11];
		}
	
		if(CHECK_BIT(payload.bytes[14], 2)){
			_ppmHIGH = (payload.bytes[8] << 8) + payload.bytes[9];
		}
	
		if(CHECK_BIT(payload.bytes[14], 3)){
			_temperatureLOW = (payload.bytes[6] << 8) + payload.bytes[7];
		}
	
		if(CHECK_BIT(payload.bytes[14], 4)){
			_temperatureHIGH = (payload.bytes[4] << 8) + payload.bytes[5];
		}
	
		if(CHECK_BIT(payload.bytes[14], 5)){
			_humidityLOW = (payload.bytes[2] << 8) + payload.bytes[3];
		}
	
		if(CHECK_BIT(payload.bytes[14], 6)){
			_humidityHIGH = (payload.bytes[0] << 8) + payload.bytes[1];
		}
		
		xSemaphoreGive(_mutex);
	}
}

uint16_t downlinkMessageDeconstructor_getHumidityDataLow(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _humidityLOW;
		xSemaphoreGive(_mutex);
		return temp;
	}
}

int16_t downlinkMessageDeconstructor_getTemperatureDataLow(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _temperatureLOW;
		xSemaphoreGive(_mutex);
		return temp;
	}
}

uint16_t downlinkMessageDeconstructor_getCO2DataLow(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _ppmLOW;
		xSemaphoreGive(_mutex);
		return temp;
	}
}

uint16_t downlinkMessageDeconstructor_getHumidityDataHigh(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _humidityHIGH;
		xSemaphoreGive(_mutex);
		return temp;
	}
}

int16_t downlinkMessageDeconstructor_getTemperatureDataHigh(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _temperatureHIGH;
		xSemaphoreGive(_mutex);
		return temp;
	}
}

uint16_t downlinkMessageDeconstructor_getCO2DataHigh(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _ppmHIGH;
		xSemaphoreGive(_mutex);
		return temp;
	}
}

uint16_t downlinkMessageDeconstructor_getSoundDataHigh(){
	if(xSemaphoreTake(_mutex, pdMS_TO_TICKS(3000)) == pdTRUE){
		int16_t temp = _soundHIGH;
		xSemaphoreGive(_mutex);
		return temp;
	}
}
