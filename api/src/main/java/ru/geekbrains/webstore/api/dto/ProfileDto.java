package ru.geekbrains.webstore.api.dto;

public class ProfileDto {

  private Long id;
  private String username;
  private String password;
  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private Long phone;

  public ProfileDto() {
  }

  public ProfileDto(Long id, String username, String password, String firstName, String middleName, String lastName, String email, Long phone) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }
}
