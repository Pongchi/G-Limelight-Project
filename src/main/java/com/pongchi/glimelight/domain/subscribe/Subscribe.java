package com.pongchi.glimelight.domain.subscribe;

import com.pongchi.glimelight.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Getter
@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			name="subscribe_uk",
			columnNames = {"fromUser", "toUser"}
		)
	}
)
public class Subscribe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fromUser")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "toUser")
    private User toUser;
}
