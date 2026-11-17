package com.moetez.message.functions;

import com.moetez.message.dto.FormateurMessageDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class MessageFunctionsTest {

    private final MessageFunctions functions = new MessageFunctions();

    @Test
    void emailReturnsTheCommunicationDetails() {
        FormateurMessageDto message = new FormateurMessageDto(1L, "test@example.com", "123456");

        assertSame(message, functions.email().apply(message));
    }

    @Test
    void smsReturnsTheFormateurId() {
        FormateurMessageDto message = new FormateurMessageDto(1L, "test@example.com", "123456");

        assertEquals(1L, functions.sms().apply(message));
    }
}
