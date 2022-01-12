package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
@Api("Rest API reservation tennis court")
@CrossOrigin(value = "*")
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Save a new guest")
    public GuestDTO createGuest(@RequestBody GuestDTO guest) {
        return guestService.saveGuest(guest);
    }

    @GetMapping
    @ApiOperation(value = "Return a list of guests")
    public List<GuestDTO> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return a guest by id")
    public GuestDTO findById(@PathVariable Long id) {
        return guestService.findById(id);
    }

    @GetMapping("/guestname/{name}")
    @ApiOperation("Return a guest by name")
    public GuestDTO findByName(@PathVariable String name) {
        return guestService.findByName(name);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Update a guest based on a given id")
    public GuestDTO updateGuest(@PathVariable Long id, @RequestBody GuestDTO guest) {
        return guestService.updateGuest(id, guest);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a guest based on a given id")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }

}

