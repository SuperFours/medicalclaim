package com.medicalclaim.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PolicyClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "policy_id ")
	private Policy policyId;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_id")
	private Hospital hospitalId;
	private String claimNumber;
	private LocalDateTime claimDate;
	private Double claimAmount;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "policyClaimId")
	private Set<PolicyClaimApproval> policyClaimApproval;
}
