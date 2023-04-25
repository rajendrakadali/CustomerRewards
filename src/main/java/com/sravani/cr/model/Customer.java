package com.sravani.cr.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Validated
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "custId")
	private Integer custId;
	@Column(name = "firstName")
	@NotNull(message="firstName can not be null")
	private String firstName;
	@Column(name = "middleName")
	private String middleName;
	@Column(name = "lastName")
	@NotNull(message="lastName can not be null")
	private String lastName;
	@Column(name = "dateTime")
	@CreationTimestamp
	private Timestamp dateTime;
	@OneToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	private Users user;
}
