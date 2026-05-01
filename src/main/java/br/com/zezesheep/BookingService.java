package br.com.zezesheep;

import jakarta.enterprise.context.ApplicationScoped;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class BookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();

    public BookingService(){
        bookings.put(12345L, new Booking(12345L, "John Doe", "Tesouros do Egito",
                BookingStatus.CONFIRMED, LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(2).plusDays(10),Category.TREASURES));
        bookings.put(67890L, new Booking(67890L, "Jane Smith", "Aevntura Amazônica",
                BookingStatus.CONFIRMED, LocalDate.now().plusMonths(3), LocalDate.now().plusMonths(3).plusDays(15), Category.ADVENTURE));
    }

    public List<Booking> findPackagesByCategory(Category category) {
        return bookings.values().stream().filter(booking -> category.equals(booking.category())).toList();
    }

    public Optional<Booking> getBookingDetails(Long bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public Optional<Booking> cancelBooking(long bookingId){
        String currentUser = SecurityContext.getCurrentUser();
        if(bookings.containsKey(bookingId)){
            Booking booking = bookings.get(bookingId);
            if(booking.customerName().endsWith(currentUser)){
                Booking canceledBooking = new Booking(booking.id(), booking.customerName(), booking.destination(), BookingStatus.CANCELLED, booking.startDate(), booking.endDate(), booking.category());
                bookings.put(bookingId, canceledBooking);
                return Optional.of(canceledBooking);
            }
        }
        return Optional.empty();
    }
}
