package com.ink.studio.tattoo.inkstudiotattoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orcamento")
public class Orcamentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
}
