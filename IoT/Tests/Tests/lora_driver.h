#pragma once
#include <stdint.h>

#define LORA_MAX_PAYLOAD_LENGTH	20

typedef struct lora_driver_payload {
	uint8_t portNo; /**< Port_no the data is received on, or to transmit to [1..223]*/
	uint8_t len; /**< Length of the payload (no of bytes) - MAX 20 bytes is allowed in this implementation! */
	uint8_t bytes[LORA_MAX_PAYLOAD_LENGTH]; /**< Array to hold the payload to be send, or that has been received */
} lora_driver_payload_t;