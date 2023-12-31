package com.babyblackdog.ddogdog.place.service;

import static com.babyblackdog.ddogdog.global.exception.ErrorCode.HOTEL_NOT_FOUND;
import static com.babyblackdog.ddogdog.global.exception.ErrorCode.ROOM_NOT_FOUND;

import com.babyblackdog.ddogdog.common.auth.Email;
import com.babyblackdog.ddogdog.global.exception.HotelException;
import com.babyblackdog.ddogdog.global.exception.RoomException;
import com.babyblackdog.ddogdog.place.model.Hotel;
import com.babyblackdog.ddogdog.place.model.Rating;
import com.babyblackdog.ddogdog.place.model.Room;
import com.babyblackdog.ddogdog.place.model.vo.Province;
import com.babyblackdog.ddogdog.place.repository.HotelRepository;
import com.babyblackdog.ddogdog.place.repository.RoomRepository;
import com.babyblackdog.ddogdog.place.service.dto.AddHotelParam;
import com.babyblackdog.ddogdog.place.service.dto.AddRoomParam;
import com.babyblackdog.ddogdog.place.service.dto.HotelResult;
import com.babyblackdog.ddogdog.place.service.dto.RoomResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PlaceServiceImpl implements
        PlaceService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public PlaceServiceImpl(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public HotelResult registerHotel(AddHotelParam param) {
        Hotel hotel = AddHotelParam.to(param);
        hotel.setRating(new Rating(0, 0));
        Hotel savedHotel = hotelRepository.save(hotel);
        return HotelResult.of(savedHotel);
    }

    @Override
    @Transactional
    public void deleteHotel(Long hotelId) {
        hotelRepository.deleteById(hotelId);
        roomRepository.deleteByHotelId(hotelId);
    }

    @Override
    @Transactional
    public RoomResult registerRoomOfHotel(AddRoomParam addRoomParam) {
        Hotel hotel = hotelRepository.findById(addRoomParam.hotelId())
                .orElseThrow(() -> new RoomException(HOTEL_NOT_FOUND));
        Room room = AddRoomParam.to(hotel, addRoomParam);
        return RoomResult.of(roomRepository.save(room));
    }

    @Override
    @Transactional
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public Page<HotelResult> findHotelsInProvince(Province province, Pageable pageable) {
        Page<Hotel> hotels = hotelRepository.findContainsAddress(province.getValue(), pageable);
        return hotels.map(HotelResult::of);
    }

    @Override
    public HotelResult findHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelException(HOTEL_NOT_FOUND));
        return HotelResult.of(hotel);
    }

    @Override
    public RoomResult findRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomException(ROOM_NOT_FOUND));
        return RoomResult.of(room);
    }

    @Override
    public Page<RoomResult> findRoomsOfHotel(Long hotelId, Pageable pageable) {
        Page<Room> rooms = roomRepository.findRoomsByHotelId(hotelId, pageable);
        return rooms.map(RoomResult::of);
    }

    @Override
    public boolean existsHotel(Long hotelId) {
        return hotelRepository.existsById(hotelId);
    }

    @Override
    public HotelResult findHotelByEmail(Email email) {
        Hotel hotel = hotelRepository.findByAdminEmail(email);
        return HotelResult.of(hotel);
    }
}
