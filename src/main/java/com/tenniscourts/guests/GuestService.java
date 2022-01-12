package com.tenniscourts.guests;

import com.tenniscourts.exceptions.GuestNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    public GuestDTO saveGuest(GuestDTO guestDTO) {
        Guest guest = guestMapper.mapToGuest(guestDTO);
        guestRepository.save(guest);
        return guestMapper.mapToGuestDTO(guest);
    }

    public List<GuestDTO> findAll() {
        return guestMapper.mapToListGuestDto(guestRepository.findAll());
    }

    @Transactional
    public GuestDTO updateGuest(Long id, GuestDTO guestDTO) {

        Guest guest = guestRepository.findById(id).orElseThrow(
                () -> new GuestNotFoundException("Guest [" + id + "] not found"));

        guest.setName(guestDTO.getName());

        return guestMapper.mapToGuestDTO(guest);
    }

    public void deleteGuest(Long id) {
        guestRepository.findById(id).orElseThrow(
                () -> new GuestNotFoundException("Guest [" + id + "] not found"));

        guestRepository.deleteById(id);
    }

    public GuestDTO findById(Long id) {
        Optional<Guest> guest = guestRepository.findById(id);

        if(guest.isEmpty())
        {
            throw new GuestNotFoundException("Guest [" + id + "] not found");
        }

        return guestMapper.mapToGuestDTO(guest.get());
    }

    public GuestDTO findByName(String name) {
        Guest guest = guestRepository.findByName(name);

        if(guest == null)
        {
            throw new GuestNotFoundException("Guest [" + name + "] not found");
        }

        return guestMapper.mapToGuestDTO(guest);
    }
}
