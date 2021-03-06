#include "gtest/gtest.h"
#include <FreeRTOS_FFF_MocksDeclaration.h>
#include <fff.h>

extern "C" {
#include <Farmerama.h>
#include <HumidityTemperatureTask.h>
#include <CO2Task.h>
#include <SoundTask.h>
#include <UplinkMessageBuilder.h>
#include <Configuration.h>
}

FAKE_VALUE_FUNC(lora_driver_payload_t, uplinkMessageBuilder_buildUplinkMessage, uint8_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setHumidityData, uint16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setTemperatureData, int16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setCO2Data, uint16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setSoundData, uint16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setSystemErrorState);

class FarmeramaTest : public ::testing::Test {
protected:
	void SetUp() override {
		RESET_FAKE(xTaskCreate);
		RESET_FAKE(xEventGroupSetBits);
		RESET_FAKE(xEventGroupWaitBits);
		RESET_FAKE(xQueueReceive);
		RESET_FAKE(xQueueSendToBack);
		RESET_FAKE(xTaskGetTickCount);
		RESET_FAKE(xTaskDelayUntil);
		RESET_FAKE(uplinkMessageBuilder_buildUplinkMessage);
		RESET_FAKE(uplinkMessageBuilder_setHumidityData);
		RESET_FAKE(uplinkMessageBuilder_setTemperatureData);
		RESET_FAKE(uplinkMessageBuilder_setCO2Data);
		RESET_FAKE(uplinkMessageBuilder_setSoundData);
		RESET_FAKE(uplinkMessageBuilder_setSystemErrorState);

		FFF_RESET_HISTORY();
	}
};

TEST_F(FarmeramaTest, Create_CallsTaskCreate) {
	//Arrange
	//Act
	farmerama_create(nullptr, nullptr, nullptr, nullptr, nullptr, nullptr, nullptr, nullptr);

	//Assert
	EXPECT_EQ(1, xTaskCreate_fake.call_count);

	EXPECT_EQ(configMINIMAL_STACK_SIZE, xTaskCreate_fake.arg2_val);
	EXPECT_EQ(NULL, xTaskCreate_fake.arg3_val);
	EXPECT_EQ(configMAX_PRIORITIES - 1, xTaskCreate_fake.arg4_val);
	EXPECT_EQ(NULL, xTaskCreate_fake.arg5_val);
}

TEST_F(FarmeramaTest, Task_SetsEventGroupBitsToStartHumidityAndTemperature) {
	//Arrange
	xEventGroupSetBits_fake.return_val = 0;
	xEventGroupWaitBits_fake.return_val = (BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE);

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xEventGroupSetBits_fake.call_count);

	EXPECT_EQ(BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT, xEventGroupSetBits_fake.arg1_val);
	EXPECT_EQ(0, uplinkMessageBuilder_setSystemErrorState_fake.call_count);
}

TEST_F(FarmeramaTest, Task_SetsEventGroupBitsToStartHumidityAndTemperatureReturnBotsNotCleared) {
	//Arrange
	xEventGroupSetBits_fake.return_val = (BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT);

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xEventGroupSetBits_fake.call_count);
	EXPECT_EQ(BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT, xEventGroupSetBits_fake.arg1_val);
	EXPECT_EQ(1, uplinkMessageBuilder_setSystemErrorState_fake.call_count);
}

TEST_F(FarmeramaTest, Task_WaitsForAllEventGroupBitsToBeSet) {
	//Arrange
	xEventGroupWaitBits_fake.return_val = (BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE);

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xEventGroupWaitBits_fake.call_count);

	EXPECT_EQ(BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE, xEventGroupWaitBits_fake.arg1_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg2_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg3_val);
	EXPECT_EQ(pdMS_TO_TICKS(300000UL), xEventGroupWaitBits_fake.arg4_val);
	EXPECT_EQ(0, uplinkMessageBuilder_setSystemErrorState_fake.call_count);
}

TEST_F(FarmeramaTest, Task_WaitsForAllEventGroupBitsToBeSetNotAllBitsSet) {
	//Arrange
	xEventGroupWaitBits_fake.return_val = 0;

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xEventGroupWaitBits_fake.call_count);

	EXPECT_EQ(BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE, xEventGroupWaitBits_fake.arg1_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg2_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg3_val);
	EXPECT_EQ(pdMS_TO_TICKS(300000UL), xEventGroupWaitBits_fake.arg4_val);
	EXPECT_EQ(1, uplinkMessageBuilder_setSystemErrorState_fake.call_count);
}

TEST_F(FarmeramaTest, Task_ReceivesFourMeasurementsFromTheQueue) {
	// Arrange
	// Act
	farmerama_runTask();

	// Assert
	EXPECT_EQ(4, xQueueReceive_fake.call_count);

	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueReceive_fake.arg2_history[0]);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueReceive_fake.arg2_history[1]);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueReceive_fake.arg2_history[2]);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueReceive_fake.arg2_history[3]);
}

TEST_F(FarmeramaTest, Task_FailToReceivesFourMeasurementsFromTheQueue) {
	// Arrange
	xQueueReceive_fake.return_val = pdFALSE;
	// Act
	farmerama_runTask();

	// Assert
	EXPECT_EQ(4, xQueueReceive_fake.call_count);

	EXPECT_EQ(CONFIG_INVALID_HUMIDITY_VALUE, uplinkMessageBuilder_setHumidityData_fake.arg0_val);
	EXPECT_EQ(CONFIG_INVALID_TEMPERATURE_VALUE, uplinkMessageBuilder_setTemperatureData_fake.arg0_val);
	EXPECT_EQ(CONFIG_INVALID_CO2_VALUE, uplinkMessageBuilder_setCO2Data_fake.arg0_val);
	EXPECT_EQ(CONFIG_INVALID_SOUND_VALUE, uplinkMessageBuilder_setSoundData_fake.arg0_val);
}

TEST_F(FarmeramaTest, Task_SendsMeasurementsToServoQueueWhenNoErrorState) {
	//Arrange
	xEventGroupWaitBits_fake.return_val = (BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE);
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(4, xQueueSendToBack_fake.call_count);

	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueSendToBack_fake.arg2_history[0]);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueSendToBack_fake.arg2_history[1]);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueSendToBack_fake.arg2_history[2]);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueSendToBack_fake.arg2_history[3]);
}

TEST_F(FarmeramaTest, Task_DoesNotSendMeasurementsToServoQueueWhenErrorStateHasOccured) {
	//Arrange
	xEventGroupSetBits_fake.return_val = (BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT);

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(0, xQueueSendToBack_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsUplinkMessageBuilderSetHumidityDataOneTime) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, uplinkMessageBuilder_setHumidityData_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsUplinkMessageBuilderSetTemperatureDataOneTime) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, uplinkMessageBuilder_setTemperatureData_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsUplinkMessageBuilderSetCo2DataOneTime) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, uplinkMessageBuilder_setCO2Data_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsUplinkMessageBuilderSetSoundDataOneTime) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, uplinkMessageBuilder_setSoundData_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsBuildUplinkMessageWithPortNumberOneTime) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, uplinkMessageBuilder_buildUplinkMessage_fake.call_count);
	EXPECT_EQ(1, uplinkMessageBuilder_buildUplinkMessage_fake.arg0_val);
}

TEST_F(FarmeramaTest, Task_SendsToServerTaskIfUplinkMessageBuilderReturnsFilledPayload) {
	//Arrange
	uplinkMessageBuilder_buildUplinkMessage_fake.return_val.len = 8;

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xQueueSendToBack_fake.call_count);
	EXPECT_EQ(pdMS_TO_TICKS(10000), xQueueSendToBack_fake.arg2_val);
}

TEST_F(FarmeramaTest, Task_DoesNotSendToServerTaskIfUplinkMessageBuilderReturnsEmptyPayload) {
	//Arrange
	uplinkMessageBuilder_buildUplinkMessage_fake.return_val.len = 0;

	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(0, xQueueSendToBack_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsTaskGetTickCount) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xTaskGetTickCount_fake.call_count);
}

TEST_F(FarmeramaTest, Task_CallsDelayUntilWithFiveMinuteDelay) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xTaskDelayUntil_fake.call_count);
	EXPECT_EQ(pdMS_TO_TICKS(300000UL), xTaskDelayUntil_fake.arg1_val);
}