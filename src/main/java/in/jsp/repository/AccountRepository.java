package in.jsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jsp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
