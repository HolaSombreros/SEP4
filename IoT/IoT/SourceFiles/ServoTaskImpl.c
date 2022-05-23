#include <ServoTask.h>
#include <DownlinkMessageDeconstructor.h>
#include <rc_servo.h>
#include <stdio.h>
#include <stdint.h>


#define TASK_NAME "ServoTask"
#define TASK_PRIORITY configMAX_PRIORITIES - 2

static void _run(void* params);

static QueueHandle_t _servoQueue;

void servoTask_create(QueueHandle_t servoQueue) {
	_servoQueue = servoQueue;
	
	xTaskCreate(_run, TASK_NAME, configMINIMAL_STACK_SIZE, NULL, TASK_PRIORITY, NULL);
}

void servoTask_initTask(void* params) {
	
}

void servoTask_runTask() {
	uint16_t humidity;
	int16_t temperature;
	uint16_t co2;
	uint16_t sound;
	xQueueReceive(_servoQueue, &humidity, portMAX_DELAY);
	xQueueReceive(_servoQueue, &temperature, portMAX_DELAY);
	xQueueReceive(_servoQueue, &co2, portMAX_DELAY);
	xQueueReceive(_servoQueue, &sound, portMAX_DELAY);
	
	if(temperature < downlinkMessageDeconstructor_getTemperatureDataLow()){
		rc_servo_setPosition(1, -100);
	}else if(temperature > downlinkMessageDeconstructor_getTemperatureDataHigh()){
		rc_servo_setPosition(1, 100);
	}
}

static void _run(void* params) {
	servoTask_initTask(params);
	
	while (1) {
		servoTask_runTask();
	}
}