package com.sravani.cr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transId")
	private Integer transId;
	@Column(name = "tranAmt")
	@NotNull(message="tranAmt can not be null")
	private Float tranAmt;
	@Column(name = "rewardPoints")
	private Integer rewardPoints;
	@Column(name = "dateTime")
	@CreationTimestamp
	private Timestamp dateTime;
	@Column(name = "custId")
	@NotNull(message="custId can not be null")
	private Integer custId;

}
