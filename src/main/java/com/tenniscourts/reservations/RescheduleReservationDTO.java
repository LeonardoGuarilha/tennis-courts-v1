package com.tenniscourts.reservations;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class RescheduleReservationDTO {

    @NotNull
    private Long reservationId;

    @NotNull
    private Long scheduleId;
}
