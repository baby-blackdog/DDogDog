package com.babyblackdog.ddogdog.place.hotel.model;

import com.babyblackdog.ddogdog.place.hotel.model.vo.BusinessName;
import com.babyblackdog.ddogdog.place.hotel.model.vo.HumanName;
import com.babyblackdog.ddogdog.place.hotel.model.vo.PhoneNumber;
import com.babyblackdog.ddogdog.place.hotel.model.vo.PlaceName;
import com.babyblackdog.ddogdog.place.hotel.model.vo.Province;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "places")
public class Place {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private PlaceName name;

  @Embedded
  private Province address;

  @Column(name = "admin_id", nullable = false)
  private Long adminId;

  @Embedded
  private PhoneNumber contact;

  @Embedded
  private HumanName representative;

  @Embedded
  private BusinessName businessName;

  public Place(PlaceName name, Province address, Long adminId, PhoneNumber contact,
      HumanName representative, BusinessName businessName) {
    this.name = name;
    this.address = address;
    this.adminId = adminId;
    this.contact = contact;
    this.representative = representative;
    this.businessName = businessName;
  }

  protected Place() {
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name.getValue();
  }

  public Province getAddress() {
    return address;
  }

  public String getAddressValue() {
    return address.getValue();
  }

  public Long getAdminId() {
    return adminId;
  }

  public String getContact() {
    return contact.getValue();
  }

  public String getRepresentative() {
    return representative.getValue();
  }

  public String getBusinessName() {
    return businessName.getValue();
  }
}
