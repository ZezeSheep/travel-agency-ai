package br.com.zezesheep;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookingTools {

    @Inject
    BookingService bookingService;

    @Tool("Obtém os detalhes completos de uma reserva com base em seu número de identificação (bookingId).")
    public String getBookingDetails(long bookingId){
        return bookingService.getBookingDetails(bookingId).map(Booking::toString).orElse("Reserva com ID "+ bookingId +" não encontrada.");
    }

    @Tool("""
            Cancela uma reserva existente.
            Para confirmar o cancelamento, é necessário fornecer o ID da reserva (bookingId)
            
            """)
    public String cancelBooking(long bookingId){
        return bookingService.cancelBooking(bookingId)
                .map(booking -> "Reserva " + bookingId + " cancelada com sucesso. Status atual: " + booking.status())
                .orElse("Não foi possível cancelar a reserva. Verifique se o ID da reserva e o sobrenome do cliente estão corretos.");
    }
}
