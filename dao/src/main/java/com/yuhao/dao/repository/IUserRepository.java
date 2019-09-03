
package com.yuhao.dao.repository;


// import org.springframework.data.jpa.repository.JpaRepository;

import com.yuhao.dao.domain.model.TUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<TUserEntity,String> {
//      public TUserEntity find();
	TUserEntity findByUserid(String userid);
}

      
