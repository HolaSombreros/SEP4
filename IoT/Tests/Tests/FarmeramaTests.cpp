#include "gtest/gtest.h"
#include <FreeRTOS_FFF_MocksDeclaration.h>
#include <fff.h>

extern "C" {
#include <Farmerama.h>
#include <HumidityTemperatureTask.h>
#include <CO2Task.h>
#include <SoundTask.h>
#include <UplinkMessageBuilder.h>
}

FAKE_VALUE_FUNC(lora_driver_payload_t, uplinkMessageBuilder_buildUplinkMessage, uint8_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setHumidityData, uint16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setTemperatureData, int16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setCO2Data, uint16_t);
FAKE_VOID_FUNC(uplinkMessageBuilder_setSoundData, uint16_t);

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

		FFF_RESET_HISTORY();
	}
};

TEST_F(FarmeramaTest, Create_CallsTaskCreate) {
	farmerama_create(nullptr, nullptr, nullptr, nullptr, nullptr, nullptr, nullptr);

	EXPECT_EQ(1, xTaskCreate_fake.call_count);
}

TEST_F(FarmeramaTest, testVerifyxEventGroupSetBitSetsTheCorrectBits) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xEventGroupSetBits_fake.call_count);

	EXPECT_EQ(BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT, xEventGroupSetBits_fake.arg1_val);
}

TEST_F(FarmeramaTest, testVerifyxEventGroupWaitBitsWaitsForAllBitsToBeSet) {
	//Arrange
	//Act
	farmerama_runTask();

	//Assert
	EXPECT_EQ(1, xEventGroupWaitBits_fake.call_count);

	EXPECT_EQ(BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE, xEventGroupWaitBits_fake.arg1_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg2_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg3_val);
	EXPECT_EQ(pdMS_TO_TICKS(300000UL), xEventGroupWaitBits_fake.arg4_val);
}

TEST_F(FarmeramaTest, testVerifyxQueueReceiveIsCalled) {
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

TEST_F(FarmeramaTest, testVerifyxQueueSendToBackIsCalled) {
	farmerama_runTask();

	EXPECT_EQ(1, xQueueSendToBack_fake.call_count);

	EXPECT_EQ(pdMS_TO_TICKS(1000), xQueueSendToBack_fake.arg2_val);
}

TEST_F(FarmeramaTest, testVerifyUplinkMessageBuilderHumidityIsCalled) {
	farmerama_runTask();

	EXPECT_EQ(1, uplinkMessageBuilder_setHumidityData_fake.call_count);

	EXPECT_EQ(15, uplinkMessageBuilder_setHumidityData_fake.arg0_val);
}

TEST_F(FarmeramaTest, testVerifyUplinkMessageBuilderTemperatureIsCalled) {
	farmerama_runTask();

	uint16_t temp = 15;

	xQueueReceive_fake.arg1_val = &temp;
	EXPECT_EQ(uplinkMessageBuilder_setTemperatureData_fake.arg0_val, temp);

	EXPECT_EQ(1, uplinkMessageBuilder_setTemperatureData_fake.call_count);
}

TEST_F(FarmeramaTest, testVerifyUplinkMessageBuilderCO2IsCalled) {
	farmerama_runTask();

	EXPECT_EQ(1, uplinkMessageBuilder_setCO2Data_fake.call_count);
}

TEST_F(FarmeramaTest, testVerifyUplinkMessageBuilderSoundIsCalled) {
	farmerama_runTask();

	EXPECT_EQ(1, uplinkMessageBuilder_setSoundData_fake.call_count);
}

TEST_F(FarmeramaTest, testVerifyUplinkMessageBuilderReturns) {
	farmerama_runTask();

	uplinkMessageBuilder_buildUplinkMessage_fake.return_val.portNo = 1;
	EXPECT_EQ(1, uplinkMessageBuilder_buildUplinkMessage_fake.call_count);
	EXPECT_EQ(1, uplinkMessageBuilder_buildUplinkMessage_fake.arg0_val);
	
	EXPECT_EQ(1, xQueueSendToBack_fake.call_count);
}

TEST_F(FarmeramaTest, testVerifyxTaskGetTickCountIsCalled) {
	farmerama_runTask();

	EXPECT_EQ(1, xTaskGetTickCount_fake.call_count);
}

TEST_F(FarmeramaTest, testVerifyxTaskDelayUntilIsCalled) {
	farmerama_runTask();

	EXPECT_EQ(1, xTaskDelayUntil_fake.call_count);

	EXPECT_EQ(pdMS_TO_TICKS(300000UL), xTaskDelayUntil_fake.arg1_val);
}