package com.alibou.security.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  @Query("SELECT u FROM User u WHERE " +
          "(:firstname IS NULL OR u.firstname LIKE CONCAT('%', LOWER(:nom), '%')) AND " +
          "(:lastname IS NULL OR u.lastname LIKE CONCAT('%', LOWER(:prenom), '%')) AND " +
          "(:address IS NULL OR u.address LIKE CONCAT('%', LOWER(:adresse), '%')) AND " +
          "(:email IS NULL OR u.email LIKE CONCAT('%', LOWER(:adresse), '%')) AND " +
          "(:phoneNumber IS NULL OR CAST(u.phoneNumber AS string) LIKE CONCAT('%', :phoneNumber, '%'))")
  List<User> rechercheAvancee(
          @Param("firstname") String firstname,
          @Param("lastname") String lastname,
          @Param("address") String address,
          @Param("email") String email,
          @Param("phoneNumber") Long phoneNumber
  );

}
