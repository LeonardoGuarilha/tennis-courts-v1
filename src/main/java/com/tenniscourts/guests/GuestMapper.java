package com.tenniscourts.guests;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    Guest mapToGuest(GuestDTO guestDTO);
    GuestDTO mapToGuestDTO(Guest guest);
    List<GuestDTO> mapToListGuestDto(List<Guest> guests);

}
