package com.pongchi.glimelight.domain.subscribe;

import com.pongchi.glimelight.domain.member.Member;

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
			columnNames = {"fromMember", "toMember"}
		)
	}
)
public class Subscribe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fromMember")
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "toMember")
    private Member toMember;

    public Subscribe(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }

}
