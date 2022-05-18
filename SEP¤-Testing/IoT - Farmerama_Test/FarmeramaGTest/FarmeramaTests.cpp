#include "gtest/gtest.h"
#include <FreeRTOS_FFF_MocksDeclaration.h>

DEFINE_FFF_GLOBALS

extern "C" {
	#include <Farmerama.h>
}

class FarmeramaTest : public ::testing::Test {
protected:
	void SetUp() override {
		RESET_FAKE(xTaskCreate);
		RESET_FAKE(xEventGroupSetBits);
		RESET_FAKE(xEventGroupWaitBits);
		FFF_RESET_HISTORY();
	}
	void TearDown() override {}
};

TEST_F(FarmeramaTest, testInitializeFarmeramaTaskWíthParameters) {
	//Arrange
	QueueHandle_t _humidityQueue = xQueueCreate(10, sizeof(uint16_t));
	QueueHandle_t _temperatureQueue = xQueueCreate(10, sizeof(int16_t));
	QueueHandle_t _co2Queue = xQueueCreate(10, sizeof(uint16_t));
	QueueHandle_t _soundQueue = xQueueCreate(10, sizeof(uint16_t));
	QueueHandle_t _senderQueue = xQueueCreate(10, sizeof(lora_driver_payload_t));

	EventGroupHandle_t _actEventGroup = xEventGroupCreate();
	EventGroupHandle_t _doneEventGroup = xEventGroupCreate();

	//Act
	farmerama_create(_senderQueue, _humidityQueue, _temperatureQueue, _co2Queue, _soundQueue, _actEventGroup, _doneEventGroup);

	//Assert
	EXPECT_EQ(1, xTaskCreate_fake.call_count);
	EXPECT_TRUE(xTaskCreate_fake.return_val);
}

TEST_F(FarmeramaTest, testVerifyxEventGroupSetBitSetsTheCorrectBits){
	//Arrange
	//Act
	farmerama_runTask();
	//Assert
	EXPECT_EQ(1, xEventGroupSetBits_fake.call_count);
	EXPECT_EQ(BIT_HUMIDITY_ACT | BIT_TEMPERATURE_ACT, xEventGroupSetBits_fake.return_val);
}

TEST_F(FarmeramaTest, testVerifyxEventGroupWaitBitsWaitsForAllBitsToBeSet) {
	//Arrange
	//Act
	farmerama_runTask();
	//Assert
	EXPECT_EQ(1, xEventGroupWaitBits_fake.call_count);
	EXPECT_EQ(BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE, xEventGroupWaitBits_fake.arg1_val);
	EXPECT_EQ(BIT_HUMIDITY_DONE | BIT_TEMPERATURE_DONE | BIT_CO2_DONE | BIT_SOUND_DONE, xEventGroupWaitBits_fake.return_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg2_val);
	EXPECT_EQ(pdTRUE, xEventGroupWaitBits_fake.arg3_val);
	EXPECT_EQ(pdMS_TO_TICKS(300000UL), xEventGroupWaitBits_fake.arg4_val);

}

