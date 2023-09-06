package com.babyblackdog.ddogdog.place.room.model.vo;

import static com.babyblackdog.ddogdog.global.exception.ErrorCode.INVALID_ROOM_NUMBER;

import com.babyblackdog.ddogdog.global.exception.RoomException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class RoomNumber {

  @NotBlank(message = "방 번호는 반드시 주어져야 합니다.")
  @Column(name = "room_number", nullable = false)
  private String value;

  public RoomNumber(String value) {
    validate(value);
    this.value = value;
  }

  protected RoomNumber() {
  }

  private void validate(String value) {
    if (StringUtils.isBlank(value)) {
      throw new RoomException(INVALID_ROOM_NUMBER);
    }
  }

  public String getValue() {
    return value;
  }
}
