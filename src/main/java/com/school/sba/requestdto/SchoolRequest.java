package com.school.sba.requestdto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SchoolRequest {
	
	@NotBlank(message="School name is required")
	private String schoolName;
	@Min(value = 1111111111,   message ="Enter valid ContactNo")
	@Max(value = 1111111111,   message="Engter valid Contact no")
	@Column(unique = true)
	private long schoolContact;
	@NotBlank(message="School address is required")
	private String schoolLocation;
	@NotBlank(message="EmailId is required")
	@Column(unique = true)
	private String schoolEmail;

}
