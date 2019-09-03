
	package com.yuhao.dao.domain.model;

	import java.io.Serializable;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;
	
	import lombok.Data;
		
	@Data
	@Entity
	@Table(name = "USER")
	public class TUserEntity implements Serializable{
		  @Id
          private String userid;
          private String username;
          private String birthday;
          private String sex;
          private String address;
	}

      
