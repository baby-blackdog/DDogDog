package com.babyblackdog.ddogdog.reservation.service;

import com.babyblackdog.ddogdog.reservation.service.reader.ReservationReaderService;
import java.util.List;

public interface ReservationService extends ReservationReaderService {

    List<Long> reserve(Long roomId, StayPeriod stayPeriod, Long orderId);

}
