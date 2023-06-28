package com.hello.day2.repository;

import com.hello.day2.enumclass.UserStatus;
import com.hello.day2.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
* Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
* Optional 클래스는 아래와 같은 value에 값을 저장하기 때문에 값이 null이더라도 바로 NPE가 발생하지 않으며, 클래스이기 때문에 각종 메소드를 제공해준다.
*
* ```
*   public final class Optional<T> {
        // If non-null, the value; if null, indicates no value is present
        private final T value;
        ...
    }
* ```
* */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // select * from users where userid=?
    // Optional 클래스 : 데이터 조회 시 에러 여부 등 리턴받을 수 있음, 받아온 데이터를 다른 데이터 타입으로 변경하기 용이한 클래스
    Optional<Users> findUsersById(Long id);

    // select * from users where userid=? and userpw=?
    Optional<Users> findUsersByUseridAndUserpw(String userid, String userpw);

    List<Users> findUsersByUseridAndName(String userid, String name);
    Users findUsersByUserid(String userid);
    Users findUsersByUseridAndStatus(String userid, UserStatus status);
    List<Users> findUsersByUseridAndNameLikeAndStatus(String userid, String name, UserStatus status);
    List<Users> findUsersByStatus(UserStatus status);
    // like 검색
    List<Users> findUsersByNameLike(String name);
    List<Users> findUsersByNameLikeAndStatus(String name, UserStatus status);
    List<Users> findUsersByUseridLikeAndStatus(String userid, UserStatus status);

    @Query("select distinct status from Users")
    List<String> findDistinctStatus();
}
