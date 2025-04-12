package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test03ReturningCustomValues {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setUp() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_CountAvailablePlaces_WhenOneRoomAvailable() {
        // given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room 1", 2)));
        int expectedCount = 2;

        // when
        int actual = bookingService.getAvailablePlaceCount();

        // then
        assertEquals(expectedCount, actual);
    }

    @Test
    void should_CountAvailablePlaces_MultipleRoomsAvailable() {
        // given
        List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 3));
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(rooms);
        int expectedCount = 5;

        // when
        int actual = bookingService.getAvailablePlaceCount();

        // then
        assertEquals(expectedCount, actual);
    }
}
