package com.tools.DnDToolKit.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull(message = "Must enter a name!")
    @Basic(optional = false)
    @Column(nullable = false)
	private String name;
	
	@NotNull(message = "Must enter an initiative!")
    @Basic(optional = false)
    @Column(nullable = false)
	private int initiative;
	
	
}
