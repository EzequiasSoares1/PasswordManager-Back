package com.Tech.PasswordManager.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passwords")
public class MyPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@Column(nullable = false)
	private String nameService;
	private String password;
	private String login;
	private String observation;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
