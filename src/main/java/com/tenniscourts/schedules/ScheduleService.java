package com.tenniscourts.schedules;

import com.tenniscourts.exceptions.ScheduleNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourt;
import com.tenniscourts.tenniscourts.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final TennisCourtRepository tennisCourtRepository;

    private final ScheduleMapper scheduleMapper;

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
        
        Optional<TennisCourt> tennisCourtExists = tennisCourtRepository.findById(createScheduleRequestDTO.getTennisCourtId());

        Schedule schedule = Schedule.builder().tennisCourt(tennisCourtExists.get())
                .startDateTime(createScheduleRequestDTO.getStartDateTime())
                .endDateTime(LocalDateTime.now().plusHours(1))
                .build();

        scheduleRepository.saveAndFlush(schedule);

        return scheduleMapper.map(schedule);
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        //TODO: implement
        return null;
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);

        if (schedule.isEmpty()) {
            throw new ScheduleNotFoundException("Schedule [" + scheduleId + "] not found!");
        }

        return scheduleMapper.map(schedule.get());
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}
