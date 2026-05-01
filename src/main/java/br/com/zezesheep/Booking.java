package br.com.zezesheep;

import java.time.LocalDate;

public record Booking(Long id, String customerName, String destination, BookingStatus status, LocalDate startDate, LocalDate endDate, Category category) {
}
